package edu.farmingdale.taskmanager.factories;

import edu.farmingdale.taskmanager.Models.Chore;
import edu.farmingdale.taskmanager.Models.MapMark;
import edu.farmingdale.taskmanager.Models.Quest;
import edu.farmingdale.taskmanager.Session;

import java.time.LocalDate;
import java.util.*;

public class QuestFactory {

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
            "The Quest of the Organized",
            "The Vanguard of Daily Duty",
            "The Command of the Homefront",
            "The Path of the Taskbearer",
            "The Challenge of the Ordered",
            "The Hero of the Hearth",
            "The Great Tidying Quest",
            "The Quest for the Clear Path",
            "The Gleamwarden Mission",
            "The Sparkling Order Trial",
            "The Dustfall Expedition",
            "The Clarity Crest Trial",
            "The Shining Chore Circuit",
            "The Freshwind Challenge",
            "The Purity Path Quest",
            "The Evertidy Trial"
    };
    private static final Random random = new Random();
    private static final Set<String> assignedChores = Session.getInstance().getAssignedChoreIds();

    private QuestFactory(){}

    public static Quest generate(){
        String questId = UUID.randomUUID().toString();
        String name = generateName();
        List<Chore> chores = generateChoreList();
        int xp = getQuestXp(chores);
        List<MapMark> marks = generateMapMarks(chores);
        //need to add map image
        Quest quest = new Quest.QuestBuilder()
                .id(questId)
                .name(name)
                .chores(chores)
                .dateAdded(LocalDate.now().toString())
                .isActive(true)
                .xp(xp)
                .mapMarks(marks)
                .nextMarkIndex(0)
                .build();
        return quest;
    }
    public static Quest generate(String name, List<Chore> chores) {
        String questId = UUID.randomUUID().toString();
        setupChores(chores);
        int xp = getQuestXp(chores);
        List<MapMark> marks = generateMapMarks(chores);
        //need to add map image
        Quest quest = new Quest.QuestBuilder()
                .id(questId)
                .name(name)
                .chores(chores)
                .dateAdded(LocalDate.now().toString())
                .isActive(true)
                .xp(xp)
                .mapMarks(marks)
                .nextMarkIndex(0)
                .build();
        return quest;
    }

    public static String generateName() {
        return QUEST_NAMES[new Random().nextInt(QUEST_NAMES.length)];
    }

    private static List<Chore> generateChoreList(){
        List<Chore> chores = Session.getInstance().getAvailableChores();


        Collections.shuffle(chores);
        int num = new Random().nextInt(3,5);

        List<Chore> questChores = chores.subList(0, Math.min(num, chores.size()));
        setupChores(questChores);
        return questChores;
    }

    private static void setupChores(List<Chore> questChores){
        for (Chore c: questChores){
            c.setCompleted(false);
            c.setChoreXP(random.nextInt(300,500));
            assignedChores.add(c.getId());
        }
    }

    private static int getQuestXp(List<Chore> chores){
        int xp = chores.stream()
                .mapToInt(Chore::getChoreXP)
                .sum();

        return (int) (xp*1.25);
    }

    private static List<MapMark> generateMapMarks(List<Chore> chores){
        List<MapMark> marks = new ArrayList<>();

        int count = chores.size();
        double minDist = 100;        // minimum distance between markers
        double width = 813;
        double height = 377;

        double paddingLeft = 100;
        double paddingRight = 100;
        double paddingTop = 70;
        double paddingBottom = 70;

        double minX = paddingLeft;
        double maxX = width - paddingRight;
        double minY = paddingTop;
        double maxY = height - paddingBottom;

        while (marks.size() < count) {
            double x = random.nextDouble() * (maxX - minX) + minX;
            double y = random.nextDouble() * (maxY - minY) + minY;

            MapMark candidate = new MapMark.MapMarkBuilder()
                    .x(x)
                    .y(y)
                    .completionIndex(-1)
                    .build();

            if (isValid(candidate, marks, minDist)) {
                marks.add(candidate);
            }
        }


        for (int i = 0; i < count; i++) {
            MapMark m = marks.get(i);
            Chore c = chores.get(i);
            m.setChoreId(c.getId());
        }

        return marks;
    }

    private static boolean isValid(MapMark mark, List<MapMark> existing, double minDist) {
        for (MapMark m : existing) {
            double dx = mark.getX() - m.getX();
            double dy = mark.getY() - m.getY();
            double dist = Math.sqrt(dx * dx + dy * dy);

            if (dist < minDist) {
                return false;
            }
        }
        return true;
    }



}
