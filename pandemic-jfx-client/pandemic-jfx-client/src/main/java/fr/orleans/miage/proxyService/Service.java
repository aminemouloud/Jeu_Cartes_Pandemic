package fr.orleans.miage.proxyService;

import fr.orleans.miage.modele.Notification;
import modele.Partie;

import java.util.Optional;
import java.util.function.Consumer;

public interface Service {
    /**
     * cette methode sert à effectuer une connexion au  serveur en utilisant un pseudo et un mot de passe.
     * @param pseudo correspond au pseudo de l'utilisateur
     * @param mdp correspond au mot de passe de l'utilisateur
     * @return l'autorisation recu par le serveur de connexion
     */
    Optional<String> login(String pseudo, String mdp);

    void createPartie(Partie partie);

    /**
     * cette methode permet de tranmettre des notification recu
     * @param consumer
     */
    void subscribe(Consumer<Notification> consumer);
    /**
     *
     * @return un booleen pour indiquer si lutilisateur possede une autorisation ou non
     */
    boolean isAuthenticated();

    void rejoindrePartie(Partie partie);

    Optional<Partie> getPartie(String idPartie);

    void updatePartie(Partie partie);

    void deletePartie(String idPartie);
}
