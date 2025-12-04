package edu.farmingdale.taskmanager.viewmodels;

import edu.farmingdale.taskmanager.Models.Boss;
import edu.farmingdale.taskmanager.Models.Party;
import edu.farmingdale.taskmanager.Models.Quest;
import edu.farmingdale.taskmanager.Models.User;
import edu.farmingdale.taskmanager.Repositories.FirebaseUserRepository;
import edu.farmingdale.taskmanager.Session;
import edu.farmingdale.taskmanager.TaskManagerApplication;
import javafx.beans.property.DoubleProperty;
import javafx.beans.property.SimpleDoubleProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.image.Image;

import java.util.List;

public class ProfileViewModel {
    private final FirebaseUserRepository userRepo = new FirebaseUserRepository();

    private final StringProperty name = new SimpleStringProperty();
    private final DoubleProperty xpPercent = new SimpleDoubleProperty();
    private final StringProperty level = new SimpleStringProperty();
    private final StringProperty xp = new SimpleStringProperty();
    private final StringProperty levelXp = new SimpleStringProperty();

    private final ObservableList<Party> visibleParties = FXCollections.observableArrayList();
    private final ObservableList<User> visibleFriends = FXCollections.observableArrayList();

    private final User user;
    private final List<User> friends;
    private final List<Party> parties;
    private final List<Quest> activeQuests;
    private final List<Boss> bossBounties;

    private Image profilePic;

    public ProfileViewModel(){
        Session session = Session.getInstance();
        user = session.getUser();
        friends = session.getFriends();
        parties = session.getParties();
        activeQuests = session.getQuests().get("Active");
        bossBounties = session.getBosses().get("Bounties");
        nameProperty().set(user.getUsername());
        profilePic = new Image(TaskManagerApplication.class.getResource("images/"+user.getProfileUrl()).toExternalForm());
        levelProperty().set(String.valueOf(user.getLevel()));
        xpProperty().set(String.valueOf(user.getXp()));
        levelXpProperty().set(String.valueOf(user.levelXp()));

        xpPercent.set((double) user.getXp()/user.levelXp());

    }

    public void setUpView(){
        visibleFriends.addAll(friends);
        visibleParties.addAll(parties);
    }

    public double getXpPercent() {
        return xpPercent.get();
    }

    public DoubleProperty xpPercentProperty() {
        return xpPercent;
    }

    public String getLevel() {
        return level.get();
    }

    public StringProperty levelProperty() {
        return level;
    }

    public String getXp() {
        return xp.get();
    }

    public StringProperty xpProperty() {
        return xp;
    }

    public ObservableList<Party> getVisibleParties() {
        return visibleParties;
    }

    public ObservableList<User> getVisibleFriends() {
        return visibleFriends;
    }

    public User getUser() {
        return user;
    }

    public String getName() {
        return name.get();
    }

    public StringProperty nameProperty() {
        return name;
    }

    public Image getProfilePic() {
        return profilePic;
    }

    public void setProfilePic(Image profilePic) {
        this.profilePic = profilePic;
    }


    public String getLevelXp() {
        return levelXp.get();
    }

    public StringProperty levelXpProperty() {
        return levelXp;
    }

    public void addXp(int i) {
        System.out.println(i +"xp added!");
        user.addXP(i);
        levelProperty().set(String.valueOf(user.getLevel()));
        xpProperty().set(String.valueOf(user.getXp()));
        levelXpProperty().set(String.valueOf(user.levelXp()));
        xpPercent.set((double) user.getXp()/user.levelXp());
    }

    public List<Quest> getActiveQuests() {
        return activeQuests;
    }

    public List<Boss> getBossBounties() {
        return bossBounties;
    }
}
