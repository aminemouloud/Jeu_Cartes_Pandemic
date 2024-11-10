package fr.orleans.miage.vues;


import fr.orleans.miage.controleur.Controller;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;

public class NbJoueur implements Vue {
    private Stage stage;
    private Scene scene;
    private Controller controleur;

    Accueil accueil;
    @FXML
    private BorderPane borderpane;

    @FXML
    private TextField nbJoueurs;

    @FXML
    private Button retourAccueil;

    @FXML
    private Button allersaisieNom;


    public static NbJoueur creer (Stage stage,Controller controller){
        URL location = NbJoueur.class.getResource("nbJoueur.fxml");
        FXMLLoader fxmlLoader = new FXMLLoader(location);
        try {
            BorderPane borderPane =fxmlLoader.load();
            NbJoueur vue =fxmlLoader.getController();
            vue.setStage(stage);
            vue.setControleur(controller);
            vue.setScene(new Scene(borderPane,600,700));
            vue.pageSaisieNomJoueurs();
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

    public void retourAccueil(ActionEvent actionEvent) {
        this.controleur.lancement();
    }

    public void pageSaisieNomJoueurs() {
        this.allersaisieNom.setOnAction(e -> {
            // Vérifier si la saisie est un nombre entier supérieur ou égal à 1
            try {
                int nb = Integer.parseInt(nbJoueurs.getText());
                if (nb >= 1) {
                    controleur.goToPageJoueur();
                } else {
                    // Afficher une alerte si la saisie est inférieure à 1
                    Alert alert = new Alert(Alert.AlertType.WARNING);
                    alert.setTitle("Attention");
                    alert.setHeaderText("Saisie invalide");
                    alert.setContentText("Le nombre de joueurs doit être supérieur ou égal à 1 !");
                    alert.showAndWait();
                }
            } catch (NumberFormatException ex) {
                // Afficher une alerte si la saisie n'est pas un nombre
                Alert alert = new Alert(Alert.AlertType.WARNING);
                alert.setTitle("Attention");
                alert.setHeaderText("Saisie invalide");
                alert.setContentText("Veuillez saisir un nombre entier supérieur ou égal à 1 !");
                alert.showAndWait();
            }
        });
    }


}
