package org.example.assignment3;

import javafx.scene.layout.StackPane;

public class MainUI extends StackPane {
    public MainUI() {
        EntityModel model = new EntityModel();
        InteractionModel iModel = new InteractionModel();
        DetailView view = new DetailView();
        AppController controller = new AppController();
    }
}
