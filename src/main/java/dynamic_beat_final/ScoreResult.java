package dynamic_beat_final;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics2D;
import java.awt.Image;

import javax.swing.ImageIcon;

public class ScoreResult {
    private Image scoreResultImage = new ImageIcon(Main.class.getResource("/images/scoreResult.png")).getImage();
    private Game game;

    public ScoreResult(Game game) {
        this.game = game;
    }

    public void draw(Graphics2D g) {
        String grade = null;

        int totalScore = game.getScore();
        int totalCombo = game.getCombo();

        if (totalScore > (300 * 100 * 0.9)) {
            grade = "S";
        } else if (totalScore > (300 * 100 * 0.6)) {
            grade = "A";
        } else if (totalScore > (300 * 100 * 0.4)) {
            grade = "B";
        } else if (totalScore >= 0) {
            grade = "C";
        }
        g.drawImage(scoreResultImage, 240, 70, null);

        g.setFont(new Font("Arial", Font.BOLD, 100));
        g.setColor(Color.white);
        g.drawString("Score : " + String.valueOf(totalScore), 350, 290);
        g.drawString("Combo : " + String.valueOf(totalCombo), 350, 400);

        g.setColor(Color.pink);
        g.drawString(grade, 600, 500);
    }
}
