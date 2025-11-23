package edu.farmingdale.taskmanager.Models;

public class MapMark {

    private int x;
    private int y;
    private boolean completed;
    private String choreId;


    public MapMark(int x, int y, boolean completed, String choreId) {
        this.x = x;
        this.y = y;
        this.completed = completed;
        this.choreId = choreId;
    }

    public int getX() {
        return x;
    }

    public void setX(int x) {
        this.x = x;
    }

    public int getY() {
        return y;
    }

    public void setY(int y) {
        this.y = y;
    }

    public boolean isCompleted() {
        return completed;
    }

    public void setCompleted(boolean completed) {
        this.completed = completed;
    }

    public String getChoreId() {
        return choreId;
    }

    public void setChoreId(String choreId) {
        this.choreId = choreId;
    }
}
