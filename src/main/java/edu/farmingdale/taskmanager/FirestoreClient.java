package edu.farmingdale.taskmanager;

import com.google.api.core.ApiFuture;
import com.google.cloud.firestore.DocumentReference;
import com.google.cloud.firestore.WriteResult;

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

    public static <T> void getDocument(){}


}
