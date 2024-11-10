package org.example.assignment3.model;

public class Portal extends Box{

    private double sclFactor, vX, vY;

    public Portal(double nx, double ny) {
        super(nx, ny);
        sclFactor = 0.5;
        vX = 0.0;
        vY = 0.0;
    }

    public void setScaleFactor(double sclFactor) {
        this.sclFactor = sclFactor;
    }
    public double getScaleFactor() {
        return sclFactor;
    }


    public void setSightPosition(double vX, double vY) {
        this.vX = vX;
        this.vY = vY;
    }
    public double getSightAtX() {
        return vX;
    }
    public double getSightAtY() {
        return vY;
    }
    public String type(){
        return "Portal";
    }
}
