package eu.epitech.java.controller;

import eu.epitech.java.controller.rest.GenericResponse;
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
        System.out.println("PBEUUU:");
        System.out.println(req.getUserPrincipal().toString());
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
                return GenericResponse.error(GenericResponse.buildErrorPLY(400,
                        "Bad request", "Invalid payload"), req.getRequestURI());
            }
            UserListHandler.addUser(payload.username, payload.password, false);
        } catch (UserListHandler.UHLException e) {
            return GenericResponse.error(GenericResponse.buildErrorPLY(503,
                    "Unknown error", "\"error: \" + e.getMessage()"), req.getRequestURI());
        }

        return GenericResponse.success(GenericResponse.buildSuccessPLY(
                "successfully created user " + payload.username), req.getRequestURI());
    }

    @RequestMapping("/users")
    @ResponseBody
    public String getUsers(HttpServletRequest req)
    {
        if (!this.canAccess(req, null))
            return GenericResponse.error(GenericResponse.buildErrorPLY(401,
                    "Unauthorized", "This action requires elevated privileges"), req.getRequestURI());
        String res= "";
        res += "Here is a list of available users:<br>";
        List<User> list = UserListHandler.getUsers();
        for (User current : list) {
            res += "-" + current.getUsername() + "<br>";
        }

        return GenericResponse.success(GenericResponse.buildSuccessPLY(
                res), req.getRequestURI());
    }

    @RequestMapping("/users/{userID}")
    @ResponseBody
    public String getUser(HttpServletRequest req, @PathVariable(value = "userID") String id)
    {
        if (!this.canAccess(req, id))
            return GenericResponse.error(GenericResponse.buildErrorPLY(401,
                    "Unauthorized", "This action requires elevated privileges"), req.getRequestURI());
        User target = UserListHandler.getUser(id);
        if (target == null)
            return GenericResponse.error(GenericResponse.buildErrorPLY(400,
                    "Bad request", "User not found"), req.getRequestURI());

        return GenericResponse.success(GenericResponse.buildSuccessPLY(
                "k user found"), req.getRequestURI());
    }

    @RequestMapping("/users/{userID}/modules")
    @ResponseBody
    public String getUserModules(HttpServletRequest req, @PathVariable(value = "userID") String id)
    {
        if (!this.canAccess(req, id))
            return GenericResponse.error(GenericResponse.buildErrorPLY(401,
                    "Unauthorized", "This action requires elevated privileges"), req.getRequestURI());
        User target = UserListHandler.getUser(id);
        if (target == null)
            return GenericResponse.error(GenericResponse.buildErrorPLY(400,
                    "Bad request", "User not found"), req.getRequestURI());

        String res = "user's modules:<br>";
        Set<Module> modules = target.getModules();
        for (Module current : modules) {
            res += "-" + current.getName() + "<br>";
        }
        return GenericResponse.success(GenericResponse.buildSuccessPLY(
                res), req.getRequestURI());
    }

    @RequestMapping("/users/{userID}/modules/{moduleID}")
    @ResponseBody
    public String getUserModule(HttpServletRequest req,
                                @PathVariable(value = "userID") String id,
                                @PathVariable(value = "moduleID") String module)
    {
        if (!this.canAccess(req, id))
            return GenericResponse.error(GenericResponse.buildErrorPLY(401,
                    "Unauthorized", "This action requires elevated privileges"), req.getRequestURI());
        User target = UserListHandler.getUser(id);
        if (target == null)
            return GenericResponse.error(GenericResponse.buildErrorPLY(400,
                    "Bad request", "User not found"), req.getRequestURI());

        String res = "target module:<br>";
        Set<Module> modules = target.getModules();
        Module tmodule = null;
        for (Module current : modules) {
            if (current.getName().equals(module))
                tmodule = current;
        }
        if (tmodule == null)
        return GenericResponse.error(GenericResponse.buildErrorPLY(400,
                "Bad request",
                "unable to find requested module in user's modules list"), req.getRequestURI());
       // res += tmodule.getName();
        return GenericResponse.success(GenericResponse.buildSuccessPLY(
                tmodule), req.getRequestURI());
    }

    @RequestMapping("/users/{userID}/modules/{moduleID}/subscribe")
    @ResponseBody
    public String subUserModule(HttpServletRequest req,
                                @PathVariable(value = "userID") String id,
                                @PathVariable(value = "moduleID") String module)
    {
        if (!this.canAccess(req, id))
            return GenericResponse.error(GenericResponse.buildErrorPLY(401,
                    "Unauthorized", "This action requires elevated privileges"), req.getRequestURI());
        User target = UserListHandler.getUser(id);
        if (target == null)
            return GenericResponse.error(GenericResponse.buildErrorPLY(400,
                    "Bad request", "User not found"), req.getRequestURI());
        
        Set<Module> modules = target.getModules();
        Module tmodule = null;
        for (Module current : modules) {
            if (current.getName().equals(module))
                tmodule = current;
        }
        if (tmodule == null)
            return GenericResponse.error(GenericResponse.buildErrorPLY(400,
                    "Bad request",
                    "unable to find requested module in user's modules list"), req.getRequestURI());
        if (!target.subscribe(tmodule))
            return GenericResponse.error(GenericResponse.buildErrorPLY(503,
                    "Unknown error",
                    "unable to subscribe to module: " + tmodule.getName()), req.getRequestURI());
        return GenericResponse.success(GenericResponse.buildSuccessPLY(
                "successfully subscribed to the module: " + tmodule.getName()), req.getRequestURI());
    }

    public class test {
        public String name = "yes";
    }

    @RequestMapping("/test")
    @ResponseBody
    public String test() {
        String succ = GenericResponse.success(GenericResponse.buildSuccessPLY(new test()), "/");
        return succ;
    }
}
