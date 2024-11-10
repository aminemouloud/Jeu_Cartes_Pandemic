package fr.orleans.miage.proxyService;

import fr.orleans.miage.modele.Notification;

import java.util.Optional;
import java.util.function.Consumer;

import modele.Partie;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.web.reactive.function.BodyInserters;
import org.springframework.web.reactive.function.client.ClientResponse;
import reactor.core.publisher.Flux;
import org.springframework.web.reactive.function.client.WebClient;


public class ServiceProxy implements Service {

    private WebClient webAuthClient;
    private Optional<String> authorization;
    private Flux<Notification> flux;

    static class Auth {
        public String pseudo;
        public String mdp;
    }

    /**
     * cette methode sert à effectuer une connexion au  serveur en utilisant un pseudo et un mot de passe.
     * Elle envoie une requête POST à l’URL “http://localhost:8080/auth/login” avec les informations d’authentification en corps de la requête.
     * Si la connexion réussit, la méthode renvoie un Optional<String> contenant l’autorisation avec le token renvoyé par le serveur springboot du projet.
     * Cette autorisation est ensuite utilisée pour effectuer des requêtes authentifiées au serveur springBoot de notre projet.
     * @param pseudo correspond au pseudonyme du joueur
     * @param mdp correspond au mot de passe du joueur
     * @return
     */
    @Override
    public Optional<String> login(String pseudo, String mdp) {
        //ici, on Crée un nouvel objet Auth et on initialise ses champs avec les valeurs des paramètres
        Auth auth = new Auth();
        auth.pseudo = pseudo;
        auth.mdp = mdp;

        // Crée un nouvel objet WebClient pour effectuer une requête POST à l'URL spécifiée
        final WebClient webClientLogin = WebClient.create("http://localhost:8080/auth/login");

        // Initialise l'autorisation à vide
        authorization = Optional.empty();

        // ici, on effectue la requête POST avec l'objet Auth en corps de la requête et on récupère la réponse
        ClientResponse response = webClientLogin
                .post()
                .contentType(MediaType.APPLICATION_JSON)
                .body(BodyInserters.fromValue(auth))
                .exchange().block();

        // ici,on Extrait l'autorisation de l'en-tête "Authorization" de la réponse
        authorization = response.headers().header("Authorization").stream().findFirst();

        // Si la réponse indique un succès et que l'autorisation est présente
        if (response.statusCode().is2xxSuccessful() && authorization.isPresent()) {
            // ici, on Crée un nouvel objet WebClient avec l'URL de base et l'en-tête "Authorization" configurés
            webAuthClient = WebClient.builder()
                    .baseUrl("http://localhost:8080/pandemic/")
                    .defaultHeader(HttpHeaders.AUTHORIZATION, authorization.get())
                    .defaultHeader(HttpHeaders.CONTENT_TYPE, MediaType.APPLICATION_JSON_VALUE)
                    .build();

            // ici, on Effectue une requête GET à l'URI "partie/subscribe" pour s'abonner aux notifications de la partie encours
            flux = webAuthClient
                    .get()
                    .uri(uriBuilder -> uriBuilder.path("creerPartie/subscribe").build())
                    .retrieve()
                    .bodyToFlux(Notification.class);
        }

        // ici on retourne l'autorisation recue!
        return authorization;
    }

    /**
     * createPartie qui envoie une requête POST pour créer une nouvelle partie sur le serveur.
     * @param partie qui sera envoyé en corps de la requête
     */
    @Override
    public void createPartie(Partie partie) {
        // Vérifier que l'utilisateur est authentifié
        if (!isAuthenticated()) {
            throw new IllegalStateException("Utilisateur non authentifié");
        }

        // Envoyer une requête POST pour créer la partie
        webAuthClient
                .post()
                .uri(uriBuilder -> uriBuilder.path("/partie").build())
                .contentType(MediaType.APPLICATION_JSON)
                .body(BodyInserters.fromValue(partie))
                .exchange()
                .block();
    }


    /**
     * cette methode permet de tranmettre des notification recu
     * @param consumer
     */
    @Override
    public void subscribe(Consumer<Notification> consumer) {
        //  ici, on s'abonner à l'objet flux
        flux.subscribe(notification -> {
            //ensuite,on Passer la notification à la méthode accept du consommateur
            consumer.accept(notification);
        });
    }

    /**
     *
     * @return un booleen pour indiquer si lutilisateur possede une autorisation ou non
     */
    @Override
    public boolean isAuthenticated() {
        return authorization.isPresent();
    }

    /**
     * rejoindrePartie qui envoie une requête PUT pour rejoindre une partie existante sur le serveur.
     * @param partie qui contiendra l'identifiant de la partie à rejoindre.
     */
    @Override
    public void rejoindrePartie(Partie partie) {
        // Vérifier que l'utilisateur est authentifié
        if (!isAuthenticated()) {
            throw new IllegalStateException("Utilisateur non authentifié");
        }

        // Envoyer une requête PUT pour rejoindre la partie
        webAuthClient
                .put()
                .uri(uriBuilder -> uriBuilder.path("/partie/{id}/joueur").build(partie.getId()))
                .contentType(MediaType.APPLICATION_JSON)
                .body(BodyInserters.fromValue(partie))
                .exchange()
                .block();
}


    /**
     * getPartie qui envoie une requête GET pour récupérer les informations d'une partie existante sur le serveur
     * @param idPartie identifiant de la partie
     * @return
     */
    @Override
    public Optional<Partie> getPartie(String idPartie) {
        // Vérifier que l'utilisateur est authentifié
        if (!isAuthenticated()) {
            throw new IllegalStateException("Utilisateur non authentifié");
        }

        // Envoyer une requête GET pour récupérer les informations de la partie
        Partie partie = webAuthClient
                .get()
                .uri(uriBuilder -> uriBuilder.path("/partie/{id}").build(idPartie))
                .retrieve()
                .bodyToMono(Partie.class)
                .block();

        return Optional.ofNullable(partie);
    }

    /**
     * cette methode permet de mettre à jour les informations d'une partie existante sur le serveur.
     * @param partie qui contient les informations à mettre à jour.
     */
    @Override
    public void updatePartie(Partie partie) {
        // Vérifier que l'utilisateur est authentifié
        if (!isAuthenticated()) {
            throw new IllegalStateException("Utilisateur non authentifié");
        }

        // Envoyer une requête PUT pour mettre à jour la partie
        webAuthClient
                .put()
                .uri(uriBuilder -> uriBuilder.path("/partie/{id}").build(partie.getId()))
                .contentType(MediaType.APPLICATION_JSON)
                .body(BodyInserters.fromValue(partie))
                .exchange()
                .block();
    }


    /**
     * cette methode permet de supprimer une partie existante sur le serveur.
     * @param idPartie correspond à l'identifiant de la partie à supprimer.
     */
    @Override
    public void deletePartie(String idPartie) {
        // Vérifier que l'utilisateur est authentifié
        if (!isAuthenticated()) {
            throw new IllegalStateException("Utilisateur non authentifié");
        }

        // Envoyer une requête DELETE pour supprimer la partie avec l'identifiant spécifié
        webAuthClient
                .delete()
                .uri(uriBuilder -> uriBuilder.path("/partie/{id}").build(idPartie))
                .exchange()
                .block();
    }
}


