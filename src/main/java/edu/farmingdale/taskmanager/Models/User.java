package edu.farmingdale.taskmanager.Models;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

public class User {
    private String username;
    private String password;
    private String email;
    private int age;
    private boolean adult;
    private int streak;
    private double xpBonus;
    private List<String> parties;
    private List<String> friends;

    private Map<String, LinkedList<Bosses>> bosses;
    private int vanquishedBossCount;
    private int completedQuestCount;
    private String profileUrl;
    private int level;

    public User() {
    }
    public User(String username, String password, String email, int age, boolean adult, int streak, double xpBonus, List<String> parties, List<String> friends, String profileUrl, int level) {
        this.username = username;
        this.password = password;
        this.email = email;
        this.age = age;
        this.adult = adult;
        this.streak = streak;
        this.xpBonus = xpBonus;
        this.parties = parties;
        this.friends = friends;


        this.profileUrl = profileUrl;
        this.level = level;
    }


    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public boolean isAdult() {
        return adult;
    }

    public void setAdult(boolean adult) {
        this.adult = adult;
    }

    public int getStreak() {
        return streak;
    }

    public void setStreak(int streak) {
        this.streak = streak;
    }


    public double getXpBonus() {
        return xpBonus;
    }

    public void setXpBonus(double xpBonus) {
        this.xpBonus = xpBonus;
    }

    public List<String> getParties() {
        return parties;
    }

    public void setParties(List<String> parties) {
        this.parties = parties;
    }

    public List<String> getFriends() {
        return friends;
    }

    public void setFriends(List<String> friends) {
        this.friends = friends;
    }




    public String getProfileUrl() {
        return profileUrl;
    }

    public void setProfileUrl(String profileUrl) {
        this.profileUrl = profileUrl;
    }

    public int getLevel() {
        return level;
    }

    public void setLevel(int level) {
        this.level = level;
    }

    public Map<String, LinkedList<Bosses>> getBosses() {
        return bosses;
    }

    public void setBosses(Map<String, LinkedList<Bosses>> bosses) {
        this.bosses = bosses;
    }

    public int getVanquishedBossCount() {
        return vanquishedBossCount;
    }

    public void setVanquishedBossCount(int vanquishedBossCount) {
        this.vanquishedBossCount = vanquishedBossCount;
    }

    public int getCompletedQuestCount() {
        return completedQuestCount;
    }

    public void setCompletedQuestCount(int completedQuestCount) {
        this.completedQuestCount = completedQuestCount;
    }
}
