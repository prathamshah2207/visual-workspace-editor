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
        model.getEntities().forEach(ntt -> {
            gc.setFill(Color.BLUE);
            gc.fillOval(ntt.getX() - ntt.getR(), ntt.getY() - ntt.getR(), ntt.getR() *2, ntt.getR() *2);
        });
    }
    public void modelChanged() {
        draw();
    }

    public void setupEvents(AppController ctrlr) {
        setOnMousePressed(ctrlr::handlePressed);
        setOnMouseReleased(ctrlr::handleReleased);
        setOnMouseDragged(ctrlr::handledDragged);
    }
}
