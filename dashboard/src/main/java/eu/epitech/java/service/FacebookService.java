package eu.epitech.java.service;

import eu.epitech.java.model.FacebookModel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.social.connect.ConnectionRepository;
import org.springframework.social.facebook.api.Facebook;
import org.springframework.social.facebook.api.PagedList;
import org.springframework.social.facebook.api.Reference;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Iterator;

@Service
public class FacebookService extends AService {

    private Facebook facebook;

    @Autowired
    public FacebookService(Facebook facebook, ConnectionRepository connectionRepository)
    {
        this.facebook = facebook;
        this.connectionRepository = connectionRepository;
    }

    @Override
    public Class getController() {
        return Facebook.class;
    }

    private ArrayList<String> getFriends()
    {
        ArrayList<String> friendsNames = new ArrayList<String>();
        PagedList<Reference> friends = facebook.friendOperations().getFriends();
        Iterator<Reference> iterator = friends.iterator();
        while (iterator.hasNext()) {
            Reference reference = (Reference) iterator.next();
            friendsNames.add(reference.getName());
        }
        return friendsNames;
    }

    public FacebookModel getFacebookDatas()
    {
        FacebookModel facebookModel = new FacebookModel();
        facebookModel.setFriends(getFriends());
        return facebookModel;
    }
}
