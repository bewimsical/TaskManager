package edu.farmingdale.taskmanager.Models;

import java.util.List;

public class Boss {
    private String id;
    private String name;
    private int xp;
    private double totalHealth;
    private double currentHealth;
    private List<Chore> chores;
    private boolean bounties;
    private boolean vanquished;
    private String dirtyImageUrl;
    private String cleanImageUrl;
    private String dateAdded;
    private String dateDefeated;
    public Boss() {}

    private Boss(BossesBuilder bossesBuilder) {
        this.name = bossesBuilder.name;
        this.id = bossesBuilder.id;
        this.xp = bossesBuilder.xp;
        this.totalHealth = bossesBuilder.totalHealth;
        this.currentHealth = bossesBuilder.currentHealth;
        this.chores = bossesBuilder.chores;
        this.bounties = bossesBuilder.bounties;
        this.vanquished = bossesBuilder.vanquished;
        this.dateAdded = bossesBuilder.dateAdded;
        this.dateDefeated = bossesBuilder.dateDefeated;
        this.dirtyImageUrl = bossesBuilder.dirtyImageUrl;
        this.cleanImageUrl = bossesBuilder.cleanImageUrl;

    }


    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public int getXp() {
        return xp;
    }

    public void setXp(int xp) {
        this.xp = xp;
    }

    public double getTotalHealth() {
        return totalHealth;
    }

    public void setTotalHealth(double totalHealth) {
        this.totalHealth = totalHealth;
    }

    public double getCurrentHealth() {
        return currentHealth;
    }

    public void setCurrentHealth(double currentHealth) {
        this.currentHealth = currentHealth;
    }

    public List<Chore> getChores() {
        return chores;
    }

    public void setChores(List<Chore> chores) {
        this.chores = chores;
    }

    public boolean isBounties() {
        return bounties;
    }

    public void setBounties(boolean bounties) {
        this.bounties = bounties;
    }

    public boolean isVanquished() {
        return vanquished;
    }

    public void setVanquished(boolean vanquished) {
        this.vanquished = vanquished;
    }

    public String getDirtyImageUrl() {
        return dirtyImageUrl;
    }

    public void setDirtyImageUrl(String dirtyImageUrl) {
        this.dirtyImageUrl = dirtyImageUrl;
    }

    public String getCleanImageUrl() {
        return cleanImageUrl;
    }

    public void setCleanImageUrl(String cleanImageUrl) {
        this.cleanImageUrl = cleanImageUrl;
    }

    public String getDateAdded() {
        return dateAdded;
    }

    public void setDateAdded(String dateAdded) {
        this.dateAdded = dateAdded;
    }

    public String getDateDefeated() {
        return dateDefeated;
    }

    public void setDateDefeated(String dateDefeated) {
        this.dateDefeated = dateDefeated;
    }

    public static class BossesBuilder {
        private String name;
        private String id;
        private int xp;
        private int totalHealth;
        private int currentHealth;
        private List<Chore> chores;
        private boolean bounties;
        private boolean vanquished;
        private String dateAdded;
        private String dateDefeated;
        private String dirtyImageUrl;
        private String cleanImageUrl;

        public BossesBuilder() {

        }

        public BossesBuilder name(String name) {
            this.name = name;
            return this;
        }

        public BossesBuilder id(String id) {
            this.id = id;
            return this;
        }

        public BossesBuilder xp(int xp) {
            this.xp = xp;
            return this;
        }

        public BossesBuilder totalHealth(int totalHealth) {
            this.totalHealth = totalHealth;
            return this;
        }

        public BossesBuilder currentHealth(int currentHealth) {
            this.currentHealth = currentHealth;
            return this;
        }

        public BossesBuilder chores(List<Chore> chores) {
            this.chores = chores;
            return this;
        }

        public BossesBuilder bounties(boolean bounties) {
            this.bounties = bounties;
            return this;
        }

        public BossesBuilder vanquished(boolean vanquished) {
            this.vanquished = vanquished;
            return this;
        }
        public BossesBuilder dateAdded(String dateAdded) {
            this.dateAdded = dateAdded;
            return this;
        }

        public BossesBuilder dateDefeated(String dateDefeated) {
            this.dateDefeated = dateDefeated;
            return this;
        }

        public BossesBuilder dirtyImageUrl(String dirtyImageUrl) {
            this.dirtyImageUrl = dirtyImageUrl;
            return this;
        }

        public BossesBuilder cleanImageUrl(String cleanImageUrl) {
            this.cleanImageUrl = cleanImageUrl;
            return this;
        }

        public Boss build() {
            return new Boss(this);
        }


    }
}
