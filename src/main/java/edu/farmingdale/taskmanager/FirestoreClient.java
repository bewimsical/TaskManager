package edu.farmingdale.taskmanager;

import com.google.api.core.ApiFuture;
import com.google.cloud.firestore.*;
import edu.farmingdale.taskmanager.Models.*;
import edu.farmingdale.taskmanager.Repositories.*;
import edu.farmingdale.taskmanager.exceptions.ResourceNotFoundException;
import edu.farmingdale.taskmanager.factories.BossFactory;
import edu.farmingdale.taskmanager.factories.QuestFactory;
import edu.farmingdale.taskmanager.factories.UserFactory;

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


        FirebaseUserRepository userRepository = new FirebaseUserRepository();
        FirebasePartyRepository partyRepository = new FirebasePartyRepository();
        User user  = null;
        try {
            user = userRepository.getUserById(id);
            Session.getInstance().setUser(user);
            System.out.printf("User %s Successfully logged in!\n", user.getUsername());

            //add friends to session
            if (user.getFriends() == null){
                user.setFriends(new ArrayList<>());
            }
            if (user.getParties() == null){
                user.setParties(new ArrayList<>());
            }

            Session.getInstance().setFriends(new ArrayList<>());
            Session.getInstance().setParties(new ArrayList<>());

            for (String idString: user.getFriends()){
                User friend = userRepository.getUserById(idString);
                if (friend != null){
                    Session.getInstance().getFriends().add(friend);
                }
            }

            for (String idString: user.getParties()){
                Party party = partyRepository.getParty(idString);
                if (party != null){
                    Session.getInstance().getParties().add(party);
                }
            }
        } catch (ResourceNotFoundException e) {
            System.out.println(e.getMessage());
        }
//        try {
//            DocumentSnapshot document = future.get();
//            if (document.exists()){
//                User user = document.toObject(User.class);
//                Session.getInstance().setUser(user);
//                System.out.printf("User %s Successfully logged in!\n", user.getUsername());
//            }
//        } catch (InterruptedException | ExecutionException e) {
//            System.out.println("No such document!");
//        }
        //Update this for bosses
        System.out.println("Trying to load bosses");
//        try {
//            List<QueryDocumentSnapshot> documents = futureBoss.get().getDocuments();
//            Map<String, List<Boss>> bosses = new HashMap<>();
//            bosses.put("Bounties", new ArrayList<>());
//            bosses.put("Vanquished", new ArrayList<>());
//            if (!documents.isEmpty()){
//                System.out.println("Reading bosses from the database");
//                for(QueryDocumentSnapshot document: documents){
//                    Boss boss = document.toObject(Boss.class);
//                    if (boss.isBounties()){
//                        bosses.get("Bounties").add(boss);
//                    }
//                    if (boss.isVanquished()) {
//                        bosses.get("Vanquished").add(boss);
//                    }
//                }
//                Session.getInstance().setBosses(bosses);
//
//                System.out.println("bosses succssfully added to Session!");
//            }
//        } catch (InterruptedException | ExecutionException e) {
//            System.out.println("No such document!");
//        }
        FirebaseBossRepository bossRepository = new FirebaseBossRepository();
        Map<String, List<Boss>> bosses;
        try {
            bosses = bossRepository.getBosses(Session.getInstance().getUser());

        } catch (ResourceNotFoundException e) {
            System.out.println(e.getMessage());
            bosses = new HashMap<>();
            bosses.put("Bounties", new ArrayList<>());
            bosses.put("Vanquished", new ArrayList<>());
        }
        Session.getInstance().setBosses(bosses);

        System.out.println("Trying to load chores");
//        try {
//            List<QueryDocumentSnapshot> documents = futureChore.get().getDocuments();
//            List<Chore> chores = new ArrayList<>();
//
//            if (!documents.isEmpty()){
//                System.out.println("Reading chores from the database");
//                for(QueryDocumentSnapshot document: documents){
//                    Chore chore = document.toObject(Chore.class);
//                    chores.add(chore);
//                }
//                Session.getInstance().setChores(chores);
//
//                System.out.println("Chores succssfully added to Session!");
//            }
//        } catch (InterruptedException | ExecutionException e) {
//            System.out.println("No such document!");
//        }

        FirebaseChoreRepository choreRepository = new FirebaseChoreRepository();
        List<Chore> chores;
        try {
            chores = choreRepository.getChores(Session.getInstance().getUser());

        } catch (ResourceNotFoundException e) {
            System.out.println(e.getMessage());
            chores = new ArrayList<>();
        }
        Session.getInstance().setChores(chores);
        //Fix this later
        FirebaseRitualRepository ritualRepository = new FirebaseRitualRepository();
        Map<String, List<Ritual>> rituals;
        try {
            rituals = ritualRepository.getRituals(Session.getInstance().getUser());

        } catch (ResourceNotFoundException e) {
            System.out.println(e.getMessage());
            rituals = new HashMap<>();
            rituals.put("Morning", new ArrayList<>());
            rituals.put("Midday", new ArrayList<>());
            rituals.put("Evening", new ArrayList<>());
        }
        Session.getInstance().setRituals(rituals);

        //Fix this later
        FirebaseQuestRepository questRepository = new FirebaseQuestRepository();
        Map<String, List<Quest>> quests;
        try {
            quests = questRepository.getQuests(Session.getInstance().getUser());

        } catch (ResourceNotFoundException e) {
            System.out.println(e.getMessage());
            quests = new HashMap<>();
            quests.put("Active", new ArrayList<>());
            quests.put("Complete", new ArrayList<>());
        }
        Session.getInstance().setQuests(quests);

        //Fix this later
        Session.getInstance().setAssignedChoreIds(new HashSet<>());

        //these methods will be moved into sign in VM
        userRepository.checkWeekStart(user, bosses, quests);
        userRepository.checkLastRitualDate(user);

    }
    //Fake sign up method for testing users
    public static void signUp(String userId, String username, String password, String email, int age, String profileImage, String weekStart){
        //This would be set up in the sign up viewModel
        FirebaseUserRepository userRepository = new FirebaseUserRepository();
        FirebaseChoreRepository choreRepository = new FirebaseChoreRepository();
        FirebaseQuestRepository questRepository = new FirebaseQuestRepository();
        FirebaseBossRepository bossRepository = new FirebaseBossRepository();

        //TODO this would be generated by the Auth
        //String userId = UUID.randomUUID().toString();
        //create a user
        User user = UserFactory.generate(userId, username,password,email,age,profileImage, weekStart);
        userRepository.setUser(user);

        //generate user chores
        try {
            List<Chore> chores = choreRepository.getDefaultChores();
            for (Chore chore: chores){
                String uuid = UUID.randomUUID().toString();
                chore.setId(uuid);
                choreRepository.setChore(chore, user);
            }
        } catch (ResourceNotFoundException e) {
            System.out.println(e.getMessage());;
        }
        //generate bosses
        for (int i = 0; i < 4; i++) {
            Boss b = BossFactory.generate();
            bossRepository.setBoss(b, user);
        }
        //generate quests
        for (int i = 0; i < 4; i++) {
            Quest q = QuestFactory.generate();
            questRepository.setQuest(q, user);
        }

    }




}
