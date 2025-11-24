package edu.farmingdale.taskmanager.Repositories;

import com.google.api.core.ApiFuture;
import com.google.cloud.firestore.*;
import edu.farmingdale.taskmanager.Models.Quest;
import edu.farmingdale.taskmanager.Models.Ritual;
import edu.farmingdale.taskmanager.Models.User;
import edu.farmingdale.taskmanager.TaskManagerApplication;
import edu.farmingdale.taskmanager.exceptions.ResourceNotFoundException;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ExecutionException;

public class FirebaseQuestRepository {
    public Map<String, List<Quest>> getQuests(User user) throws ResourceNotFoundException {
        CollectionReference ref = TaskManagerApplication.fstore.collection("users").document(user.getId()).collection("quests");
        ApiFuture<QuerySnapshot> future = ref.get();
        LocalDate today = LocalDate.now();

        try {
            List<QueryDocumentSnapshot> documents = future.get().getDocuments();
            Map<String, List<Quest>> quests = new HashMap<>();
            quests.put("Active", new ArrayList<>());
            quests.put("Complete", new ArrayList<>());

            if (documents.isEmpty()){
                throw new ResourceNotFoundException("No quests found for user");
            }
            System.out.println("Reading quests from the database");
            for(QueryDocumentSnapshot document: documents){
                Quest quest = document.toObject(Quest.class);
                if (quest.getDateAdded() != null) {
                    LocalDate questDate = LocalDate.parse(quest.getDateAdded());
                    if (today.equals(questDate.plusDays(8)) && !quest.isCompleted()) {
                        deleteQuest(quest, user);
                        // maybe set some flag to true? so I can:
                        // generate quests
                        // add them to db
                        // add them to the map
                        continue;
                    }
                }
                if (quest.isActive()){
                    quests.get("Active").add(quest);
                }
                if (quest.isCompleted()) {
                   quests.get("Complete").add(quest);
                }

            }
            System.out.println("quests succssfully added to Session!");
            return quests;

        } catch (InterruptedException | ExecutionException e) {
            System.out.println("No such document!");
            throw new ResourceNotFoundException("No rituals found for user");

        }
    }

    public void setQuest(Quest quest, User user){
        DocumentReference docRef = TaskManagerApplication.fstore.collection("users").document(user.getId()).collection("quests").document(quest.getId());
        ApiFuture<WriteResult> result = docRef.set(quest);

        result.addListener(() -> {
            try {
                System.out.println("successfully updated at: " + result.get().getUpdateTime());
            } catch (Exception e) {
                e.printStackTrace();
            }
        }, Runnable::run);
    }

    public void updateQuest(Quest quest, User user){
        DocumentReference docRef = TaskManagerApplication.fstore.collection("users").document(user.getId()).collection("quests").document(quest.getId());
        ApiFuture<WriteResult> result = docRef.set(quest, SetOptions.merge());

        result.addListener(() -> {
            try {
                System.out.println("successfully updated at: " + result.get().getUpdateTime());
            } catch (Exception e) {
                e.printStackTrace();
            }
        }, Runnable::run);
    }

    public void deleteQuest(Quest quest, User user, Runnable onSuccess){
        DocumentReference docRef = TaskManagerApplication.fstore.collection("users").document(user.getId()).collection("quests").document(quest.getId());
        ApiFuture<WriteResult> future = docRef.delete();

        future.addListener(() -> {
            try {
                WriteResult result = future.get();
                System.out.println("Quest successfully deleted at: " + result.getUpdateTime());

                // Run the callback on the JavaFX Application Thread
                if (onSuccess != null) {
                    javafx.application.Platform.runLater(onSuccess);
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        }, java.util.concurrent.Executors.newSingleThreadExecutor());
    }

    public void deleteQuest(Quest quest, User user){
        DocumentReference docRef = TaskManagerApplication.fstore.collection("users").document(user.getId()).collection("quests").document(quest.getId());
        ApiFuture<WriteResult> future = docRef.delete();

        future.addListener(() -> {
            try {
                WriteResult result = future.get();
                System.out.println("Quest successfully deleted at: " + result.getUpdateTime());

            } catch (Exception e) {
                e.printStackTrace();
            }
        }, java.util.concurrent.Executors.newSingleThreadExecutor());
    }
}
