package eu.epitech.java.controller;

import eu.epitech.java.controller.rest.GenericResponse;
import eu.epitech.java.entities.Module;
import eu.epitech.java.entities.User;
import eu.epitech.java.lists.UserListHandler;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
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

    private String errorElevated(final HttpServletRequest req, HttpServletResponse resp) {
        return GenericResponse.error(resp, GenericResponse.buildErrorPLY(401,
                "This action requires elevated privileges"), req.getRequestURI());
    }

    static private class UserPLY {
        public String username;
        public String password;
    }

    @RequestMapping(value = "/users/register",
            method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_VALUE)
    @ResponseBody
    public String registerUser(HttpServletRequest req, HttpServletResponse resp,
                               @RequestBody UserPLY payload)
    {
        try {
            if (payload.username == null || payload.password == null) {
                return GenericResponse.error(resp, GenericResponse.buildErrorPLY(400,
                        "Invalid payload"), req.getRequestURI());
            }
            UserListHandler.addUser(payload.username, payload.password, false);
        } catch (UserListHandler.UHLException e) {
            return GenericResponse.error(resp, GenericResponse.buildErrorPLY(400,
                    "error: "  + e.getMessage()), req.getRequestURI());
        }

        return GenericResponse.success(resp, GenericResponse.buildSuccessPLY(
                "successfully created user " + payload.username), req.getRequestURI());
    }

    @RequestMapping("/users")
    @ResponseBody
    public String getUsers(HttpServletRequest req, HttpServletResponse resp)
    {
        if (!this.canAccess(req, null))
            return errorElevated(req, resp);

        List<User> list = UserListHandler.getUsers();

        return GenericResponse.success(resp, GenericResponse.buildSuccessPLY(
                list), req.getRequestURI());
    }

    @RequestMapping("/users/{userID}")
    @ResponseBody
    public String getUser(HttpServletRequest req, HttpServletResponse resp,
                          @PathVariable(value = "userID") String id)
    {
        //aresp.setContentType("application/json");
        if (!this.canAccess(req, id))
            return errorElevated(req, resp);

        User target = UserListHandler.getUser(id);
        if (target == null)
            return GenericResponse.error(resp, GenericResponse.buildErrorPLY(400,
                     "User not found"), req.getRequestURI());

        return GenericResponse.success(resp, GenericResponse.buildSuccessPLY(
                target), req.getRequestURI());
    }

    @RequestMapping("/users/{userID}/modules")
    @ResponseBody
    public String getUserModules(HttpServletRequest req, HttpServletResponse resp,
                                 @PathVariable(value = "userID") String id)
    {
        if (!this.canAccess(req, id))
            return errorElevated(req, resp);

        User target = UserListHandler.getUser(id);
        if (target == null)
            return GenericResponse.error(resp, GenericResponse.buildErrorPLY(400,
                    "User not found"), req.getRequestURI());

        String res = "user's modules:<br>";
        Set<Module> modules = target.getModules();
        for (Module current : modules) {
            res += "-" + current.getName() + "<br>";
        }
        return GenericResponse.success(resp, GenericResponse.buildSuccessPLY(
                modules), req.getRequestURI());
    }

    @RequestMapping("/users/{userID}/modules/{moduleID}")
    @ResponseBody
    public String getUserModule(HttpServletRequest req, HttpServletResponse resp,
                                @PathVariable(value = "userID") String id,
                                @PathVariable(value = "moduleID") String module)
    {
        if (!this.canAccess(req, id))
            return errorElevated(req, resp);

        User target = UserListHandler.getUser(id);
        if (target == null)
            return GenericResponse.error(resp, GenericResponse.buildErrorPLY(400,
                    "User not found"), req.getRequestURI());

        Set<Module> modules = target.getModules();
        Module tmodule = null;
        for (Module current : modules) {
            if (current.getName().equals(module))
                tmodule = current;
        }
        if (tmodule == null)
        return GenericResponse.error(resp, GenericResponse.buildErrorPLY(400,
                "unable to find requested module in user's modules list"), req.getRequestURI());
        return GenericResponse.success(resp, GenericResponse.buildSuccessPLY(
                tmodule), req.getRequestURI());
    }

    @RequestMapping(value = "/users/{userID}/modules/{moduleID}/config",
            method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_VALUE)
    @ResponseBody
    public String configModule(HttpServletRequest req, HttpServletResponse resp,
                                @PathVariable(value = "userID") String id,
                                @PathVariable(value = "moduleID") String module,
                                @RequestBody String payload)
    {
        if (!this.canAccess(req, id))
            return errorElevated(req, resp);

        User target = UserListHandler.getUser(id);
        if (target == null)
            return GenericResponse.error(resp, GenericResponse.buildErrorPLY(400,
                    "User not found"), req.getRequestURI());
        
        Set<Module> modules = target.getModules();
        Module tmodule = null;
        for (Module current : modules) {
            if (current.getName().equals(module))
                tmodule = current;
        }

        if (tmodule == null)
            return GenericResponse.error(resp, GenericResponse.buildErrorPLY(400,
                    "unable to find requested module in user's modules list"), req.getRequestURI());
        Module.MSettings config = tmodule.checkSettings(payload);
        if (config == null || !tmodule.setSettings(config))
            return GenericResponse.error(resp, GenericResponse.buildErrorPLY(503,
                    "unable to configure: " + tmodule.getName() + ": invalid payload."), req.getRequestURI());
        /*
        if (!target.subscribe(tmodule))
            return GenericResponse.error(resp, GenericResponse.buildErrorPLY(503,
                    "unable to subscribe to module: " + tmodule.getName()), req.getRequestURI());
                    */
        UserListHandler.commit(target);
        return GenericResponse.success(resp, GenericResponse.buildSuccessPLY(
                "successfully configured the module: " + tmodule.getName()), req.getRequestURI());
    }
}
