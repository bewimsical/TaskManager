package edu.farmingdale.taskmanager.Controllers;

import javafx.fxml.FXML;
import javafx.scene.control.CheckBox;
import javafx.scene.control.Label;


public class GameSettingController {
    @FXML
    private CheckBox tutorialsCheck;
    @FXML
    private CheckBox animationsCheck;
    @FXML
    private CheckBox notificationsCheck;
    @FXML
    private CheckBox gameModeCheck;
    @FXML
    private Label statusLabel;


    @FXML
    public void initialize() {
        // Load saved values (for now using defaults)
        tutorialsCheck.setSelected(true);
        animationsCheck.setSelected(true);
        notificationsCheck.setSelected(false);
        gameModeCheck.setSelected(false);
    }

    @FXML
    private void openGameSetting() {
        boolean tutorials = tutorialsCheck.isSelected();
        boolean animations = animationsCheck.isSelected();
        boolean notifications = notificationsCheck.isSelected();
        boolean gameMode = gameModeCheck.isSelected();

        System.out.println("Saving Game Settings:");
        System.out.println("Tutorials: " + tutorials);
        System.out.println("Animations: " + animations);
        System.out.println("Notifications: " + notifications);
        System.out.println("Game Mode: " + gameMode);

        statusLabel.setText("Saved!");
    }
}


