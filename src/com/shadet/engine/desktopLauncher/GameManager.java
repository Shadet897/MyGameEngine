package com.shadet.engine.desktopLauncher;

import com.shadet.engine.abstractGame.AbstractGame;
import com.shadet.engine.container.Container;
import com.shadet.engine.gfx.Image;
import com.shadet.engine.renderer.Renderer;



public class GameManager extends AbstractGame {

    private Image image;
    private Renderer renderer;

    public GameManager(){
        image = new Image("/baseShadetEngine.png");
    }

    @Override
    public void update(Container co, float dt) {

    }

    @Override
    public void render(Container co, Renderer renderer) {
        renderer.clear();
        renderer.drawImage(image, co.getInput().getMouseX(), co.getInput().getMouseY());
    }
}