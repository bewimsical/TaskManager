package edu.farmingdale.taskmanager.Models;

import java.util.List;

public class Quest {
    private String name;
    private String id;
    private boolean isActive;
    private boolean isCompleted;
    private double xp;
    private double totalCompleted;
    private double totalActive;
    private List<Chore> chores;
    private String dateAdded;
    private String dateDefeated;
    private String mapImageUrl;
    private List <MapMark> mapMarks;

    public Quest(QuestBuilder questBuilder) {
        this.id = questBuilder.id;
        this.name = questBuilder.name;
        this.isActive = questBuilder.isActive;
        this.isCompleted = questBuilder.isCompleted;
        this.xp = questBuilder.xp;
        this.totalCompleted = questBuilder.totalCompleted;
        this.totalActive = questBuilder.totalActive;
        this.chores = questBuilder.chores;
        this.dateAdded = questBuilder.dateAdded;
        this.dateDefeated = questBuilder.dateDefeated;
        this.mapImageUrl = questBuilder.mapImageUrl;
        this.mapMarks = questBuilder.mapMarks;
    }


    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
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

    public boolean isCompleted() {
        return isCompleted;
    }

    public void setCompleted(boolean completed) {
        isCompleted = completed;
    }

    public double getXp() {
        return xp;
    }

    public void setXp(double xp) {
        this.xp = xp;
    }

    public double getTotalCompleted() {
        return totalCompleted;
    }

    public void setTotalCompleted(double totalCompleted) {
        this.totalCompleted = totalCompleted;
    }

    public double getTotalActive() {
        return totalActive;
    }

    public void setTotalActive(double totalActive) {
        this.totalActive = totalActive;
    }

    public List<Chore> getChores() {
        return chores;
    }

    public void setChores(List<Chore> chores) {
        this.chores = chores;
    }

    public String getDateAdded() {
        return dateAdded;
    }

    public void setDateAdded(String dateAdded) {
        this.dateAdded = dateAdded;
    }

    public String getDateDefeated() {
        return dateDefeated;
    }

    public void setDateDefeated(String dateDefeated) {
        this.dateDefeated = dateDefeated;
    }

    public String getMapImageUrl() {
        return mapImageUrl;
    }

    public void setMapImageUrl(String mapImageUrl) {
        this.mapImageUrl = mapImageUrl;
    }

    public List<MapMark> getMapMarks() {
        return mapMarks;
    }

    public void setMapMarks(List<MapMark> mapMarks) {
        this.mapMarks = mapMarks;
    }

    public static class QuestBuilder{
        private String name;
        private String id;
        private boolean isActive;
        private boolean isCompleted;
        private double xp;
        private double totalCompleted;
        private double totalActive;
        private List<Chore> chores;
        private String dateAdded;
        private String dateDefeated;
        private String mapImageUrl;
        private List <MapMark> mapMarks;

        public QuestBuilder(){}


        public QuestBuilder  name(String name) {
            this.name = name;
            return this;
        }

        public QuestBuilder  id(String id) {
            this.id = id;
            return this;
        }

        public QuestBuilder  active(boolean active) {
            isActive = active;
            return this;
        }

        public QuestBuilder  completed(boolean completed) {
            isCompleted = completed;
            return this;
        }

        public QuestBuilder  xp(double xp) {
            this.xp = xp;
            return this;
        }

        public QuestBuilder  totalCompleted(double totalCompleted) {
            this.totalCompleted = totalCompleted;
            return this;
        }

        public QuestBuilder  totalActive(double totalActive) {
            this.totalActive = totalActive;
            return this;
        }

        public QuestBuilder  chores(List<Chore> chores) {
            this.chores = chores;
            return this;
        }

        public QuestBuilder  dateAdded(String dateAdded) {
            this.dateAdded = dateAdded;
            return this;
        }

        public QuestBuilder  dateDefeated(String dateDefeated) {
            this.dateDefeated = dateDefeated;
            return this;
        }

        public QuestBuilder  mapImageUrl(String mapImageUrl) {
            this.mapImageUrl = mapImageUrl;
            return this;
        }

        public QuestBuilder  mapMarks(List<MapMark> mapMarks) {
            this.mapMarks = mapMarks;
            return this;
        }

        public Quest build() {
            return new Quest(this);
        }
    }
}
