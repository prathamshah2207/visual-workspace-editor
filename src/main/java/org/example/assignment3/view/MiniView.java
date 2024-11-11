package org.example.assignment3.view;

import javafx.scene.paint.Color;

public class MiniView extends DetailView{
    
    private double miniSz = 200;

    public MiniView(){
        super();
        myCan.setWidth(miniSz);
        myCan.setHeight(miniSz);
    }

    public void draw(){
        // the background
        gc.clearRect(0, 0, miniSz, miniSz);
        gc.setFill(Color.LIGHTGRAY);
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
            double miniSclFctr = minibox.getScaleFactor()*miniSz / world;
            double miniSightX = minibox.getSightAtX()*miniSz / world;
            double miniSightY = minibox.getSightAtY()*miniSz / world;

            gc.setGlobalAlpha(1);
            gc.setStroke(Color.BLACK);
            gc.setLineWidth(0.3);
            if(minibox.type() == "Box"){
                if (imodel.getSelected() == minibox) {
                    gc.setFill(Color.ORANGE);
                }
                else {
                    gc.setFill(Color.BLUE);
                }
                gc.fillRect(relativeX, relativeY, relativeWidth, relativeHeight);
                gc.strokeRect(relativeX, relativeY, relativeWidth, relativeHeight);

            } else if (minibox.type()=="Portal") {
                gc.setFill(Color.LIGHTGRAY);
                gc.fillRect(relativeX, relativeY, relativeWidth, relativeHeight);
                gc.strokeRect(relativeX, relativeY, relativeWidth, relativeHeight);
                gc.setLineWidth(1);
                portalDrawing(relativeX, relativeY, relativeWidth, relativeHeight, miniSclFctr, miniSightX, miniSightY, 1);
            }
        });
    }
}
