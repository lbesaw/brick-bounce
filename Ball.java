package com.tutorial.main;

import java.awt.*;

/**
 * Created by narhwal on 6/13/2016.
 */
public class Ball extends GameObject {
    Handler handler;
    private int tempLife = 0;
    public Ball(int x, int y, ID id, Handler handler) {
        super(x, y, id);
        this.handler = handler;
        velX = 12;
        velY = 12;
        if (this.id == ID.TempBall) {
            tempLife = 3000;
        }
    }

    @Override
    public Rectangle getBounds() {
        return new Rectangle(x, y, 16, 16);
    }

    @Override
    public void tick() {
        x += velX;
        y += velY;
        if (this.id == ID.TempBall) {
            tempLife--;
            if (tempLife <= 1) {
                handler.removeObject(this);
            }
        }
        if (y <= 5 || y >= Game.HEIGHT - 50) velY *= -1;
        if (x <= 5 || x >= Game.WIDTH - 20) velX *= -1;
        handler.addObject(new Trail(x, y, ID.Trail, Color.red, 16, 16, 0.05f, handler));
    }

    @Override
    public void render(Graphics g) {
        g.fillOval(x, y, 16, 16);
    }
}
