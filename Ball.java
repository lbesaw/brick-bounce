package com.tutorial.main;

import java.awt.*;

/**
 * Created by narhwal on 6/13/2016.
 */
public class Ball extends GameObject {
    Handler handler;

    public Ball(int x, int y, ID id, Handler handler) {
        super(x, y, id);
        this.handler = handler;
        velX = 5;
        velY = 5;
    }

    @Override
    public Rectangle getBounds() {
        return new Rectangle(x, y, 4, 4);
    }

    @Override
    public void tick() {
        x += velX;
        y += velY;

        if (y <= 0 || y >= Game.HEIGHT - 48) velY *= -1;
        if (x <= 0 || x >= Game.WIDTH - 16) velX *= -1;
        handler.addObject(new Trail(x, y, ID.Trail, Color.red, 16, 16, 0.05f, handler));
    }

    @Override
    public void render(Graphics g) {
        g.fillOval(x, y, 16, 16);
    }
}
