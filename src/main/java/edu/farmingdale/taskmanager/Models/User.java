package edu.farmingdale.taskmanager.Models;

import java.util.ArrayList;
import java.util.List;

public class User {
    private final String username;
    private final String password;
    private final String email;
    private final int age;
    private final boolean adult;
    private final int streak;
    private final double xpBonus;
    private final List<String> parties;
    private final List<String> friends;


    private User(UserBuilder userBuilder) {
        this.username = userBuilder.username;
        this.password = userBuilder.password;
        this.email = userBuilder.email;
        this.age = userBuilder.age;
        this.adult = userBuilder.adult;
        this.streak = userBuilder.streak;
        this.xpBonus = userBuilder.xpBonus;
        this.parties = userBuilder.parties;
        this.friends = userBuilder.friends;
    }


    public String getUsername() {
        return username;
    }

    public String getPassword() {
        return password;
    }

    public String getEmail() {
        return email;
    }

    public int getAge() {
        return age;
    }

    public boolean isAdult() {
        return adult;
    }

    public int getStreak() {
        return streak;
    }

    public double getXpBonus() {
        return xpBonus;
    }

    public List<String> getParties() {
        return parties;
    }

    public List<String> getFriends() {
        return friends;
    }

    public static class UserBuilder {
        private final String username;
        private String password;
        private String email;
        private int age;
        private boolean adult;
        private int streak;
        private double xpBonus;
        private List<String> parties;
        private List<String> friends;

        public UserBuilder(String username) {
            this.username = username;
        }

        public UserBuilder password(String password) {
            this.password = password;
            return this;
        }

        public UserBuilder email(String email) {
            this.email = email;
            return this;
        }

        public UserBuilder age(int age) {
            this.age = age;
            return this;
        }

        public UserBuilder adult(boolean adult) {
            this.adult = adult;
            return this;
        }

        public UserBuilder streak(int streak) {
            this.streak = streak;
            return this;
        }

        public UserBuilder xpBonus(double xpBonus) {
            this.xpBonus = xpBonus;
            return this;
        }

        public UserBuilder parties(List<String> parties) {
            this.parties = parties;
            return this;
        }

        public UserBuilder friends(List<String> friends) {
            this.friends = friends;
            return this;
        }

        public User build() {
            return new User(this);
        }
    }
}
