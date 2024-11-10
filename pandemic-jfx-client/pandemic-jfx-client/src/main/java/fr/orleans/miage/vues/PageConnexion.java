package fr.orleans.miage.vues;

import fr.orleans.miage.controleur.Controller;
import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;

public class PageConnexion implements Vue {
    private Stage stage;
    private Scene scene;
    private Controller controleur;


    public static PageConnexion creer (Stage stage, Controller controller){
        URL location = NbJoueur.class.getResource("pageConnexion.fxml");
        FXMLLoader fxmlLoader = new FXMLLoader(location);
        try {
            BorderPane borderPane =fxmlLoader.load();
            PageConnexion vue =fxmlLoader.getController();
            vue.setStage(stage);
            vue.setControleur(controller);
            vue.setScene(new Scene(borderPane,600,700));
            return vue;
        } catch (IOException e) {
            throw new RuntimeException("probleme lors du chargement du fichier");
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
    public void allerPageAuthentification(ActionEvent actionEvent) {
        this.controleur.goToPageMenuApresConnexion();
    }

    //permet de retourner en arriere
    public void retour(ActionEvent actionEvent) {
        this.controleur.goToPageJoueur();

    }
}
