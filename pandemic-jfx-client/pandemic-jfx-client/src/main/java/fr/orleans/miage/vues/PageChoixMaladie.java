package fr.orleans.miage.vues;

import fr.orleans.miage.controleur.Controller;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;

public class PageChoixMaladie  implements Vue{
    private Stage stage;
    private Scene scene;
    private Controller controller;
    @FXML
    private BorderPane borderpane;


    @FXML
    private Button retouraccueil;


    public void setController(Controller controller) {
        this.controller = controller;
    }

    public static PageChoixMaladie creer (Stage stage,Controller controller){
        URL location = PageChoixMaladie.class.getResource("pageChoixCarteMaladie.fxml");
        FXMLLoader fxmlLoader = new FXMLLoader(location);
        try {
            BorderPane borderPane =fxmlLoader.load();
            PageChoixMaladie vue =fxmlLoader.getController();
            vue.setStage(stage);
            vue.setController(controller);
            vue.setScene(new Scene(borderPane,600,700));
            vue.initialiserBoutonsPageJ();
            return vue;
        } catch (IOException e) {
            throw new RuntimeException("problème FXML:pageChoixCarteMaladie.fxml");
        }
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

    public void initialiserBoutonsPageJ() {
        this.retouraccueil.setOnAction(e -> controller.lancement());
    }

}
