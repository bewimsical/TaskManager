package edu.farmingdale.taskmanager;

import com.google.api.core.ApiFuture;
import com.google.cloud.firestore.*;
import edu.farmingdale.taskmanager.Models.Boss;
import edu.farmingdale.taskmanager.Models.Chore;
import edu.farmingdale.taskmanager.Models.Ritual;
import edu.farmingdale.taskmanager.Models.User;
import edu.farmingdale.taskmanager.Repositories.FirebaseRitualRepository;
import edu.farmingdale.taskmanager.exceptions.ResourceNotFoundException;

import java.util.*;
import java.util.concurrent.ExecutionException;

public class FirestoreClient {

    private FirestoreClient(){}

    public static <T> void setDocument(T object, String collection, String id){
        DocumentReference docRef = TaskManagerApplication.fstore.collection(collection).document(id);
        setDocumentHelper(object, docRef);
    }

    public static <T> void setDocument(T object, String collection, String docId, String subCollection, String subDocId){
        DocumentReference docRef = TaskManagerApplication.fstore.collection(collection).document(docId).collection(subCollection).document(subDocId);
        setDocumentHelper(object, docRef);
    }

    private static <T> void setDocumentHelper(T object,DocumentReference docRef ){
        ApiFuture<WriteResult> result = docRef.set(object);

        result.addListener(() -> {
            try {
                System.out.println("successfully updated at: " + result.get().getUpdateTime());
            } catch (Exception e) {
                e.printStackTrace();
            }
        }, Runnable::run);
    }

    public static void getUser(String collection, String id){
        DocumentReference docRef = TaskManagerApplication.fstore.collection(collection).document(id);
        CollectionReference bossRef = TaskManagerApplication.fstore.collection(collection).document(id).collection("bosss");
        CollectionReference choreRef = TaskManagerApplication.fstore.collection(collection).document(id).collection("chores");
        ApiFuture<DocumentSnapshot> future = docRef.get();
        ApiFuture<QuerySnapshot> futureBoss = bossRef.get();
        ApiFuture<QuerySnapshot> futureChore = choreRef.get();

        try {
            DocumentSnapshot document = future.get();
            if (document.exists()){
                User user = document.toObject(User.class);
                Session.getInstance().setUser(user);
                System.out.printf("User %s Successfully logged in!\n", user.getUsername());
            }
        } catch (InterruptedException | ExecutionException e) {
            System.out.println("No such document!");
        }
        //Update this for bosses
        System.out.println("Trying to load bosses");
        try {
            List<QueryDocumentSnapshot> documents = futureBoss.get().getDocuments();
            Map<String, List<Boss>> bosses = new HashMap<>();
            bosses.put("Bounties", new ArrayList<>());
            bosses.put("Vanquished", new ArrayList<>());
            if (!documents.isEmpty()){
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
                Session.getInstance().setBosses(bosses);

                System.out.println("bosses succssfully added to Session!");
            }
        } catch (InterruptedException | ExecutionException e) {
            System.out.println("No such document!");
        }
        System.out.println("Trying to load chores");
        try {
            List<QueryDocumentSnapshot> documents = futureChore.get().getDocuments();
            List<Chore> chores = new ArrayList<>();

            if (!documents.isEmpty()){
                System.out.println("Reading chores from the database");
                for(QueryDocumentSnapshot document: documents){
                    Chore chore = document.toObject(Chore.class);
                    chores.add(chore);
                }
                Session.getInstance().setChores(chores);

                System.out.println("Chores succssfully added to Session!");
            }
        } catch (InterruptedException | ExecutionException e) {
            System.out.println("No such document!");
        }
        //Fix this later
        FirebaseRitualRepository ritualRepository = new FirebaseRitualRepository();
        Map<String, List<Ritual>> rituals = null;
        try {
            rituals = ritualRepository.getRituals(Session.getInstance().getUser());
            Session.getInstance().setRituals(rituals);
        } catch (ResourceNotFoundException e) {
            System.out.println(e.getMessage());
        }



        //Fix this later
        Session.getInstance().setAssignedChoreIds(new HashSet<>());



    }

    private static void getBosses(){

    }


}
