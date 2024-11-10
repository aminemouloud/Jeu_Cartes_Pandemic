package fr.orleans.miage.vues;

import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.VBox;
import javafx.scene.text.Font;
import javafx.stage.Stage;

public class Accueil1 implements Vue {
    private Stage stage;
    private Scene scene;

    private BorderPane borderPane;
    private Button commencerPartie;
    private Button quitterPartie;

    private Accueil1(){

    }

    public Stage getStage() {
        return stage;
    }

    public void setStage(Stage stage) {
        this.stage = stage;
    }

    public Scene getScene() {
        return scene;
    }

    public void setScene(Scene scene) {
        this.scene = scene;
    }

    public static Accueil1 creer(Stage stage){
        Accueil1 accueil1 =new Accueil1();
        accueil1.setStage(stage);
        accueil1.initialisationComposants();
        return accueil1;

    }

    /**
     * permet d'initialiser et afficher la page d'accueil du jeu
     */
    private void initialisationComposants() {
        this.borderPane =new BorderPane();
        this.commencerPartie=new Button("Commencer une partie");
        this.quitterPartie =new Button("Quitter");

        //gestion de la taille des bouttons
        this.commencerPartie.setMaxWidth(Double.MAX_VALUE);
        this.quitterPartie.setMaxWidth(Double.MAX_VALUE);

        VBox vBox =new VBox();
        vBox.setAlignment(Pos.CENTER);
        vBox.setSpacing(20);
        vBox.getChildren().addAll(this.commencerPartie,this.quitterPartie);
        this.borderPane.setCenter(vBox);

        Label label =new Label("Bienvenue Au Jeu de Pandelic");

        label.setFont(Font.font(32));
        this.borderPane.setTop(label);

        borderPane.setAlignment(label,Pos.CENTER);
        borderPane.setAlignment(vBox,Pos.CENTER);
        this.setScene(new Scene(this.borderPane,600,700));


    }

    @Override
    public void show() {
        this.stage.setScene(this.scene);
        this.stage.show();

    }
}
