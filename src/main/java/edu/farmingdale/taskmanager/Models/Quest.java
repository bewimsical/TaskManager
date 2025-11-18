package edu.farmingdale.taskmanager.Models;

public class Quest {
    String name;
    boolean isActive;
    boolean isCompleted;
    double xp;


    public Quest(String name, boolean isActive, double xp) {
        this.name = name;
        this.isActive = isActive;
        this.xp = xp;
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

    public double getXp() {
        return xp;
    }

    public void setXp(double xp) {
        this.xp = xp;
    }
}
