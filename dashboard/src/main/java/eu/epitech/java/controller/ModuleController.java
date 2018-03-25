package eu.epitech.java.controller;

import eu.epitech.java.controller.rest.GenericResponse;
import eu.epitech.java.entities.Module;
import eu.epitech.java.lists.ModuleListHandler;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.List;

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
}
