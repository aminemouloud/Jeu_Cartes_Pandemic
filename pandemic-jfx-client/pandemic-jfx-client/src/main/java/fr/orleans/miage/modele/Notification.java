package fr.orleans.miage.modele;


public class Notification {
    private String partie;
    private String utilisateur;

    public Notification() {
    }

    public Notification(String partie, String utilisateur) {
        this.partie = partie;
        this.utilisateur = utilisateur;
    }

    public String getPartie() {
        return partie;
    }

    public void setPartie(String partie) {
        this.partie = partie;
    }

    public String getUtilisateur() {
        return utilisateur;
    }

    public void setUtilisateur(String utilisateur) {
        this.utilisateur = utilisateur;
    }
}
