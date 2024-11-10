package fr.orleans.miage.controleur;

import fr.orleans.miage.vues.*;
import javafx.stage.Stage;

import javafx.stage.Stage;

public class Controller {
    private Accueil accueil;
    private MenuJeu menuJeu;
    private NbJoueur nbJoueur;
    private PageJoueur pageJoueur;
    //private PageChoixMaladie pageChoixMaladie;
    private PagePlateau pagePlateau;
    private MenuApresConnexion menuApresConnexion;
    private PageConnexion pageConnexion;
    private int nombreJoueur;

    public Controller(Stage stage) {
        this.initialiserVue(stage);
    }

    private void initialiserVue(Stage stage){
        this.accueil = Accueil.creer(stage);
        this.accueil.setController(this); // ajouter la référence au contrôleur
        this.menuJeu = MenuJeu.creer(stage);
        this.nbJoueur = NbJoueur.creer(stage,this);
        this.pageJoueur = PageJoueur.creer(stage,this);
        //this.pageChoixMaladie = PageChoixMaladie.creer(stage,this);
        this.menuApresConnexion =MenuApresConnexion.creer(stage,this);
        this.pageConnexion =PageConnexion.creer(stage,this);
        this.pagePlateau = pagePlateau.creer(stage,this);
    }

    public void lancement(){
        accueil.show();
    }

    public void goToNombreJoueurs() {
        this.nbJoueur.show();
    }

    public void goToMenuJeu() {
        this.menuJeu.show();
    }

    public void goToPageJoueur() {
        this.pageJoueur.show();
    }

    public void goToPageMenuApresConnexion() {
        this.menuApresConnexion.show();
    }

    public void goToPageConnexion() {
        this.pageConnexion.show();
    }


   /* public void goToPageChoixMaladie() {
        this.pageChoixMaladie.show();
    }*/
    public void goToPageAffichagePlateau() {
        this.pagePlateau.show();
    }

}

