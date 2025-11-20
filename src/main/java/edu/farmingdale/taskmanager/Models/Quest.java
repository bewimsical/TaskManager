package edu.farmingdale.taskmanager.Models;

import java.util.List;

public class Quest {
    String name;
    boolean isActive;
    boolean isCompleted;
    double xp;
    double totalCompleted;
    double totalActive;
    List<String> chores;


    public Quest(String name, boolean isActive, boolean isCompleted, double xp, double totalCompleted, double totalActive, List<String> chores) {
        this.name = name;
        this.isActive = isActive;
        this.isCompleted = isCompleted;
        this.xp = xp;
        this.totalCompleted = totalCompleted;
        this.totalActive = totalActive;
        this.chores = chores;
    }


    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public boolean isActive() {
        return isActive;
    }

    public void setActive(boolean active) {
        isActive = active;
    }

    public boolean isCompleted() {
        return isCompleted;
    }

    public void setCompleted(boolean completed) {
        isCompleted = completed;
    }

    public double getXp() {
        return xp;
    }

    public void setXp(double xp) {
        this.xp = xp;
    }

    public double getTotalCompleted() {
        return totalCompleted;
    }

    public void setTotalCompleted(double totalCompleted) {
        this.totalCompleted = totalCompleted;
    }

    public double getTotalActive() {
        return totalActive;
    }

    public void setTotalActive(double totalActive) {
        this.totalActive = totalActive;
    }

    public List<String> getChores() {
        return chores;
    }

    public void setChores(List<String> chores) {
        this.chores = chores;
    }
}
