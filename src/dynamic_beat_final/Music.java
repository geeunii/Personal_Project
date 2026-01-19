package dynamic_beat_final;

import java.io.BufferedInputStream;
import java.io.File;
import java.io.FileInputStream;

import javazoom.jl.player.Player;

public class Music extends Thread {
	
	private Player player; // mp3 를 재생해주는 클래스
	private boolean isLoop; // 음악의 무한 루프 확인
	private File file; // 파일을 가져오는 클래스
	private FileInputStream fis;
	private BufferedInputStream bis;
	
	// 음악 mp3 파일 이름, 음악 반복재생 여부, 게임관련 음악인지 메뉴 관련 음악인지 여부
	public Music(String name, boolean isLoop) {
		try {
			this.isLoop = isLoop; // isLoop 초기화
			// 음악 파일을 inputstream 에 넣어서 가져옴
			file = new File ("music/" + name);
			fis = new FileInputStream(file);
			bis = new BufferedInputStream(fis);
			player = new Player(bis);
		} catch (Exception e) {
			System.out.println(e.getMessage());
		}
	}
	
	public int getTime() {
		// 실행되는 음악의 시간 위치 => 3분짜리 음악에 10초 재생중
        // 이것은 나중에 음악에 맞춰 노트를 떨어뜨릴때 getTime을 통해 분석
		if (player == null)
			return 0;
		return player.getPosition();
	}
	
	public void close() { 
		isLoop = false;
		player.close();
		// 해당 스레드를 종료상태로 만듦 => 게임을 실행하는것과 별도로 곡 재생을 하는 스레드가 존재하는데
        // 곡 재생 스레드를 종료해줌
		this.interrupt();
	}
	
	@Override
	public void run() {
		try {
			do {
				player.play(); // 최종적으로 player 인스턴스는 버퍼에 담긴 음악 파일을 play
				//fis = new FileInputStream(file);
				//bis = new BufferedInputStream(fis);
				//player = new Player(bis);
			} while (isLoop == true); // isLoop 가 true 인 동안 음악 반복 재생
		} catch (Exception e) {
			System.out.println(e.getMessage());
		}
	}
	
	public boolean isComplete() {
        return player != null && player.isComplete();
    }
}
