package eu.epitech.java.controller;

import org.springframework.security.authentication.AnonymousAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@RestController
public class AuthenticationController {

    @RequestMapping("/login")
    public String loginPage (HttpServletRequest req, HttpServletResponse res) {
        if (SecurityContextHolder.getContext().getAuthentication().getPrincipal() != null) {
            Authentication auth = SecurityContextHolder.getContext().getAuthentication();
            if (!(auth instanceof AnonymousAuthenticationToken)) {
                System.out.println("ALREADY LOGGED IN");
                res.setHeader("Location:", req.getHeader("referer"));
                return null;
            }
        }
        return null;
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
