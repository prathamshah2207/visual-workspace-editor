package org.example.assignment3;

import javafx.scene.layout.StackPane;

public class MainUI extends StackPane {
    public MainUI() {
        EntityModel model = new EntityModel();
        InteractionModel iModel = new InteractionModel();
        DetailView view = new DetailView();
        AppController controller = new AppController();

        controller.setModel(model);
        controller.setIModel(iModel);
        view.setModel(model);
        view.setIModel(iModel);
        view.setupEvents(controller);
        model.addSubscriber(view);

        getChildren().add(view);
    }
}
