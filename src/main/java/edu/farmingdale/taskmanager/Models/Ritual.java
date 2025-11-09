package edu.farmingdale.taskmanager.Models;

import java.time.LocalDate;
import java.util.*;

public class Ritual {
    public enum TimeOfDay {
        MORNING, MIDDAY, EVENING
    }

    private LocalDate date;
    private double xp;
    private LocalDate dateRecorded;
    private Map<TimeOfDay, List<Chore>> chores;


    public Ritual(LocalDate date, double xp, LocalDate dateRecorded, Map<TimeOfDay, List<Chore>> chores) {
        this.date = date;
        this.xp = xp;
        this.dateRecorded = dateRecorded;
        this.chores = chores;
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

    public List<Chore> getChores(TimeOfDay timeOfDay) {
        return chores.getOrDefault(timeOfDay, Collections.emptyList());
    }

    public Map<TimeOfDay, List<Chore>> getAllChores() {
        return chores;
    }

    public void setChores(Map<TimeOfDay, List<Chore>> chores) {
        this.chores = chores;
    }

    public void addChore(TimeOfDay timeOfDay, Chore chore) {
        chores.computeIfAbsent(timeOfDay, t -> new ArrayList<>()).add(chore);
    }

    public void resetAllChores() {
        for (List<Chore> timedChores : chores.values()) {
            for (Chore chore : timedChores) {
                chore.reset();
            }
        }
    }

    public static class ChoreBuilder {
        private final Map<TimeOfDay, List<Chore>> chores = new EnumMap<>(TimeOfDay.class);
        private LocalDate date;
        private double xp;
        private LocalDate dateRecorded;

        public ChoreBuilder() {

        }

        public ChoreBuilder date(LocalDate date) {
            this.date = date;
            return this;
        }

        public ChoreBuilder xp(double xp) {
            this.xp = xp;
            return this;
        }

        public ChoreBuilder dateRecorded(LocalDate dateRecorded) {
            this.dateRecorded = dateRecorded;
            return this;
        }

    }


}
