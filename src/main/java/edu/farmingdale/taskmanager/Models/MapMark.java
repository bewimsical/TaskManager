package edu.farmingdale.taskmanager.Models;

public class MapMark {

    private double x;
    private double y;
    private boolean completed;
    private String choreId;
    private int completionIndex;

    public MapMark(){}
    public MapMark(MapMarkBuilder mapMarkBuilder) {
        this.x = mapMarkBuilder.x;
        this.y = mapMarkBuilder.y;
        this.completed = mapMarkBuilder.completed;
        this.choreId = mapMarkBuilder.choreId;
        this.completionIndex = mapMarkBuilder.completionIndex;
    }

    public double getX() {
        return x;
    }

    public void setX(double x) {
        this.x = x;
    }

    public double getY() {
        return y;
    }

    public void setY(double y) {
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

    public int getCompletionIndex() {
        return completionIndex;
    }

    public void setCompletionIndex(int completionIndex) {
        this.completionIndex = completionIndex;
    }

    public static class MapMarkBuilder{



        public MapMarkBuilder(){}

        private double x;
        private double y;
        private boolean completed;
        private String choreId;
        private int completionIndex;

        public MapMarkBuilder x(double x) {
            this.x = x;
            return this;
        }

        public MapMarkBuilder y(double y) {
            this.y = y;
            return this;
        }

        public MapMarkBuilder completed(boolean completed) {
            this.completed = completed;
            return this;
        }

        public MapMarkBuilder choreId(String choreId) {
            this.choreId = choreId;
            return this;
        }
        public MapMarkBuilder completionIndex(int index) {
            this.completionIndex = index;
            return this;
        }

        public MapMark build(){
            return new MapMark(this);
        }
    }
}
