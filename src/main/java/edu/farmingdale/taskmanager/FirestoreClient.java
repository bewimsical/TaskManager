package edu.farmingdale.taskmanager;

import com.google.api.core.ApiFuture;
import com.google.cloud.firestore.DocumentReference;
import com.google.cloud.firestore.DocumentSnapshot;
import com.google.cloud.firestore.WriteResult;
import edu.farmingdale.taskmanager.Models.User;

import java.util.concurrent.ExecutionException;

public class FirestoreClient {

    private FirestoreClient(){}

    public static <T> void setDocument(T object, String collection, String id){
        DocumentReference docRef = TaskManagerApplication.fstore.collection(collection).document(id);
        ApiFuture<WriteResult> result = docRef.set(object);

        result.addListener(() -> {
            try {
                System.out.println(collection + "successfully updated at: " + result.get().getUpdateTime());
            } catch (Exception e) {
                e.printStackTrace();
            }
        }, Runnable::run);
    }

    public static User getUser(String collection, String id){
        DocumentReference docRef = TaskManagerApplication.fstore.collection(collection).document(id);
        ApiFuture<DocumentSnapshot> future = docRef.get();
        try {
            DocumentSnapshot document = future.get();
            if (document.exists()){
                User user = document.toObject(User.class);
                Session.getInstance().setUser(user);
                System.out.printf("User %s Successfully logged in!", user.getUsername());
                return user;
            }


        } catch (InterruptedException | ExecutionException e) {
            System.out.println("No such document!");
            return null;
        }
        return null;
    }


}
