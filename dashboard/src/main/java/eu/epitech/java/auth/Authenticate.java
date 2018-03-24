package eu.epitech.java.auth;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;

@EnableWebSecurity
public class Authenticate extends WebSecurityConfigurerAdapter {
    @Bean
    public AuthFilter JSONAuth() throws Exception {
        AuthFilter authenticationFilter = new AuthFilter();
        //authenticationFilter.setAuthenticationSuccessHandler(this::loginSuccessHandler);
        //authenticationFilter.setAuthenticationFailureHandler(this::loginFailureHandler);
        authenticationFilter.setRequiresAuthenticationRequestMatcher(new AntPathRequestMatcher("/login", "POST"));
        authenticationFilter.setAuthenticationManager(this.authenticationManagerBean());
        return authenticationFilter;
    }
    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http
                .addFilterBefore(JSONAuth(), UsernamePasswordAuthenticationFilter.class)
                .formLogin().loginPage("/login").failureUrl("/login-error")
                .and()
                .authorizeRequests()
                .antMatchers("/login**", "/css/**", "/error", "/h2admin/**").permitAll()
                //.antMatchers("/admin/**").hasRole("ADMIN")
                //.antMatchers("/**").hasRole("USER")
                .and()
                .logout().logoutUrl("/logout")
                ;
        http.csrf().disable(); // on est en localhost
        http.headers().frameOptions().sameOrigin(); // autoriser les frames pour h2console (h2admin)
    }

    @Autowired
    public void configureGlobal(AuthenticationManagerBuilder auth) throws Exception {
        auth.inMemoryAuthentication().withUser("user").password("password").roles("USER");
    }
}