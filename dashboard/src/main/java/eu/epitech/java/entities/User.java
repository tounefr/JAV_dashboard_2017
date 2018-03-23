package eu.epitech.java.entities;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Column;
import javax.persistence.Table;
import javax.persistence.ElementCollection;
import java.io.Serializable;
import java.util.Set;

@Entity
@Table(name = "users")
public class User implements Serializable {
    @Id
    @GeneratedValue
    private long id;
    private String username;
    private String password;
    private boolean admin;

    @Column(name = "modules")
    @ElementCollection
    private Set<Module> modules;

    public User() {
        this.username = null;
        this.password = null;
        this.admin = false;
    }

    public User(final String _username, final String _password, boolean _admin) {
        this.username = _username;
        this.password = _password;
        this.admin = _admin;
    }

    public boolean subscribe(Module target) {
        return this.modules.add(target);
    }

    public boolean unsubscribe(String target) {
        for(Module current : this.modules) {
            if (current.getName().equals(target)) {
                return this.modules.remove(current);
            }
        }
        return false;
    }

    public String getUsername() {
        return this.username;
    }
    public boolean checkPass(final String _password) {
        return this.password.equals(_password);
    }
    public boolean isAdmin() {
        return this.admin;
    }

    public Set<Module> getModules() {
        return this.modules;
    }

}
