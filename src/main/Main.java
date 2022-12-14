package main;

import controller.ConverterInterfaceController;
import controller.StandardInterfaceController;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.paint.Color;
import javafx.stage.Stage;

public class Main extends Application {
    public static void main(String[] args) {
        launch();
    }

    public void start(Stage stage) throws Exception {
        // First Scene
        FXMLLoader rootOfStandard = new FXMLLoader(Main.class.getResource("/design/StandardInterface.fxml"));
        Scene sceneOfStandard = new Scene(rootOfStandard.load());
        sceneOfStandard.setFill(Color.TRANSPARENT);
        // Second Scene
        FXMLLoader rootOfConverter = new FXMLLoader(Main.class.getResource("/design/ConverterInterface.fxml"));
        Scene sceneOfConverter = new Scene(rootOfConverter.load());
        sceneOfConverter.setFill(Color.TRANSPARENT);
        // injecting second scene into the controller of the first scene
        StandardInterfaceController standardInterfaceController = rootOfStandard.getController();
        standardInterfaceController.setSecondScene(sceneOfConverter);

        // injecting first scene into the controller of the second scene
        ConverterInterfaceController converterInterfaceController = rootOfConverter.getController();
        converterInterfaceController.setFirstScene(sceneOfStandard);

        // Show stage
        stage.setScene(sceneOfStandard);
        stage.show();
    }
}
