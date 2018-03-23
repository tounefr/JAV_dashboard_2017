package eu.epitech.java.entities;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Column;
import javax.persistence.Table;
import javax.persistence.ElementCollection;
import java.io.Serializable;
import java.util.Map;
import java.util.Set;

@Entity
@Table(name = "users")
public class User implements Serializable {
    @Id
    @GeneratedValue
    private long id;

    @Column(name = "modules")
    @ElementCollection
    private Set<Module> modules;

    public boolean subscribe(Module target) {
        return this.modules.add(target);
    }

    public boolean unsubscribe(String target) {
        return true;
    }
}
