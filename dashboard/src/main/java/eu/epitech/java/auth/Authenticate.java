package eu.epitech.java.auth;

import eu.epitech.java.controller.rest.GenericResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.authentication.AuthenticationFailureHandler;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.security.web.authentication.logout.LogoutHandler;
import org.springframework.security.web.authentication.logout.LogoutSuccessHandler;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;
import eu.epitech.java.lists.UserListHandler;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@EnableWebSecurity
public class Authenticate extends WebSecurityConfigurerAdapter {
    @Bean
    public AuthFilter JSONAuth() throws Exception {
        AuthFilter authenticationFilter = new AuthFilter();
        authenticationFilter.setAuthenticationSuccessHandler(new loginSuccess());
        authenticationFilter.setAuthenticationFailureHandler(new loginError());
        authenticationFilter.setRequiresAuthenticationRequestMatcher(new AntPathRequestMatcher("/login", "POST"));
        authenticationFilter.setAuthenticationManager(this.authenticationManagerBean());
        return authenticationFilter;
    }

    private class loginSuccess implements AuthenticationSuccessHandler {

        @Override
        public void onAuthenticationSuccess(HttpServletRequest req,
                                            HttpServletResponse resp,
                                            Authentication authentication) throws IOException, ServletException {
            System.out.println("user logged in.");
            //resp.sendRedirect("/login");
        }
    }

    private class loginError implements AuthenticationFailureHandler {

        @Override
        public void onAuthenticationFailure(HttpServletRequest req,
                                            HttpServletResponse resp,
                                            AuthenticationException e) throws IOException, ServletException {
            System.out.println("user failed logging in.");
            GenericResponse.error(resp, GenericResponse.buildErrorPLY(403,
                    "Bad credentials"), req.getRequestURI());
        }
    }
    
   private class logoutHandler implements LogoutHandler {
       @Override
       public void logout(HttpServletRequest req, HttpServletResponse resp, Authentication authentication) {
           if (authentication != null && authentication.isAuthenticated()) {
               authentication.setAuthenticated(false);
               resp.setStatus(200);
               System.out.println("User logged out.");
           }
           else
           {
               GenericResponse.error(resp, GenericResponse.buildErrorPLY(400,
                       "User not authenticated"), req.getRequestURI());
               System.out.println("User not authenticated.");
           }
       }
   }

   private class logoutSuccess implements LogoutSuccessHandler {
       @Override
       public void onLogoutSuccess(HttpServletRequest req,
                                   HttpServletResponse resp,
                                   Authentication authentication) throws IOException, ServletException {
           resp.setHeader("Access-Control-Allow-Origin", "http://dashboard.epitech.eu");
           resp.setHeader("Access-Control-Allow-Credentials", "true");
           resp.setStatus(200);

       }
   }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http
                .addFilterBefore(JSONAuth(), UsernamePasswordAuthenticationFilter.class)
                .authorizeRequests()
                .antMatchers(HttpMethod.OPTIONS, "/**").permitAll()
                .antMatchers("/login**", "/users/register", "/css/**", "/error", "/h2admin/**").permitAll()
                .antMatchers("/admin/**").hasRole("ADMIN")
                .antMatchers("/**").hasRole("USER")
                .and()
                .logout().
                    logoutRequestMatcher(new AntPathRequestMatcher("/logout", "GET")).
                    addLogoutHandler(new logoutHandler()).logoutSuccessHandler(new logoutSuccess())
                ;
        http.csrf().disable(); // on est en localhost
        http.headers().frameOptions().sameOrigin(); // autoriser les frames pour h2console (h2admin)
    }

    @Autowired
    private UserListHandler UserListHandler;


    @Bean
    public DaoAuthenticationProvider authenticationProvider() {
        DaoAuthenticationProvider authProvider = new DaoAuthenticationProvider();
        System.out.println(UserListHandler.getUserDetails());
        authProvider.setUserDetailsService(UserListHandler.getUserDetails());
        return authProvider;
    }
    

    @Autowired
    public void configureGlobal(AuthenticationManagerBuilder auth) throws Exception {
        auth.authenticationProvider(authenticationProvider());
    }
}