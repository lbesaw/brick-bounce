package com.tutorial.main;

import java.awt.*;
import java.awt.image.BufferedImage;
import java.awt.image.ImageObserver;
import java.io.File;
import java.io.IOException;
import javax.imageio.*;

/**
 * Created by narhwal on 6/13/2016.
 */
public class BrickObject extends GameObject {
    Handler handler;
    private int brickLife = 2;
    BufferedImage bufferedImage;


    public BrickObject(int x, int y, ID id, Handler handler) {
        super(x, y, id);
        this.handler = handler;
    }

    @Override
    public Rectangle getBounds() {
        return new Rectangle(x, y, 16, 8);
    }

    @Override
    public void tick() {
        if (brickLife <= 0) handler.removeObject(this);
        collision();
    }

    private void collision() {
        for (int i = 0; i < handler.object.size(); i++) {
            GameObject tempObject = handler.object.get(i);

            if (tempObject.getId() == ID.Ball) {
                if (getBounds().intersects(tempObject.getBounds())) {
                    //tempObject.setVelX(tempObject.getVelX()*-1);
                    tempObject.setVelY(tempObject.getVelY() * -1);
                    brickLife -= 1;
                }
            }
        }
    }

    @Override
    public void render(Graphics g) {
        Graphics2D g2 = (Graphics2D) g;
        if (this.id == ID.BlueBrick) {
            try {
                bufferedImage = ImageIO.read(new File("C:\\Users\\narhwal\\Desktop\\GameTutorial\\src\\com\\tutorial\\main\\Assets\\ball.png"));
            } catch (IOException e) {

            }

            g2.drawImage(bufferedImage, null, this.getX(), this.getY());
        } else if (this.id == ID.RedBrick) {
            g.setColor(Color.RED);
            g.fillRect(x, y, 16, 8);
        } else if (this.id == ID.GreenBrick) {

            g.setColor(Color.GREEN);
            g.fillRect(x, y, 16, 8);
        } else {
            g.setColor(Color.WHITE);
            g.fillRect(x, y, 16, 8);
        }


    }
}
