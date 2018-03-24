package eu.epitech.java.controller;

import eu.epitech.java.api.Twitter.Stats;
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
import org.springframework.cache.annotation.Cacheable;

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
    @RequestMapping(value = "/twitter/friends")
    @Cacheable("twitter.friends")
    public CursoredList<TwitterProfile> getFriends()
    {
        return twitter.friendOperations().getFriends();
    }

    @RequestMapping(value = "/twitter/my-tweets")
    @Cacheable("twitter.mytweets")
    public List<Tweet> getMyTweets()
    {
        return twitter.timelineOperations().getHomeTimeline();
    }

    @RequestMapping(value = "/twitter/mentions")
    @Cacheable("twitter.mentions")
    public List<Tweet> getMentions()
    {
        return twitter.timelineOperations().getMentions();
    }

    @RequestMapping(value = "/twitter/connected", method = RequestMethod.GET)
    public boolean isConnected()
    {
        return connectionRepository.findPrimaryConnection(Twitter.class) != null;
    }

    @RequestMapping(value = "/twitter/followers", method = RequestMethod.GET)
    @Cacheable("twitter.followers")
    public CursoredList<TwitterProfile> getFollowers()
    {
        return twitter.friendOperations().getFollowers();
    }

    @RequestMapping(value = "/twitter/stats", method = RequestMethod.GET)
    @Cacheable("twitter.stats.followers")
    public Stats getStats()
    {
        TwitterProfile profile = twitter.userOperations().getUserProfile();
        Stats stats = new Stats();
        stats.setFollowers(profile.getFollowersCount());
        stats.setFriends(profile.getFriendsCount());
        stats.setStatus(profile.getStatusesCount());
        return stats;
    }

}