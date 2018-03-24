package eu.epitech.java.controller;

import eu.epitech.java.entities.Module;
import eu.epitech.java.lists.UserList;
import eu.epitech.java.lists.ModuleList;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.security.authentication.AnonymousAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class AuthenticationController {

    @RequestMapping("/login")
    @ResponseBody
    @ModelAttribute //FIXME c'est juste pour afficher la view pour debug sans angular mais il faut enlever l'annotation pour passer en mode rest
    public String loginPage () {
        if (SecurityContextHolder.getContext().getAuthentication().getPrincipal() != null) {
            Authentication auth = SecurityContextHolder.getContext().getAuthentication();
            if (!(auth instanceof AnonymousAuthenticationToken)) {
                System.out.println("ALREADY LOGGED IN");
                //return "/"; // TODO ici à cause du ModelAttribute ça redirect pas, mais ici faut redirect l'user car déjà authentifié :)
            }
        }
        return "success";
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

    @Autowired
    String testSingleTon;

    @Autowired
    UserList UserList;

    @Autowired
    ModuleList ModuleList;
    
    @RequestMapping("/modules")
    public String getModuleList() {

        System.out.println("===========================");
        System.out.println(testSingleTon);
        System.out.println(UserList.count());
        String res= "";
        res += "Here is a list of available modules:<br>";
        List<Module> list = ModuleList.findAll();
        for (Module current : list) {
            res += "-" + current.getName() + "<br>";
        }
        return res;
    }
}
