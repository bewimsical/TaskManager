package edu.farmingdale.taskmanager;

import edu.farmingdale.taskmanager.Models.Bosses;
import edu.farmingdale.taskmanager.Models.User;

import java.util.LinkedList;
import java.util.List;
import java.util.Map;

public class Session {
    private static Session instance;
    private User currentUser;
    private List<String> parties;
    private List<String> friends;
    private Map<String, LinkedList<Bosses>> bosses;

    private Session() {
        //private constructor to enforce singleton
    }

    public static Session getInstance() {
        if (instance == null) {
            instance = new Session();
        }
        return instance;
    }

    public void setUser(User user) {
        this.currentUser = user;
    }

    public User getUser() {
        return this.currentUser;
    }

    public void clearSession() {
        currentUser = null;
        instance = null;
    }


    public Map<String, LinkedList<Bosses>> getBosses() {
        return bosses;
    }

    public void setBosses(Map<String, LinkedList<Bosses>> bosses) {
        this.bosses = bosses;
    }
}
