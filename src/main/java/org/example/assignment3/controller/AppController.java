package org.example.assignment3.controller;

import javafx.scene.input.*;
import org.example.assignment3.model.EntityModel;
import org.example.assignment3.model.InteractionModel;

public class AppController {

    private enum State {READY, MOVING, PREPARE_CREATE, PANNING}
    private State currentState;
    private double x, y, dX, dY;
    private EntityModel model;
    private InteractionModel imodel;
    boolean pan = false;

    public AppController() {
        currentState = State.READY;
    }

    public void setModel(EntityModel mdl) {
        model = mdl;
    }
    public void setIModel(InteractionModel imdl) {
        imodel = imdl;
    }


    // this functioon will check if mouse click was on the miniview or not
    public boolean clickInMini(double x, double y) {
        return x >=0 && x<= 210 && y >=0 && y<=210;
    }


    public void handlePressed(MouseEvent event) {

        x = event.getX();
        y=event.getY();

        if (event.isShiftDown()) {
            currentState = State.PANNING;
            pan = true;
        }
        else if (clickInMini(x,y ) == true) {
            y *= 10;
            x *= 10;
            pan=false;
        }
        else {
            y+= model.getViewTop();
            x+= model.getViewLeft();
            pan=false;
        }

        switch (currentState) {

            case READY:
                // this will get triggered when click is on an existing box to move it
                if (model.contains(x,y)) {
                    imodel.setSelected(model.whichObject(x,y));
                    model.notifySubscribers();
                    currentState = State.MOVING;
                }
                // this wil start creating a new box
                else {
                    imodel.setSelected(null);
                    imodel.setSelected(model.makeObject(x,y));
                    model.addObject(imodel.getSelected());
                    model.notifySubscribers();

                    currentState = State.PREPARE_CREATE;
                }
        }
//        System.out.println("Mouse Pressed");
    }

    public void handleReleased(MouseEvent event) {

        double eX = event.getX();
        double eY = event.getY();
        if (clickInMini(eX, eY) == true) {
            eX *= 10;
            eY *= 10;
        }
        switch (currentState) {
            case PREPARE_CREATE:
                currentState = State.READY;
            case MOVING:
                currentState = State.READY;
            case PANNING:
                currentState = State.READY;
        }
//        System.out.println("Mouse Released");
    }

    public void handleDrag(MouseEvent event) {

        double eX = event.getX();
        double eY = event.getY();

        if (pan == false) {
            if (clickInMini(eX, eY) == false) {
                eX += model.getViewLeft();
                eY += model.getViewTop();
            }
            else {

                eX *= 10;
                eY *= 10;
            }
        }

//        System.out.println(currentState);
        switch (currentState) {
            case PREPARE_CREATE:
                // will create a box while updating its width and height so the box will grow or shrink as per mouse dragging
                double dWitdh = eX - x;
                double dHeight = eY - y;
                imodel.getSelected().setDims(dWitdh, dHeight);
                model.notifySubscribers();
                break;

            case MOVING:
                // simply will move the box with the cursors position
                dX = eX - x;
                dY = eY - y;
                x = eX;
                y = eY;
                model.moveObject(imodel.getSelected(), dX, dY);
                model.notifySubscribers();
                break;

            // this will make the canvas move around for looking at different parts of world
            case PANNING:
                dX = (eX) - x;
                dY = eY - y;
                x = eX;
                y = eY;

                model.panView(dX, dY);
                model.notifySubscribers();
                break;
        }
//        System.out.println("Mouse Drag");
    }

    public void handleKeyPress(KeyEvent event) {
//        System.out.println("Delete");
        if (event.getCode() == KeyCode.DELETE || event.getCode() == KeyCode.BACK_SPACE) {
            if (imodel.getSelected() != null) {
                model.removeObject(imodel.getSelected());
                imodel.setSelected(null);
                model.notifySubscribers();
            }
        }
    }
}
