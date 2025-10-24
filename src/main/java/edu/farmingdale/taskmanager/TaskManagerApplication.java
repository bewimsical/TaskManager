package edu.farmingdale.taskmanager;

import com.google.cloud.firestore.Firestore;
import com.google.firebase.auth.FirebaseAuth;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
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

        FXMLLoader fxmlLoader = new FXMLLoader(TaskManagerApplication.class.getResource("navbar-view.fxml"));
        Scene scene = new Scene(fxmlLoader.load(), 320, 1024);
        stage.setTitle("Hello!");
        scene.getStylesheets().add(getClass().getResource("styles/style.css").toExternalForm());
        stage.setScene(scene);
        stage.show();
    }

    public static void main(String[] args) {
        launch();
    }
}
