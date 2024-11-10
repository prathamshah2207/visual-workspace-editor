package org.example.assignment3.model;

public class Box implements object{
    double x, y, hieght, width;

    public Box(double newX, double newY) {
        x = newX;
        y = newY;
        width = 0;
        hieght = 0;
    }

    public double getX() {
        return x;
    }

    public double getY() {
        return y;
    }

    public void setCoords(double x, double y) {
        this.x = x;
        this.y = y;
    }

    public double getWidth() {
        return width;
    }

    public double getHeight() {
        return hieght;
    }

    public void setDims(double width, double height) {
        this.width = width;
        this.hieght = height;
    }


    public boolean contains(double mX, double mY) {
        return mX >= x && mX <= x + width && mY >= y && mY <= y + hieght;
    }

    public void move(double deltaX, double deltaY) {
        x += deltaX;
        y += deltaY;
    }
    public String type(){
        return "Box";
    }
}