package com.tutorial.main;

import java.awt.*;
import java.util.Random;

/**
 * Created by narhwal on 6/19/2016.
 */
public class Level {
    private static Handler handler;
    private static HUD hud;
    private boolean stillBricks = false;
    private int levelNum = 1;
    private static BrickObject[][] tempObject = new BrickObject[24][5];
    private static Level level;
    private Random r = new Random();
    public int getLevel() {
        return levelNum;
    }

    public void setLevel(int levelNum) {
        this.levelNum = levelNum;
    }

    public Level(Handler handler, HUD hud) {
        this.handler = handler;
        this.hud = hud;
    }

    public void setLevelObj(Level level) {
        this.level = level;
    }

    public void startUp() {
        this.loadLevel(1);
    }

    public void tick() {

        if (level != null) {
            stillBricks = false;
            for (int i = 0; i < handler.object.size(); i++) {
                GameObject tempObject = handler.object.get(i);
                if (tempObject.getId() != ID.Player && (tempObject.getId() == ID.BlueBrick || tempObject.getId() == ID.GreenBrick || tempObject.getId() == ID.RedBrick)) {
                    stillBricks = true;
                }
            }
            if (!stillBricks) {
                stillBricks = true;
                levelNum++;
                handler.addObject(new NumFade(Game.WIDTH / 2 - 96, Game.HEIGHT / 2, ID.LevelFade, Color.white, "Level " + levelNum, 0.1f, handler, level));
                loadLevel(levelNum);
                hud.setLevel(levelNum);

            }
        } else
            System.out.println(level);

    }

    public BrickObject getBrick(int n, int i) {
        return tempObject[n][i];
    }

    public ID randomBall() {
        int temp = r.nextInt(100);
        if (temp <= 33) return ID.RedBrick;
        if (temp > 33 && temp <= 66) return ID.GreenBrick;
        if (temp > 66) return ID.BlueBrick;
        return null;
    }
    public void loadLevel(int levelNum) {

        for (int i = 0; i < handler.object.size(); i++) {
            GameObject tempObject = handler.object.get(i);
            if (tempObject.getId() == ID.Ball) {
                tempObject.setVelX(0);
                tempObject.setVelY(0);
                tempObject.setX(Game.WIDTH / 2);
                tempObject.setY(Game.HEIGHT / 2);
                handler.addObject(new NumFade(Game.WIDTH / 2 - 96, Game.HEIGHT / 2 - 40, ID.TextFade, Color.white, "Press n to launch ball", 0.01f, handler, level));
            }
        }
        for (int i = 0; i < 5; i++) {
            for (int n = 0; n < 24; n++) {
                tempObject[n][i] = new BrickObject(n * 22, i * 14, ID.GreenBrick, handler, hud, n, i, level);
                handler.addObject(tempObject[n][i]);
            }
        }
        switch (levelNum) {
            case 1:
                //algorithm for making columns
                for (int i = 0; i < 5; i++) {
                    for (int n = 0; n < 24; n = n + 2) {
                        if (n < 24)
                            handler.removeObject(tempObject[n][i]);
                    }
                }
                break;
            case 2:
                //algorithm removing every other
                for (int i = 0; i < 5; i++) {
                    for (int n = 0; n < 24; n++) {
                        if (i % 2 != 0 && n % 2 == 0) {
                            handler.removeObject(tempObject[n][i]);
                        }
                        if (i % 2 == 0 && n % 2 != 0) {
                            handler.removeObject(tempObject[n][i]);
                        }
                    }
                }
                break;
            case 3:
                //algorithm experimental "castle" shape
                for (int i = 0; i < 5; i++) {
                    for (int n = 0; n < 24; n++) {
                        if (i % 2 != 0 && n % 2 == 0) {
                            handler.removeObject(tempObject[n][i]);
                        }
                        if (i % 3 == 0 && n % 3 != 0) {
                            handler.removeObject(tempObject[n][i]);
                        }
                    }
                }

        }
    }
}
