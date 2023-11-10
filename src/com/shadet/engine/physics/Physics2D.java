package com.shadet.engine.physics;

import java.util.ArrayList;

public class Physics2D {
    private ArrayList<Body> bodies;
    private double xMomentumMax, yMomentumMax, xMomentumChange, yMomentumChange;
    private float timePassed;

    public Physics2D(double xMomentumMax, double yMomentumMax, double xMomentumChange, double yMomentumChange){
        this.xMomentumMax = xMomentumMax;
        this.yMomentumMax = yMomentumMax;
        this.xMomentumChange = xMomentumChange;
        this.yMomentumChange = yMomentumChange;
        timePassed = 0;
        bodies = new ArrayList<>();
    }

    public void updatePhysics2D(){

        updateColision();
    }


    public void updateColision(){
        /*
        for (Body body : bodies){
            for (Body body1 : bodies){
                for (int x1 = 0; x1 < body.getWidth(); x1++) {
                    for (int y1 = 0; y1 < body.getHeight(); y1++) {

                    }
                }
            }
        }

         */
    }

    public void updateGravity(float dt){
        timePassed += dt;

        for (Body body : bodies) {
            if (timePassed >= 1.0){
            body.setyMomentum(body.getyMomentum() + yMomentumChange);
            }
            if (body.isAffected()) {
                if (yMomentumChange < yMomentumMax) {
                    body.setPosY((int) ((body.getPosY()) + body.getyMomentum()));
                }
                else if (body.getyMomentum() >= yMomentumMax) {
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
