package edu.farmingdale.taskmanager.Models;

public class Ritual {
    public enum TimeOfDay {
        Morning, Midday, Evening
    }

    private String date;
    private double xp;
    private String dateRecorded;
    private TimeOfDay timeOfDay;
    private Chore chore;




    public Ritual(RitualBuilder ritualBuilder) {
        this.date = ritualBuilder.date;
        this.xp = ritualBuilder.xp;
        this.dateRecorded = ritualBuilder.dateRecorded;
        this.chore = ritualBuilder.chores;
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

    public TimeOfDay getTimeOfDay() {
        return timeOfDay;
    }

    public void setTimeOfDay(TimeOfDay timeOfDay) {
        this.timeOfDay = timeOfDay;
    }



    public static class RitualBuilder {
        private Chore chores;
        private String date;
        private double xp;
        private String dateRecorded;
        private TimeOfDay timeOfDay;

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
            this.chores = chores;
            return this;
        }

        public RitualBuilder timeOfDay(TimeOfDay timeOfDay){
            this.timeOfDay = timeOfDay;
            return this;
        }

        public Ritual build() {
            return new Ritual(this);
        }
    }
}
