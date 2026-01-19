package dynamic_beat_final;

import java.awt.Graphics2D;
import java.awt.Image;

import javax.swing.ImageIcon;

//각각의 떨어지는 노트 모두 Thread

/*
    노트 판정 과정
    노트판정은 Queue 형식으로 구현한다. 즉 first in first out 형식.
    이는 당연히도 먼저 출현 - 들어온 - 노트가 가장 먼저 판정이 되어야하기 때문이다.
    큐는 특별한 코드로 만들기보다는 ArrayList 로 구현하면 된다.
*/
public class Note extends Thread {

	private Image noteBasicImage = new ImageIcon("images/noteBasic.png").getImage();
	
	// 현재 노트 위치 확인을 위한 x, y 좌표
    // 이 중 y 값의 시작위치를 특정값으로 고정으로 해두어서
    // 노트가 생성된 지 1초 뒤 노트 판정위치인 580에 도달 할 수 있도록 함
	private int x, y = 580 - (1000 / Main.SLEEP_TIME * Main.NOTE_SPEED) * Main.REACH_TIME;
	
	private String noteType;
	// 현재 노트의 진행 여부 -> 즉 노트가 판정이 필요한 범위를 넘어가는지 확인할 수 있게
	private boolean proceeded = true;
	
	// 노트 판정 -> 현재 노트 타입 - 키보드 어떤 버튼인지 - 반환
	public String getNoteType() {
		return noteType;
	}
	
	public boolean isProceeded() {
		return proceeded;
	}
	
	// 노트를 더이상 사용할 필요가 없다면 false
	public void close() {
		proceeded = false;
	}
	
	public Note(String noteType) {
		if(noteType.equals("S")) {
			x = 228;
		}
		else if(noteType.equals("D")) {
			x = 332;
		}
		else if(noteType.equals("F")) {
			x = 436;
		}
		else if(noteType.equals("Space")) {
			x = 540;
		}
		else if(noteType.equals("J")) {
			x = 744;
		}
		else if(noteType.equals("K")) {
			x = 848;
		}
		else if(noteType.equals("L")) {
			x = 952;
		}
		this.noteType = noteType;
	}
	
	public void screenDraw(Graphics2D g) {
		// 짧은 노트라면 현재 x, y 좌표에 노트를 그려냄
        // 노트 타입 - 키 - 에 따라서 다른 그래픽 그림을 그려냄 출력함
		if(!noteType.equals("Space"))
		{
			g.drawImage(noteBasicImage, x, y, null);
		}
		else // 긴 노트라면 노트 이미지 2개를 그려냄, 이때 겹치면 안되니까 한쪽은 x+100
		{
			g.drawImage(noteBasicImage, x, y, null);
			g.drawImage(noteBasicImage, x + 100, y, null);
		}
	}
	
	public void drop() {
		y += Main.NOTE_SPEED;
		if(y > 620) {
			//System.out.println("Miss");
			close();
		}
	}
	
	// 스레드 실행 함수
	@Override
	public void run() {
		try {
			// 노트 떨어지는것은 무한반복
            // 1초에 Main.NOTE_SPEED * 100 정도만큼 움직임
			while (true) {
				drop();
				if(proceeded == true) {
					// 떨어질때 Main.Sleep_Time 에 설정된 시간만큼 딜레이를 주면서 떨어짐
                    // 현재 노트가 계속해서 움직이고 있다면 반복적으로 내려옴
                    // 해당 노트에대한 작업처리가 끝나면 proceeded 가 false 로 변경
					Thread.sleep(Main.SLEEP_TIME);
				}
				else {
					// 노트에 대한 전반적 작업 - 판정, 입력 등- 끝나서
                    // 더이상 해당 노트가 필요없어지면 스레드 종료(interrupt)
                    proceeded = false;
					interrupt();
					break;
				}
			}
		} catch(Exception e) {
			System.err.println(e.getMessage());
		}
	}
	
	public String judge() {
		if(y >= 613) {
			//System.out.println("Late");
			close();
			return "Late";
		}
		else if(y >= 600) {
				//System.out.println("Good");
				close();
				return "Good";
		}
		else if(y >= 587) {
			//System.out.println("Great");
			close();
			return "Great";
		}
		else if(y >= 573) {
			//System.out.println("Perfect");
			close();
			return "Perfect";
		}
		else if(y >= 565) {
			//System.out.println("Great");
			close();
			return "Great";
		}
		else if(y >= 535) {
			//System.out.println("Early");
			close();
			return "Early";
		}
		return "None";
	}
	
	public int getY() {
		return y;
	}
	
}
