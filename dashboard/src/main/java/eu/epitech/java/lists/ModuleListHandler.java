package eu.epitech.java.lists;

import eu.epitech.java.entities.Module;
import eu.epitech.java.entities.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import java.util.ArrayList;
import java.util.List;

@Component
public class ModuleListHandler {
    @Autowired
    private ModuleList ModuleList;

    public Integer getSize() {
        return ModuleList.findAll().size();
    }

    public List<Module> getModules() {
        List<Module> old = ModuleList.findAll();
        List<Module> ret = new ArrayList<Module>();

        for (Module current : old) {
            if (current.getOwner() == null)
                ret.add(current);
        }
        return ret;
    }

    public Module getModule(final String name, final User owner) {
        List<Module> list = this.getModules();
        for (Module current : list) {
            if (current.getName().equals(name) &&
                    (owner == null || (current.getOwner() != null && current.getOwner().equals(owner.getUsername()))))
                return current;
        }
        return null;
    }

    public Module addModule(final Module nmodule, final User owner) throws MLHException {
        if (getModule(nmodule.getName(), owner) != null)
            throw new ModuleListHandler.MLHException("module already exists");
        Module add = nmodule;
        if (owner != null) {
            try {
                add = nmodule.getClass().newInstance();
                add.setOwner(owner);
            } catch (InstantiationException e) {
                System.out.println(e.getMessage());
                throw new MLHException("Unknown error");
            } catch (IllegalAccessException e) {
                System.out.println(e.getMessage());
                throw new MLHException("Unknown error");
            }
        }
        ModuleList.save(add);
        return add;
    }

    public void delModule(final Module target) {
        ModuleList.delete(target);
    }

    public class MLHException extends Exception {
        public MLHException(String what) {
            super(what);
        }
    }
}
