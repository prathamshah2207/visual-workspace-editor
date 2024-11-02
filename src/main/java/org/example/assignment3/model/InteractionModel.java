package org.example.assignment3.model;

public class InteractionModel {
    Box selected;

    public InteractionModel() {
        selected = null;
    }
    public void setSelected(Box bx) {
        selected = bx;
    }
    public Box getSelected() {
        return selected;
    }
}
