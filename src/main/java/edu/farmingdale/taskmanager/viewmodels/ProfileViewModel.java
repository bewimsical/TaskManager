package edu.farmingdale.taskmanager.viewmodels;

import edu.farmingdale.taskmanager.Models.Party;
import edu.farmingdale.taskmanager.Models.User;
import edu.farmingdale.taskmanager.Repositories.FirebaseUserRepository;
import javafx.beans.property.DoubleProperty;
import javafx.beans.property.SimpleDoubleProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

public class ProfileViewModel {
    private final FirebaseUserRepository userRepo = new FirebaseUserRepository();
    private final DoubleProperty xpPercent = new SimpleDoubleProperty();
    private final StringProperty level = new SimpleStringProperty();
    private final StringProperty xp = new SimpleStringProperty();

    private final ObservableList<Party> parties = FXCollections.observableArrayList();
    private final ObservableList<User> friends = FXCollections.observableArrayList();


}
