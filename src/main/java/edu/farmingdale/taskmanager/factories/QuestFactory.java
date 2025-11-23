package edu.farmingdale.taskmanager.factories;

import edu.farmingdale.taskmanager.Models.Quest;

import java.util.Random;
import java.util.UUID;

public class QuestFactory {

    private static final String[] BOSS_ADJECTIVES = {
            "Frenzied",
            "Chaotic",
            "Grimy",
            "Filthy",
            "Dusty",
            "Sticky",
            "Moldy",
            "Gunky",
            "Crusty",
            "Stinky",
            "Overdue",
            "Lazy",
            "Unwashed",
            "Cluttered",
            "Greasy",
            "Muddy",
            "Smudged",
            "Soaked",
            "Tangled",
            "Rotted",
            "Clutter-Crowned",
            "Dust-Forged",
            "Trash-Bound",
            "Lint-Laden",
            "Scrub-Scarred"
    };

    private static final String[] QUEST_NAMES = {
            "The Hero of the Household",
            "The Taskmaster’s Challenge",
            "The Orderbringer Assignment",
            "The Keeper of the Clean Realm",
            "The Homeward Hero’s Mission",
            "The Quest for Order",
            "The Great Housewide Challenge",
            "The Steward’s Cleanly Charge",
            "The Realm of Tidy Trials",
            "The Whispering Chores of Dawn",
            "The Bright Broom Allegiance",
            "The Everclean Expedition",
            "The Shining Hearth Assignment",
            "The Cleanstone Quest",
            "The Gleamgate Mission",
            "The Sparkspire Challenge",
            "The Housewind Duty",
            "The Hearthstar Expedition",
            "The Battle of the Daily Tasks",
            "The Orderkeeper’s Oath",
            "The Champion of Cleanliness",
            "The Guardian of the Hearth",
            "The Quest of the Organized Warrior",
            "The Vanguard of Daily Duty",
            "The Command of the Homefront",
            "The Path of the Taskbearer",
            "The Challenge of the Ordered Realm",
            "The Hero of the Homebound Frontier",
            "The Great Tidying Quest",
            "The Quest for the Clear Path",
            "The Gleamwarden Mission",
            "The Sparkling Order Assignment",
            "The Dustfall Expedition",
            "The Clarity Crest Trial",
            "The Shining Chore Circuit",
            "The Freshwind Challenge",
            "The Purity Path Quest",
            "The Evertidy Trial"
    };
    private static final Random random = new Random();

    private QuestFactory(){}

    public static Quest generate(){
        String questId = UUID.randomUUID().toString();
        String name = generateName();
        Quest quest = new Quest.QuestBuilder()
                .id(questId)
                .name(name)
                .build();
        return quest;
    }

    public static String generateName() {
        return QUEST_NAMES[new Random().nextInt(QUEST_NAMES.length)];
    }
}
