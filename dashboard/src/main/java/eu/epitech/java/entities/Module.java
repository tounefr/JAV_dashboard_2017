package eu.epitech.java.entities;

import javax.persistence.MappedSuperclass;
import javax.persistence.Inheritance;
import javax.persistence.InheritanceType;
import javax.persistence.Entity;
import javax.persistence.Table;
import javax.persistence.Id;
import javax.persistence.GeneratedValue;
import javax.persistence.Column;
import java.io.Serializable;

//@MappedSuperclass
//@Inheritance(strategy = InheritanceType.JOINED)
@Entity
@Inheritance
@Table(name = "modules")
public abstract class Module implements Serializable{
    @Id
    @GeneratedValue
    private long id;
    @Column(name = "name")
    private String name;

    public Module(String _name)
    {
        this.name = _name;
    }

    public String getName()
    {
        return this.name;
    }
}
