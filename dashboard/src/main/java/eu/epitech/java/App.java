package eu.epitech.java;

/*
 * Point d'entrée du projet, initialisation de Spring, utilisation de Jetty pour le webserver
 *
 */

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class App {
    public static void main (String[] args) {
        SpringApplication.run(App.class, args);
    }
}
