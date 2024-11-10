package org.example.assignment3.model;

public class InteractionModel {
    private object slct;
    public double edge = 4.6;

    public InteractionModel() {
        slct = null;
    }
    public void setSelected(object ob) {
        slct = ob;
    }
    public object getSelected() {
        return slct;
    }
}
