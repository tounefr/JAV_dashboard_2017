package eu.epitech.java.entities.modules;

import eu.epitech.java.entities.Module;

import javax.persistence.Entity;

@Entity
public class TestModule extends Module {
    public TestModule() {
        super("Test");
    }

    public static class MSettings_test extends Module.MSettings {
        Integer maxLength = 10;
    }
}
