package org.example.assignment3.model;

public class InteractionModel {
    private Box slct;

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
