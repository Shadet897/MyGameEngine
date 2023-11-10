package com.shadet.engine.container;

import com.shadet.engine.abstractGame.AbstractGame;
import com.shadet.engine.inputListener.Input;
import com.shadet.engine.renderer.Renderer;
import com.shadet.engine.window.Window;

public class Container implements Runnable{
    private Thread thread;
    private Window window;
    private Renderer renderer;
    private Input input;
    private AbstractGame game;

    private boolean render = false;
    private boolean running = false;
    private final double UPDATE_CAP = 1.0/60.0;

    private int width = 320;
    private int height = 240;
    private float scale = 3f;
    private String title = "ShadetEngine v0.0.1";

    private double frameTime = 0;
    private int frames = 0;
    private int fps = 0;

    public Container(AbstractGame game){
        this.game = game;
    }
    public int getWidth() {
        return width;
    }

    public void start(){
        window = new Window(this);
        renderer = new Renderer(this, window);
        input = new Input(this);

        thread = new Thread(this);
        thread.run();
    }

    public void end(){

    }

    @Override
    public void run() {
        running = true;

        double firstTime = 0;
        double lastTime = System.nanoTime() / 1000000000.0;
        double deltaTime = 0;
        double unprocessedTime = 0;

        frameTime = 0;
        frames = 0;
        fps = 0;

        while (running){
            render = false;
            firstTime = System.nanoTime() / 1000000000.0;
            deltaTime = firstTime - lastTime;
            lastTime = firstTime;

            unprocessedTime += deltaTime;
            frameTime += deltaTime;

            while (unprocessedTime >= UPDATE_CAP){
                unprocessedTime -= UPDATE_CAP;
                render = true;

                //TODO: Update game
                game.update(this, (float)(UPDATE_CAP));

                input.update();
                if (frameTime >= 1.0){
                    frameTime = 0;
                    fps = frames;
                    frames = 0;
                }
            }
            if (render){
                game.render(this, renderer);
                window.update();
                frames++;
            }
            else {
                try {
                    Thread.sleep(1);
                } catch (InterruptedException e) {
                    System.err.println("");
                }
            }
        }

        dispose();
    }

    public void dispose(){

    }

    public void setWidth(int width) {
        this.width = width;
    }

    public int getHeight() {
        return height;
    }

    public void setHeight(int height) {
        this.height = height;
    }

    public float getScale() {
        return scale;
    }

    public void setScale(float scale) {
        this.scale = scale;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public Window getWindow() {
        return window;
    }

    public Input getInput() {
        return input;
    }

    public int getFps() {
        return fps;
    }

    public Renderer getRenderer() {
        return renderer;
    }
}
