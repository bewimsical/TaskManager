package edu.farmingdale.taskmanager.Controllers;

import javafx.fxml.FXML;
import javafx.scene.control.CheckBox;
import javafx.scene.control.Label;
import javafx.scene.control.Slider;


public class SoundSettingsController {

    @FXML private Slider masterSlider;
    @FXML private Slider musicSlider;
    @FXML private Slider effectsSlider;

    @FXML private Label masterValue;
    @FXML private Label musicValue;
    @FXML private Label effectsValue;

    @FXML private CheckBox muteMusic;
    @FXML private CheckBox muteEffects;
    @FXML private Label statusLabel;

//update labels
    @FXML public void initialize (){
        masterSlider.valueProperty().addListener((obs, oldVal, newVal) ->
                masterValue.setText(String.valueOf(newVal.intValue())));

        musicSlider.valueProperty().addListener((obs, oldVal, newVal) ->
                masterValue.setText(String.valueOf(newVal.intValue())));

        effectsSlider.valueProperty().addListener((obs, oldVal, newVal) ->
                masterValue.setText(String.valueOf(newVal.intValue())));

        }
        @FXML
    private void openSoundSetting(){
            int master = (int) masterSlider.getValue();
            int music = (int) musicSlider.getValue();
            int effects = (int) effectsSlider.getValue();


        }






    }




