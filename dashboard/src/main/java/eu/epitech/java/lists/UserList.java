package eu.epitech.java.lists;

import eu.epitech.java.entities.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserList extends JpaRepository<User, Long>{
}
