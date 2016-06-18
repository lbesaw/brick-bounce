package com.tutorial.main;

import java.awt.*;
import java.util.Random;


public class Player2 extends GameObject {

    Handler handler;
    Random r = new Random();
    public Player2(int x, int y, ID id, Handler handler) {
        super(x, y, id);
        this.handler = handler;
    }

    public Rectangle getBounds() {
        return new Rectangle(x, y, 48, 16);
    }

    private void collision() {
        for (int i = 0; i < handler.object.size(); i++) {
            GameObject tempObject = handler.object.get(i);

            if (tempObject.getId() == ID.Ball) {
                if (getBounds().intersects(tempObject.getBounds())) {
                    int modifier;
                    int temp = r.nextInt(100);
                    if (temp <= 33) {
                        modifier = -1;
                    } else if (temp > 33 && temp <= 66) {
                        modifier = 1;
                    } else if (temp > 66) {
                        modifier = 0;
                    } else modifier = 0;
                    if (tempObject.getVelX() < 0) tempObject.setVelX((tempObject.getVelX() + modifier) * -1);
                    //tempObject.setVelX(tempObject.getVelX()*-1);
                    tempObject.setVelY(tempObject.getVelY() * -1);
                }
            }
            if (tempObject.getId() == ID.BasicEnemy) {
                if (getBounds().intersects(tempObject.getBounds())) {
//                    tempObject.setVelX(tempObject.getVelX()*-1);
//                    tempObject.setVelY(tempObject.getVelY()*-1);
                    HUD.HEALTH -= 2;
                }
            }
        }
    }

    public void tick() {
        x += velX;

        y += velY;
        x = Game.clamp(x, 48, Game.WIDTH - 48);
        y = Game.clamp(y, 0, Game.HEIGHT - 60);
        collision();
    }

    public void render(Graphics g) {

    }
}
