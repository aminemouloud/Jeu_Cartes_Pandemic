package fr.orleans.miage.vues;

import fr.orleans.miage.controleur.Controller;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;

public class PageJoueur implements Vue {

    private Stage stage;
    private Scene scene;
    private Controller controleur;
    @FXML
    private Button retourChoixNbJoueur;
    @FXML
    private Button lancerPartie;



    public static PageJoueur creer (Stage stage,Controller controleur){
        URL location = PageJoueur.class.getResource("pageJoueur.fxml");
        FXMLLoader fxmlLoader = new FXMLLoader(location);
        try {
            BorderPane borderPane =fxmlLoader.load();
            PageJoueur vue =fxmlLoader.getController();
            vue.setStage(stage);
            vue.setControleur(controleur);
            vue.setScene(new Scene(borderPane,600,700));

            return vue;
        } catch (IOException e) {
            throw new RuntimeException("problème FXML:PageJoueur.fxml");
        }
    }


    public void setStage(Stage stage) {
        this.stage = stage;
    }

    public void setScene(Scene scene) {
        this.scene = scene;
    }

    public void setControleur(Controller controleur) {
        this.controleur = controleur;
    }

    @Override
    public void show() {
        this.stage.setScene(this.scene);
        this.stage.show();

    }


    public void allerPageDeConnexion(ActionEvent actionEvent) {
        this.controleur.goToPageConnexion();
    }

    public void allerPageAuthentification(ActionEvent actionEvent) {
        this.controleur.goToPageMenuApresConnexion();
    }
    public void retour(ActionEvent actionEvent) {
        this.controleur.goToNombreJoueurs();
    }
}
