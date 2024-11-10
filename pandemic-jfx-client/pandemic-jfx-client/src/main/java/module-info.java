module fr.orleans.miage {
    requires javafx.controls;
    requires javafx.fxml;
    requires java.desktop;
    requires reactor.core;
    requires spring.webflux;
    requires spring.web;
    opens fr.orleans.miage.vues to javafx.fxml;
    exports fr.orleans.miage;
}