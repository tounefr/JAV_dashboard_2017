package eu.epitech.java;

import eu.epitech.java.entities.Module;
import eu.epitech.java.entities.modules.CoinMarketModule;
import eu.epitech.java.entities.modules.FacebookModule;
import eu.epitech.java.entities.modules.TwitterModule;
import eu.epitech.java.lists.ModuleListHandler;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;
import org.springframework.web.filter.CorsFilter;

import java.util.HashSet;
import java.util.Set;

@Configuration
public class Config {
    @Bean
    public FilterRegistrationBean corsFilter() {
        UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();
        CorsConfiguration config = new CorsConfiguration();
        config.setAllowCredentials(true);
        config.addAllowedOrigin("*");
        config.addAllowedHeader("*");
        config.addAllowedMethod("*");
        source.registerCorsConfiguration("/**", config);
        FilterRegistrationBean bean = new FilterRegistrationBean(new CorsFilter(source));
        bean.setOrder(0);
        return bean;
    }

    public Config() {
    }

    @Autowired
    ModuleListHandler ModuleListHandler;

    @Bean
    public boolean loadModules() {
        try {
            Set<Module> modules = new HashSet<Module>();
            modules.add(new FacebookModule());
            modules.add(new TwitterModule());
            modules.add(new CoinMarketModule());

            for (Module current : modules) {
                if (ModuleListHandler.getModule(current.getName(), null) == null)
                    ModuleListHandler.addModule(current, null);
            }
            
            return true;
        } catch (ModuleListHandler.MLHException ex) {
            System.out.println("Fatal error while loading custom module: " + ex.getMessage());
            return false;
        }
    }
}
