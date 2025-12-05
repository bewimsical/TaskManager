package edu.farmingdale.taskmanager.factories;

import edu.farmingdale.taskmanager.Models.Boss;
import edu.farmingdale.taskmanager.Models.Chore;
import edu.farmingdale.taskmanager.Session;

import java.time.LocalDate;
import java.util.*;
import java.util.stream.Collectors;

public class BossFactory {

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
    private static final Random random = new Random();

    private static final Set<String> assignedChores = Session.getInstance().getAssignedChoreIds();

   private BossFactory() {}

    public static Boss generate(){
        String bossId = UUID.randomUUID().toString();
        String baseUrl = image();
        String name = generateName(baseUrl);
        List<Chore> chores = generateChoreList();
        int health = getBossHP(chores);

        Boss boss = new Boss.BossesBuilder()
                .id(bossId)
                .dirtyImageUrl("Dirty"+baseUrl)
                .cleanImageUrl("Clean"+baseUrl)
                .name(name)
                .chores(chores)
                .totalHealth(health)
                .currentHealth(health)
                .xp((int) ( health*1.25))
                .dateAdded(LocalDate.now().toString())
                .bounties(true)
                .build();
        return boss;
    }

    public static Boss generate(String name, List<Chore> chores){
        String bossId = UUID.randomUUID().toString();
        String baseUrl = image();
        setupChores(chores);
        int health = getBossHP(chores);

        Boss boss = new Boss.BossesBuilder()
                .id(bossId)
                .dirtyImageUrl("Dirty"+baseUrl)
                .cleanImageUrl("Clean"+baseUrl)
                .name(name)
                .chores(chores)
                .totalHealth(health)
                .currentHealth(health)
                .xp((int) ( health*1.25))
                .dateAdded(LocalDate.now().toString())
                .bounties(true)
                .build();
        return boss;
    }

    private static List<Chore> generateChoreList(){
        List<Chore> chores = Session.getInstance().getAvailableChores();
        chores = chores.stream()
                .filter(c -> !c.getRooms().isEmpty())
                .toList();
        String[] rooms = {"Kitchen", "Bathroom", "Living Room","Dining Room"};
        List<Chore> filtered = new ArrayList<>();

        while (filtered.isEmpty() && !chores.isEmpty()) {
            String room = rooms[random.nextInt(rooms.length)];
            filtered = chores.stream()
                    .filter(chore -> chore.getRooms().contains(room))
                    .collect(Collectors.toList());
        }

        Collections.shuffle(filtered);

        List<Chore> bossChores = filtered.subList(0, Math.min(4, filtered.size()));
        setupChores(bossChores);
        return bossChores;
    }

    private static void setupChores(List<Chore> bossChores){
        for (Chore c: bossChores){
            c.setCompleted(false);
            c.setChoreXP(random.nextInt(300,500));
            assignedChores.add(c.getId());
        }
    }

    private static int getBossHP(List<Chore> chores){
       return chores.stream()
               .mapToInt(Chore::getChoreXP)
               .sum();
    }

    private static String image(){
        String[] images = {"BogFrog.PNG", "Dust Bunny.PNG", "Sea Serpent.PNG"};
        int index = random.nextInt(images.length);

        return images[index];
    }

    public static String generateName(String filename) {
        String base = cleanBaseName(filename);
        return randomPrefix() + " " + base;
    }

    //name helper method
    private static String randomPrefix() {
        return BOSS_ADJECTIVES[random.nextInt(BOSS_ADJECTIVES.length)];
    }

    //name helper method
    private static String cleanBaseName(String filename) {
        // Remove extension
        int dotIndex = filename.lastIndexOf('.');
        String base = (dotIndex > 0) ? filename.substring(0, dotIndex) : filename;

        // Replace dashes with spaces
        base = base.replace("-", " ");

        // Optional: lowercase everything except first letter of each word
        String[] words = base.split(" ");
        StringBuilder sb = new StringBuilder();
        for (String w : words) {
            if (!w.isEmpty()) {
                sb.append(Character.toUpperCase(w.charAt(0)))
                        .append(w.substring(1).toLowerCase())
                        .append(" ");
            }
        }

        return sb.toString().trim();
    }






}
