package edu.farmingdale.taskmanager.Models;

import java.util.ArrayList;
import java.util.List;

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


    public User() {
    }

    public User(String username, String password, String email, int age, boolean adult, int streak, double xpBonus, List<String> parties, List<String> friends) {
        this.username = username;
        this.password = password;
        this.email = email;
        this.age = age;
        this.adult = adult;
        this.streak = streak;
        this.xpBonus = xpBonus;
        this.parties = parties;
        this.friends = friends;
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
}
