package com.shadet.engine.physics;

import com.shadet.engine.renderer.Renderer;

import java.util.ArrayList;

public class Physics2D {
    private ArrayList<Body> bodies;
    private double xMomentum, yMomentum;

    public Physics2D(double xMomentum, double yMomentum){
        this.xMomentum = xMomentum;
        this.yMomentum = yMomentum;
        bodies = new ArrayList<>();
    }

    public void updatePhysics2D(){
        updateGravity();
        updateColision();
    }


    public void updateColision(){
        for (Body body : bodies){
            for (Body body1 : bodies){
                for (int x1 = 0; x1 < body.getWidth(); x++) {
                    for (int y1 = 0; y1 < body.getHeight(); y1++) {

                    }
                }
            }
        }
    }

    public void updateGravity(){
        for (Body body : bodies){
            if (body.isAffected()){
                body.setPosY(body.getPosY() + (int) yMomentum);
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

    public double getxMomentum() {
        return xMomentum;
    }

    public void setxMomentum(int xMomentum) {
        this.xMomentum = xMomentum;
    }

    public double getyMomentum() {
        return yMomentum;
    }

    public void setyMomentum(int yMomentum) {
        this.yMomentum = yMomentum;
    }

    public ArrayList<Body> getBodies() {
        return bodies;
    }
}
