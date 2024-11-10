package fr.orleans.miage.vues;

import fr.orleans.miage.controleur.Controller;
import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;

public class MenuJeu implements Vue {
    private Stage stage;
    private Scene scene;
    Controller controller;

    public static MenuJeu creer (Stage stage){
        URL location = MenuJeu.class.getResource("menuJeux.fxml");
        FXMLLoader fxmlLoader = new FXMLLoader(location);
        try {
            BorderPane borderPane =fxmlLoader.load();
            MenuJeu vue =fxmlLoader.getController();
            vue.setStage(stage);
            vue.setScene(new Scene(borderPane,600,700));
            return vue;
        } catch (IOException e) {
            throw new RuntimeException("problème FXML:menuJeux.fxml");
        }
    }


    public void setStage(Stage stage) {
        this.stage = stage;
    }

    public void setScene(Scene scene) {
        this.scene = scene;
    }

    public void setController(Controller controller) {
        this.controller = controller;
    }


    @Override
    public void show() {
        this.stage.setScene(this.scene);
        this.stage.show();

    }

    public void retourAccueil(ActionEvent actionEvent) {
        controller.lancement();
    }
}
