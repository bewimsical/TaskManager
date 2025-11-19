package edu.farmingdale.taskmanager.Repositories;

import com.google.api.core.ApiFuture;
import com.google.cloud.firestore.DocumentReference;
import com.google.cloud.firestore.DocumentSnapshot;
import com.google.cloud.firestore.QuerySnapshot;
import edu.farmingdale.taskmanager.Models.User;
import edu.farmingdale.taskmanager.Session;
import edu.farmingdale.taskmanager.TaskManagerApplication;
import edu.farmingdale.taskmanager.exceptions.ResourceNotFoundException;

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


}
