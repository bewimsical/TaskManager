package edu.farmingdale.taskmanager.Models;

import java.util.List;

public class Bosses {
    private String name;
    private String attacks;
    private int xp;
    private double totalHealth;
    private double currentHealth;
    private List<String> chores;
    private boolean bounties;
    private boolean vanquished;


    private Bosses(BossesBuilder bossesBuilder) {
        this.name = bossesBuilder.name;
        this.attacks = bossesBuilder.attacks;
        this.xp = bossesBuilder.xp;
        this.totalHealth = bossesBuilder.totalHealth;
        this.currentHealth = bossesBuilder.currentHealth;
        this.chores = bossesBuilder.chores;
        this.bounties = bossesBuilder.bounties;
        this.vanquished = bossesBuilder.vanquished;
    }


    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getAttacks() {
        return attacks;
    }

    public void setAttacks(String attacks) {
        this.attacks = attacks;
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

    public List<String> getChores() {
        return chores;
    }

    public void setChores(List<String> chores) {
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

    public static class BossesBuilder {
        private String name;
        private String attacks;
        private int xp;
        private double totalHealth;
        private double currentHealth;
        private List<String> chores;
        private boolean bounties;
        private boolean vanquished;

        public BossesBuilder() {

        }

        public BossesBuilder name(String name) {
            this.name = name;
            return this;
        }

        public BossesBuilder attacks(String attacks) {
            this.attacks = attacks;
            return this;
        }

        public BossesBuilder xp(int xp) {
            this.xp = xp;
            return this;
        }

        public BossesBuilder totalHealth(double totalHealth) {
            this.totalHealth = totalHealth;
            return this;
        }

        public BossesBuilder currentHealth(double currentHealth) {
            this.currentHealth = currentHealth;
            return this;
        }

        public BossesBuilder chores(List<String> chores) {
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

        public Bosses build() {
            return new Bosses(this);
        }


    }
}
