package eu.epitech.java.entities.modules;

import eu.epitech.java.entities.Module;

import javax.persistence.Entity;
import javax.persistence.Table;

@Entity
@Table(name = "modules")
public class TestModule extends Module {
    public TestModule() {
        super("Test");
    }
}
