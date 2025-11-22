package edu.farmingdale.taskmanager.Models;

import java.util.List;

public class Chore {


    private String id;
    private String name;
    private int choreXP;
    private List<String> rooms;
    private String completedTime;
    private double frequency;
    private boolean completed;
    private List<String> timeOfDay;


    public Chore() {

    }

    public Chore(String id, String name, int choreXP, List<String> rooms, String completedTime, double frequency, boolean completed, List<String> timeOfDay) {
        this.id = id;
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

    public int getChoreXP() {
        return choreXP;
    }

    public void setChoreXP(int choreXP) {
        this.choreXP = choreXP;
    }

    public List<String> getRooms() {
        return rooms;
    }

    public void setRooms(List<String> rooms) {
        this.rooms = rooms;
    }

    public String getCompletedTime() {
        return completedTime;
    }

    public void setCompletedTime(String completedTime) {
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

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

}
