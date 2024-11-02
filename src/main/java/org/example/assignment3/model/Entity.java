package org.example.assignment3.model;

public class Entity {
    double x, y, r;

    public Entity(double newX, double newY) {
        x = newX;
        y = newY;
        r = 10;
    }

    public double getX() {
        return x;
    }
    public double getY() {
        return y;
    }
    public double getR() {
        return r;
    }
    public boolean contains(double mX, double mY) {
        return Math.hypot(x - mX, y - mY) <= r;
    }
    public void move(double deltaX, double deltaY) {
        x += deltaX;
        y += deltaY;
    }
}
