package eu.epitech.java.entities.modules;

import eu.epitech.java.entities.Module;

import javax.persistence.Entity;

@Entity
public class FacebookModule extends Module {
    public FacebookModule() {
        super("Facebook", new MSettings_facebook());
    }
    
    @Override
    protected Class<? extends MSettings> getMSettings() {
        return MSettings_facebook.class;
    }

    public static class MSettings_facebook extends Module.MSettings {
        public Integer maxLength = 10;
    }
}
