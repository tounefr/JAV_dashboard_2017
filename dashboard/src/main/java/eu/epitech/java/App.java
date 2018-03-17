package eu.epitech.java;

/**
 * Point d'entrée du projet, initialisation de Spring, utilisation de Jetty pour le webserver
 *
 */

//import org.springframework.boot.web.servlet.support.SpringBootServletInitializer;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;

@SpringBootApplication
public class App {

/*
    @Override
    protected SpringApplicationBuilder configure (SpringApplicationBuilder builder) {
        return builder.sources(App.class);
    }
*/
    public static void main (String[] args) {
        SpringApplication.run(App.class, args);
    }

}
