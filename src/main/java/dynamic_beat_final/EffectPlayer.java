package dynamic_beat_final;

import java.io.BufferedInputStream;
import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import javazoom.jl.player.Player;

/**
 * 짧은 사운드 효과를 위한 전용 플레이어.
 * 사운드 파일을 미리 메모리에 로드(캐싱)하고, 스레드 풀을 사용하여 재생 반응 속도를 높입니다.
 */
public class EffectPlayer {

    // 사운드 파일 이름을 key로, 오디오 데이터를 byte 배열로 저장하는 Map
    private static Map<String, byte[]> soundCache = new HashMap<>();
    
    // 스레드 생성 비용을 줄이기 위한 스레드 풀 (필요할 때 스레드를 재사용)
    private static final ExecutorService threadPool = Executors.newCachedThreadPool();

    /**
     * 게임 시작 시 필요한 사운드 효과들을 미리 로드합니다.
     */
    public static void init() {
        loadSound("buttonEnteredMusic.mp3");
        loadSound("buttonPressedMusic.mp3");
        loadSound("drumSmall1.mp3");
        loadSound("drumBig1.mp3");
    }

    /**
     * 지정된 사운드 파일을 읽어 byte 배열로 변환한 후 캐시에 저장합니다.
     */
    private static void loadSound(String soundName) {
        try (InputStream is = Main.class.getResourceAsStream("/music/" + soundName);
             BufferedInputStream bis = new BufferedInputStream(is)) {
            
            if (is == null) {
                System.err.println("오류: 사운드 파일을 찾을 수 없습니다 - " + soundName);
                return;
            }
            
            byte[] audioData = bis.readAllBytes();
            soundCache.put(soundName, audioData);

        } catch (IOException e) {
            System.err.println("사운드 파일 로딩 중 오류 발생: " + soundName);
            e.printStackTrace();
        }
    }

    /**
     * 캐시된 사운드를 재생합니다.
     */
    public static void play(String soundName) {
        byte[] audioData = soundCache.get(soundName);
        if (audioData == null) {
            // 로드되지 않은 사운드는 무시 (또는 로그 출력)
            return;
        }

        // 스레드 풀을 사용하여 즉시 재생 작업 실행
        threadPool.execute(() -> {
            try {
                ByteArrayInputStream bais = new ByteArrayInputStream(audioData);
                Player player = new Player(bais);
                player.play();
            } catch (Exception e) {
                e.printStackTrace();
            }
        });
    }
}
