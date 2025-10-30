# 🎵 Dynamic Beat - Java 리듬 게임

> **Java Swing 기반 7키 리듬 게임**  
> 음악에 맞춰 떨어지는 노트를 타이밍에 맞게 입력하는 클래식 리듬 게임

<p align="center">
  <br>
  <img src="https://private-user-images.githubusercontent.com/44717307/309545036-7b0aad76-e37e-41de-88db-f304c0844a0b.png?jwt=eyJhbGciOiJIUzI1NiIsInR5cCI6IkpXVCJ9.eyJpc3MiOiJnaXRodWIuY29tIiwiYXVkIjoicmF3LmdpdGh1YnVzZXJjb250ZW50LmNvbSIsImtleSI6ImtleTUiLCJleHAiOjE3MDk0NjQ1MjEsIm5iZiI6MTcwOTQ2NDIyMSwicGF0aCI6Ii80NDcxNzMwNy8zMDk1NDUwMzYtN2IwYWFkNzYtZTM3ZS00MWRlLTg4ZGItZjMwNGMwODQ0YTBiLnBuZz9YLUFtei1BbGdvcml0aG09QVdTNC1ITUFDLVNIQTI1NiZYLUFtei1DcmVkZW50aWFsPUFLSUFWQ09EWUxTQTUzUFFLNFpBJTJGMjAyNDAzMDMlMkZ1cy1lYXN0LTElMkZzMyUyRmF3czRfcmVxdWVzdCZYLUFtei1EYXRlPTIwMjQwMzAzVDExMTAyMVomWC1BbXotRXhwaXJlcz0zMDAmWC1BbXotU2lnbmF0dXJlPWQ4ZTRiZmRlMWYwNGRkZGNlMGE4YmI0NWQxZTYzOWI2MTg1NWQ3NjUxNjA4YWJiOTE2ODA1NWU2ZWE1MDkzMjEmWC1BbXotU2lnbmVkSGVhZGVycz1ob3N0JmFjdG9yX2lkPTAma2V5X2lkPTAmcmVwb19pZD0wIn0.bLWRDnv7DeqHKBwlX9QUuYy5misCYOZoVl5Z0fVGFyE">
  <br>
</p>

---

## 📑 목차
- [프로젝트 소개](#-프로젝트-소개)
- [기술 스택](#-기술-스택)
- [게임 시스템](#-게임-시스템)
- [주요 기능](#-주요-기능)
- [핵심 구현](#-핵심-구현)
- [프로젝트 구조](#-프로젝트-구조)
- [실행 방법](#-실행-방법)
- [배운 점](#-배운-점--회고)
- [개선 방향](#-개선-방향)

---

## 🎮 프로젝트 소개

### 프로젝트 개요
이 프로젝트는 [Java_Rhythm 튜토리얼](https://www.youtube.com/playlist?list=PLRx0vPvlEmdDySO3wDqMYGKMVH4Qa4QhR)을 기반으로 제작한 **7키 리듬 게임**입니다. 음악에 맞춰 떨어지는 노트를 S, D, F, Space, J, K, L 키로 입력하며, Perfect, Great, Good, Late 판정을 통해 점수와 콤보를 쌓는 게임입니다.

### 개발 동기
- 🎵 **음악과 게임의 융합**: 리듬 게임 특유의 몰입감 있는 경험 제공
- ⏱️ **짧은 플레이 타임**: 3~4분 내외의 가볍게 즐길 수 있는 게임성
- 🎓 **학습 목표**: Java Swing, 멀티스레딩, 이벤트 처리 실전 경험

### 개발 환경
- **개발 도구**: Eclipse IDE
- **개발 기간**: 2023년 2학기 (개인 프로젝트)
- **개발 인원**: 1인
- **참고 자료**: YouTube Java Rhythm Game 튜토리얼

---

## 🛠️ 기술 스택

<div align="center">

| Category | Technology |
|:--------:|:----------:|
| Language | <img src="https://img.shields.io/badge/java-007396?style=for-the-badge&logo=java&logoColor=white"> |
| GUI | <img src="https://img.shields.io/badge/Swing-007396?style=for-the-badge&logo=java&logoColor=white"> |
| Audio | <img src="https://img.shields.io/badge/JLayer-FF6B6B?style=for-the-badge&logo=java&logoColor=white"> |
| Threading | <img src="https://img.shields.io/badge/Java%20Thread-007396?style=for-the-badge&logo=java&logoColor=white"> |

</div>

### 주요 라이브러리
- **JLayer 1.0.1**: MP3 파일 재생
- **Java Swing**: GUI 구현
- **Java AWT**: 그래픽 렌더링 및 이벤트 처리

---

## 🎯 게임 시스템

### 게임 플로우
```
인트로 화면 → 곡 선택 → 난이도 선택 → 게임 플레이 → 결과 화면
```

### 조작 방법
| 키 | 노트 레인 |
|:--:|:--------:|
| S | 1번 레인 |
| D | 2번 레인 |
| F | 3번 레인 |
| **Space** | **4~5번 레인 (롱노트)** |
| J | 6번 레인 |
| K | 7번 레인 |
| L | 8번 레인 |

### 판정 시스템
| 판정 | 점수 | 판정 범위 |
|:----:|:----:|:--------:|
| **Perfect** | +50 | ±13ms |
| **Great** | +30 | ±14ms |
| **Good** | +20 | ±13ms |
| **Late** | +5 | ±13ms |
| **Miss** | -10 | 판정선 통과 |

---

## ⭐ 주요 기능

### 1️⃣ 곡 선택 시스템
- ✅ 3곡 지원 (Easy Love, Feel Me, Oceans)
- ✅ 좌우 화살표로 곡 선택
- ✅ 선택한 곡의 미리듣기 음악 자동 재생
- ✅ 앨범 커버 이미지 표시

### 2️⃣ 난이도 선택
- ✅ **Easy 모드**: 200~500ms 간격의 기본 비트
- ✅ **Hard 모드**: 100~300ms 간격의 밀집된 비트
- ✅ 난이도별 다른 노트 생성 알고리즘

### 3️⃣ 점수 & 콤보 시스템
- ✅ 실시간 점수 표시
- ✅ 현재 콤보 & 최고 콤보 표시
- ✅ 최고 점수 자동 갱신
- ✅ 게임 종료 후 등급 표시 (S/A/B/C)

### 4️⃣ 동적 노트 생성
- ✅ 난이도에 따른 랜덤 비트 생성
- ✅ 최소/최대 간격 조절로 난이도 조정
- ✅ 음악 길이에 맞춘 노트 개수 자동 계산

---

## 🔧 핵심 구현

### 1. 더블 버퍼링 기술
```java
// 화면 깜빡임 방지
public void paint(Graphics g) {
    screenImage = createImage(Main.SCREEN_WIDTH, Main.SCREEN_HEIGHT);
    screenGraphic = screenImage.getGraphics();
    screenDraw((Graphics2D) screenGraphic);
    g.drawImage(screenImage, 0, 0, null);
}
```

**의미**: 백버퍼에 미리 그린 후 한 번에 화면에 출력하여 깜빡임 현상 제거

### 2. 멀티스레드 노트 시스템
```java
public class Note extends Thread {
    @Override
    public void run() {
        while (true) {
            drop(); // 노트를 아래로 이동
            if(proceeded) {
                Thread.sleep(Main.SLEEP_TIME); // 10ms마다 갱신
            } else {
                interrupt(); // 판정 완료 시 스레드 종료
                break;
            }
        }
    }
}
```

**특징**:
- 각 노트가 독립적인 스레드로 동작
- 판정 완료 시 자동으로 스레드 종료
- ArrayList로 관리하여 동적 생성/삭제

### 3. 랜덤 비트 생성 알고리즘
```java
private Beat[] generateRandomBeats(int duration, int minGap, int maxGap, int numBeats) {
    Beat[] beats = new Beat[numBeats];
    beats[0] = new Beat((int)(Math.random() * duration), getRandomNote());
    
    for(int i = 1; i < numBeats; i++) {
        int gap = (int)(Math.random() * (maxGap - minGap + 1) + minGap);
        int startTime = beats[i-1].getTime() + gap;
        beats[i] = new Beat(startTime, getRandomNote());
    }
    return beats;
}
```

**특징**:
- 이전 비트 끝 시간 기준으로 간격 조절
- 최소/최대 간격으로 난이도 조정
- S, D, F, J, K, L, Space 중 랜덤 배치

### 4. 판정 시스템 (First In First Out)
```java
public void judge(String input) {
    for(int i = 0; i < noteList.size(); i++) {
        Note note = noteList.get(i);
        if(input.equals(note.getNoteType())) {
            judgeEvent(note.judge()); // 노트 y좌표 기반 판정
            break; // 가장 먼저 온 노트만 판정
        }
    }
}
```

**특징**:
- ArrayList를 Queue처럼 사용
- 가장 먼저 생성된 노트부터 판정
- y좌표 기반 정밀 판정 (±13ms 단위)

### 5. 음악 동기화
```java
public class Music extends Thread {
    @Override
    public void run() {
        do {
            player.play(); // MP3 재생
        } while(isLoop); // 무한 반복 옵션
    }
    
    public int getTime() {
        return player.getPosition(); // 현재 재생 시간 반환
    }
}
```

**의미**: 음악 재생 시간과 노트 생성 시간을 동기화하여 정확한 타이밍 보장

---

## 📂 프로젝트 구조

```
Dynamic Beat/
├── src/dynamic_beat_final/
│   ├── Main.java              # 메인 실행 및 상수 정의
│   ├── DynamicBeat.java       # 게임 메인 프레임
│   ├── Game.java              # 게임 로직 & 렌더링
│   ├── Music.java             # MP3 재생 (Thread)
│   ├── Note.java              # 노트 객체 (Thread)
│   ├── Beat.java              # 비트 타이밍 정보
│   ├── Track.java             # 곡 정보 VO
│   ├── KeyListener.java       # 키보드 입력 처리
│   └── ScoreResult.java       # 결과 화면 (Thread)
│
├── images/                     # 게임 UI 이미지
│   ├── noteBasic.png          # 기본 노트
│   ├── judgeLine.png          # 판정선
│   ├── keyPadBasic.png        # 기본 키패드
│   └── ...
│
├── music/                      # 배경음악 & 효과음
│   ├── introMusic.mp3         # 인트로 음악
│   ├── Easy Love - Hotham.mp3
│   ├── drumSmall1.mp3         # 타격음
│   └── ...
│
└── lib/
    └── jl1.0.1.jar            # JLayer 라이브러리
```

### 클래스 다이어그램
```
DynamicBeat (JFrame)
    ↓
Game (Thread)
    ↓
Note (Thread) × N개
    ↓
Music (Thread)
```

---

## 🚀 실행 방법

### 1. 환경 설정
```bash
# Java 8 이상 필요
java -version

# Eclipse IDE 권장
```

### 2. 라이브러리 추가
1. JLayer 1.0.1 다운로드
2. Eclipse: Project → Properties → Java Build Path → Libraries → Add External JARs
3. `jl1.0.1.jar` 추가

### 3. 리소스 파일 구조
```
프로젝트 루트/
├── images/       # 이미지 파일
└── music/        # 음악 파일
```

### 4. 실행
```bash
# Main.java 실행
Run As → Java Application
```

---

## 💡 배운 점 & 회고

### 기술적 성장
#### 1. **멀티스레딩 이해**
- 각 노트가 독립적인 스레드로 동작하는 구조 설계
- `interrupt()`를 통한 스레드 안전 종료 방법 학습
- 여러 스레드 간 동기화 문제 해결 경험

#### 2. **Java Swing 심화**
- 더블 버퍼링으로 화면 깨짐 현상 해결
- `repaint()` 호출 타이밍 최적화
- 이벤트 리스너를 활용한 반응형 UI 구현

#### 3. **객체지향 설계**
- Note, Beat, Track 등 역할별 클래스 분리
- Thread 상속을 통한 동시성 구현
- VO 패턴으로 데이터 관리

#### 4. **파일 입출력**
- MP3 파일 스트리밍 방식 이해
- BufferedInputStream으로 성능 최적화
- 리소스 경로 관리 (`music/`, `images/`)

### 개발 과정의 어려움
#### 1. **노트 판정 정확도**
- **문제**: 판정 범위가 너무 넓어서 게임성 저하
- **해결**: y좌표 기반 세밀한 판정 구간 설정 (±13ms 단위)

#### 2. **음악 동기화**
- **문제**: 노트 생성 타이밍과 음악이 어긋남
- **해결**: `Music.getTime()`으로 현재 재생 시간 추적

#### 3. **랜덤 비트 생성**
- **문제**: 완전 랜덤 생성 시 노트가 몰리거나 비는 현상
- **해결**: 최소/최대 간격 제한으로 밀도 조절

#### 4. **리소스 경로 문제**
- **문제**: 이미지/음악 파일 경로 오류 빈번
- **해결**: 상대 경로 통일 (`images/`, `music/`)

### 개인적 성장
> "튜토리얼을 따라 하는 것도 쉽지 않다는 것을 알게 되었습니다. 하지만 난이도별 랜덤 비트 생성, 이미지/음악 파일 경로 수정, 저작권 없는 노래 선택 등 스스로 문제를 해결하며 프로젝트를 완성했다는 것에 큰 보람을 느꼈습니다."

- ✅ 독학으로 프로젝트 완성 능력 향상
- ✅ 디버깅 역량 강화 (특히 멀티스레드 환경)
- ✅ 게임 개발에 대한 전반적인 이해도 증가

---

## 🔮 개선 방향

### 기능 추가
- [ ] **더 많은 곡 지원**: 최소 10곡 이상
- [ ] **노트 에디터**: 직접 비트맵 제작 기능
- [ ] **멀티플레이**: 로컬 2P 대전 모드
- [ ] **랭킹 시스템**: 로컬 DB에 점수 저장

### 기술 개선
- [ ] **디자인 패턴 적용**: Singleton, Observer 패턴
- [ ] **리소스 관리 개선**: 이미지/음악 로딩 최적화
- [ ] **판정 시스템 고도화**: 판정 범위 사용자 설정
- [ ] **UI/UX 개선**: 현대적인 디자인 적용

### 코드 품질
- [ ] **예외 처리 강화**: try-catch 블록 세분화
- [ ] **주석 보완**: Javadoc 형식 통일
- [ ] **리팩토링**: 매직 넘버 상수화, 긴 메서드 분리
- [ ] **테스트 코드**: JUnit으로 핵심 로직 테스트

---

## 📊 게임 스펙

### 게임 정보
- **해상도**: 1280 × 720
- **키 개수**: 7키 + 롱노트
- **지원 곡**: 3곡
- **난이도**: Easy / Hard

### 판정 시스템
| 요소 | 값 |
|:----:|:--:|
| 노트 속도 | 5 px/frame |
| 갱신 주기 | 10ms |
| 판정 도달 시간 | 2초 |

---

## 📜 라이선스

MIT License © 2024

본 프로젝트는 학습 목적으로 제작되었습니다.

---

## 🎵 음악 출처

- **Easy Love** - Hotham (저작권 무료)
- **Feel Me** - Rexlambo (저작권 무료)
- **Oceans** - Away, Hotham (저작권 무료)

※ 모든 음악은 저작권 없는 음악으로 선정하였습니다.

---

<div align="center">

</div>
