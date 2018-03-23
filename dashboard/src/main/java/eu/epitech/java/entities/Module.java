package eu.epitech.java.entities;

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

    private Integer refresh;

    public Module(String _name)
    {
        this.name = _name;
    }

    public String getName()
    {
        return this.name;
    }
}
