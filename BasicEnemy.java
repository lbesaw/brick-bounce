package com.tutorial.main;

import java.awt.*;
import java.util.Random;

/**
 * Created by narhwal on 6/10/2016.
 */
public class BasicEnemy extends GameObject {

    private Handler handler;
    private Random r = new Random();

    public BasicEnemy(int x, int y, ID id, Handler handler) {
        super(x, y, id);
        this.handler = handler;
        velX = r.nextInt(8) + 1;
        velY = r.nextInt(7) + 1;
    }

    public Rectangle getBounds() {
        return new Rectangle(x, y, 16, 16);
    }

    public void tick() {
        x += velX;
        y += velY;

        if (y <= 0 || y >= Game.HEIGHT - 48) velY *= -1;
        if (x <= 0 || x >= Game.WIDTH - 16) velX *= -1;
        handler.addObject(new Trail(x, y, ID.Trail, Color.red, 16, 16, 0.05f, handler));
    }

    public void render(Graphics g) {
        g.setColor(Color.red);
        g.fillRect(x, y, 16, 16);
    }
}
