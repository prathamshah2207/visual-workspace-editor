package org.example.assignment3.view;

import javafx.scene.paint.Color;

public class MiniView extends DetailView{
    
    private double miniSz = 200;

    public MiniView(){
        myCan.setWidth(world);
        myCan.setHeight(world);
    }

    public void draw(){
        gc.clearRect(model.getViewLeft(), model.getViewTop(), model.getViewLeft()+ miniSz, model.getViewTop() + miniSz);
        gc.setFill(Color.LIGHTGRAY);
        gc.fillRect(model.getViewLeft(), model.getViewTop(), model.getViewLeft()+ miniSz, model.getViewTop() + miniSz);
        gc.setStroke(Color.BLACK);
        gc.setLineWidth(1);
        gc.strokeRect(model.getViewLeft(), model.getViewTop(), model.getViewLeft()+ miniSz, model.getViewTop() + miniSz);
        
        model.getObjects().forEach(minibox -> {
            if (imodel.getSelected() == minibox) {
                gc.setFill(Color.ORANGE);
            }
            else {
                gc.setFill(Color.BLUE);
            }

            double relativeX = minibox.getX()*miniSz / world;
            double relativeY = minibox.getY()*miniSz / world;
            double relativeWidth = minibox.getWidth()*miniSz / world;
            double relativeHeight = minibox.getHeight()*miniSz / world;

            gc.fillRect(relativeX, relativeY, relativeWidth, relativeHeight);
            gc.setStroke(Color.BLACK);
            gc.setLineWidth(0.5);
            gc.strokeRect(relativeX, relativeY, relativeWidth, relativeHeight);
        });
        
    }
}
