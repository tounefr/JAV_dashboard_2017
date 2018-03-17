package eu.epitech.java.controller;

/*
 * Fichier destiné à la gestion des routes via des "crontroleurs"
 */

import java.text.DateFormat;
import java.util.Date;
import java.util.Locale;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.social.connect.ConnectionRepository;
import org.springframework.social.twitter.api.CursoredList;
import org.springframework.social.twitter.api.Twitter;
import org.springframework.social.twitter.api.TwitterProfile;
import org.springframework.ui.Model;
import eu.epitech.java.service.TwitterService;
import eu.epitech.java.model.TwitterModel;

@Controller
public class FeedController
{
    private TwitterService twitterService;

    @Autowired
    public FeedController(TwitterService twitterService)
    {
        this.twitterService = twitterService;
    }

    @RequestMapping(value = "/", method = RequestMethod.GET)
    public String getIndex(Model model)
    {
        TwitterModel twitter = twitterService.getTwitterDatas();
        model.addAttribute("twitter", twitter);

        return "feed";
    }

    @RequestMapping("/teub")
    @ResponseBody
    public String getTeub()
    {
        return "teub";
    }

    @RequestMapping("/get/{userID}")
    @ResponseBody
    public String getUser(@PathVariable(value = "userID") String id)
    {
        return ("user id  = " + id + ".");
    }
}