package org.example.assignment3.model;

public interface object {
    boolean contains(double x, double y);
    void move(double x, double y);
    double getX();
    double getY();
    double getWidth();
    double getHeight();
    void setDims(double w, double h);
    void setCoords(double x, double y);
    String type();
    double getScaleFactor();
    double getSightAtX();
    double getSightAtY();
}
