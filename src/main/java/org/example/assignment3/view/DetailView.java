package org.example.assignment3.view;

import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.layout.StackPane;
import javafx.scene.paint.Color;
import org.example.assignment3.model.InteractionModel;
import org.example.assignment3.controller.AppController;
import org.example.assignment3.model.EntityModel;
import org.example.assignment3.model.Subscriber;

public class DetailView extends StackPane implements Subscriber {
    double hieght, width;
    GraphicsContext gc;
    EntityModel model;
    InteractionModel imodel;

    public DetailView() {
        hieght = 600;
        width = 800;
        Canvas myCan = new Canvas(width, hieght);
        gc = myCan.getGraphicsContext2D();
        getChildren().add(myCan);
    }

    public void setModel(EntityModel mdl) {
        model = mdl;
    }
    public void setIModel(InteractionModel imdl) {
        imodel = imdl;
    }
    public void draw() {
        gc.clearRect(0, 0, width, hieght);

        model.getObjects().forEach(bx -> {
            if (imodel.getSelected() == bx) {
                gc.setFill(Color.ORANGE);
            }
            else {
            gc.setFill(Color.BLUE);
            }
            gc.fillRect(bx.getX(), bx.getY(), bx.getWidth(), bx.getHeight());
        });
    }
    public void modelChanged() {
        draw();
    }

    public void setupEvents(AppController ctrlr) {
        setOnMousePressed(ctrlr::handlePressed);
        setOnMouseReleased(ctrlr::handleReleased);
        setOnMouseDragged(ctrlr::handleDrag);
    }
}
