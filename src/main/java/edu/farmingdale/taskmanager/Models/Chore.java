package edu.farmingdale.taskmanager.Models;

import java.time.LocalDateTime;
import java.util.List;

public class Chore {
    private String name;
    private String choreXP;
    private List<String> rooms;
    private LocalDateTime completedTime;
    private double frequency;
    private boolean completed;
    private List<String> timeOfDay;


    public Chore() {

    }

    public Chore(String name, String choreXP, List<String> rooms, LocalDateTime completedTime, double frequency, boolean completed, List<String> timeOfDay) {
        this.name = name;
        this.choreXP = choreXP;
        this.rooms = rooms;
        this.completedTime = completedTime;
        this.frequency = frequency;
        this.completed = completed;
        this.timeOfDay = timeOfDay;

    }


    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getChoreXP() {
        return choreXP;
    }

    public void setChoreXP(String choreXP) {
        this.choreXP = choreXP;
    }

    public List<String> getRooms() {
        return rooms;
    }

    public void setRooms(List<String> rooms) {
        this.rooms = rooms;
    }

    public LocalDateTime getCompletedTime() {
        return completedTime;
    }

    public void setCompletedTime(LocalDateTime completedTime) {
        this.completedTime = completedTime;
    }

    public double getFrequency() {
        return frequency;
    }

    public void setFrequency(double frequency) {
        this.frequency = frequency;
    }

    public boolean isCompleted() {
        return completed;
    }

    public void setCompleted(boolean completed) {
        this.completed = completed;
    }

    public List<String> getTimeOfDay() {
        return timeOfDay;
    }

    public void setTimeOfDay(List<String> timeOfDay) {
        this.timeOfDay = timeOfDay;
    }
}
