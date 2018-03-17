package eu.epitech.java.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.social.connect.ConnectionRepository;
import org.springframework.social.facebook.api.Facebook;
import org.springframework.social.facebook.api.PagedList;
import org.springframework.social.facebook.api.Reference;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;


@Controller
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

    @RequestMapping(value = "/facebook", method = RequestMethod.GET)
    public String getFacebook(Model model)
    {
        if (connectionRepository.findPrimaryConnection(Facebook.class) == null) {
            return "redirect:/connect/facebook";
        }

        model.addAttribute("facebookProfile", facebook.userOperations().getUserProfile());
        PagedList<Reference> friends = facebook.friendOperations().getFriends();
        model.addAttribute("friends", friends);

        return "facebook";
    }
}