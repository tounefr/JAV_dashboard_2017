package eu.epitech.java.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.social.connect.ConnectionRepository;
import org.springframework.social.facebook.api.Facebook;
import org.springframework.social.facebook.api.PagedList;
import org.springframework.web.bind.annotation.*;
import org.springframework.social.facebook.api.User;
import org.springframework.social.facebook.api.Post;

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

    @Cacheable("facebook.profile")
    @RequestMapping(value = "/facebook/profile", method = RequestMethod.GET)
    public User getProfile()
    {
        return facebook.userOperations().getUserProfile();
    }

    @RequestMapping(value = "/facebook/feed-posts", method = RequestMethod.GET)
    public PagedList<Post> getFeedPosts() {
        return facebook.feedOperations().getFeed();
    }

    @RequestMapping(value = "/facebook/status", method = RequestMethod.GET)
    public PagedList<Post> getStatus() {
        return facebook.feedOperations().getStatuses();
    }

    @RequestMapping(value = "/facebook/mentions", method = RequestMethod.GET)
    public PagedList<Post> getMentions() {
        return facebook.feedOperations().getTagged();
    }

    @RequestMapping(value = "/facebook/friends", method = RequestMethod.GET)
    public PagedList<User> getFriends()
    {
        return facebook.friendOperations().getFriendProfiles();
    }

    @RequestMapping(value = "/facebook/connected", method = RequestMethod.GET)
    public boolean isConnected()
    {
        return connectionRepository.findPrimaryConnection(Facebook.class) != null;
    }
}