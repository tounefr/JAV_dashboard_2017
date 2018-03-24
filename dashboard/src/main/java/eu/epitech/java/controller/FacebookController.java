package eu.epitech.java.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.social.connect.ConnectionRepository;
import org.springframework.social.facebook.api.Facebook;
import org.springframework.social.facebook.api.PagedList;
import org.springframework.social.facebook.api.Reference;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.social.facebook.api.User;

@RestController
public class FacebookController
{

    private Facebook facebook;
    private ConnectionRepository connectionRepository;

    @Autowired
    public FacebookController(Facebook facebook, ConnectionRepository connectionRepository)
    {
        this.facebook = facebook;
        this.connectionRepository = connectionRepository;
    }

    @RequestMapping(value = "/facebook/profile", method = RequestMethod.GET)
    public User getProfile()
    {
        return facebook.userOperations().getUserProfile();
    }

    @RequestMapping(value = "/facebook/friends", method = RequestMethod.GET)
    public PagedList<Reference> getFriends()
    {
        return facebook.friendOperations().getFriends();
    }
}