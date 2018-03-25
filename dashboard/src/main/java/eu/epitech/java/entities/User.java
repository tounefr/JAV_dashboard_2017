package eu.epitech.java.entities;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Set;

@Entity
@Table(name = "users")
public class User implements Serializable {
    @Id
    @GeneratedValue
    private long id;
    @Column(nullable = false, unique = true)
    private String username;
    private String password;
    private boolean admin;

    @Column(name = "modules")
    @CollectionTable(joinColumns=@JoinColumn(name="owner"))
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
    public String getPassword() {
        return this.password;
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
