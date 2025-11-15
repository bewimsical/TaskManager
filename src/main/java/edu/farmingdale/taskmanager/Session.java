package edu.farmingdale.taskmanager;

import edu.farmingdale.taskmanager.Models.Boss;
import edu.farmingdale.taskmanager.Models.Chore;
import edu.farmingdale.taskmanager.Models.User;

import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.stream.Collectors;

public class Session {
    private static Session instance;
    private User currentUser;
    private Map<String, List<Boss>> bosses;
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

    public void clearSession() {
        currentUser = null;
        instance = null;
    }

    public void setUser(User user) {
        this.currentUser = user;
    }

    public User getUser() {
        return this.currentUser;
    }

    public Map<String, List<Boss>> getBosses() {
        return bosses;
    }

    public void setBosses(Map<String, List<Boss>> bosses) {
        this.bosses = bosses;
    }

    public List<Chore> getChores() {
        return chores;
    }

    public void setChores(List<Chore> chores) {
        this.chores = chores;
    }

    public List<Chore> getAvailableChores() {
        return chores.stream()
                .filter(c -> !assignedChoreIds.contains(c.getId()))
                .collect(Collectors.toList());
    }

    public Set<String> getAssignedChoreIds() {
        return assignedChoreIds;
    }

    public void setAssignedChoreIds(Set<String> assignedChoreIds) {
        this.assignedChoreIds = assignedChoreIds;
    }
}
