package fr.orleans.miage;


import fr.orleans.miage.controleur.Controller;
import fr.orleans.miage.vues.*;
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;

/**
 * JavaFX App
 */
public class App extends Application {

    private static Scene scene;

    @Override
    public void start(Stage stage) throws IOException {

        Controller controller = new Controller(stage);
        controller.lancement();

    }



    public static void main(String[] args) {
        launch();
    }

}