package com.shadet.engine.physics;

import java.util.ArrayList;

public class Physics2D {
    private ArrayList<Body> bodies;
    private ArrayList<SolidObject> solidObjects;
    private double xMomentumMax, yMomentumMax, xMomentumChange, yMomentumChange;
    private float timePassed;

    public Physics2D(double xMomentumMax, double yMomentumMax, double xMomentumChange, double yMomentumChange){
        this.xMomentumMax = xMomentumMax;
        this.yMomentumMax = yMomentumMax;
        this.xMomentumChange = xMomentumChange;
        this.yMomentumChange = yMomentumChange;
        timePassed = 0;
        bodies = new ArrayList<>();
        solidObjects = new ArrayList<>();
    }

    public void updateCollisionLimited(){
        for (Body body : bodies){
            for (SolidObject solid : solidObjects){
                for (int x1 = 0; x1 < body.getWidth(); x1++) {
                    for (int y1 = 0; y1 < body.getHeight(); y1++) {
                        for (int x2 = 0; x2 < solid.getWidth(); x2++) {
                            for (int y2 = 0; y2 < solid.getHeight(); y2++) {
                                if (body.getPosX() + x1 == solid.getPosX() + x2 && body.getPosY() + y1 == solid.getPosY() + y2){
                                    body.setPosX(body.getLastPosX());
                                    body.setPosY(body.getLastPosY());
                                    //body1.setPosX(body1.getLastPosX());
                                    //body1.setPosY(body1.getLastPosY());

                                    body.setxMomentum(0.0);
                                    body.setyMomentum(0.0);
                                    //body1.setxMomentum(0.0);
                                    //body1.setyMomentum(0.0);
                                    break;
                                }
                            }
                        }
                    }
                }
            }
        }
    }

    public void updateGravity(float dt){
        timePassed += dt;

        for (Body body : bodies) {
            if (timePassed >= 1.0){
            body.setyMomentum(body.getyMomentum() + yMomentumChange);
            }
            if (body.isAffected()) {
                if (yMomentumChange < yMomentumMax) {
                    body.setLastPosX((body.getPosX()));
                    body.setLastPosY((body.getPosY()));
                    body.setPosY((int) ((body.getPosY()) + body.getyMomentum()));
                }
                else if (body.getyMomentum() >= yMomentumMax) {
                    body.setLastPosY((body.getPosY()));
                    body.setLastPosY((body.getPosX()));
                    body.setPosY((int) (body.getPosY() + yMomentumMax * dt));
                }
            }
        }
    }

    public void changeBodyAffected(Body body, boolean isAffected){
        for (Body body1 : bodies){
            if (body1 == body){
                body.setAffected(isAffected);
                return;
            }
        }
    }

    public void addBody(Body body){
        bodies.add(body);
    }

    public void removeBody(Body body){
        bodies.remove(body);
    }

    public void addSolidObject(SolidObject solid){
        solidObjects.add(solid);
    }

    public void removeSolidObject(SolidObject solid){
        solidObjects.remove(solid);
    }

    public double getxMomentumMax() {
        return xMomentumMax;
    }

    public void setxMomentumMax(int xMomentumMax) {
        this.xMomentumMax = xMomentumMax;
    }

    public double getyMomentumMax() {
        return yMomentumMax;
    }

    public void setyMomentumMax(int yMomentumMax) {
        this.yMomentumMax = yMomentumMax;
    }

    public ArrayList<Body> getBodies() {
        return bodies;
    }
}
