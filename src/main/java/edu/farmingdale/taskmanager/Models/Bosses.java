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

    public Bosses() {

    }

    public Bosses(String name, String attacks, int xp, double totalHealth, double currentHealth, List<String> chores, boolean bounties, boolean vanquished) {
        this.name = name;
        this.attacks = attacks;
        this.xp = xp;
        this.totalHealth = totalHealth;
        this.currentHealth = currentHealth;
        this.chores = chores;
        this.bounties = bounties;
        this.vanquished = vanquished;
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
}
