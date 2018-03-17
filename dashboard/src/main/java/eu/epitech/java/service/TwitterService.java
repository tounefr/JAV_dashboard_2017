package eu.epitech.java.service;

import eu.epitech.java.model.TweetModel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.social.twitter.api.Tweet;
import org.springframework.social.connect.ConnectionRepository;
import org.springframework.social.twitter.api.CursoredList;
import org.springframework.social.twitter.api.Twitter;
import org.springframework.social.twitter.api.TwitterProfile;
import org.springframework.stereotype.Service;
import eu.epitech.java.model.TwitterModel;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

@Service
public class TwitterService
{
    private Twitter twitter;

    private ConnectionRepository connectionRepository;

    @Autowired
    public TwitterService(Twitter twitter, ConnectionRepository connectionRepository)
    {
        this.twitter = twitter;
        this.connectionRepository = connectionRepository;
    }

    private boolean isConnected()
    {
        if (connectionRepository.findPrimaryConnection(Twitter.class) == null)
            return false;
        return true;
    }

    private CursoredList<TwitterProfile> getLastFriends()
    {
        CursoredList<TwitterProfile> friends = twitter.friendOperations().getFriends();
        return friends;
    }

    private ArrayList<TweetModel> parseTweets(List<Tweet> tweets)
    {
        Iterator it = tweets.iterator();
        ArrayList<TweetModel> tweetsList = new ArrayList<TweetModel>();
        while (it.hasNext()) {
            Tweet tweet = (Tweet) it.next();
            TweetModel twitterMentionModel = new TweetModel();
            twitterMentionModel.setContent(tweet.getText());
            twitterMentionModel.setCreatedAt(tweet.getCreatedAt());
            twitterMentionModel.setFromUser(tweet.getFromUser());
            tweetsList.add(twitterMentionModel);
        }
        return tweetsList;
    }

    private ArrayList<TweetModel> getLastMentions()
    {
        List<Tweet> mentions = twitter.timelineOperations().getMentions();
        ArrayList<TweetModel> mentionsList = parseTweets(mentions);
        return mentionsList;
    }

    private ArrayList<String> getLastFollowers()
    {
        CursoredList<TwitterProfile> followers = twitter.friendOperations().getFollowers();
        Iterator it = followers.iterator();
        ArrayList<String> names = new ArrayList<String>();
        while (it.hasNext()) {
            TwitterProfile tp = (TwitterProfile) it.next();
            names.add(tp.getName());
        }
        return names;
    }

    private TwitterProfile getMyProfil()
    {
        TwitterProfile twitterProfile = twitter.userOperations().getUserProfile();
        return twitterProfile;
    }

    private ArrayList<TweetModel> getLastTweets()
    {
        List<Tweet> tweets = twitter.timelineOperations().getHomeTimeline();
        ArrayList<TweetModel> tweetsList = parseTweets(tweets);
        return tweetsList;
    }

    public TwitterModel getTwitterDatas()
    {
        TwitterModel twitter = new TwitterModel();
        boolean _isConnected = isConnected();
        twitter.setConnected(_isConnected);
        if (_isConnected) {

            twitter.setLastFollowers(getLastFollowers());
            twitter.setLastMentions(getLastMentions());
            twitter.setLastTweets(getLastTweets());

            TwitterProfile twitterProfile = getMyProfil();
            twitter.setNbSubscriptions(twitterProfile.getFriendsCount());
            twitter.setNbFollowers(twitterProfile.getFollowersCount());
            twitter.setNbLikes(twitterProfile.getFavoritesCount());
            twitter.setNbTweets(twitterProfile.getStatusesCount());

        }
        return twitter;
    }
}