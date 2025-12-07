package edu.farmingdale.taskmanager;

import com.google.api.core.ApiFuture;
import com.google.cloud.firestore.*;
import com.google.firebase.auth.FirebaseAuth;
import edu.farmingdale.taskmanager.Models.Boss;
import edu.farmingdale.taskmanager.Models.Chore;
import edu.farmingdale.taskmanager.Models.User;
import edu.farmingdale.taskmanager.Repositories.FirebaseUserRepository;
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
import java.util.*;
import java.util.concurrent.ExecutionException;

public class TaskManagerApplication extends Application {
    public static Firestore fstore;
    public static FirebaseAuth fauth;
    private final FirestoreContext contxtFirebase = new FirestoreContext();

    @Override
    public void start(Stage stage) throws IOException {
        fstore = contxtFirebase.firebase();
        fauth = FirebaseAuth.getInstance();


        //get user from the database and set the session
        FirestoreClient.getUser("users", "3b597b86-4e4e-4bb3-a4b9-285a48fe01f1");
        //FirestoreClient.getUser("users", "user1");

        FXMLLoader fxmlLoader = new FXMLLoader(TaskManagerApplication.class.getResource("signup-view.fxml"));
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
