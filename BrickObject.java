package com.tutorial.main;

import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import javax.imageio.*;

/**
 * Created by narhwal on 6/13/2016.
 */
public class BrickObject extends GameObject {
    private static Handler handler;
    private int brickLife = 1;
    BufferedImage bufferedImage;
    private static HUD hud;
    private static Level level;
    private int n;
    private int i;
    private boolean stillBricks = true;

    public BrickObject(int x, int y, ID id, Handler handler, HUD hud, int n, int i, Level level) {
        super(x, y, id);
        this.handler = handler;
        this.hud = hud;
        this.level = level;
        this.n = n;
        this.i = i;
        if (this.getId() == ID.BlueBrick) brickLife = 2;
        else if (this.getId() == ID.RedBrick) brickLife = 1;
        else if (this.getId() == ID.GreenBrick) brickLife = 3;
        else brickLife = 1;
    }

    @Override
    public Rectangle getBounds() {
        return new Rectangle(x, y, 16, 8);
    }

    @Override
    public void tick() {
        if (brickLife <= 0) {
            handler.removeObject(level.getBrick(this.n, this.i));
//            for (int i = 0; i < handler.object.size(); i++) {
//                GameObject tempObject = handler.object.get(i);
//                if(tempObject.getId() != ID.Player && (tempObject.getId() == ID.BlueBrick || tempObject.getId() == ID.GreenBrick || tempObject.getId() == ID.RedBrick)) {
//                    stillBricks = true;
//                }
//                if(!stillBricks) {
//                    level.loadLevel(level.getLevel()+1);
//                    level.setLevel(level.getLevel()+1);
//                }
//            }
        }
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
                    switch (brickLife) {
                        case 1:
                            this.setId(ID.RedBrick);
                            hud.setScore(hud.getScore() + 50);
                            handler.addObject(new NumFade(tempObject.getX(), tempObject.getY(), ID.NumFade, Color.red, "50", 0.02f, handler, level));
                            break;
                        case 2:
                            this.setId(ID.BlueBrick);
                            handler.addObject(new NumFade(tempObject.getX(), tempObject.getY(), ID.NumFade, Color.blue, "25", 0.02f, handler, level));
                            hud.setScore(hud.getScore() + 25);
                            break;
                        case 3:
                            this.setId(ID.GreenBrick);
                            break;
                        default:
                            this.setId(ID.RedBrick);
                            break;
                    }
                }
                }
            }
        }


    @Override
    public void render(Graphics g) {
        Graphics2D g2 = (Graphics2D) g;

        if (this.id == ID.BlueBrick) {
            try {
                bufferedImage = ImageIO.read(new File("src\\com\\tutorial\\main\\Assets\\blueball.png"));
            } catch (IOException e) {

            }

            g2.drawImage(bufferedImage, null, this.getX(), this.getY());


        } else if (this.id == ID.RedBrick) {
            try {
                bufferedImage = ImageIO.read(new File("src\\com\\tutorial\\main\\Assets\\redball.png"));
            } catch (IOException e) {

            }

            g2.drawImage(bufferedImage, null, this.getX(), this.getY());
        } else if (this.id == ID.GreenBrick) {

            try {
                bufferedImage = ImageIO.read(new File("src\\com\\tutorial\\main\\Assets\\greenball.png"));
            } catch (IOException e) {

            }

            g2.drawImage(bufferedImage, null, this.getX(), this.getY());
        } else {
            g.setColor(Color.WHITE);
            g.fillRect(x, y, 16, 8);
        }


    }
}
