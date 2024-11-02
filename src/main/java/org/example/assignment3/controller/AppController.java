package org.example.assignment3.controller;

import javafx.scene.input.MouseEvent;
import org.example.assignment3.model.EntityModel;
import org.example.assignment3.model.InteractionModel;

public class AppController {

    private enum State {READY, MOVING, PREPARE_CREATE}
    private State currentState = State.READY;
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
        }
        System.out.println("Mouse Pressed");
    }

    public void handleReleased(MouseEvent event) {
        switch (currentState) {
            case PREPARE_CREATE:
            case MOVING:
        }
        System.out.println("Mouse Released");
    }

    public void handleDrag(MouseEvent event) {
        switch (currentState) {
            case PREPARE_CREATE:
            case MOVING:
        }
        System.out.println("Mouse Drag");
    }
}
