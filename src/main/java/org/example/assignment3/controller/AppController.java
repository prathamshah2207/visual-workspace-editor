package org.example.assignment3.controller;

import javafx.scene.input.MouseEvent;
import org.example.assignment3.model.EntityModel;
import org.example.assignment3.model.InteractionModel;

public class AppController {

    private enum State {READY, MOVING, PREPARE_CREATE}
    private State currentState;
    private double x, y, dX, dY;
    private EntityModel model;
    private InteractionModel imodel;

    public AppController() {
        currentState = State.READY;
    }

    public void setModel(EntityModel mdl) {
        model = mdl;
    }
    public void setIModel(InteractionModel imdl) {
        imodel = imdl;
    }

    public void handlePressed(MouseEvent event) {
        switch (currentState) {
            case READY:
                x = event.getX();
                y = event.getY();

                if (model.contains(x, y)) {
                    imodel.setSelected(model.whichObject(x, y));
                    currentState = State.MOVING;
                }
                else {
                    imodel.setSelected(null);
                    imodel.setSelected(model.makeObject(x, y));
                    model.addObject(imodel.getSelected());

                    currentState = State.PREPARE_CREATE;
                }
        }
        System.out.println("Mouse Pressed");
    }

    public void handleReleased(MouseEvent event) {
        switch (currentState) {
            case PREPARE_CREATE:
                model.addObject(imodel.getSelected());
                currentState = State.READY;
            case MOVING:
                currentState = State.READY;
        }
        System.out.println("Mouse Released");
    }

    public void handleDrag(MouseEvent event) {
        System.out.println(currentState);
        switch (currentState) {
            case PREPARE_CREATE:

                double dWitdh = Math.abs(event.getX() - x);
                double dHeight = Math.abs(event.getY() - y);
                imodel.getSelected().setDims(dWitdh, dHeight);
                model.notifySubscribers();
                break;

            case MOVING:
                dX = event.getX() - x;
                dY = event.getY() - y;
                x = event.getX();
                y = event.getY();
                model.moveObject(imodel.getSelected(), dX, dY);
                model.notifySubscribers();
                break;
        }
//        System.out.println("Mouse Drag");
    }
}
