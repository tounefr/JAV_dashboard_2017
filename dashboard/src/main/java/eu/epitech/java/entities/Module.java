package eu.epitech.java.entities;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import javax.persistence.Inheritance;
import javax.persistence.InheritanceType;
import javax.persistence.Entity;
import javax.persistence.Table;
import javax.persistence.Id;
import javax.persistence.GeneratedValue;
import javax.persistence.Column;
import java.io.Serializable;

@Entity
@Inheritance(strategy=InheritanceType.SINGLE_TABLE)
@Table(name = "modules")
public abstract class Module implements Serializable{
    @Id
    @GeneratedValue
    private long id;
    @Column(name = "name")
    private String name;

    protected String settings;

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

    protected Module(String _name)
    {
        this.name = _name;
    }

    public String getName()
    {
        return this.name;
    }

    public static abstract class MSettings {
        public Integer refresh = 15;
    }
}
