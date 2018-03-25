package eu.epitech.java.entities;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import javax.persistence.*;
import java.io.IOException;
import java.io.Serializable;

@Entity
@Inheritance(strategy=InheritanceType.SINGLE_TABLE)
@Table(name = "modules")
public abstract class Module implements Serializable {
    @Id
    @GeneratedValue
    private long id;
    @Column(name = "name", nullable = false, unique = false)
    private String name;

    private String owner = null;

    protected String settings;

    protected abstract Class<? extends MSettings> getMSettings();

    public void setOwner(User _owner)
    {
        this.owner = _owner.getUsername();
    }

    public String getOwner()
    {
        return this.owner;
    }

    public boolean setSettings(MSettings _settings) {
        ObjectMapper mapper = new ObjectMapper();
        mapper.enableDefaultTyping();
        try {
            String ret = mapper.writeValueAsString(_settings);
            this.settings = ret;
            return true;
        } catch (JsonProcessingException ex) {
            return false;
        }
    }

    protected Module(String _name, MSettings _settings)
    {
        this.name = _name;
        this.setSettings(_settings);
    }

    public String getName()
    {
        return this.name;
    }

    @Transient
    public MSettings getSettings() {
        ObjectMapper mapper = new ObjectMapper();
        mapper.enableDefaultTyping();
        try {
            if (this.settings == null)
                return null;
            MSettings ret = mapper.readValue(this.settings, this.getMSettings());
            return ret;
        } catch (JsonProcessingException ex) {
            System.out.println(ex.getMessage());
            return null;
        } catch (IOException ex) {
            System.out.println(ex.getMessage());
            return null;
        }
    }

    @Transient
    public MSettings checkSettings(final String config) {
        ObjectMapper mapper = new ObjectMapper();
        mapper.enableDefaultTyping();
        try {
            if (config == null)
                return null;
            MSettings ret = mapper.readValue(config, this.getMSettings());
            return ret;
        } catch (JsonProcessingException ex) {
            System.out.println(ex.getMessage());
            return null;
        } catch (IOException ex) {
            System.out.println(ex.getMessage());
            return null;
        }
    }

    public static abstract class MSettings {
        public Integer refresh = 15;
    }
}
