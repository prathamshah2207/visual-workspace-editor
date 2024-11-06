package org.example.assignment3.model;

public class InteractionModel {
    private Box slct;
    public double edge = 4.6;

    public InteractionModel() {
        slct = null;
    }
    public void setSelected(Box bx) {
        slct = bx;
    }
    public Box getSelected() {
        return slct;
    }
}
