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
    private int xp;


    public User() {
    }

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
        this.bosses = userBuilder.bosses;
        this.vanquishedBossCount = userBuilder.vanquishedBossCount;
        this.completedQuestCount = userBuilder.completedQuestCount;
        this.profileUrl = userBuilder.profileUrl;
        this.level = userBuilder.level;
        this.xp = userBuilder.xp;

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

    public int getXp() {
        return xp;
    }

    public void setXp(int xp) {
        this.xp = xp;
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

        private Map<String, LinkedList<Bosses>> bosses;
        private int vanquishedBossCount;
        private int completedQuestCount;
        private String profileUrl;
        private int level;
        private int xp;

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


        public UserBuilder bosses(Map<String, LinkedList<Bosses>> bosses) {
            this.bosses = bosses;
            return this;
        }

        public UserBuilder vanquishedBossCount(int vanquishedBossCount) {
            this.vanquishedBossCount = vanquishedBossCount;
            return this;
        }

        public UserBuilder completedQuestCount(int completedQuestCount) {
            this.completedQuestCount = completedQuestCount;
            return this;
        }

        public UserBuilder profileUrl(String profileUrl) {
            this.profileUrl = profileUrl;
            return this;
        }
        public UserBuilder level(int level) {
            this.level = level;
            return this;
        }
        public UserBuilder xp(int xp) {
            this.xp = xp;
            return this;
        }


        public User build() {
            return new User(this);
        }


    }
}
