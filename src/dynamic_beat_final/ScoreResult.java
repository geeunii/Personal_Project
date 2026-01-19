package dynamic_beat_final;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics2D;
import java.awt.Image;

import javax.swing.ImageIcon;

public class ScoreResult extends Thread {
    private Image scoreResult = new ImageIcon("images/scoreResult.png").getImage();

    // Graphics2D를 인스턴스 변수로 사용하지 않음
    // Graphics2D g;

    // 생성자에서 Graphics2D를 받아와서 인스턴스 변수에 저장
    private Graphics2D g;

    public ScoreResult(Graphics2D g) {
        this.g = g;
    }

    public void screenDraw() {
        String grade = null;

        int totalScore = Game.score;
        int totalCombo = Game.combo;

        if (totalScore > (300 * 100 * 0.9)) {
            grade = "S";
        } else if (totalScore > (300 * 100 * 0.6)) {
            grade = "A";
        } else if (totalScore > (300 * 100 * 0.4)) {
            grade = "B";
        } else if (totalScore >= 0) {
            grade = "C";
        }
        g.drawImage(scoreResult, 240, 70, null);

        g.setFont(new Font("Arial", Font.BOLD, 100));
        g.setColor(Color.white);
        g.drawString("Score : " + String.valueOf(totalScore), 350, 290);
        g.drawString("Combo : " + String.valueOf(totalCombo), 350, 400);

        g.setColor(Color.pink);
        g.drawString(grade, 600, 500);
    }

    @Override
    public void run() {
        screenDraw();
    }

    public void close() {
        interrupt();
    }
}
