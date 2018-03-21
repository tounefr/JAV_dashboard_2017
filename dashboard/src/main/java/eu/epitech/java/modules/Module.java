package eu.epitech.java.modules;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;
import java.io.Serializable;

@Entity
@Table(name = "modules")
public class Module implements Serializable{
    @Id
    @GeneratedValue
    private long id;
}
