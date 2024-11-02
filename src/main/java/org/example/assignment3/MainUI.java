package org.example.assignment3;
import org.example.assignment3.model.InteractionModel;
import org.example.assignment3.controller.AppController;
import org.example.assignment3.model.EntityModel;


import javafx.scene.layout.StackPane;
import org.example.assignment3.view.DetailView;

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
