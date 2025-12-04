package edu.farmingdale.taskmanager.cards;

import edu.farmingdale.taskmanager.Models.User;
import edu.farmingdale.taskmanager.TaskManagerApplication;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.geometry.Insets;
import javafx.scene.Parent;
import javafx.scene.control.Label;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;

import java.io.IOException;
import java.util.List;

public abstract class StatCard extends VBox {

    @FXML
    protected VBox root;

    @FXML
    protected Label stat;

    @FXML
    protected Label statLabel;

    @FXML
    protected Label statTitle;


    User user;

    public StatCard(User user) {
        this.user = user;
        try {
            FXMLLoader loader = new FXMLLoader(TaskManagerApplication.class.getResource("stat-view.fxml"));
            loader.setRoot(this);       // <--- CRITICAL: makes THIS VBox the root node
            loader.setController(this); // attaches your class as controller
            loader.load();

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    protected Label makeLabel(String text){
        Label label = new Label(text);
        label.setMinWidth(400);
        label.setMaxWidth(400);
        label.getStyleClass().add("stat-data");
        label.setPadding(new Insets(-9,0, -9,0));
        return label;
    }

    protected Label makeSmallLabel(String text){
        Label label = new Label(text);
        label.setMinWidth(400);
        label.setMaxWidth(400);
        label.getStyleClass().add("stat-data-small");
        label.setPadding(new Insets(-9,0, -9,0));
        return label;
    }

}
