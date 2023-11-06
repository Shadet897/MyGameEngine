package com.shadet.engine.overlay;

public class FpsCounter {
    private int fps;
    private int frames;
    private double frameTime;

    public void update(double deltaTime) {
        frameTime += deltaTime;
        frames++;

        if (frameTime >= 1.0) {
            frameTime = 0;
            fps = frames;
            frames = 0;
        }
    }

    public int getFPS() {
        return fps;
    }

}
