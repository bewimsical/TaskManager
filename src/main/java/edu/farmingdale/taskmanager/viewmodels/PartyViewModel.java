package edu.farmingdale.taskmanager.viewmodels;

import edu.farmingdale.taskmanager.Models.Boss;
import edu.farmingdale.taskmanager.Models.Party;
import edu.farmingdale.taskmanager.Models.User;
import edu.farmingdale.taskmanager.Repositories.FirebasePartyRepository;
import edu.farmingdale.taskmanager.Repositories.FirebaseUserRepository;
import edu.farmingdale.taskmanager.Session;
import edu.farmingdale.taskmanager.exceptions.ResourceNotFoundException;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

import java.util.ArrayList;
import java.util.List;

public class PartyViewModel {
    FirebaseUserRepository userRepo = new FirebaseUserRepository();
    FirebasePartyRepository partyRepo = new FirebasePartyRepository();

    //set up property Bindings
    private final StringProperty name = new SimpleStringProperty();
    private final ObservableList<User> visibleUsers = FXCollections.observableArrayList();
    private final ObservableList<Party> visibleParties = FXCollections.observableArrayList();

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

    public void setUpView(){
        visibleUsers.addAll(friends);
        visibleParties.addAll(parties);
        name.set("All Friends");
    }

    public User search(String username) {
        User result;
        try {
            result = userRepo.getUserByUsername(username);
        } catch (ResourceNotFoundException e) {
            System.out.println(e.getMessage());
            result = null;
        }
        return result;
    }

    public void addFriend(User friend){
        if (user.getFriends() == null) {
            user.setFriends(new ArrayList<>());
        }
        user.getFriends().add(friend.getId());
        //todo fix this? add to the observable list?
        friends.add(friend);
        //todo - do i show all friends when the friend is added?
        visibleUsers.add(friend);
        //Session.getInstance().getFriends().add(friend);
        userRepo.updateUser(user);
    }

    public void showParty(Party party) {
        List<User> members = new ArrayList<>();
        if (party != null){
            name.set(party.getName());
            List<String> memberIds = party.getMembers();
            for (String id: memberIds){
                try {
                    members.add(userRepo.getUserById(id));
                } catch (ResourceNotFoundException e) {
                    System.out.println(e.getMessage());
                }
            }
        }
        visibleUsers.clear();
        visibleUsers.addAll(members);

    }


    public void showFriends() {
        visibleUsers.clear();
        visibleUsers.addAll(friends);
        name.set("All Friends");
    }

    public String getName() {
        return name.get();
    }

    public StringProperty nameProperty() {
        return name;
    }

    public ObservableList<User> getVisibleUsers() {
        return visibleUsers;
    }

    public ObservableList<Party> getVisibleParties() {
        return visibleParties;
    }

    public List<User> getFriends() {
        return friends;
    }
}
