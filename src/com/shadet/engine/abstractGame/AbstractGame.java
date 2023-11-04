package com.shadet.engine.abstractGame;

import com.shadet.engine.container.Container;
import com.shadet.engine.renderer.Renderer;

public abstract class AbstractGame {
    public abstract void update(Container co, float dt);
    public abstract void render(Container co, Renderer renderer);
}
