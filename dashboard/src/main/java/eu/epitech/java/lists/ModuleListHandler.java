package eu.epitech.java.lists;

import eu.epitech.java.entities.Module;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class ModuleListHandler {
    @Autowired
    private ModuleList ModuleList;

    public List<Module> getModules() {
        return ModuleList.findAll();
    }
}
