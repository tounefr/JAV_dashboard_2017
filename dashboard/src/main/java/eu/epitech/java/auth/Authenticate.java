package eu.epitech.java.auth;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;

@EnableWebSecurity
public class Authenticate extends WebSecurityConfigurerAdapter {
    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http
                .formLogin().loginPage("/login").failureUrl("/login-error")
                .and()
                .authorizeRequests()
                    .antMatchers("/login**", "/css/**", "/error", "/h2admin/**").permitAll()
                    .antMatchers("/**").hasRole("USER")
                ;
        http.csrf().disable(); // on est en localhost
        http.headers().frameOptions().sameOrigin(); // autoriser les frames pour h2console (h2admin)
    }

    @Autowired
    public void configureGlobal(AuthenticationManagerBuilder auth) throws Exception {
        auth.inMemoryAuthentication().withUser("user").password("password").roles("USER");
    }
}