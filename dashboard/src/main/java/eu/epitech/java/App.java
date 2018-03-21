package eu.epitech.java;

/*
 * Point d'entrée du projet, initialisation de Spring, utilisation de Jetty pour le webserver
 *
 */

import eu.epitech.java.entity.Module;
import eu.epitech.java.repositories.ModuleRepository;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;

@SpringBootApplication
@EnableGlobalMethodSecurity(prePostEnabled = true, securedEnabled = true)
public class App {
    public static void main (String[] args) {
       // SpringApplication.run(App.class, args);

        ConfigurableApplicationContext context = SpringApplication.run(App.class, args);
        //test
        ModuleRepository repo = context.getBean(ModuleRepository.class);
        repo.save(new Module());
    }
}
