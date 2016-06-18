package com.tutorial.main;

import java.awt.*;

/**
 * Created by narhwal on 6/10/2016.
 */
public class HUD {
    public static int HEALTH = 100;
    private int score = 0;
    private int level = 1;
    private int greenValue = 255;

    public int getScore() {
        return score;
    }

    public void setScore(int score) {
        this.score = score;
    }

    public int getLevel() {
        return level;
    }

    public void setLevel(int level) {
        this.level = level;
    }

    public void tick() {

    }

    public void render(Graphics g) {
       /* g.setColor(Color.gray);
        g.fillRect(15, 15, 200, 32);
        g.setColor(new Color(75, greenValue, 0));
        g.fillRect(15, 15, HEALTH * 2, 32);
        g.setColor(Color.white);
        g.drawRect(15, 15, 200, 32);
*/
        g.setColor(Color.gray);
        g.fillRect(15, Game.HEIGHT - 50, 150, 16);
        g.fillRect(170, Game.HEIGHT - 50, 100, 16);
        g.setColor(Color.white);
        g.drawString("Score:" + score, 18, Game.HEIGHT - 36);
        g.drawString("Level:" + level, 175, Game.HEIGHT - 36);
    }
}
