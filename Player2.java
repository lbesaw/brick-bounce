package com.tutorial.main;

import java.awt.*;


public class Player2 extends GameObject {

    Handler handler;

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
                    if (tempObject.getVelX() < 0) tempObject.setVelX(tempObject.getVelX() * -1);
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
        x = Game.clamp(x, 0, Game.WIDTH - 38);
        y = Game.clamp(y, 0, Game.HEIGHT - 60);
        collision();
    }

    public void render(Graphics g) {
        g.setColor(Color.white);
        g.fillRect(x, y, 48, 16);
    }
}
