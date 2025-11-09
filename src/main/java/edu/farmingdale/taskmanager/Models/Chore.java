package edu.farmingdale.taskmanager.Models;

import java.time.LocalTime;

public class Chore {
    private String name;
    private String description;
    private String choreXP;
    private LocalTime time;
    private boolean completed;


    public Chore(String name, String description, String choreXP, LocalTime time, boolean completed) {
        this.name = name;
        this.description = description;
        this.choreXP = choreXP;
        this.time = time;
        this.completed = completed;
    }


    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getChoreXP() {
        return choreXP;
    }

    public void setChoreXP(String choreXP) {
        this.choreXP = choreXP;
    }

    public LocalTime getTime() {
        return time;
    }

    public void setTime(LocalTime time) {
        this.time = time;
    }

    public boolean isCompleted() {
        return completed;
    }

    public void markCompleted() {
        this.completed = true;
    }

    public void reset() {
        this.completed = false;
    }

    public void setCompleted(boolean completed) {
        this.completed = completed;
    }


    @Override
    public String toString() {
        return "Chore{" +
                "name='" + name + '\'' +
                ", description='" + description + '\'' +
                ", choreXP='" + choreXP + '\'' +
                ", time=" + time +
                ", completed=" + completed +
                '}';
    }
}
