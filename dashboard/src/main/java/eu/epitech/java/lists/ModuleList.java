package eu.epitech.java.lists;

import eu.epitech.java.entities.Module;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ModuleList extends JpaRepository<Module, Long> {
}
