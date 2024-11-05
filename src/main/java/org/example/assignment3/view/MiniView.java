package org.example.assignment3.view;

import javafx.scene.paint.Color;

public class MiniView extends DetailView{

    private double worldSz = 2000;
    private double miniSz = 200;

    public MiniView(){
        myCan.setWidth(worldSz);
        myCan.setHeight(worldSz);
    }

    public void draw(){
        gc.clearRect(0, 0, worldSz, worldSz);
        gc.setFill(Color.LIGHTGRAY);
        gc.fillRect(0, 0, miniSz, miniSz);
        
        model.getObjects().forEach(minibox -> {
            if (imodel.getSelected() == minibox) {
                gc.setFill(Color.ORANGE);
            }
            else {
                gc.setFill(Color.BLUE);
            }

            double relativeX = minibox.getX()*miniSz / worldSz;
            double relativeY = minibox.getY()*miniSz / worldSz;
            double relativeWidth = minibox.getWidth()*miniSz / worldSz;
            double relativeHeight = minibox.getHeight()*miniSz / worldSz;

            gc.fillRect(relativeX, relativeY, relativeWidth, relativeHeight);
            gc.setStroke(Color.BLACK);
            gc.setLineWidth(0.5);
            gc.strokeRect(relativeX, relativeY, relativeWidth, relativeHeight);
        });
        
    }
}
