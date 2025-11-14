package edu.farmingdale.taskmanager;

import com.google.api.core.ApiFuture;
import com.google.cloud.firestore.*;
import edu.farmingdale.taskmanager.Models.Bosses;
import edu.farmingdale.taskmanager.Models.User;

import java.util.*;
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

    public static <T> void setDocument(T object, String collection, String docId, String subCollection, String subDocId){
        DocumentReference docRef = TaskManagerApplication.fstore.collection(collection).document(docId).collection(subCollection).document(subDocId);


        ApiFuture<WriteResult> result = docRef.set(object);

        result.addListener(() -> {
            try {
                System.out.println(collection + "successfully updated at: " + result.get().getUpdateTime());
            } catch (Exception e) {
                e.printStackTrace();
            }
        }, Runnable::run);
    }

    public static void getUser(String collection, String id){
        DocumentReference docRef = TaskManagerApplication.fstore.collection(collection).document(id);
        CollectionReference bossRef = TaskManagerApplication.fstore.collection(collection).document(id).collection("bosss");
        ApiFuture<DocumentSnapshot> future = docRef.get();
        ApiFuture<QuerySnapshot> futureBoss = bossRef.get();

        try {
            DocumentSnapshot document = future.get();
            if (document.exists()){
                User user = document.toObject(User.class);
                Session.getInstance().setUser(user);
                System.out.printf("User %s Successfully logged in!", user.getUsername());
            }


        } catch (InterruptedException | ExecutionException e) {
            System.out.println("No such document!");
        }
        //Update this for bosses
        try {
            List<QueryDocumentSnapshot> documents = futureBoss.get().getDocuments();
            Map<String, List<Bosses>> bosses = new HashMap<>();
            bosses.put("Bounties", new ArrayList<>());
            bosses.put("Vanquished", new ArrayList<>());
            if (!documents.isEmpty()){
                System.out.println("Reading bosses from the database");
                for(QueryDocumentSnapshot document: documents){
                    Bosses boss = document.toObject(Bosses.class);
                    if (boss.isBounties()){
                        bosses.get("Bounties").add(boss);
                    } else if (boss.isVanquished()) {
                        bosses.get("Vanquished").add(boss);
                    }
                }
                Session.getInstance().setBosses(bosses);

                System.out.println("bosses succssfully added to Session!");
            }


        } catch (InterruptedException | ExecutionException e) {
            System.out.println("No such document!");
        }
    }


}
