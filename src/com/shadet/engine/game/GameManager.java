package com.shadet.engine.game;

import com.shadet.engine.abstractGame.AbstractGame;
import com.shadet.engine.container.Container;
import com.shadet.engine.renderer.Renderer;

public class GameManager extends AbstractGame {
    public GameManager(){

    }

    @Override
    public void update(Container co, float dt) {
    }

    @Override
    public void render(Container co, Renderer renderer) {
        renderer.clear(0x00000000);
        renderer.drawRect(0, 10, 20, 10, 0xffffaabb);
        renderer.drawFillRect(0, 20, 20, 10, 0xffffffff);
    }
}