package com.shadet.engine.game;

import com.shadet.engine.abstractGame.AbstractGame;
import com.shadet.engine.container.Container;
import com.shadet.engine.gfx.Font;
import com.shadet.engine.gfx.Image;
import com.shadet.engine.overlay.FpsCounter;
import com.shadet.engine.physics.Body;
import com.shadet.engine.physics.Physics2D;
import com.shadet.engine.physics.SolidObject;
import com.shadet.engine.renderer.Renderer;

public class GameManager extends AbstractGame {
    private int playerX, playerY, playerWidth, playerHeight;
    private int solidX, solidY, solidWidth, solidHeight;
    private Image image;
    private Physics2D physics2D;
    private Body playerBody;
    private FpsCounter fpsCounter;
    private Font font;
    private SolidObject solidObject;

    public GameManager(){
        image = new Image("/baseShadetEngine.png");

        font = new Font("/standartFont.png");

        playerWidth = image.getWidth();
        playerHeight = image.getHeight();
        playerX = 20;
        playerY = 0;

        solidX = 20;
        solidY = 200;
        solidWidth = image.getWidth();
        solidHeight = image.getHeight();

        physics2D = new Physics2D(0, 3, 0, 0.3);
        playerBody = new Body(playerX, playerY, playerWidth, playerHeight, true);
        solidObject = new SolidObject(solidX, solidY, solidWidth, solidHeight);
        physics2D.addBody(playerBody);
        physics2D.addSolidObject(solidObject);

        fpsCounter = new FpsCounter();
    }

    @Override
    public void update(Container c, float dt) {
        physics2D.updateGravity(dt);
        physics2D.updateCollisionLimited();
        fpsCounter.update(dt);
    }

    @Override
    public void render(Container c, Renderer renderer) {
        renderer.clear(0x00000000);
        renderer.drawImage(image, playerBody.getPosX(), playerBody.getPosY());
        renderer.drawImage(image, solidObject.getPosX(), solidObject.getPosY());
        fpsCounter.drawFpsCounter(c, 0, 0, font, "FPS: ", 0xff00ffff);
    }
}