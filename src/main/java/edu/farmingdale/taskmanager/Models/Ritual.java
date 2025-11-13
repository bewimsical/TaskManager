package edu.farmingdale.taskmanager.Models;

import java.time.LocalDate;
import java.util.*;

public class Ritual {
    public enum TimeOfDay {
        Morning, Midday, Evening
    }

    private LocalDate date;
    private double xp;
    private LocalDate dateRecorded;
    private Map<TimeOfDay, List<Chore>> chores;


    public Ritual(RitualBuilder ritualBuilder) {
        this.date = ritualBuilder.date;
        this.xp = ritualBuilder.xp;
        this.dateRecorded = ritualBuilder.dateRecorded;
        this.chores = ritualBuilder.chores;
    }


    public LocalDate getDate() {
        return date;
    }

    public void setDate(LocalDate date) {
        this.date = date;
    }

    public double getXp() {
        return xp;
    }

    public void setXp(double xp) {
        this.xp = xp;
    }

    public LocalDate getDateRecorded() {
        return dateRecorded;
    }

    public void setDateRecorded(LocalDate dateRecorded) {
        this.dateRecorded = dateRecorded;
    }

    public Map<TimeOfDay, List<Chore>> getChores() {
        return chores;
    }

    public void setChores(Map<TimeOfDay, List<Chore>> chores) {
        this.chores = chores;
    }



    public static class RitualBuilder {
        private Map<TimeOfDay, List<Chore>> chores = new EnumMap<>(TimeOfDay.class);
        private LocalDate date;
        private double xp;
        private LocalDate dateRecorded;

        public RitualBuilder() {

        }

        public RitualBuilder date(LocalDate date) {
            this.date = date;
            return this;
        }

        public RitualBuilder xp(double xp) {
            this.xp = xp;
            return this;
        }

        public RitualBuilder dateRecorded(LocalDate dateRecorded) {
            this.dateRecorded = dateRecorded;
            return this;
        }

        public RitualBuilder chores(Map<TimeOfDay, List<Chore>> chores) {
            this.chores = chores;
            return this;
        }

        public Ritual build() {
            return new Ritual(this);
        }
    }
}
