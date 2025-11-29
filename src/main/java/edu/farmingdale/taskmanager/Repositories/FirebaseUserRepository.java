package edu.farmingdale.taskmanager.Repositories;

import com.google.api.core.ApiFuture;
import com.google.cloud.firestore.*;
import edu.farmingdale.taskmanager.Models.Ritual;
import edu.farmingdale.taskmanager.Models.User;
import edu.farmingdale.taskmanager.Session;
import edu.farmingdale.taskmanager.TaskManagerApplication;
import edu.farmingdale.taskmanager.exceptions.ResourceNotFoundException;
import edu.farmingdale.taskmanager.factories.UserFactory;

import java.time.DayOfWeek;
import java.time.LocalDate;
import java.util.HashMap;
import java.util.Map;
import java.util.Optional;
import java.util.concurrent.ExecutionException;

public class FirebaseUserRepository {

    public User getUserById(String id) throws ResourceNotFoundException{
        DocumentReference docRef = TaskManagerApplication.fstore.collection("users").document(id);
        ApiFuture<DocumentSnapshot> future = docRef.get();


        try {
            DocumentSnapshot document = future.get();
            if (document.exists()){
                Optional<User> opUser = Optional.ofNullable(document.toObject(User.class));
                User user = opUser.orElseThrow(() -> new ResourceNotFoundException("User not found with id: " + id));
                System.out.printf("User %s Successfully logged in!\n", user.getUsername());
                return user;
            }
        } catch (InterruptedException | ExecutionException e) {
            throw new ResourceNotFoundException("User not found with id: " + id);
        }
        throw new ResourceNotFoundException("User not found with id: " + id);
    }

    public User getUserByUsername(String username) throws ResourceNotFoundException{
        ApiFuture<QuerySnapshot> future = TaskManagerApplication.fstore.collection("users")
                .whereEqualTo("username", username)
                .limit(1)
                .get();
        try {
            QuerySnapshot document = future.get();
            if (!document.isEmpty()){
                DocumentSnapshot doc = document.getDocuments().get(0);
                Optional<User> opUser = Optional.ofNullable(doc.toObject(User.class));
                User user = opUser.orElseThrow(() -> new ResourceNotFoundException("User not found with username: " + username));
                System.out.printf("User %s Successfully logged in!\n", user.getUsername());
                return user;
            }
        } catch (InterruptedException | ExecutionException e) {
            throw new ResourceNotFoundException("User not found with username: " + username);
        }
        throw new ResourceNotFoundException("User not found with username: " + username);
    }

    public void setUser(User user){
        DocumentReference docRef = TaskManagerApplication.fstore.collection("users").document(user.getId());
        ApiFuture<WriteResult> result = docRef.set(user);

        result.addListener(() -> {
            try {
                System.out.println("successfully updated at: " + result.get().getUpdateTime());
            } catch (Exception e) {
                e.printStackTrace();
            }
        }, Runnable::run);
    }

    public void updateUser(User user){
        DocumentReference docRef = TaskManagerApplication.fstore.collection("users").document(user.getId());
        ApiFuture<WriteResult> result = docRef.set(user, SetOptions.merge());

        result.addListener(() -> {
            try {
                System.out.println("successfully updated at: " + result.get().getUpdateTime());
            } catch (Exception e) {
                e.printStackTrace();
            }
        }, Runnable::run);
    }
    //TODO move to sign in??
    public void checkWeekStart(User user){
        LocalDate today = LocalDate.now();
        DayOfWeek weekStartDay;
        LocalDate currentWeekStart;

        if (user.getWeekStart() != null) {
            weekStartDay = DayOfWeek.valueOf(user.getWeekStart());
        }
        else{
            weekStartDay = DayOfWeek.valueOf("MONDAY");
        }
        currentWeekStart = UserFactory.computeWeekStart(weekStartDay);
        System.out.println(user.getWeekStartDate());
        System.out.println(currentWeekStart);
        if (LocalDate.parse(user.getWeekStartDate()).isBefore(currentWeekStart)){
            //reset week streak tracker
            Map<String, Boolean> weekStreak = new HashMap<>();
            for (int i = 1; i < 8; i++) {
                String day = String.valueOf(i);
                weekStreak.put(day, false);
            }
            user.setWeekStreak(weekStreak);

            //TODO reset bosses

            //TODO reset quests

        }
    }
    //TODO move to sign in??
    public void checkLastRitualDate(User user){
        LocalDate today = LocalDate.now();
        LocalDate last = LocalDate.parse(user.getLastRitualComplete());

        if (last.equals(today) || last.equals(today.minusDays(1))) {
            // Already counted today – do nothing
        } else {
            // Missed a day → reset streak
            user.setStreak(0);
            user.setXpBonus(1);
        }
    }


}
