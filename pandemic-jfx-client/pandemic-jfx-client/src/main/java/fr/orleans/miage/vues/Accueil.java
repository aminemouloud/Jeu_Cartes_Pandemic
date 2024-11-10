package fr.orleans.miage.vues;

import fr.orleans.miage.controleur.Controller;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;
import java.util.Optional;

public class Accueil implements Vue {
    private Stage stage;
    private Scene scene;
    private Controller controller;
    private MenuJeu menuJeu;
    @FXML
    public BorderPane pane;

    @FXML
    private Button jouer;

    public static Accueil creer(Stage stage) {
        URL location = Accueil.class.getResource("accueil.fxml");
        FXMLLoader fxmlLoader = new FXMLLoader(location);
        try {
            BorderPane borderPane = fxmlLoader.load();
            Accueil vue = fxmlLoader.getController();
            vue.setStage(stage);
            vue.setScene(new Scene(borderPane, 600, 700));
            vue.initialiserBoutons();
            return vue;
        } catch (IOException e) {
            throw new RuntimeException("problème FXML:accueil.fxml");
        }
    }

    public void setController(Controller controller) {
        this.controller = controller;
    }

    public void setStage(Stage stage) {
        this.stage = stage;
    }

    public void setScene(Scene scene) {
        this.scene = scene;
    }

    @Override
    public void show() {
        this.stage.setScene(this.scene);
        this.stage.show();

    }

    public void quitter(ActionEvent actionEvent) {
        Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
        alert.setTitle("Confirmation de sortie");
        alert.setHeaderText("Êtes-vous sûr de vouloir quitter le jeu ?");
        alert.setContentText("Cliquez sur Oui pour quitter ou sur Non pour annuler.");

        ButtonType boutonOui = new ButtonType("Oui");
        ButtonType boutonNon = new ButtonType("Non");

        alert.getButtonTypes().setAll(boutonOui, boutonNon);

        Optional<ButtonType> resultat = alert.showAndWait();
        if (resultat.get() == boutonOui){
            System.exit(0);
        } else {
            // L'utilisateur a cliqué sur "Non" ou a fermé la boîte de dialogue
            // Rien à faire, on reste dans le jeu.
        }
    }

    public void afficherRegles(ActionEvent actionEvent) {
        // URL des règles du jeu de Pandemic
        String url = "https://cdn.1j1ju.com/medias/f8/5d/96-pandemic-regle.pdf";

        // Ouvrir la page web dans le navigateur par défaut
        try {
            java.awt.Desktop.getDesktop().browse(java.net.URI.create(url));
        } catch (java.io.IOException e) {
            System.out.println(e.getMessage());
        }
    }

    public void initialiserBoutons() {
        this.jouer.setOnAction(e -> controller.goToNombreJoueurs());
    }


}
