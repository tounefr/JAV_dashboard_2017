package eu.epitech.java.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.social.twitter.api.Tweet;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.social.connect.ConnectionRepository;
import org.springframework.social.twitter.api.CursoredList;
import org.springframework.social.twitter.api.Twitter;
import org.springframework.social.twitter.api.TwitterProfile;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class TwitterController
{
    private Twitter twitter;
    protected ConnectionRepository connectionRepository;

    @Autowired
    public TwitterController(Twitter twitter, ConnectionRepository connectionRepository)
    {
        this.twitter = twitter;
        this.connectionRepository = connectionRepository;
    }

    @CrossOrigin(origins = "http://localhost:4200")
    @RequestMapping(value = "/twitter/friends")
    public CursoredList<TwitterProfile> getFriends()
    {
        return twitter.friendOperations().getFriends();
    }

    @CrossOrigin(origins = "http://localhost:4200")
    @RequestMapping(value = "/twitter/my-tweets")
    public List<Tweet> getMyTweets()
    {
        return twitter.timelineOperations().getHomeTimeline();
    }

    @CrossOrigin(origins = "http://localhost:4200")
    @RequestMapping(value = "/twitter/mentions")
    public List<Tweet> getMentions()
    {
        return twitter.timelineOperations().getMentions();
    }

    @CrossOrigin(origins = "http://localhost:4200")
    @RequestMapping(value = "/twitter/followers", method = RequestMethod.GET)
    public CursoredList<TwitterProfile>  getTwitter()
    {
        return twitter.friendOperations().getFollowers();
    }

}