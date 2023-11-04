package com.shadet.engine.container;

import com.shadet.engine.inputListener.Input;
import com.shadet.engine.window.Renderer;
import com.shadet.engine.window.Window;

public class Container implements Runnable{
    private Thread thread;
    private Window window;
    private Renderer renderer;
    private Input input;

    private boolean render = false;
    private boolean running = false;
    private final double UPDATE_CAP = 1.0/60.0;

    private int width = 320;
    private int height = 240;
    private float scale = 4f;
    private String title = "ShadetEngine v0.0.1";



    public int getWidth() {
        return width;
    }

    public Container(){

    }

    public void start(){
        window = new Window(this);
        renderer = new Renderer(this);
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
        double passedTime = 0;
        double unprocessedTime = 0;

        double frameTime = 0;
        int frames = 0;
        int fps = 0;

        while (running){
            render = false;
            firstTime = System.nanoTime() / 1000000000.0;
            passedTime = firstTime - lastTime;
            lastTime = firstTime;

            unprocessedTime += passedTime;
            frameTime += passedTime;

            while (unprocessedTime >= UPDATE_CAP){
                unprocessedTime -= UPDATE_CAP;
                render = true;

                //TODO: Update game
                System.out.println("mouseX: " + input.getMouseX() + " mouseY: " + input.getMouseY());

                input.update();

                if (frameTime >= 1.0){
                    frameTime = 0;
                    fps = frames;
                    frames = 0;
                    System.out.println("FPS: " + fps);
                }
            }
            if (render){
                renderer.clear();
                //TODO: Render game
                frames++;
                window.update();
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
}
