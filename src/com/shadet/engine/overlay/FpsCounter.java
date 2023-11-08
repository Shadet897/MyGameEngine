package com.shadet.engine.overlay;

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

    public int getFPS() {
        return fps;
    }
}
