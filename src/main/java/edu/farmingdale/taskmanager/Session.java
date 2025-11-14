package edu.farmingdale.taskmanager;

import edu.farmingdale.taskmanager.Models.Bosses;
import edu.farmingdale.taskmanager.Models.Chore;
import edu.farmingdale.taskmanager.Models.User;

import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Set;

public class Session {
    private static Session instance;
    private User currentUser;
    private Map<String, List<Bosses>> bosses;
    private List<Chore> chores;
    private Set<String> assignedChoreIds;


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


    public Map<String, List<Bosses>> getBosses() {
        return bosses;
    }

    public void setBosses(Map<String, List<Bosses>> bosses) {
        this.bosses = bosses;
    }

    public List<Chore> getChores() {
        return chores;
    }

    public void setChores(List<Chore> chores) {
        this.chores = chores;
    }
}
