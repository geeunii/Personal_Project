package dynamic_beat_final;

import javax.swing.SwingUtilities;

public class Main {

	public static final int SCREEN_WIDTH = 1280;
	public static final int SCREEN_HEIGHT = 720;
	// 떨어지는 노트 속도
	public static final int NOTE_SPEED = 5;
	// 노트가 떨어지는 시간 주기
	public static final int SLEEP_TIME = 10;
	// 노트가 생성 된 후 판정바에 도달하기까지 시간
	public static final int REACH_TIME = 2;
	
	public static void main(String[] args) {
		
        // Swing 프로그램은 Event Dispatch Thread에서 실행하는 것이 원칙입니다.
        SwingUtilities.invokeLater(() -> {
            DynamicBeat dynamicBeat = new DynamicBeat();
            dynamicBeat.setVisible(true);
        });
		
	}

}
