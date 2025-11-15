package edu.farmingdale.taskmanager;

import com.google.cloud.firestore.*;
import com.google.firebase.auth.FirebaseAuth;
import javafx.application.Application;
import javafx.beans.binding.Bindings;
import javafx.beans.binding.DoubleBinding;
import javafx.fxml.FXMLLoader;
import javafx.scene.Group;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;

import java.io.IOException;

public class TaskManagerApplication extends Application {
    public static Firestore fstore;
    public static FirebaseAuth fauth;
    private final FirestoreContext contxtFirebase = new FirestoreContext();

    @Override
    public void start(Stage stage) throws IOException {
        fstore = contxtFirebase.firebase();
        fauth = FirebaseAuth.getInstance();


        //test user "login"
        //create the user
        //Map<String, LinkedList<Bosses>> bosses = new HashMap<>();
//        List<Bosses> bosses = new ArrayList<>();
//
//        bosses.add(BossFactory.generate("boss1"));
//        bosses.add(BossFactory.generate("boss2"));
//        bosses.add(BossFactory.generate("boss3"));
//
//
//        User hazelTheNut = new User.UserBuilder()
//                .username("HazelTheNut")
//                .level(7)
//                .xp(75)
//                .build();
//
//        //Add a user to the database
//        FirestoreClient.setDocument(hazelTheNut, "users", "user1");
//
//        //add bosses subcollection
//        for (Bosses boss: bosses) {
//            DocumentReference docRef = TaskManagerApplication.fstore.collection("users")
//                    .document("user1")
//                    .collection("bosss")
//                    .document(boss.getId());
//            ApiFuture<WriteResult> result = docRef.set(boss);
//
//            result.addListener(() -> {
//                try {
//                    System.out.println("user" + "successfully updated at: " + result.get().getUpdateTime());
//                } catch (Exception e) {
//                    e.printStackTrace();
//                }
//            }, Runnable::run);
//        }

//        // add chores subcollection
//        CollectionReference choreRef = TaskManagerApplication.fstore.collection("chores");
//        ApiFuture<QuerySnapshot> futureChores = choreRef.get();
//        try {
//            List<QueryDocumentSnapshot> documents = futureChores.get().getDocuments();
//            if (!documents.isEmpty()){
//                System.out.println("Reading chores from the database");
//                for(QueryDocumentSnapshot document: documents){
//                    Chore chore = document.toObject(Chore.class);
//                    String uuid = UUID.randomUUID().toString();
//                    chore.setId(uuid);
//
//                    FirestoreClient.setDocument(chore, "users", "user1","chores", uuid);
//                }
//
//
//                System.out.println("bosses succssfully added to Session!");
//            }
//
//
//        } catch (InterruptedException | ExecutionException e) {
//            System.out.println("No such document!");
//        }

        //get user from the database and set the session
        FirestoreClient.getUser("users", "user1");

        FXMLLoader fxmlLoader = new FXMLLoader(TaskManagerApplication.class.getResource("profile-view.fxml"));
        Parent root = fxmlLoader.load();

        double baseWidth = 1440;
        double baseHeight = 1024;

        Pane wrapper = setup(root);
        Scene scene = new Scene(wrapper, baseWidth, baseHeight);
        scene.getStylesheets().add(getClass().getResource("styles/style.css").toExternalForm());
        stage.setTitle("Chore Quest");
        stage.setScene(scene);
        stage.show();

    }

    public static Pane setup(Parent root){
        // base dimensions
        double baseWidth = 1440;
        double baseHeight = 1024;

        Group scaledGroup = new Group(root);
        Pane wrapper = new Pane(scaledGroup);
        wrapper.getStyleClass().add("bg");

        // Bind scale based on window size, maintaining aspect ratio
        DoubleBinding scaleBinding = Bindings.createDoubleBinding(() ->
                        Math.min(wrapper.getWidth() / baseWidth, wrapper.getHeight() / baseHeight),
                wrapper.widthProperty(),
                wrapper.heightProperty()
        );
        scaledGroup.scaleXProperty().bind(scaleBinding);
        scaledGroup.scaleYProperty().bind(scaleBinding);

        // Center content
        scaledGroup.layoutXProperty().bind(
                Bindings.createDoubleBinding(() -> {
                            double scale = scaledGroup.getScaleX();
                            double scaledWidth = baseWidth * scale;
                            double x = (wrapper.getWidth() - scaledWidth) / 2;
                            return ((scaledWidth - baseWidth)/2) + x;
                        },
                        wrapper.widthProperty(),
                        wrapper.heightProperty(),
                        scaleBinding
                )
        );
        scaledGroup.layoutYProperty().bind(
                Bindings.createDoubleBinding(() -> {
                            double y = (wrapper.getHeight() - baseHeight * scaledGroup.getScaleY()) / 2;
                            double scale = scaleBinding.get();
                            double scaledHeight = baseHeight * scale;
                            return ((scaledHeight - baseHeight)/2) + y;
                        },
                        wrapper.heightProperty(),
                        wrapper.widthProperty(),
                        scaleBinding
                )
        );
        return wrapper;
    }

}
