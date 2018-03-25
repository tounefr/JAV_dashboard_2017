package eu.epitech.java.controller;

import eu.epitech.java.controller.rest.GenericResponse;
import org.springframework.security.authentication.AnonymousAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@RestController
public class AuthenticationController {

    @RequestMapping("/login")
    public String loginPage (HttpServletRequest req, HttpServletResponse resp) {
        if (SecurityContextHolder.getContext().getAuthentication().getPrincipal() != null) {
            Authentication auth = SecurityContextHolder.getContext().getAuthentication();
            if (!(auth instanceof AnonymousAuthenticationToken)) {
                return GenericResponse.success(resp, GenericResponse.buildSuccessPLY(
                        "successfully logged in, " + req.getUserPrincipal().getName()), req.getRequestURI());
            }
        }
        return null;
    }
}
