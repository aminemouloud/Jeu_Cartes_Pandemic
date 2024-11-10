package fr.orleans.miage.vues;

import fr.orleans.miage.controleur.Controller;
import javafx.animation.KeyFrame;
import javafx.animation.KeyValue;
import javafx.animation.Timeline;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;
import javafx.fxml.FXML;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.util.Duration;

import java.io.IOException;
import java.net.URL;

public class PagePlateau implements Vue{
    private Stage stage;
    private Scene scene;
    private Controller controller;
    @FXML
    private ImageView pion1;
    @FXML
    private ImageView pion2;
    @FXML
    private ImageView pion3;
    @FXML
    private ImageView pion4;
    private double orgSceneX, orgSceneY;
    private double orgTranslateX, orgTranslateY;

    // les images sont recuperées ici en bdd
    /*public void initialize() {
        Image img1 = new Image("chemin/vers/image1.png");
        image1.setImage(img1);

        Image img2 = new Image("chemin/vers/image2.png");
        image2.setImage(img2);
    }*/

    public void initialize() {
        // ici, on Crée un objet Image à partir d'un fichier image situé dans le dossier "images/pions" avec le nom "Pion_Blanc.png"
        Image img1 = new Image(getClass().getResource("images/cubesCures/cure_jaune.png").toString());
        pion1.setImage(img1);
        pion1.setTranslateX(-150.0);
        pion1.setTranslateY(300.0);

        Image img2 = new Image(getClass().getResource("images/cubesCures/cure_rouge.png").toString());
        pion2.setImage(img2);
        pion2.setTranslateX(-80.0);
        pion2.setTranslateY(300.0);
        Image img3 = new Image(getClass().getResource("images/cubesCures/cure_bleue.png").toString());
        pion3.setImage(img3);
        pion3.setTranslateX(-6.0);
        pion3.setTranslateY(300.0);
        Image img4 = new Image(getClass().getResource("images/cubesCures/cure_noire.png").toString());
        pion4.setImage(img4);
        pion4.setTranslateX(-80.0);
        pion4.setTranslateY(300.0);

        // ici ,on Définit l'image de l'élément ImageView "pion1" avec l'objet Image créé précédemment
        pion1.setOnMousePressed(this::onMousePressed);
        pion1.setOnMouseDragged(this::onMouseDragged);

        pion2.setOnMousePressed(this::onMousePressed);
        pion2.setOnMouseDragged(this::onMouseDragged);

        pion3.setOnMousePressed(this::onMousePressed);
        pion3.setOnMouseDragged(this::onMouseDragged);

        pion4.setOnMousePressed(this::onMousePressed);
        pion4.setOnMouseDragged(this::onMouseDragged);


        Timeline timeline = new Timeline(
                new KeyFrame(Duration.ZERO,
                        new KeyValue(pion1.opacityProperty(), 1),
                        new KeyValue(pion2.opacityProperty(), 1),
                        new KeyValue(pion3.opacityProperty(), 1),
                        new KeyValue(pion4.opacityProperty(), 1)
                ),
                new KeyFrame(Duration.seconds(1),
                        new KeyValue(pion1.opacityProperty(), 0)
                ),
                new KeyFrame(Duration.seconds(2),
                        new KeyValue(pion1.opacityProperty(), 1),
                        new KeyValue(pion2.opacityProperty(), 0)
                ),
                new KeyFrame(Duration.seconds(3),
                        new KeyValue(pion2.opacityProperty(), 1),
                        new KeyValue(pion3.opacityProperty(), 0)
                ),
                new KeyFrame(Duration.seconds(4),
                        new KeyValue(pion3.opacityProperty(), 1),
                        new KeyValue(pion4.opacityProperty(), 0)
                ),
                new KeyFrame(Duration.seconds(5),
                        new KeyValue(pion4.opacityProperty(), 1)
                )
        );
        timeline.setCycleCount(Timeline.INDEFINITE);
        timeline.play();
    }

    /**
     * cette permet de gerer le deplacement des pion avec le clic de la souris
     * @param event
     */
    private void onMousePressed(MouseEvent event) {
        // ici, on Récupère la source de l'événement (l'élément ImageView qui a déclenché l'événement)
        // et la stocke dans la variable imageView
        ImageView imageView = (ImageView) event.getSource();

// on Enregistre les coordonnées actuelles de la souris dans les variables orgSceneX et orgSceneY
        orgSceneX = event.getSceneX();
        orgSceneY = event.getSceneY();
        orgTranslateX = imageView.getTranslateX();
        orgTranslateY = imageView.getTranslateY();
        // affichage des coordonnées de la souris
        System.out.println("Coordonnées de la souris : X=" + event.getX() + " Y=" + event.getY());


    // affichage des coordonnées de la souris
        System.out.println("Coordonnées de la souris : X=" + event.getX() + " Y=" + event.getY());

    }

    /**
     * cette permet de gerer le deplacement des pion avec le deplacement de la souris
     * @param event
     */
    private void onMouseDragged(MouseEvent event) {

        // Récupère la source de l'événement (l'élément ImageView qui a déclenché l'événement)
        // et la stocke dans la variable imageView
        ImageView imageView = (ImageView) event.getSource();

        // Calcule le décalage en X et en Y entre les coordonnées actuelles de la souris
        // et les coordonnées enregistrées lors de l'événement onMousePressed
        double offsetX = event.getSceneX() - orgSceneX;
        double offsetY = event.getSceneY() - orgSceneY;
        // Calcule les nouvelles valeurs des propriétés translateX et translateY en ajoutant
        // les décalages calculés aux valeurs enregistrées lors de l'événement onMousePressed
        double newTranslateX = orgTranslateX + offsetX;
        double newTranslateY = orgTranslateY + offsetY;

        // Met à jour les propriétés translateX et
        // translateY de l'élément ImageView avec les nouvelles valeurs calculées
        imageView.setTranslateX(newTranslateX);
        imageView.setTranslateY(newTranslateY);
    }


    public void setController(Controller controller) {
        this.controller = controller;
    }

    public static PagePlateau creer (Stage stage, Controller controller){
        URL location = PagePlateau.class.getResource("pageAffichagePlateau.fxml");
        FXMLLoader fxmlLoader = new FXMLLoader(location);
        try {
            BorderPane borderPane =fxmlLoader.load();
            PagePlateau vue =fxmlLoader.getController();
            vue.setStage(stage);
            vue.setController(controller);
            vue.setScene(new Scene(borderPane,600,700));
            return vue;
        } catch (IOException e) {
            throw new RuntimeException("erreur de chargement du fichier fxml");
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

}
