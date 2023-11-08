package com.shadet.engine.game;

import com.shadet.engine.abstractGame.AbstractGame;
import com.shadet.engine.container.Container;
import com.shadet.engine.gfx.Image;
import com.shadet.engine.physics.Body;
import com.shadet.engine.physics.Physics2D;
import com.shadet.engine.renderer.Renderer;

public class GameManager extends AbstractGame {
    private int playerX, playerY, playerWidth, playerHeight;
    private Image image;
    private Physics2D physics2D;
    private Body playerBody;

    public GameManager(){
        image = new Image("/baseShadetEngine.png");

        playerWidth = image.getWidth();
        playerHeight = image.getHeight();
        playerX = 20;
        playerY = 0;

        physics2D = new Physics2D(0, 2);
        playerBody = new Body(playerX, playerY, playerWidth, playerHeight, true);
        physics2D.addBody(playerBody);


    }

    @Override
    public void update(Container co, float dt) {
        physics2D.updatePhysics2D();
    }

    @Override
    public void render(Container co, Renderer renderer) {
        renderer.clear(0x00000000);
        renderer.drawImage(image, playerBody.getPosX(), playerBody.getPosY());
    }
}