package edu.farmingdale.taskmanager.Models;

import java.util.List;

public class Party {
    String id;
    String name;
    String type;
    List<String> members;
    String quests;
    String bosses;

    public Party() {
    }
    public Party(PartyBuilder partyBuilder) {
        this.id = partyBuilder.id;
        this.name = partyBuilder.name;
        this.type = partyBuilder.type;
        this.members = partyBuilder.members;
        this.quests = partyBuilder.quests;
        this.bosses = partyBuilder.bosses;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public List<String> getMembers() {
        return members;
    }

    public void setMembers(List<String> members) {
        this.members = members;
    }

    public String getQuests() {
        return quests;
    }

    public void setQuests(String quests) {
        this.quests = quests;
    }

    public String getBosses() {
        return bosses;
    }

    public void setBosses(String bosses) {
        this.bosses = bosses;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public class PartyBuilder{
        String id;
        String name;
        String type;
        List<String> members;
        String quests;
        String bosses;

        public PartyBuilder() {
        }


        public PartyBuilder id(String id) {
            this.id = id;
            return this;
        }

        public PartyBuilder name(String name) {
            this.name = name;
            return this;
        }

        public PartyBuilder type(String type) {
            this.type = type;
            return this;
        }

        public PartyBuilder members(List<String> members) {
            this.members = members;
            return this;
        }

        public PartyBuilder quests(String quests) {
            this.quests = quests;
            return this;
        }

        public PartyBuilder bosses(String bosses) {
            this.bosses = bosses;
            return this;
        }
    }

}
