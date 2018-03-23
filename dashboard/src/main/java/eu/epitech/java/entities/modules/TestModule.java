package eu.epitech.java.entities.modules;

import eu.epitech.java.entities.Module;

import javax.persistence.Entity;

@Entity
public class TestModule extends Module {
    public TestModule() {
        super("Test");
    }
}
