package edu.farmingdale.taskmanager.Repositories;

import com.google.api.core.ApiFuture;
import com.google.cloud.firestore.*;
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
import java.util.concurrent.Executors;

public class FirebaseRitualRepository {

    public Map<String,List<Ritual>> getRituals(User user) throws ResourceNotFoundException{
        CollectionReference ref = TaskManagerApplication.fstore.collection("users").document(user.getId()).collection("rituals");
        ApiFuture<QuerySnapshot> future = ref.get();
        LocalDate today = LocalDate.now();

        try {
            List<QueryDocumentSnapshot> documents = future.get().getDocuments();
            Map<String, List<Ritual>> rituals = new HashMap<>();
            rituals.put("Morning", new ArrayList<>());
            rituals.put("Midday", new ArrayList<>());
            rituals.put("Evening", new ArrayList<>());

            if (documents.isEmpty()){
                throw new ResourceNotFoundException("No rituals found for user");
            }
            System.out.println("Reading rituals from the database");
            for(QueryDocumentSnapshot document: documents){
                Ritual ritual = document.toObject(Ritual.class);
                if (ritual.getDateRecorded() != null) {
                    LocalDate ritualDate = LocalDate.parse(ritual.getDateRecorded());
                    if (!today.equals(ritualDate)) {
                        ritual.setCompleted(false);

                    }
                }
                rituals.get(ritual.getTimeOfDay().toString()).add(ritual);

            }
            System.out.println("rituals succssfully added to Session!");
            return rituals;

        } catch (InterruptedException | ExecutionException e) {
            System.out.println("No such document!");
            throw new ResourceNotFoundException("No rituals found for user");

        }
    }

    public void setRitual(Ritual ritual, User user){
        System.out.println("User id "+user.getId() );
        System.out.println("Ritual id " + ritual.getId());
        DocumentReference docRef = TaskManagerApplication.fstore.collection("users").document(user.getId()).collection("rituals").document(ritual.getId());
        ApiFuture<WriteResult> result = docRef.set(ritual);

        result.addListener(() -> {
            try {
                System.out.println("successfully updated at: " + result.get().getUpdateTime());
            } catch (Exception e) {
                e.printStackTrace();
            }
        }, Runnable::run);
    }

    public void updateRitual(Ritual ritual, User user){
        DocumentReference docRef = TaskManagerApplication.fstore.collection("users").document(user.getId()).collection("rituals").document(ritual.getId());
        ApiFuture<WriteResult> result = docRef.set(ritual, SetOptions.merge());

        result.addListener(() -> {
            try {
                System.out.println("successfully updated at: " + result.get().getUpdateTime());
            } catch (Exception e) {
                e.printStackTrace();
            }
        }, Runnable::run);
    }

    public void deleteRitual(Ritual ritual, User user, Runnable onSuccess){
        DocumentReference docRef = TaskManagerApplication.fstore.collection("users").document(user.getId()).collection("rituals").document(ritual.getId());
        ApiFuture<WriteResult> future = docRef.delete();

        future.addListener(() -> {
            try {
                WriteResult result = future.get();
                System.out.println("Ritual successfully deleted at: " + result.getUpdateTime());

                // Run the callback on the JavaFX Application Thread
                if (onSuccess != null) {
                    javafx.application.Platform.runLater(onSuccess);
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        }, java.util.concurrent.Executors.newSingleThreadExecutor());
    }
}
