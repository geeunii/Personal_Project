# 🎵 Dynamic Beat - Java Thread & Algorithm Challenge

> **Java Swing 기반 7키 리듬 게임 (Algorithm & Multi-threading)**  
> "정해진 악보를 넘어, 알고리즘으로 생성되는 무한한 패턴에 도전하다."

---

## 📑 목차
- [프로젝트 소개](#-프로젝트-소개)
- [기술 스택](#-기술-스택)
- [시스템 아키텍처](#-시스템-아키텍처-uml)
- [핵심 기술적 도전](#-핵심-기술적-도전-key-features)
- [트러블 슈팅](#-트러블-슈팅-troubleshooting)
- [게임 시스템](#-게임-시스템)
- [프로젝트 구조](#-프로젝트-구조)
- [회고](#-회고-retrospective)

---

## 🎮 프로젝트 소개

### 💡 개발 배경: "CS 이론을 눈에 보이는 결과물로"
컴퓨터공학의 핵심인 **프로세스와 스레드(Thread)**, 그리고 **메모리 관리**가 실제 어플리케이션에서 어떻게 작동하는지 이해하기 위해 시작했습니다.
기존의 정형화된 리듬 게임 로직을 분석한 뒤, 이를 발전시켜 '난이도 조절 알고리즘'과 '랜덤 노트 생성 로직'을 독자적으로 구현하여 게임성을 강화했습니다.

### 🎯 핵심 목표
- **Multi-threading:** 수십 개의 노트(Note) 객체와 배경음악(Music)을 동시에 제어하며 동시성 프로그래밍 학습
- **Algorithm:** 난이도에 따라 불규칙하지만 리듬감 있는 비트를 생성하는 알고리즘 설계
- **GUI Optimization:** Java Swing의 한계인 화면 깜빡임을 '더블 버퍼링' 기법으로 해결

---

## 🛠️ 기술 스택

<div align="center">

| Category | Technology |
|:--------:|:----------:|
| Language | <img src="https://img.shields.io/badge/java-007396?style=for-the-badge&logo=java&logoColor=white"> |
| GUI | <img src="https://img.shields.io/badge/Swing-007396?style=for-the-badge&logo=java&logoColor=white"> |
| Core | <img src="https://img.shields.io/badge/Multi_Thread-007396?style=for-the-badge&logo=java&logoColor=white"> |
| IDE | <img src="https://img.shields.io/badge/Eclipse-2C2255?style=for-the-badge&logo=eclipse&logoColor=white"> |

</div>

---

## 📐 시스템 아키텍처 (UML)



```mermaid
classDiagram
    class Main {
        +public static void main()
    }
    class DynamicBeat {
        -screenImage : Image
        -introMusic : Music
        +paint(Graphics g)
    }
    class Game {
        -noteList : ArrayList<Note>
        -gameMusic : Music
        +pressS()
        +pressD()
        +judge(String input)
    }
    class Note {
        -x, y : int
        -noteType : String
        +run() : void
        +drop() : void
        +judge() : String
    }
    class Music {
        -player : Player
        +run() : void
        +getTime() : int
    }
    
    Main --> DynamicBeat : Entry Point
    DynamicBeat --> Game : Starts
    Game "1" *-- "*" Note : Manages (Thread List)
    Game --> Music : Syncs
    Note --|> Thread : extends
    Music --|> Thread : extends

```

---

## ⭐ 핵심 기술적 도전 (Key Features)

### 1. 멀티스레드 기반 노트 시스템 (Concurrency)

각각의 노트(`Note`)를 독립적인 스레드로 구현하여, 메인 게임 루프와 상관없이 독립적인 속도와 타이밍을 가지도록 설계했습니다.

```java
public class Note extends Thread {
    @Override
    public void run() {
        while (true) {
            drop(); // 노트 하강 로직
            if(proceeded) {
                Thread.sleep(Main.SLEEP_TIME); // 10ms 단위 렌더링
            } else {
                interrupt(); // 판정 종료 시 스레드 자원 해제
                break;
            }
        }
    }
}

```

### 2. 더블 버퍼링 (Double Buffering) 시각화 최적화

Java Swing의 `paint()` 메소드가 호출될 때마다 화면이 깜빡이는 플리커링(Flickering) 현상을 해결하기 위해, 메모리(Back Buffer)에 이미지를 먼저 그린 후 화면에 한 번에 전송하는 기법을 적용했습니다.

### 3. 랜덤 비트 생성 알고리즘

단순 랜덤이 아닌, 음악적 템포를 고려한 '최소/최대 간격' 제약 조건을 둔 알고리즘을 구현했습니다.

```java
// 난이도 조절 알고리즘 예시
int gap = (int)(Math.random() * (maxGap - minGap + 1) + minGap);
int startTime = beats[i-1].getTime() + gap;

```

* `minGap`: 노트가 겹치지 않도록 하는 최소 시간
* `maxGap`: 리듬이 끊기지 않도록 하는 최대 시간

---

## 🔧 트러블 슈팅 (Troubleshooting)

### Q. 수많은 노트 스레드로 인한 성능 저하?

* **문제:** 게임이 진행될수록 생성된 `Note` 스레드가 쌓여 메모리 누수 발생.
* **해결:** 판정선(Judge Line)을 지나거나 키 입력이 된 노트는 `interrupt()`를 호출하여 즉시 스레드를 종료시키고, `ArrayList`에서 제거(Garbage Collection 유도)하여 메모리를 최적화했습니다.

### Q. 음악과 노트의 싱크 밀림 현상?

* **문제:** `Thread.sleep()`의 오차로 인해 시간이 갈수록 음악과 노트 박자가 어긋남.
* **해결:** 단순히 시간을 재는 것이 아니라, `Music` 스레드에서 현재 재생 중인 음악의 타임스탬프(`getTime()`)를 실시간으로 가져와 노트 생성 타이밍을 보정(Sync)하는 로직을 추가했습니다.

---

## 🎯 게임 시스템

### 조작 방법

| 키 | 노트 레인 |
| --- | --- |
| S | 1번 레인 |
| D | 2번 레인 |
| F | 3번 레인 |
| **Space** | **4~5번 레인 (롱노트)** |
| J | 6번 레인 |
| K | 7번 레인 |
| L | 8번 레인 |

### 판정 시스템 (FIFO 구조)

`ArrayList`를 Queue처럼 활용하여, 가장 먼저 생성된 노트부터 순차적으로 판정합니다.

| 판정 | 점수 | 판정 범위 |
| --- | --- | --- |
| **Perfect** | +50 | ±13ms |
| **Great** | +30 | ±14ms |
| **Good** | +20 | ±13ms |
| **Late** | +5 | ±13ms |
| **Miss** | -10 | 판정선 통과 |

---

## 📂 프로젝트 구조

```bash
src/dynamic_beat_final
├── Main.java          # Entry Point
├── DynamicBeat.java   # GUI Frame & Double Buffering
├── Game.java          # Main Logic & Rendering
├── Note.java          # Note Object (Thread)
├── Music.java         # Audio Player (Thread)
├── Beat.java          # Data Structure
└── KeyListener.java   # Input Handler

```

---

## 🤔 회고 (Retrospective)

> "화려한 프레임워크 없이, **오직 Java 언어 자체의 기능(Thread, AWT)** 만으로 시스템을 구축해본 소중한 경험입니다."

이 프로젝트를 통해 '동시성(Concurrency)'이 얼마나 다루기 까다로운지 몸소 체험했습니다. 여러 스레드가 동시에 자원에 접근할 때 발생하는 문제와, 이를 해결하기 위한 동기화의 중요성을 배웠습니다. 이는 훗날 웹 서버의 요청 처리 방식을 이해하는 데 큰 밑거름이 되었습니다.

---
