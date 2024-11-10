package org.example.assignment3.view;

import javafx.scene.paint.Color;

public class MiniView extends DetailView{
    
    private double miniSz = 200;

    public MiniView(){
        myCan.setWidth(miniSz);
        myCan.setHeight(miniSz);
    }

    public void draw(){
        // the background
        gc.clearRect(0, 0, miniSz, miniSz);
        gc.setFill(Color.GRAY);
        gc.setGlobalAlpha(0.82);
        gc.fillRect(0, 0, miniSz, miniSz);

        //thi is the viewport view in miniview
        gc.setFill(Color.LIMEGREEN);
        gc.fillRect(model.getViewLeft() * (miniSz / world), model.getViewTop() * (miniSz / world), model.getViewWidth() * (miniSz / world), model.getViewHeight() * (miniSz / world));


        // border
        gc.setStroke(Color.BLACK);
        gc.setLineWidth(1);
        gc.strokeRect(0, 0, miniSz, miniSz);

        // each box inide the miniview
        model.getObjects().forEach(minibox -> {

            //this is just for a relative size calculation from the actual canvas
            double relativeX = minibox.getX()* miniSz / world;
            double relativeY = minibox.getY()*miniSz / world;
            double relativeWidth = minibox.getWidth()*miniSz / world;
            double relativeHeight = minibox.getHeight()*miniSz / world;

            if(minibox.type() == "Box"){
                if (imodel.getSelected() == minibox) {
                    gc.setFill(Color.ORANGE);
                }
                else {
                    gc.setFill(Color.BLUE);
                }

            } else if (minibox.type()=="Portal") {
                gc.setFill(Color.GRAY);
            }

            gc.setGlobalAlpha(1);
            gc.fillRect(relativeX, relativeY, relativeWidth, relativeHeight);
            gc.setStroke(Color.BLACK);
            gc.setLineWidth(0.1);
            gc.strokeRect(relativeX, relativeY, relativeWidth, relativeHeight);
        });
    }
}
