package edu.farmingdale.taskmanager;

import edu.farmingdale.taskmanager.Models.Boss;
import edu.farmingdale.taskmanager.Models.Chore;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

public class BossFactory {
    List<Chore> available = Session.getInstance().getAvailableChores();


   private BossFactory() {

    }

    public static Boss generate(String name){
        String bossId = UUID.randomUUID().toString();

        Boss boss = new Boss();
        boss.setName(name);
        boss.setId(bossId);
        boss.setXp(300);
        boss.setTotalHealth(650);
        boss.setCurrentHealth(650);
        boss.setChores(new ArrayList<>());
        boss.getChores().add("chore2");
        boss.getChores().add("chore3");
        boss.getChores().add("chore4");
        boss.getChores().add("chore5");
        boss.setBounties(true);
        boss.setVanquished(false);
        boss.setDirtyImageUrl("MonsterCardDirtyBogFrog.PNG");
        boss.setCleanImageUrl("MonsterCardCleanBogFrog.PNG");
        return boss;
    }
}
