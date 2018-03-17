package eu.epitech.java.controller;

/*
 * Fichier destiné à la gestion des routes via des "crontroleurs"
 *
*/


import java.text.DateFormat;
import java.util.Date;
import java.util.Locale;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.social.twitter.api.*;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.social.connect.ConnectionRepository;
import org.springframework.ui.Model;

import java.util.List;

@Controller
public class Routing {

    @Controller
    public class bController {
        @RequestMapping(value = "/", method = RequestMethod.GET)
        public String getIndex() {
            return "feed";
        }

        @RequestMapping("/teub")
        @ResponseBody
        public String getTeub() {
            return "teub";
        }

        @RequestMapping("/get/{userID}")
        @ResponseBody
        public String getUser(@PathVariable(value = "userID") String id) {
            return ("user id  = " + id + ".");
        }
    }

    @Controller
    public class TwitterController {

        private Twitter twitter;

        private ConnectionRepository connectionRepository;

        @Autowired
        public TwitterController(Twitter twitter, ConnectionRepository connectionRepository) {
            this.twitter = twitter;
            this.connectionRepository = connectionRepository;
        }

        @RequestMapping(value = "/twitter", method = RequestMethod.GET)
        public String getTwitter(Model model) {
            if (connectionRepository.findPrimaryConnection(Twitter.class) == null) {
                return "redirect:/connect/twitter";
            }

			model.addAttribute(twitter.userOperations().getUserProfile());
			CursoredList<TwitterProfile> friends = twitter.friendOperations().getFriends();
			model.addAttribute("friends", friends);
            List<Tweet> mentions = twitter.timelineOperations().getMentions();
            model.addAttribute("mentions", mentions);
			return "twitter";
		}
    }
}
