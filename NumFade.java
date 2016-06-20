package com.tutorial.main;

import java.awt.*;

/**
 * Created by narhwal on 6/12/2016.
 */
public class NumFade extends GameObject {


    private float alpha = 1;
    private Handler handler;
    private Color color;
    private float life;
    private String points;
    private Level level;

    public NumFade(int x, int y, ID id, Color color, String points, float life, Handler handler, Level level) {
        super(x, y, id);
        this.handler = handler;
        this.color = color;
        this.points = points;
        this.life = life;
        this.level = level;
    }

    @Override
    public Rectangle getBounds() {
        return null;
    }

    @Override
    public void tick() {
        if (alpha > life) {
            alpha -= life - 0.001f;
        } else {
            handler.removeObject(this);
        }
    }

    @Override
    public void render(Graphics g) {
        Font currentFont = g.getFont();
        Font newFont = g.getFont();
        if (this.getId() == ID.NumFade)
            newFont = new Font("TimesRoman", Font.BOLD, 16);
        else if (this.getId() == ID.LevelFade)
            newFont = new Font("TimesRoman", Font.BOLD, 60);
        else newFont = currentFont;
        g.setFont(newFont);
        Graphics2D g2d = (Graphics2D) g;
        g2d.setComposite(makeTransparent(alpha));
        g.setColor(color);
        double test = alpha * 15;
        int size = (int) test;
        g.drawString(points, x, y);
        g.setFont(currentFont);
        g2d.setComposite(makeTransparent(1));

    }

    private AlphaComposite makeTransparent(float alpha) {
        int type = AlphaComposite.SRC_OVER;
        return (AlphaComposite.getInstance(type, alpha));
    }
}
