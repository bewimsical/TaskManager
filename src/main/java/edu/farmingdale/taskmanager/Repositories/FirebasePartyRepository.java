package edu.farmingdale.taskmanager.Repositories;

import com.google.api.core.ApiFuture;
import com.google.cloud.firestore.*;
import edu.farmingdale.taskmanager.Models.Boss;
import edu.farmingdale.taskmanager.Models.Chore;
import edu.farmingdale.taskmanager.Models.Party;
import edu.farmingdale.taskmanager.Models.User;
import edu.farmingdale.taskmanager.Session;
import edu.farmingdale.taskmanager.TaskManagerApplication;
import edu.farmingdale.taskmanager.exceptions.ResourceNotFoundException;

import java.time.LocalDate;
import java.util.*;
import java.util.concurrent.ExecutionException;

public class FirebasePartyRepository {

    public List<Party> getParties(User user) {
        List<String> partyIds = user.getParties();
        List<Party> parties = null;
        try {
            for(String id:partyIds){
                Party p = getParty(id);
                parties.add(p);
            }
        } catch (ResourceNotFoundException e) {
            System.out.println(e.getMessage());
        }

        return parties;
    }

    public Party getParty(String id) throws ResourceNotFoundException {
        DocumentReference docRef = TaskManagerApplication.fstore.collection("parties").document(id);
        ApiFuture<DocumentSnapshot> future = docRef.get();

        try {
            DocumentSnapshot document = future.get();
            if (document.exists()){
                Optional<Party> opParty = Optional.ofNullable(document.toObject(Party.class));
                return opParty.orElseThrow(() -> new ResourceNotFoundException("Party not found with id: " + id));
            }
        } catch (InterruptedException | ExecutionException e) {
            throw new ResourceNotFoundException("party not found with id: " + id);
        }
        throw new ResourceNotFoundException("party not found with id: " + id);
    }

    public void setParty(Party party){
        DocumentReference docRef = TaskManagerApplication.fstore.collection("parties").document(party.getId());
        ApiFuture<WriteResult> result = docRef.set(party);

        result.addListener(() -> {
            try {
                System.out.println("successfully updated at: " + result.get().getUpdateTime());
            } catch (Exception e) {
                e.printStackTrace();
            }
        }, Runnable::run);
    }

    public void updateParty(Party party){
        DocumentReference docRef = TaskManagerApplication.fstore.collection("parties").document(party.getId());
        ApiFuture<WriteResult> result = docRef.set(party, SetOptions.merge());

        result.addListener(() -> {
            try {
                System.out.println("successfully updated at: " + result.get().getUpdateTime());
            } catch (Exception e) {
                e.printStackTrace();
            }
        }, Runnable::run);
    }

    public void deleteParty(Party party, Runnable onSuccess){
        DocumentReference docRef = TaskManagerApplication.fstore.collection("parties").document(party.getId());
        ApiFuture<WriteResult> future = docRef.delete();

        future.addListener(() -> {
            try {
                WriteResult result = future.get();
                System.out.println("Party successfully deleted at: " + result.getUpdateTime());

                // Run the callback on the JavaFX Application Thread
                if (onSuccess != null) {
                    javafx.application.Platform.runLater(onSuccess);
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        }, java.util.concurrent.Executors.newSingleThreadExecutor());
    }

    public void deleteChore(Party party){
        DocumentReference docRef = TaskManagerApplication.fstore.collection("parties").document(party.getId());
        ApiFuture<WriteResult> future = docRef.delete();

        future.addListener(() -> {
            try {
                WriteResult result = future.get();
                System.out.println("Party successfully deleted at: " + result.getUpdateTime());

            } catch (Exception e) {
                e.printStackTrace();
            }
        }, java.util.concurrent.Executors.newSingleThreadExecutor());
    }
}
