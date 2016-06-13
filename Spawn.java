package com.tutorial.main;

import java.util.Random;

/**
 * Created by narhwal on 6/12/2016.
 */
public class Spawn {
    private Handler handler;
    private HUD hud;
    private int scoreKeep = 0;
    private Random r = new Random();

    public Spawn(Handler handler, HUD hud) {
        this.handler = handler;
        this.hud = hud;
    }

    public void tick() {

        scoreKeep++;

        if (scoreKeep >= 250) {
            scoreKeep = 0;
            hud.setLevel(hud.getLevel() + 1);
            if (hud.getLevel() % 5 == 0) {
                handler.addObject(new FastEnemy(r.nextInt(Game.HEIGHT - 50), r.nextInt(Game.WIDTH - 50), ID.FastEnemy, handler));
            } else
                handler.addObject(new BasicEnemy(r.nextInt(Game.WIDTH - 50), r.nextInt(Game.HEIGHT - 50), ID.BasicEnemy, handler));
            

        }
    }
}
