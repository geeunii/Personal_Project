package dynamic_beat_final;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.RenderingHints;
import java.util.ArrayList;

import javax.swing.ImageIcon;

public class Game extends Thread{
	
	// ingame 각 버튼멸 및 노트 닷지, 이펙트 이미지
	private Image noteRouteLineImage = new ImageIcon(Main.class.getResource("/images/noteRouteLine.png")).getImage();
	private Image judgementLineImage = new ImageIcon(Main.class.getResource("/images/judgementLine.png")).getImage();
	private Image gameInfoImage = new ImageIcon(Main.class.getResource("/images/gameInfo.png")).getImage();
	private Image noteRouteSImage = new ImageIcon(Main.class.getResource("/images/noteRoute.png")).getImage();
	private Image noteRouteDImage = new ImageIcon(Main.class.getResource("/images/noteRoute.png")).getImage();
	private Image noteRouteFImage = new ImageIcon(Main.class.getResource("/images/noteRoute.png")).getImage();
	private Image noteRouteSpace1Image = new ImageIcon(Main.class.getResource("/images/noteRoute.png")).getImage();
	private Image noteRouteSpace2Image = new ImageIcon(Main.class.getResource("/images/noteRoute.png")).getImage();
	private Image noteRouteJImage = new ImageIcon(Main.class.getResource("/images/noteRoute.png")).getImage();
	private Image noteRouteKImage = new ImageIcon(Main.class.getResource("/images/noteRoute.png")).getImage();
	private Image noteRouteLImage = new ImageIcon(Main.class.getResource("/images/noteRoute.png")).getImage();
	private Image blueFlareImage;
	private Image judgeImage;
	private Image keyPadSImage = new ImageIcon(Main.class.getResource("/images/keyPadBasic.png")).getImage();
	private Image keyPadDImage = new ImageIcon(Main.class.getResource("/images/keyPadBasic.png")).getImage();
	private Image keyPadFImage = new ImageIcon(Main.class.getResource("/images/keyPadBasic.png")).getImage();
	private Image keyPadSpace1Image = new ImageIcon(Main.class.getResource("/images/keyPadBasic.png")).getImage();
	private Image keyPadSpace2Image = new ImageIcon(Main.class.getResource("/images/keyPadBasic.png")).getImage();
	private Image keyPadJImage = new ImageIcon(Main.class.getResource("/images/keyPadBasic.png")).getImage();
	private Image keyPadKImage = new ImageIcon(Main.class.getResource("/images/keyPadBasic.png")).getImage();
	private Image keyPadLImage = new ImageIcon(Main.class.getResource("/images/keyPadBasic.png")).getImage();
	private Image comboImage = new ImageIcon(Main.class.getResource("/images/combo.png")).getImage();
	
	// 게임 실행 시 그 게임에 맞는 음악을 플레이하기 위한 변수
	private String titleName;
	private String difficulty;
	private String musicTitle;
	private Music gameMusic;
	
	// 점수, 콤보 출력
    static int score = 0;
    static int highScore = 0;
    static int combo = 0;
    static int highCombo = 0;
	
    // 음악 노트를 개별적으로 관리할 ArrayList
	ArrayList<Note> noteList = new ArrayList<Note>();
	
	public Game(String titleName, String difficulty, String musicTitle) {
		this.titleName = titleName;
		this.difficulty = difficulty;
		this.musicTitle = musicTitle;
		gameMusic = new Music(this.musicTitle, false);
		gameMusic.start();
	}
	
	public void screenDraw(Graphics2D g) {
		// INGAME 노트 경로 배경, 노트 경로별 경계선(라인), 어떤키 사용하는지, 점수 등 => 총 7키
		// 뒷부분에 그려지는 이미지 - 코드 일수록 - 앞쪽으로 튀어나와서 그려지게 됨, 마치 모드처럼
			
		g.drawImage(noteRouteSImage, 228, 30, null);
		g.drawImage(noteRouteDImage, 332, 30, null);
		g.drawImage(noteRouteFImage, 436, 30, null);
		g.drawImage(noteRouteSpace1Image, 540, 30, null);
		g.drawImage(noteRouteSpace2Image, 640, 30, null);
		g.drawImage(noteRouteJImage, 744, 30, null);
		g.drawImage(noteRouteKImage, 848, 30, null);
		g.drawImage(noteRouteLImage, 952, 30, null);
		g.drawImage(noteRouteLineImage, 224, 30, null);
		g.drawImage(noteRouteLineImage, 328, 30, null);
		g.drawImage(noteRouteLineImage, 432, 30, null);
		g.drawImage(noteRouteLineImage, 536, 30, null);
		g.drawImage(noteRouteLineImage, 740, 30, null);
		g.drawImage(noteRouteLineImage, 844, 30, null);
		g.drawImage(noteRouteLineImage, 948, 30, null);
		g.drawImage(noteRouteLineImage, 1052, 30, null);
		g.drawImage(gameInfoImage, 0, 660, null); // ingame 시 게임 정보 표시를 위한 파란 줄
		g.drawImage(judgementLineImage, 0, 580, null);
		
		// noteList 에는 note 위치 - x, y - 가 저장되어 있음
	    // for 문을 통해 noteList 안에 있는 내용을 하나하나 꺼내오면서 반복 출력
	    // 반복 출력되면서 Graph 으로 만듦
		for(int i = 0; i < noteList.size(); i++)
		{
			Note note = noteList.get(i);
			
			// 노트 판정의 마지노선이 620 이기 때문에
	        // 620 이 넘어가는 note 들에 대해서는 miss 이미지 출력
			if(note.getY() > 620) {
				judgeImage = new ImageIcon(Main.class.getResource("/images/judgeMiss.png")).getImage();
				score -= 10;
				combo = 0;
			}
			
			// 현재 노트가 동작 상태가 아니라면 - Proceeded 가 false 라면 -
	        // 사용되지 않은 노트는 화면에서 지워짐 -> 해당 i 번째 노트를 삭제
			if(!note.isProceeded()) {
				noteList.remove(i);
				i--;
			}
			else {
				note.screenDraw(g);
			}
		}
		
		//글씨 색깔, 폰트 설정
		g.setColor(Color.white);
		// Graphics2D 설정 : 이 설정을 통해서 TEXT에 ANTIALIASING 설정을 할 수 있고, 글씨가 좀더 뚜렷하게 보임
		g.setRenderingHint(RenderingHints.KEY_TEXT_ANTIALIASING, RenderingHints.VALUE_TEXT_ANTIALIAS_ON);
		g.setColor(Color.white);
		g.setFont(new Font("Arial", Font.BOLD, 30));
		g.drawString(titleName, 20, 702); // 인게임에서 노래 제목 출력
		g.drawString(difficulty, 1190, 702); // 인게임에서 노래 난이도 출력
		// 최고 점수, 최고 콤보 확인
        if(highScore < score){
            highScore = score;
        }
        // 게임 점수 출력
        if(score<0){ // 점수가 0 미만으로 내려가면 그냥 0 출력
            g.drawString(String.valueOf(0), 620, 702);
        }else{ // 아니면 그대로 출력
            g.drawString(String.valueOf(score), 620, 702);
        }
        g.drawString(String.valueOf(highScore), 750, 702);
        // 게임 콤보 출력
        g.drawImage(comboImage, 1000,130,null);
        g.setColor(Color.CYAN);
        if(highCombo < combo){
            highCombo = combo;
        }
        g.drawString(String.valueOf(combo), 1150, 270);
        g.drawString(String.valueOf(highCombo), 1150, 350);
        
		g.setFont(new Font("Arial", Font.PLAIN, 26));
		g.setColor(Color.DARK_GRAY);
		// 인게임 키패드 확인 출력
		g.drawString("S", 270, 609);
		g.drawString("D", 374, 609);
		g.drawString("F", 478, 609);
		g.drawString("Space Bar", 580, 609);
		g.drawString("J", 784, 609);
		g.drawString("K", 889, 609);
		g.drawString("L", 993, 609);
		g.setColor(Color.LIGHT_GRAY);
		g.setFont(new Font("Elephant", Font.BOLD, 30));
		// 음악 노트 판정 배경, 이미지, 글자
		g.drawImage(blueFlareImage, 480, 430, null);
		g.drawImage(judgeImage, 460, 420, null);
		g.drawImage(keyPadSImage, 228, 580, null);
		g.drawImage(keyPadDImage, 332, 580, null);
		g.drawImage(keyPadFImage, 436, 580, null);
		g.drawImage(keyPadSpace1Image, 540, 580, null);
		g.drawImage(keyPadSpace2Image, 640, 580, null);
		g.drawImage(keyPadJImage, 744, 580, null);
		g.drawImage(keyPadKImage, 848, 580, null);
		g.drawImage(keyPadLImage, 952, 580, null);
	}
	
	
	// press 메서드 : 해당 버튼을 눌렀을 때 이미지변경
	// release 메서드 : 해당 버튼에서 손을 뗐을 때 이미지 변경
	public void pressS() {
		judge("S");
		noteRouteSImage = new ImageIcon(Main.class.getResource("/images/noteRoutePressed.png")).getImage();
		keyPadSImage = new ImageIcon(Main.class.getResource("/images/keyPadPressed.png")).getImage();
		new Music("drumSmall1.mp3", false).start();
	}
	
	public void releaseS() {
		noteRouteSImage = new ImageIcon(Main.class.getResource("/images/noteRoute.png")).getImage();
		keyPadSImage = new ImageIcon(Main.class.getResource("/images/keyPadBasic.png")).getImage();
	}
	
	public void pressD() {
		judge("D");
		noteRouteDImage = new ImageIcon(Main.class.getResource("/images/noteRoutePressed.png")).getImage();
		keyPadDImage = new ImageIcon(Main.class.getResource("/images/keyPadPressed.png")).getImage();
		new Music("drumSmall1.mp3", false).start();
	}
	
	public void releaseD() {
		noteRouteDImage = new ImageIcon(Main.class.getResource("/images/noteRoute.png")).getImage();
		keyPadDImage = new ImageIcon(Main.class.getResource("/images/keyPadBasic.png")).getImage();
	}
	
	public void pressF() {
		judge("F");
		noteRouteFImage = new ImageIcon(Main.class.getResource("/images/noteRoutePressed.png")).getImage();
		keyPadFImage = new ImageIcon(Main.class.getResource("/images/keyPadPressed.png")).getImage();
		new Music("drumSmall1.mp3", false).start();
	}
	
	public void releaseF() {
		noteRouteFImage = new ImageIcon(Main.class.getResource("/images/noteRoute.png")).getImage();
		keyPadFImage = new ImageIcon(Main.class.getResource("/images/keyPadBasic.png")).getImage();
	}
	
	public void pressSpace() {
		judge("Space");
		noteRouteSpace1Image = new ImageIcon(Main.class.getResource("/images/noteRoutePressed.png")).getImage();
		keyPadSpace1Image = new ImageIcon(Main.class.getResource("/images/keyPadPressed.png")).getImage();
		noteRouteSpace2Image = new ImageIcon(Main.class.getResource("/images/noteRoutePressed.png")).getImage();
		keyPadSpace2Image = new ImageIcon(Main.class.getResource("/images/keyPadPressed.png")).getImage();
		new Music("drumBig1.mp3", false).start();
	}
	
	public void releaseSpace() {
		noteRouteSpace1Image = new ImageIcon(Main.class.getResource("/images/noteRoute.png")).getImage();
		keyPadSpace1Image = new ImageIcon(Main.class.getResource("/images/keyPadBasic.png")).getImage();
		noteRouteSpace2Image = new ImageIcon(Main.class.getResource("/images/noteRoute.png")).getImage();
		keyPadSpace2Image = new ImageIcon(Main.class.getResource("/images/keyPadBasic.png")).getImage();
	}
	
	public void pressJ() {
		judge("J");
		noteRouteJImage = new ImageIcon(Main.class.getResource("/images/noteRoutePressed.png")).getImage();
		keyPadJImage = new ImageIcon(Main.class.getResource("/images/keyPadPressed.png")).getImage();
		new Music("drumSmall1.mp3", false).start();
	}
	
	public void releaseJ() {
		noteRouteJImage = new ImageIcon(Main.class.getResource("/images/noteRoute.png")).getImage();
		keyPadJImage = new ImageIcon(Main.class.getResource("/images/keyPadBasic.png")).getImage();
	}
	
	public void pressK() {
		judge("K");
		noteRouteKImage = new ImageIcon(Main.class.getResource("/images/noteRoutePressed.png")).getImage();
		keyPadKImage = new ImageIcon(Main.class.getResource("/images/keyPadPressed.png")).getImage();
		new Music("drumSmall1.mp3", false).start();
	}
	
	public void releaseK() {
		noteRouteKImage = new ImageIcon(Main.class.getResource("/images/noteRoute.png")).getImage();
		keyPadKImage = new ImageIcon(Main.class.getResource("/images/keyPadBasic.png")).getImage();
	}
	public void pressL() {
		judge("L");
		noteRouteLImage = new ImageIcon(Main.class.getResource("/images/noteRoutePressed.png")).getImage();
		keyPadLImage = new ImageIcon(Main.class.getResource("/images/keyPadPressed.png")).getImage();
		new Music("drumSmall1.mp3", false).start();
	}
	
	public void releaseL() {
		noteRouteLImage = new ImageIcon(Main.class.getResource("/images/noteRoute.png")).getImage();
		keyPadLImage = new ImageIcon(Main.class.getResource("/images/keyPadBasic.png")).getImage();
	}
	
	@Override
	public void run() {
		dropNotes(this.titleName);
	}
	
	public void close() {
		gameMusic.close();
		this.interrupt();
	}
	
	public void dropNotes(String titleName) {
		Beat[] beats = null; // 비트 배열 초기화
		if (titleName.equals("Easy Love - Hotham") && difficulty.equals("Easy")) {
	        // 노래의 지속 시간을 밀리초 단위로 설정
	        int songDuration = 4460 - Main.REACH_TIME * 1000;

	        // 기본적인 비트 생성
	        beats = generateRandomBeats(songDuration, 200, 500, 1000);
	        
	        // 노래 지속 시간 내에서 무작위로 비트 생성
	        //beats = generateRandomBeats(songDuration);
	    } else if (titleName.equals("Easy Love - Hotham") && difficulty.equals("Hard")) {
	        // 노래의 지속 시간을 밀리초 단위로 설정
	        int songDuration = 4460 - Main.REACH_TIME * 1000;

	        beats = generateRandomBeats(songDuration, 100, 300, 1500);
	        
	        // 노래 지속 시간 내에서 무작위로 비트 생성
	        //beats = generateRandomBeats(songDuration);
	    }
		else if (titleName.equals("Feel Me - Rexlambo") && difficulty.equals("Easy")) 
		{
			int songDuration = 4460 - Main.REACH_TIME * 1000;
			
			beats = generateRandomBeats(songDuration, 200, 500, 1000);
		}
		else if (titleName.equals("Feel Me - Rexlambo") && difficulty.equals("Hard")) 
		{
			int songDuration = 4460 - Main.REACH_TIME * 1000;
			
			beats = generateRandomBeats(songDuration, 100, 300, 1500);
		}
		else if (titleName.equals("Oceans - Away Hotham") && difficulty.equals("Easy")) {
			int songDuration = 4460 - Main.REACH_TIME * 1000;
			
			beats = generateRandomBeats(songDuration, 200, 500, 1000);
			
		}
		else if (titleName.equals("Oceans - Away Hotham") && difficulty.equals("Hard")) {
			int songDuration = 4460 - Main.REACH_TIME * 1000;
			
			beats = generateRandomBeats(songDuration, 100, 300, 1500);
		}
		int i = 0;
	   
	    while (i < beats.length && !isInterrupted()) {
	        boolean dropped = false;
	        if (beats[i].getTime() <= gameMusic.getTime()) {
	            Note note = new Note(beats[i].getNoteName()); // Note 클래스의 인스턴스를 생성하고 해당 비트의 음표 이름을 설정
	            note.start(); // start 메서드를 호출하여 음표를 떨어뜨리는 스레드 시작
	            noteList.add(note); // 생성된 음표를 notelist에 추가
	            i++;
	            dropped = true;
	        }
	        if (!dropped) {
	            try {
	                Thread.sleep(5);
	            } catch (InterruptedException e) {
	            }
	        }
	    }
	}
	
	private Beat[] generateRandomBeats(int duration, int minGap, int maxGap, int numBeats) {
	    // 생성할 무작위 비트의 수 정의
	    Beat[] beats = new Beat[numBeats];

	    // 첫 번째 비트는 무작위로 생성
	    beats[0] = new Beat((int) (Math.random() * duration), getRandomNote());

	    // 나머지 비트는 이전 비트의 끝 시간에서부터 무작위 갭을 둬서 생성
	    for (int i = 1; i < numBeats; i++) {
	        int gap = (int) (Math.random() * (maxGap - minGap + 1) + minGap);
	        int startTime = beats[i - 1].getTime() + gap;

	        // 무작위로 S, D, F, J, K, L 중 하나를 선택하여 할당
	        String randomNote = getRandomNote();

	        beats[i] = new Beat(startTime, randomNote);
	    }

	    return beats;
	}
	
	private String getRandomNote() {
	    String[] notes = {"S", "D", "F", "J", "K", "L","Space"};
	    int randomIndex = (int) (Math.random() * notes.length);
	    return notes[randomIndex];
	}
	
	public void judge(String input) {
		// for 문을 통해 전체 노트를 훑어보게됨
		for(int i = 0; i < noteList.size(); i++) {
			// 만약 noteList.get(i) 로 가져온 현재 노트 - nowNote.getNoteType - 가
            // 사용자가 입력한 input 과 일치한다면 note.judge() 메서드 실행
            // 만약 노트 입력이 안된거면 그냥 무시 => 추후 miss 판정
			Note note = noteList.get(i);
			if(input.equals(note.getNoteType())) {
				// judgeEvent 는 String 을 매개변수로 받음
                // Note 의 judge 메서드는 각 판정에 맞는 String 타입을 반환함
				judgeEnent(note.judge());
				break;
			}
		}
	}
	
	public void judgeEnent(String judge) {
		if(!judge.equals("None")) {
			blueFlareImage = new ImageIcon(Main.class.getResource("/images/blueFlare.png")).getImage();
		}
		if(judge.equals("Miss")) {
			judgeImage = new ImageIcon(Main.class.getResource("/images/judgeMiss.png")).getImage();
			score -= 10;
			combo = 0;
		}
		else if(judge.equals("Late")) {
			judgeImage = new ImageIcon(Main.class.getResource("/images/judgeLate.png")).getImage();
			score += 5;
			combo += 1;
		}
		else if(judge.equals("Good")) {
			judgeImage = new ImageIcon(Main.class.getResource("/images/judgeGood.png")).getImage();
			score += 20;
			combo += 1;
		}
		else if(judge.equals("Great")) {
			judgeImage = new ImageIcon(Main.class.getResource("/images/judgeEarly.png")).getImage();
			score += 30;
			combo += 1;
		}
		else if(judge.equals("Perfect")) {
			judgeImage = new ImageIcon(Main.class.getResource("/images/judgePerfect.png")).getImage();
			score += 50;
			combo += 1;
		}
		else if(judge.equals("Early")) {
			judgeImage = new ImageIcon(Main.class.getResource("/images/judgeEarly.png")).getImage();
			score += 10;
			combo += 1;
		}
	}
	
	 public Music length(){
	        return gameMusic;
	    }
}
