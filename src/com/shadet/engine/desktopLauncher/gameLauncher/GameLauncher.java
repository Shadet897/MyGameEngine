package com.shadet.engine.desktopLauncher.gameLauncher;

import com.shadet.engine.container.Container;
import com.shadet.engine.desktopLauncher.GameManager;

public class GameLauncher {
    public static void main(String[] args) {
        Container c = new Container(new GameManager());
        c.start();
    }
}
