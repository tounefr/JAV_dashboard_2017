package eu.epitech.java.lists;

import eu.epitech.java.entities.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;


@Component
public class UserListHandler {
    @Autowired
    private UserList UserList;

    public User commit(final User target) {
        return UserList.save(target);
    }
    
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

    /* Pour avoir une authentification spring des users à partir de la DB, et runtime compliant */

    public class UserRole implements GrantedAuthority {
        String role;
        public UserRole(final String _role) {
            this.role = _role;
        }
        @Override
        public String getAuthority() {
            return this.role;
        }
    }

    public class UserPrincipal implements UserDetails {
        private User user;

        public UserPrincipal(User _user) {
            this.user = _user;
        }

        @Override
        public Collection<? extends GrantedAuthority> getAuthorities() {
            List<UserRole> roles = new ArrayList<UserRole>();
            roles.add((user.isAdmin()) ? new UserRole("ROLE_ADMIN") : new UserRole("ROLE_USER"));
            return roles;
        }

        @Override
        public String getPassword() {
            return user.getPassword();
        }

        @Override
        public String getUsername() {
            return user.getUsername();
        }

        @Override
        public boolean isAccountNonExpired() {
            return true;
        }

        @Override
        public boolean isAccountNonLocked() {
            return true;
        }

        @Override
        public boolean isCredentialsNonExpired() {
            return true;
        }

        @Override
        public boolean isEnabled()
        {
            return true;
        }
    }

    private class DBUserDetailsService implements UserDetailsService {
        @Override
        public UserDetails loadUserByUsername(final String username) {
            User target = getUser(username);
            if (target == null) {
                throw new UsernameNotFoundException(username);
            }
            return new UserPrincipal(target);
        }

    }

    // pas déclaré de manière conventionnelle (en haut), car cas particulier
    private DBUserDetailsService UserDetails = new DBUserDetailsService();

    public DBUserDetailsService getUserDetails()
    {
        return this.UserDetails;
    }
}
