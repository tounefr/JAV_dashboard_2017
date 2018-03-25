package eu.epitech.java.controller;

import eu.epitech.java.controller.rest.GenericResponse;
import eu.epitech.java.entities.Module;
import eu.epitech.java.entities.User;
import eu.epitech.java.lists.ModuleListHandler;
import eu.epitech.java.lists.UserListHandler;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.List;
import java.util.Set;

@RestController
public class ModuleController {

    @Autowired
    private ModuleListHandler ModuleListHandler;

    @RequestMapping("/modules")
    @ResponseBody
    public String getModules(HttpServletRequest req, HttpServletResponse resp)
    {
        List<Module> modules = ModuleListHandler.getModules();
        return GenericResponse.success(resp, GenericResponse.buildSuccessPLY(
                modules), req.getRequestURI());
    }

    @Autowired
    UserListHandler UserListHandler;


    @RequestMapping("/modules/{moduleID}/subscribe")
    @ResponseBody
    public String subModule(HttpServletRequest req, HttpServletResponse resp,
                                @PathVariable(value = "moduleID") String module)
    {
        User target = UserListHandler.getUser(req.getUserPrincipal().getName());
        if (target == null)
            return GenericResponse.error(resp, GenericResponse.buildErrorPLY(400,
                    "User not found"), req.getRequestURI());

        Module tmodule = ModuleListHandler.getModule(module, null);

        if (tmodule == null)
            return GenericResponse.error(resp, GenericResponse.buildErrorPLY(400,
                    "unknown module"), req.getRequestURI());

        Set<Module> modules = target.getModules();
        for (Module current : modules) {
            if (current.getName().equals(module))
                return GenericResponse.error(resp, GenericResponse.buildErrorPLY(400,
                        "You already subscribed to this module"), req.getRequestURI());
        }
        Module add = null;

        try {
            add = ModuleListHandler.addModule(tmodule, target);
        } catch (ModuleListHandler.MLHException e){
            System.out.println("Registering user module: " + e.getMessage());
        }

        if (add == null || !target.subscribe(add))
            return GenericResponse.error(resp, GenericResponse.buildErrorPLY(503,
                    "unable to subscribe to module: " + tmodule.getName()), req.getRequestURI());
        UserListHandler.commit(target);
        return GenericResponse.success(resp, GenericResponse.buildSuccessPLY(
                "successfully subscribed to the module: " + tmodule.getName()), req.getRequestURI());
    }

    @RequestMapping("/modules/{moduleID}/unsubscribe")
    @ResponseBody
    public String unsubModule(HttpServletRequest req, HttpServletResponse resp,
                                @PathVariable(value = "moduleID") String module)
    {
        User target = UserListHandler.getUser(req.getUserPrincipal().getName());
        if (target == null)
            return GenericResponse.error(resp, GenericResponse.buildErrorPLY(400,
                    "User not found"), req.getRequestURI());

        Module tmodule = ModuleListHandler.getModule(module, null);

        if (tmodule == null)
            return GenericResponse.error(resp, GenericResponse.buildErrorPLY(400,
                    "unknown module"), req.getRequestURI());

        Set<Module> modules = target.getModules();
        tmodule = null;
        for (Module current : modules) {
            if (current.getName().equals(module)) {
                tmodule = current;
                break;
            }
        }

        if (tmodule == null)
        return GenericResponse.error(resp, GenericResponse.buildErrorPLY(400,
                "You ain't subscribed to this module"), req.getRequestURI());

        if (!target.unsubscribe(tmodule.getName()))
            return GenericResponse.error(resp, GenericResponse.buildErrorPLY(503,
                    "unable to unsubscribe from module: " + tmodule.getName()), req.getRequestURI());
        ModuleListHandler.delModule(tmodule);
        UserListHandler.commit(target);
        return GenericResponse.success(resp, GenericResponse.buildSuccessPLY(
                "successfully unsubscribed from the module: " + tmodule.getName()), req.getRequestURI());
    }

    @RequestMapping("/debugmodules")
    @ResponseBody
    public String ModSize(HttpServletRequest req, HttpServletResponse resp)
    {
        return GenericResponse.success(resp, GenericResponse.buildSuccessPLY(
                ModuleListHandler.getSize()), req.getRequestURI());
    }

}
