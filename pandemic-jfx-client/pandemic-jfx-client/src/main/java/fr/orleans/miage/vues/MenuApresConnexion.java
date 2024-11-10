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

public class MenuApresConnexion implements  Vue {

    private Stage stage;
    private Scene scene;
    private Controller controleur;
    @FXML
    private Button lancerPartie;


    public static MenuApresConnexion creer (Stage stage, Controller controller){
        URL location = NbJoueur.class.getResource("pageMenuApresConnexion.fxml");
        FXMLLoader fxmlLoader = new FXMLLoader(location);
        try {
            BorderPane borderPane =fxmlLoader.load();
            MenuApresConnexion vue =fxmlLoader.getController();
            vue.setStage(stage);
            vue.setControleur(controller);
            vue.setScene(new Scene(borderPane,600,700));
            vue.afficherPageCoixCarte();
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
    //ppour afficher la page de lancement de la partie
    public void afficherPageCoixCarte(){
        this.lancerPartie.setOnAction(e->controleur.goToPageAffichagePlateau());
    }

    //permet de retourner en arriere
    public void retour(ActionEvent actionEvent) {
        this.controleur.goToPageJoueur();

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


}
