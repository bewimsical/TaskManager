package edu.farmingdale.taskmanager.Models;

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

    public static class UserBuilder {
        private String username;
        private String password;
        private String email;
        private int age;
        private boolean adult;
        private int streak;
        private double xpBonus;
        private List<String> parties;
        private List<String> friends;

        public UserBuilder() {

        }

        public UserBuilder username(String username) {
            this.username = username;
            return this;
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
