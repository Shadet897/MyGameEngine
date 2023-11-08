package com.shadet.engine.game.gameLauncher;

import com.shadet.engine.container.Container;
import com.shadet.engine.game.GameManager;
import com.shadet.engine.renderer.Renderer;

public class GameLauncher {
    public static void main(String[] args) {
        Container c = new Container(new GameManager());

        c.setTitle("baseShadetEngine v0.0.1");
        c.start();
    }
}
