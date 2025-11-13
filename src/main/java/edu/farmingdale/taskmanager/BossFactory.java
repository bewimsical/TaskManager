package edu.farmingdale.taskmanager;

import edu.farmingdale.taskmanager.Models.Bosses;

import java.util.ArrayList;
import java.util.UUID;

public class BossFactory {

   private BossFactory() {

    }

    public static Bosses generate(String name){
        String bossId = UUID.randomUUID().toString();
        Bosses boss = new Bosses();
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
