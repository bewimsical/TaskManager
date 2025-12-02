package edu.farmingdale.taskmanager.Repositories;

import com.google.api.core.ApiFuture;
import com.google.cloud.firestore.*;
import edu.farmingdale.taskmanager.Models.Chore;
import edu.farmingdale.taskmanager.Models.User;
import edu.farmingdale.taskmanager.TaskManagerApplication;
import edu.farmingdale.taskmanager.exceptions.ResourceNotFoundException;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ExecutionException;

public class FirebaseChoreRepository {

    public List<Chore> getChores(User user) throws ResourceNotFoundException {
        CollectionReference ref = TaskManagerApplication.fstore.collection("users").document(user.getId()).collection("chores");
        ApiFuture<QuerySnapshot> future = ref.get();
        LocalDate today = LocalDate.now();

        try {
            List<QueryDocumentSnapshot> documents = future.get().getDocuments();
            List<Chore> chores = new ArrayList<>();

            if (documents.isEmpty()){
                throw new ResourceNotFoundException("No chores found for user");
            }
            System.out.println("Reading chores from the database");
            for(QueryDocumentSnapshot document: documents){
                Chore chore = document.toObject(Chore.class);
                chores.add(chore);

            }
            System.out.println("chores succssfully added to Session!");
            return chores;

        } catch (InterruptedException | ExecutionException e) {
            System.out.println("No such document!");
            throw new ResourceNotFoundException("No chores found for user");

        }
    }

    public List<Chore> getDefaultChores() throws ResourceNotFoundException {
        CollectionReference ref = TaskManagerApplication.fstore.collection("chores");
        ApiFuture<QuerySnapshot> future = ref.get();
        try {
            List<QueryDocumentSnapshot> documents = future.get().getDocuments();
            List<Chore> chores = new ArrayList<>();

            if (documents.isEmpty()){
                throw new ResourceNotFoundException("No chores found");
            }
            System.out.println("Reading chores from the database");
            for(QueryDocumentSnapshot document: documents){
                Chore chore = document.toObject(Chore.class);
                chores.add(chore);
            }
            System.out.println("chores succssfully added to Session!");
            return chores;

        } catch (InterruptedException | ExecutionException e) {
            System.out.println("No such document!");
            throw new ResourceNotFoundException("No chores found for user");
        }
    }

    public void setChore(Chore chore, User user){
        DocumentReference docRef = TaskManagerApplication.fstore.collection("users").document(user.getId()).collection("chores").document(chore.getId());
        ApiFuture<WriteResult> result = docRef.set(chore);

        result.addListener(() -> {
            try {
                System.out.println("successfully updated at: " + result.get().getUpdateTime());
            } catch (Exception e) {
                e.printStackTrace();
            }
        }, Runnable::run);
    }

    public void updateChore(Chore chore, User user){
        DocumentReference docRef = TaskManagerApplication.fstore.collection("users").document(user.getId()).collection("chores").document(chore.getId());
        ApiFuture<WriteResult> result = docRef.set(chore, SetOptions.merge());

        result.addListener(() -> {
            try {
                System.out.println("successfully updated at: " + result.get().getUpdateTime());
            } catch (Exception e) {
                e.printStackTrace();
            }
        }, Runnable::run);
    }

    public void deleteChore(Chore chore, User user, Runnable onSuccess){
        DocumentReference docRef = TaskManagerApplication.fstore.collection("users").document(user.getId()).collection("chores").document(chore.getId());
        ApiFuture<WriteResult> future = docRef.delete();

        future.addListener(() -> {
            try {
                WriteResult result = future.get();
                System.out.println("Chore successfully deleted at: " + result.getUpdateTime());

                // Run the callback on the JavaFX Application Thread
                if (onSuccess != null) {
                    javafx.application.Platform.runLater(onSuccess);
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        }, java.util.concurrent.Executors.newSingleThreadExecutor());
    }

    public void deleteChore(Chore chore, User user){
        DocumentReference docRef = TaskManagerApplication.fstore.collection("users").document(user.getId()).collection("chores").document(chore.getId());
        ApiFuture<WriteResult> future = docRef.delete();

        future.addListener(() -> {
            try {
                WriteResult result = future.get();
                System.out.println("Chore successfully deleted at: " + result.getUpdateTime());

            } catch (Exception e) {
                e.printStackTrace();
            }
        }, java.util.concurrent.Executors.newSingleThreadExecutor());
    }
}//end class
