package org.example.assignment3.model;

public class InteractionModel {
    Entity selected;

    public InteractionModel() {
        selected = null;
    }
    public void setSelected(Entity ntt) {
        selected = ntt;
    }
    public Entity getSelected() {
        return selected;
    }
}
