package edu.farmingdale.taskmanager.Repositories;

import com.google.api.core.ApiFuture;
import com.google.cloud.firestore.*;
import edu.farmingdale.taskmanager.Models.Boss;
import edu.farmingdale.taskmanager.Models.Quest;
import edu.farmingdale.taskmanager.Models.User;
import edu.farmingdale.taskmanager.TaskManagerApplication;
import edu.farmingdale.taskmanager.exceptions.ResourceNotFoundException;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ExecutionException;

public class FirebaseBossRepository {

    public Map<String, List<Boss>> getBosses(User user) throws ResourceNotFoundException {
        CollectionReference ref = TaskManagerApplication.fstore.collection("users").document(user.getId()).collection("bosss");
        ApiFuture<QuerySnapshot> future = ref.get();
        LocalDate today = LocalDate.now();

        try {
            List<QueryDocumentSnapshot> documents = future.get().getDocuments();
            Map<String, List<Boss>> bosses = new HashMap<>();
            bosses.put("Bounties", new ArrayList<>());
            bosses.put("Vanquished", new ArrayList<>());

            if (documents.isEmpty()){
                throw new ResourceNotFoundException("No bosses found for user");
            }
            System.out.println("Reading bosses from the database");
            for(QueryDocumentSnapshot document: documents){
                Boss boss = document.toObject(Boss.class);
                if (boss.isBounties()){
                    bosses.get("Bounties").add(boss);
                }
                if (boss.isVanquished()) {
                    bosses.get("Vanquished").add(boss);
                }

            }
            System.out.println("bosses succssfully added to Session!");
            return bosses;

        } catch (InterruptedException | ExecutionException e) {
            System.out.println("No such document!");
            throw new ResourceNotFoundException("No bosses found for user");
        }
    }

    public void setBoss(Boss boss, User user){
        DocumentReference docRef = TaskManagerApplication.fstore.collection("users").document(user.getId()).collection("bosss").document(boss.getId());
        ApiFuture<WriteResult> result = docRef.set(boss);

        result.addListener(() -> {
            try {
                System.out.println("successfully updated at: " + result.get().getUpdateTime());
            } catch (Exception e) {
                e.printStackTrace();
            }
        }, Runnable::run);
    }

    public void updateBoss(Boss boss, User user){
        DocumentReference docRef = TaskManagerApplication.fstore.collection("users").document(user.getId()).collection("bosss").document(boss.getId());
        ApiFuture<WriteResult> result = docRef.set(boss, SetOptions.merge());

        result.addListener(() -> {
            try {
                System.out.println("successfully updated at: " + result.get().getUpdateTime());
            } catch (Exception e) {
                e.printStackTrace();
            }
        }, Runnable::run);
    }

    public void deleteBoss(Boss boss, User user, Runnable onSuccess){
        DocumentReference docRef = TaskManagerApplication.fstore.collection("users").document(user.getId()).collection("bosss").document(boss.getId());
        ApiFuture<WriteResult> future = docRef.delete();

        future.addListener(() -> {
            try {
                WriteResult result = future.get();
                System.out.println("Boss successfully deleted at: " + result.getUpdateTime());

                // Run the callback on the JavaFX Application Thread
                if (onSuccess != null) {
                    javafx.application.Platform.runLater(onSuccess);
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        }, java.util.concurrent.Executors.newSingleThreadExecutor());
    }

    public void deleteBoss(Boss boss, User user){
        DocumentReference docRef = TaskManagerApplication.fstore.collection("users").document(user.getId()).collection("bosss").document(boss.getId());
        ApiFuture<WriteResult> future = docRef.delete();

        future.addListener(() -> {
            try {
                WriteResult result = future.get();
                System.out.println("Boss successfully deleted at: " + result.getUpdateTime());

            } catch (Exception e) {
                e.printStackTrace();
            }
        }, java.util.concurrent.Executors.newSingleThreadExecutor());
    }
}
