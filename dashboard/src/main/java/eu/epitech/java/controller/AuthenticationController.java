package eu.epitech.java.controller;

import org.omg.Messaging.SYNC_WITH_TRANSPORT;
import org.springframework.security.authentication.AnonymousAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.authentication.logout.SecurityContextLogoutHandler;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@RestController
public class AuthenticationController {

    @RequestMapping("/login")
    @ModelAttribute //FIXME c'est juste pour afficher la view pour debug sans angular mais il faut enlever l'annotation pour passer en mode rest
    public String loginPage () {
        if (SecurityContextHolder.getContext().getAuthentication().getPrincipal() != null) {
            Authentication auth = SecurityContextHolder.getContext().getAuthentication();
            if (!(auth instanceof AnonymousAuthenticationToken)) {
                System.out.println("ALREADY LOGGED IN");
                return "/"; // TODO ici à cause du ModelAttribute ça redirect pas, mais ici faut redirect l'user car déjà authentifié :)
            }
        }
        return "login";
    }

    @RequestMapping("/login-error")
    @ResponseBody
    public String getError()
    {
        return "invalid credentials<br/><a href=\"/login\">Try again</a>";
    }

    // ça c'est juste à titre d'exemple pour par oublier ce qu'on peut faire d'un point de vue API
    @RequestMapping("/get/{userID}")
    @ResponseBody
    public String getUser(@PathVariable(value = "userID") String id)
    {
        return ("user id  = " + id + ".");
    }
}
