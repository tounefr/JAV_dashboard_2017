package eu.epitech.java.controller;

import eu.epitech.java.entities.Module;
import eu.epitech.java.entities.User;
import eu.epitech.java.lists.UserListHandler;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;
import javax.servlet.http.HttpServletRequest;
import java.util.List;
import java.util.Set;

@RestController
public class UserController {

    @Autowired
    private UserListHandler UserListHandler;
    
    private boolean canAccess(HttpServletRequest req, final String target) {
        if ((target != null && target.equals(req.getUserPrincipal().getName())) || req.isUserInRole("ADMIN"))
            return true;
        return false;
    }


    static private class UserPLY {
        public String username;
        public String password;
    }

    @RequestMapping(value = "/users/register",
            method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_VALUE)
    @ResponseBody
    public String registerUser(HttpServletRequest req,
                               @RequestBody UserPLY payload)
    {
        System.out.println("user: ");
        System.out.println(payload.username);
        try {
            if (payload.username == null || payload.password == null) {
                return "invalid payload";
            }
            UserListHandler.addUser(payload.username, payload.password, false);
        } catch (UserListHandler.UHLException e) {
            return "error: " + e.getMessage();
        }

        return "successfully created user " + payload.username;
    }
    
    //@RequestMapping("/users/{userID}/modules")
    @RequestMapping("/users")
    @ResponseBody
    //public String getUserModules(@PathVariable(value = "userID") String id)
    public String getUsers(HttpServletRequest req)
    {
        if (!this.canAccess(req, null))
            return "this action requires elevated privileges";
/*        try {
            
        } catch (UserListHandler.UHLException e) {

        } */
        String res= "";
        res += "Here is a list of available users:<br>";
        List<User> list = UserListHandler.getUsers();
        for (User current : list) {
            res += "-" + current.getUsername() + "<br>";
        }

        return res;
    }

    @RequestMapping("/users/{userID}")
    @ResponseBody
    public String getUser(HttpServletRequest req, @PathVariable(value = "userID") String id)
    {
        if (!this.canAccess(req, id))
            return "this action requires elevated privileges";
        User target = UserListHandler.getUser(id);
        if (target == null) return "user not found";

        return "k user found";
    }

    @RequestMapping("/users/{userID}/modules")
    @ResponseBody
    public String getUserModules(HttpServletRequest req, @PathVariable(value = "userID") String id)
    {
        if (!this.canAccess(req, id))
            return "this action requires elevated privileges";
        User target = UserListHandler.getUser(id);
        if (target == null) return "user not found";

        String res = "user's modules:<br>";
        Set<Module> modules = target.getModules();
        for (Module current : modules) {
            res += "-" + current.getName() + "<br>";
        }
        return res;
    }

    @RequestMapping("/users/{userID}/modules/{moduleID}")
    @ResponseBody
    public String getUserModule(HttpServletRequest req,
                                @PathVariable(value = "userID") String id,
                                @PathVariable(value = "moduleID") String module)
    {
        if (!this.canAccess(req, id))
            return "this action requires elevated privileges";
        User target = UserListHandler.getUser(id);
        if (target == null) return "user not found";

        String res = "target module:<br>";
        Set<Module> modules = target.getModules();
        Module tmodule = null;
        for (Module current : modules) {
            if (current.getName().equals(module))
                tmodule = current;
        }
        if (tmodule == null)
            return "unable to find requested module in user's modules list";
        res += tmodule.getName();
        return res;
    }

    @RequestMapping("/users/{userID}/modules/{moduleID}/subscribe")
    @ResponseBody
    public String subUserModule(HttpServletRequest req,
                                @PathVariable(value = "userID") String id,
                                @PathVariable(value = "moduleID") String module)
    {
        if (!this.canAccess(req, id))
            return "this action requires elevated privileges";
        User target = UserListHandler.getUser(id);
        if (target == null) return "user not found";
        
        Set<Module> modules = target.getModules();
        Module tmodule = null;
        for (Module current : modules) {
            if (current.getName().equals(module))
                tmodule = current;
        }
        if (tmodule == null)
            return "unable to find requested module in user's modules list";
        if (!target.subscribe(tmodule))
            return "unable to subscribe to module: " + tmodule.getName();
        return "successfully subscribed to the module: " + tmodule.getName();
    }
}
