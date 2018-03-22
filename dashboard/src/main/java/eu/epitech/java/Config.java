package eu.epitech.java;

import org.springframework.beans.factory.config.ConfigurableBeanFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Scope;

@Configuration
public class Config {
    @Bean
    @Scope(value = ConfigurableBeanFactory.SCOPE_SINGLETON)
    public String testSingleTon() {
        System.out.println("_________---------------------_____________________-------------------");
        return "yes m9";
    }

    public Config() {
    }
}
