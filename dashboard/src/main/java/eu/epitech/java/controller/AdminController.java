package eu.epitech.java.controller;

import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.RestController;
import eu.epitech.java.entities.Module;
import eu.epitech.java.entities.User;
import eu.epitech.java.lists.UserListHandler;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import javax.servlet.http.HttpServletRequest;
import java.util.List;
import java.util.Set;

@RestController
public class AdminController {
    @Autowired
    private UserListHandler UserListHandler;


    static private class UserPLY {
        public String username;
        public String password;
        public boolean admin;
    }

    @RequestMapping(value = "/admin/adduser",
            method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_VALUE)
    @ResponseBody
    public String addUser(HttpServletRequest req,
                          @RequestBody UserPLY payload)
    {
        System.out.println("user: ");
        System.out.println(payload.username);
        try {
            if (payload.username == null || payload.password == null) {
                return "invalid payload";
            }
            UserListHandler.addUser(payload.username, payload.password, payload.admin);
        } catch (UserListHandler.UHLException e) {
            return "error: " + e.getMessage();
        }
        
        return "successfully added user " + payload.username;
    }

    @RequestMapping(value = "/admin/deluser/{userID}",
            method = RequestMethod.DELETE)
    @ResponseBody
    public String delUser(HttpServletRequest req, @PathVariable(value = "userID") String id) {
        try {
            if (id == null) {
                return "invalid payload";
            }
            UserListHandler.delUser(id);
        } catch (UserListHandler.UHLException e) {
            return "error: " + e.getMessage();
        }

        return "successfully removed user " + id;
    }
}
