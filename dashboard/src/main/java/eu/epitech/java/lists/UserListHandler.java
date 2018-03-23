package eu.epitech.java.lists;

import eu.epitech.java.entities.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class UserListHandler {
    @Autowired
    private UserList UserList;

    public List<User> getUsers() {
        return UserList.findAll();
    }

    public User getUser(final String name) {
        List<User> list = UserList.findAll();
        for (User current : list) {
            if (current.getUsername().equals(name))
                return current;
        }
       return null;
    }

    public void addUser(final String username, final String password, boolean admin) throws UHLException {
        if (getUser(username) != null)
            throw new UHLException("user already exists");
        User nuser = new User(username, password, admin);
        UserList.save(nuser);
    }

    public void delUser(final String username) throws UHLException {
        User target = null;
        if ((target = getUser(username)) == null)
            throw new UHLException("user not found");
        UserList.delete(target);
    }

    static public class UHLException extends Exception {
        public UHLException(String what) {
            super(what);
        }
    }
}
