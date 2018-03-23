package eu.epitech.java;

/*
 * Point d'entrée du projet, initialisation de Spring, utilisation de Jetty pour le webserver
 *
 */

import eu.epitech.java.entities.modules.TestModule;
import eu.epitech.java.lists.ModuleList;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;

import org.springframework.cache.annotation.EnableCaching;

@EnableCaching
@SpringBootApplication
@EnableGlobalMethodSecurity(prePostEnabled = true, securedEnabled = true)
public class App {
    public static void main (String[] args) {
       // SpringApplication.run(App.class, args);

        ConfigurableApplicationContext context = SpringApplication.run(App.class, args);
        //test
        ModuleList repo = context.getBean(ModuleList.class);
        repo.save(new TestModule());
        System.out.println(repo.count());
        System.out.println(repo.findAll().get(0).getName());
    }
}
