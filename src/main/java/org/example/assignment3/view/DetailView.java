package org.example.assignment3.view;

import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import org.example.assignment3.model.InteractionModel;
import org.example.assignment3.controller.AppController;
import org.example.assignment3.model.EntityModel;
import org.example.assignment3.model.Portal;
import org.example.assignment3.model.Subscriber;

import java.util.ArrayList;

public class DetailView extends Pane implements Subscriber {

    Canvas myCan;
    GraphicsContext gc;
    EntityModel model;
    InteractionModel imodel;
    double world = 2000;

    public DetailView() {
        myCan = new Canvas(world, world);
        gc = myCan.getGraphicsContext2D();
        getChildren().add(myCan);

        setPrefSize(world/3, world/3);
        setFocusTraversable(true);
        widthProperty().addListener(e -> draw());
        heightProperty().addListener(e -> draw());
    }

    public void setModel(EntityModel mdl) {
        model = mdl;
    }
    public void setIModel(InteractionModel imdl) {
        imodel = imdl;
    }
    public void draw() {
        gc.clearRect(0, 0, getWidth(), getHeight());

        model.getObjects().forEach(bx -> {

            double X = bx.getX() - model.getViewLeft();
            double Y = bx.getY() - model.getViewTop();
            gc.setLineWidth(2);

            if (bx.type().equals("Box")) {
                if (imodel.getSelected() == bx) {

                    gc.setFill(Color.ORANGE);
                } else {
                    gc.setFill(Color.BLUE);
                }
                gc.fillRect(X, Y, bx.getWidth(), bx.getHeight());
                gc.setStroke(Color.BLACK);
                gc.strokeRect(X, Y, bx.getWidth(), bx.getHeight());

            } else if (bx.type().equals("Portal")) {
                gc.setFill(Color.LIGHTGRAY);
                gc.fillRect(X, Y, bx.getWidth(), bx.getHeight());
                gc.setStroke(Color.BLACK);
                gc.strokeRect(X, Y, bx.getWidth(), bx.getHeight());

                portalDrawing(X, Y, bx.getWidth(), bx.getHeight(), bx.getScaleFactor(), bx.getSightAtX(), bx.getSightAtY(), 1);
            }

            //the edge circles creation is here with a proper border too

            if (bx == imodel.getSelected()) {
                cornerDrawing(X, Y, bx.getWidth(), bx.getHeight());
            }
        });

        model.setViewHeight(getHeight());
        model.setViewWidth(getWidth());
    }

    private void cornerDrawing(double X, double Y, double width, double height) {
        gc.setFill(Color.WHITE);
        gc.fillOval(X - imodel.edge, Y - imodel.edge, imodel.edge * 2, imodel.edge * 2);
        gc.fillOval(X - imodel.edge + width, Y - imodel.edge + height, imodel.edge * 2, imodel.edge * 2);
        gc.fillOval(X - imodel.edge, Y - imodel.edge + height, imodel.edge * 2, imodel.edge * 2);
        gc.fillOval(X - imodel.edge + width, Y - imodel.edge, imodel.edge * 2, imodel.edge * 2);

        gc.setStroke(Color.BLACK);
        gc.strokeOval(X - imodel.edge, Y - imodel.edge, imodel.edge * 2, imodel.edge * 2);
        gc.strokeOval(X - imodel.edge + width, Y - imodel.edge + height, imodel.edge * 2, imodel.edge * 2);
        gc.strokeOval(X - imodel.edge, Y - imodel.edge + height, imodel.edge * 2, imodel.edge * 2);
        gc.strokeOval(X - imodel.edge + width, Y - imodel.edge, imodel.edge * 2, imodel.edge * 2);
    }

    private void portalDrawing(double x, double y, double width, double height, double sclFctr, double Xsight, double Ysight, int level){
        gc.save();

        gc.translate(x, y);
        gc.scale(sclFctr,sclFctr);
        gc.translate(-Xsight, -Ysight);


        model.getObjects().forEach(inbx -> {

            double innX = inbx.getX()- Xsight;
            double innY =inbx.getY()-Ysight;

            if (inbx.type()=="Box"){
                if (imodel.getSelected() == inbx) {

                    gc.setFill(Color.ORANGE);
                } else {
                    gc.setFill(Color.BLUE);
                }
                gc.fillRect(innX, innY, Math.min(inbx.getWidth(),((width/sclFctr)-inbx.getX())), Math.min(inbx.getHeight(), ((height/sclFctr)-inbx.getY())));
                gc.strokeRect(innX, innY, Math.min(inbx.getWidth(),((width/sclFctr)-inbx.getX())), Math.min(inbx.getHeight(), ((height/sclFctr)-inbx.getY())));

            } else if (inbx.type() == "Portal") {
                gc.setFill(Color.LIGHTGRAY);
                gc.fillRect(innX, innY, Math.min(inbx.getWidth(),((width/sclFctr)-inbx.getX())), Math.min(inbx.getHeight(), ((height/sclFctr)-inbx.getY())));
                gc.strokeRect(innX, innY, Math.min(inbx.getWidth(),((width/sclFctr)-inbx.getX())), Math.min(inbx.getHeight(), ((height/sclFctr)-inbx.getY())));
                if (level < 2)
                    portalDrawing(x, y, inbx.getWidth(), inbx.getHeight(), inbx.getScaleFactor(), inbx.getSightAtX(), inbx.getSightAtY(), level + 1);
            }
        });
        gc.restore();
    }

    public void modelChanged() {
        draw();
    }

    public void setupEvents(AppController ctrlr) {
        setOnMousePressed(ctrlr::handlePressed);
        setOnMouseReleased(ctrlr::handleReleased);
        setOnMouseDragged(ctrlr::handleDrag);
        setOnKeyPressed(ctrlr::handleKeyPress);
    }
}
