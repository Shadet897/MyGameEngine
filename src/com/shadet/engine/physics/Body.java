package com.shadet.engine.physics;

public class Body {
    private int posX, posY, width, height;
    private int lastPosX, lastPosY;
    private boolean isAffected;
    private double yMomentum, xMomentum;
    public Body(int posX, int posY, int width, int height, boolean isAffected){
        this.posX = posX;
        this.posY = posY;
        this.width = width;
        this.height = height;
        this.isAffected = isAffected;
        yMomentum = 0;
        xMomentum = 0;
    }

    public boolean isAffected() {
        return isAffected;
    }

    public void setAffected(boolean affected) {
        isAffected = affected;
    }

    public int getPosX() {
        return posX;
    }

    public int getPosY() {
        return posY;
    }

    public int getWidth() {
        return width;
    }

    public int getHeight() {
        return height;
    }

    public void setPosX(int posX) {
        this.posX = posX;
    }

    public void setPosY(int posY) {
        this.posY = posY;
    }

    public void setWidth(int width) {
        this.width = width;
    }

    public void setHeight(int height) {
        this.height = height;
    }

    public double getyMomentum() {
        return yMomentum;
    }

    public void setyMomentum(double yMomentum) {
        this.yMomentum = yMomentum;
    }

    public double getxMomentum() {
        return xMomentum;
    }

    public void setxMomentum(double xMomentum) {
        this.xMomentum = xMomentum;
    }

    public int getLastPosX() {
        return lastPosX;
    }

    public void setLastPosX(int lastPosX) {
        this.lastPosX = lastPosX;
    }

    public int getLastPosY() {
        return lastPosY;
    }

    public void setLastPosY(int lastPosY) {
        this.lastPosY = lastPosY;
    }
}
