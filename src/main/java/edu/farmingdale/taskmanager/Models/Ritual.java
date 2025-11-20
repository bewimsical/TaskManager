package edu.farmingdale.taskmanager.Models;

public class Ritual {


    public enum TimeOfDay {
        Morning, Midday, Evening
    }

    private String id;
    private boolean isCompleted;
    private String date;
    private double xp;
    private String dateRecorded;
    private String timeOfDay;
    private Chore chore;

    public Ritual(){}
    public Ritual(RitualBuilder ritualBuilder) {
        this.id = ritualBuilder.id;
        this.isCompleted = ritualBuilder.isCompleted;
        this.date = ritualBuilder.date;
        this.xp = ritualBuilder.xp;
        this.dateRecorded = ritualBuilder.dateRecorded;
        this.chore = ritualBuilder.chore;
        this.timeOfDay = ritualBuilder.timeOfDay;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public double getXp() {
        return xp;
    }

    public void setXp(double xp) {
        this.xp = xp;
    }

    public String getDateRecorded() {
        return dateRecorded;
    }

    public void setDateRecorded(String dateRecorded) {
        this.dateRecorded = dateRecorded;
    }

    public Chore getChore() {
        return chore;
    }

    public void setChore(Chore chore) {
        this.chore = chore;
    }

    public String getTimeOfDay() {
        return timeOfDay;
    }

    public void setTimeOfDay(String timeOfDay) {
        this.timeOfDay = timeOfDay;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public boolean isCompleted() {
        return isCompleted;
    }

    public void setCompleted(boolean completed) {
        isCompleted = completed;
    }



    public static class RitualBuilder {
        private String id;
        private boolean isCompleted;
        private Chore chore;
        private String date;
        private double xp;
        private String dateRecorded;
        private String timeOfDay;

        public RitualBuilder() {

        }

        public RitualBuilder date(String date) {
            this.date = date;
            return this;
        }

        public RitualBuilder xp(double xp) {
            this.xp = xp;
            return this;
        }

        public RitualBuilder dateRecorded(String dateRecorded) {
            this.dateRecorded = dateRecorded;
            return this;
        }

        public RitualBuilder chores(Chore chores) {
            this.chore = chores;
            return this;
        }

        public RitualBuilder timeOfDay(String timeOfDay){
            this.timeOfDay = timeOfDay;
            return this;
        }

        public RitualBuilder id(String id) {
            this.id = id;
            return this;
        }

        public RitualBuilder isCompleted(boolean completed){
            this.isCompleted = completed;
            return this;
        }

        public Ritual build() {
            return new Ritual(this);
        }
    }
}
