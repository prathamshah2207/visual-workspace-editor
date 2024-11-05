package org.example.assignment3.view;

import javafx.scene.paint.Color;

public class MiniView extends DetailView{
    
    private double miniSz = 200;

    public MiniView(){
        myCan.setWidth(miniSz);
        myCan.setHeight(miniSz);
    }

    public void draw(){
        gc.clearRect(0, 0, miniSz, miniSz);
        gc.setFill(Color.LIGHTGRAY);
        gc.fillRect(0, 0, miniSz, miniSz);

        gc.setStroke(Color.GREEN);
        gc.setLineWidth(1);
        gc.strokeRect(model.getViewLeft() * (miniSz / world), model.getViewTop() * (miniSz / world), model.getViewWidth() * (miniSz / world), model.getViewHeight() * (miniSz / world));


        gc.setStroke(Color.BLACK);
        gc.setLineWidth(1);
        gc.strokeRect(0, 0, miniSz, miniSz);
        
        model.getObjects().forEach(minibox -> {
            if (imodel.getSelected() == minibox) {
                gc.setFill(Color.ORANGE);
            }
            else {
                gc.setFill(Color.BLUE);
            }

            double relativeX = minibox.getX()* miniSz / world;
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
