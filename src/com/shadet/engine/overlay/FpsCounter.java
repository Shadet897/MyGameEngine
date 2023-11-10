package com.shadet.engine.overlay;

import com.shadet.engine.container.Container;
import com.shadet.engine.gfx.Font;

public class FpsCounter {
    private int frames;
    private int fps;
    private double timePassed;

    public FpsCounter() {
        frames = 0;
        fps = 0;
        timePassed = 0;
    }

    public void update(float dt) {
        timePassed += dt;
        frames++;

        if (timePassed >= 1.0) {
            fps = frames;
            frames = 0;
            timePassed = 0;
        }
    }

    public void drawFpsCounter(Container c, int xPos, int yPos, Font font, String text, int color){
        c.getRenderer().drawText(text + fps, xPos, yPos, color, font);
    }

    public int getFPS() {
        return fps;
    }
}
