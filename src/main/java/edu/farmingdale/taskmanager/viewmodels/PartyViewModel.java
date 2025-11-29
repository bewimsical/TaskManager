package edu.farmingdale.taskmanager.viewmodels;

import edu.farmingdale.taskmanager.Models.Party;
import edu.farmingdale.taskmanager.Models.User;
import edu.farmingdale.taskmanager.Repositories.FirebasePartyRepository;
import edu.farmingdale.taskmanager.Repositories.FirebaseUserRepository;
import edu.farmingdale.taskmanager.Session;

import java.util.List;

public class PartyViewModel {
    FirebaseUserRepository userRepo = new FirebaseUserRepository();
    FirebasePartyRepository partyRepo = new FirebasePartyRepository();

    //set up property Bindings

    //Session data
    private final List<User> friends;
    private final List<Party> parties;
    private final User user;

    public PartyViewModel(){
        Session session = Session.getInstance();
        user = session.getUser();
        friends = session.getFriends();
        parties = session.getParties();
    }

}
