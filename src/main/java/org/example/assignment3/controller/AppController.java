package org.example.assignment3.controller;

import javafx.scene.input.*;
import org.example.assignment3.model.EntityModel;
import org.example.assignment3.model.InteractionModel;

public class AppController {

    private enum State {READY, MOVING, PREPARE_CREATE, RESIZING, PANNING, PORTALING, PORTAL_CREATE, IN_PORTAL_MOVING}
    private State currentState;
    private double x, y, dX, dY, refX, refY;
    private EntityModel model;
    private InteractionModel imodel;
    boolean pan = false;
    //edgemodes will be the edge the mouse will click onto
    int edgeMode = 0;

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

    public boolean isClickOnEdge(double mX, double mY, double xBox, double yBoc) {
        double dist = Math.sqrt(((mX-xBox)*(mX-xBox)) + ((mY-yBoc)*(mY-yBoc)));
        if (dist <= imodel.edge)
            return true;
        else
            return false;
    }



    public void handlePressed(MouseEvent event) {

        x = event.getX();
        y = event.getY();

        if (event.isShiftDown()) {
            currentState = State.PANNING;
            pan = true;
        } else if (event.isControlDown()) {
            currentState = State.PORTALING;
            System.out.println("portaling");
            x +=model.getViewLeft();
            y +=model.getViewTop();
            pan = false;
        } else if (clickInMini(x, y) == true) {
            y *= 10;
            x *= 10;
            pan = false;
        } else {
            y += model.getViewTop();
            x += model.getViewLeft();
            pan = false;
        }

        switch (currentState) {

            case READY:

                if (imodel.getSelected() != null) {

                    double xBox = imodel.getSelected().getX();
                    double yBox = imodel.getSelected().getY();
                    double wBox = imodel.getSelected().getWidth();
                    double hBoc = imodel.getSelected().getHeight();

                    //top-left
                    if (isClickOnEdge(x, y, xBox, yBox)) {
                        refX = xBox + wBox;
                        refY = yBox + hBoc;
                        edgeMode = 1;
                    }
                    //top-right
                    else if (isClickOnEdge(x, y, xBox + wBox, yBox)) {
                        refX = xBox;
                        refY = yBox + hBoc;
                        edgeMode = 2;
                    }
                    //bottom-left
                    else if (isClickOnEdge(x, y, xBox, yBox + hBoc)) {
                        refX = xBox + wBox;
                        refY = yBox;
                        edgeMode = 3;
                    }
                    //bottomright
                    else if (isClickOnEdge(x, y, xBox + wBox, yBox + hBoc)) {
                        refX = xBox;
                        refY = yBox;
                        edgeMode = 4;
                    } else {
                        refX = 0;
                        refY = 0;
                        edgeMode = 0;
                    }
                }

                if (edgeMode != 0) {
                    currentState = State.RESIZING;
                }
                // this will get triggered when click is on an existing box to move it
                else if (model.contains(x, y)) {
                    imodel.setSelected(model.whichObject(x, y));
                    model.notifySubscribers();
                    currentState = State.MOVING;
                }
                // this wil start creating a new box
                else {
                    imodel.setSelected(null);
                    imodel.setSelected(model.makeObject(x, y));
                    model.addObject(imodel.getSelected());
                    model.notifySubscribers();

                    currentState = State.PREPARE_CREATE;
                }
                break;

            case PORTALING:
                if(model.contains(x,y)) {
                    imodel.setSelected(model.whichObject(x, y));
                    model.notifySubscribers();
                    currentState = State.IN_PORTAL_MOVING;
                }else {
                    imodel.setSelected(null);
                    imodel.setSelected(model.makePortal(x, y));
                    model.addObject(imodel.getSelected());
                    model.notifySubscribers();
                    currentState = State.PORTAL_CREATE;
                }
                break;
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

        edgeMode=0;

        switch (currentState) {
            case PREPARE_CREATE:
                currentState = State.READY;
            case MOVING:
                currentState = State.READY;
            case PANNING:
                currentState = State.READY;
            case RESIZING:
                currentState = State.READY;
            case PORTAL_CREATE:
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
                //will create a box while updating its width and height so the box will grow or shrink as per mouse dragging
                double dWitdh, dHeight, nX, nY;

                //made this for making boxes in all directions
                if (eX> x) {
                    dWitdh = eX - x;
                    nX = x;
                }
                else {
                    dWitdh = x - eX;
                    nX = eX;
                }
                if (eY > y) {
                    dHeight = eY - y;
                    nY = y;
                }
                else {
                    dHeight = y - eY;
                    nY = eY;
                }

                imodel.getSelected().setDims(dWitdh, dHeight);
                imodel.getSelected().setCoords(nX, nY);
                model.notifySubscribers();
                break;

            case PORTAL_CREATE:
                System.out.println("creating portl");
                double pWidth, pHeight, pX, pY;
                //this will make portals in all directions
                if (eX> x) {
                    pWidth = eX - x;
                    pX = x;
                }
                else {
                    pWidth = x - eX;
                    pX = eX;
                }
                if (eY > y) {
                    pHeight = eY - y;
                    pY = y;
                }
                else {
                    pHeight = y - eY;
                    pY = eY;
                }

                imodel.getSelected().setDims(pWidth, pHeight);
                imodel.getSelected().setCoords(pX, pY);
                model.notifySubscribers();
                break;

            case MOVING:
                //simply will move the box with the cursors position
                dX = eX - x;
                dY = eY - y;
                x = eX;
                y = eY;
                model.moveObject(imodel.getSelected(), dX, dY);
                model.notifySubscribers();
                break;

            case IN_PORTAL_MOVING:
                System.out.println("box moving in portal");
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

            case RESIZING:
                double nWidth, nHieght, newX, newY;

                //top-left
                if (edgeMode == 1) {
                    if (eX> refX) {
                        newX = refX;
                        nWidth = eX - refX;
                    }
                    else {
                        newX = eX;
                        nWidth = (refX)-eX;
                    }
                    if (eY> refY) {
                        newY = refY;
                        nHieght = eY- refY;
                    }
                    else {
                        newY = eY;
                        nHieght = refY - eY;
                    }
                }

                //top-right
                else if (edgeMode == 2) {
                    if (eX > refX) {
                        newX = refX;
                        nWidth = eX - refX;
                    }
                    else{
                        newX = eX;
                        nWidth = (refX)-eX;
                    }
                    if (eY>refY) {
                        newY = refY;
                        nHieght = eY - refY;
                    }
                    else {
                        newY = eY;
                        nHieght = refY - eY;
                    }
                }

                //bottom-left
                else if (edgeMode == 3) {
                    if (eX>refX){
                        newX = refX;
                        nWidth = eX - refX;
                    }
                    else{
                        newX = eX;
                        nWidth = (refX)-eX;
                    }
                    if (eY>refY) {
                        newY = refY;
                        nHieght = eY - refY;
                    }
                    else {
                        newY = eY;
                        nHieght = refY - eY;
                    }
                }

                //bottom-right
                else if (edgeMode == 4) {
                    if (eX>refX) {
                        newX = refX;
                        nWidth = eX - refX;
                    }
                    else{
                        newX = eX;
                        nWidth = (refX)-eX;
                    }
                    if (eY>refY) {
                        newY = refY;
                        nHieght = eY - refY;
                    }
                    else{
                        newY = eY;
                        nHieght = refY - eY;
                    }
                }
                else{
                    newX = refX;
                    newY = refY;
                    nHieght = imodel.getSelected().getHeight();
                    nWidth = imodel.getSelected().getWidth();
                }

                imodel.getSelected().setDims(nWidth, nHieght);
                imodel.getSelected().setCoords(newX, newY);
                model.notifySubscribers();
                break;
        }
    }

    public void handleKeyPress(KeyEvent event) {
        if (event.getCode() == KeyCode.DELETE || event.getCode() == KeyCode.BACK_SPACE) {
            if (imodel.getSelected() != null) {
                model.removeObject(imodel.getSelected());
                imodel.setSelected(null);
                model.notifySubscribers();
            }
        }
    }
}
