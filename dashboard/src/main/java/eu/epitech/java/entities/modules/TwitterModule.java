package eu.epitech.java.entities.modules;

import eu.epitech.java.entities.Module;

import javax.persistence.Entity;

@Entity
public class TwitterModule extends Module {
    public TwitterModule() {
        super("Twitter", new MSettings_twitter());
    }

    @Override
    protected Class<? extends MSettings> getMSettings() {
        return MSettings_twitter.class;
    }

    public static class MSettings_twitter extends Module.MSettings {
        public Integer maxLength = 10;
    }
}
