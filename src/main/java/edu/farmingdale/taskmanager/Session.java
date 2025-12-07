package edu.farmingdale.taskmanager;

import edu.farmingdale.taskmanager.Models.*;

import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.stream.Collectors;

public class Session {
    private static Session instance;
    private User currentUser;
    private Map<String, List<Boss>> bosses;
    private Map<String, List<Quest>> quests;
    private Map<String, List<Ritual>> rituals;
    private List<Chore> chores;
    private List<User> friends;
    private List<Party> parties;

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
        List<Chore> ritualChores = rituals.values().stream()
                .flatMap(List::stream)
                .map(Ritual::getChore)
                .toList();

        return chores.stream()
                .filter(c -> !ritualChores.contains(c))
                .filter(c -> !assignedChoreIds.contains(c.getId()))
                .collect(Collectors.toList());
    }

    public Set<String> getAssignedChoreIds() {
        return assignedChoreIds;
    }

    public void setAssignedChoreIds(Set<String> assignedChoreIds) {
        this.assignedChoreIds = assignedChoreIds;
    }

    public Map<String, List<Ritual>> getRituals() {
        return rituals;
    }

    public void setRituals(Map<String, List<Ritual>> rituals) {
        this.rituals = rituals;
    }

    public Map<String, List<Quest>> getQuests() {
        return quests;
    }

    public void setQuests(Map<String, List<Quest>> quests) {
        this.quests = quests;
    }

    public List<User> getFriends() {
        return friends;
    }

    public void setFriends(List<User> friends) {
        this.friends = friends;
    }

    public List<Party> getParties() {
        return parties;
    }

    public void setParties(List<Party> parties) {
        this.parties = parties;
    }
}
