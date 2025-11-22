package edu.farmingdale.taskmanager.Controllers;

import edu.farmingdale.taskmanager.Models.Chore;
import edu.farmingdale.taskmanager.Models.Ritual;
import edu.farmingdale.taskmanager.TaskManagerApplication;
import edu.farmingdale.taskmanager.cards.ChoreCard;
import edu.farmingdale.taskmanager.cards.RitualCard;
import edu.farmingdale.taskmanager.viewmodels.RitualViewModel;
import javafx.collections.ListChangeListener;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ProgressBar;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.HBox;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.paint.CycleMethod;
import javafx.scene.paint.LinearGradient;
import javafx.scene.paint.Stop;
import javafx.scene.shape.Rectangle;
import javafx.scene.text.TextAlignment;
import javafx.stage.Modality;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class RitualController implements Initializable {

    @FXML
    private Label date;

    @FXML
    private VBox eveningContainer;

    @FXML
    private VBox middayContainer;

    @FXML
    private VBox morningContainer;

    @FXML
    private ImageView streak1;

    @FXML
    private ImageView streak2;

    @FXML
    private ImageView streak3;

    @FXML
    private ImageView streak4;

    @FXML
    private ImageView streak5;

    @FXML
    private ImageView streak6;

    @FXML
    private ImageView streak7;

    @FXML
    private Label streakBonus;

    @FXML
    private Label streakCount;

    @FXML
    private ProgressBar xpBar;

    @FXML
    private StackPane xpBarContainer;

    @FXML
    private StackPane root;

    private final RitualViewModel vm = new RitualViewModel();


    @Override
    public void initialize(URL location, ResourceBundle resources){

        //set up shiny xp bar
        Rectangle shine = new Rectangle();
        shine.setHeight(12);

        shine.setFill(new LinearGradient(
                0, 0, 0, 1, true, CycleMethod.NO_CYCLE,
                new Stop(0, Color.web("#D9D9D900")),
                new Stop(0.5, Color.rgb(255, 255, 255, 1.0)),
                new Stop(1, Color.web("#73737300"))
        ));
        shine.widthProperty().bind(xpBar.widthProperty().multiply(xpBar.progressProperty()).subtract(10));
        xpBarContainer.getChildren().add(shine);
        StackPane.setMargin(shine, new Insets(12, 0, 0, 10));

        //bind date
        date.textProperty().bind(vm.dateProperty());
        //bind streak
        streakCount.textProperty().bind(vm.streakProperty());
        //bind streak bonus
        streakBonus.textProperty().bind(vm.xpBonusProperty());
        //bind progress bar
        xpBar.progressProperty().bind(vm.getXpPercentProperty());
        //bind lists
        setUpLists(vm.getMorningRituals(), morningContainer);
        setUpLists(vm.getMiddayRituals(), middayContainer);
        setUpLists(vm.getEveningRituals(), eveningContainer);
        //bind streak diamonds
        //update this to work with sunday start or monday start
        bind(streak1, "1");
        bind(streak2, "2");
        bind(streak3, "3");
        bind(streak4, "4");
        bind(streak5, "5");
        bind(streak6, "6");
        bind(streak7, "7");

        //call method to make bindings work
        vm.setUpViews();
    }

    //rename this method
    //bind imageview to vm
    private void bind(ImageView img, String day) {
        vm.imageProperty(day).addListener((o, oldImg, newImg) -> {
            img.setImage(newImg);
        });
    }
    //
    private void setUpLists(ObservableList<Ritual> list, VBox container){
        // reactively render lists:
        list.addListener((ListChangeListener<Ritual>) change -> {
            container.getChildren().clear();
            for (Ritual r : list) {
               RitualCard card = new RitualCard(r, vm::completeChore);
                card.setOnXClick(c -> handleXClick(c));
                container.getChildren().add(card.createView());
            }
        });
    }

    //this is not the best example of MVVM Pattern...
    //creates popup to add a ritual
    @FXML
    void AddMorningRitual(MouseEvent event) {
        addRitual("Morning", event);
    }

    @FXML
    void addEveningRitual(MouseEvent event) {
        addRitual("Evening", event);
    }

    @FXML
    void addMiddayRitual(MouseEvent event) {
        addRitual("Midday", event);
    }

    private void addRitual(String timeOfDay, MouseEvent event){
        Node source = (Node) event.getSource();
        Stage primaryStage = (Stage) source.getScene().getWindow();
        Stage popUp = new Stage();
        popUp.initModality(Modality.APPLICATION_MODAL);
        popUp.initOwner(primaryStage);
        FXMLLoader fxmlLoader = new FXMLLoader(TaskManagerApplication.class.getResource("add-ritual-view.fxml"));
        Scene popUpScene = null;
        try {
            popUpScene = new Scene(fxmlLoader.load(), 500,310);
            popUpScene.getStylesheets().add(TaskManagerApplication.class.getResource("styles/style.css").toExternalForm());
            popUp.setScene(popUpScene);
            popUp.setTitle("Add Ritual");
            popUp.setX(primaryStage.getX() + ((primaryStage.getWidth() - 500)/2 ));
            popUp.setY(primaryStage.getY() + ((primaryStage.getHeight() - 310)/2 ));
            //popUp.show();
            AddRitualController arc = fxmlLoader.getController();
            arc.setTimeOfDay(timeOfDay);

            popUp.showAndWait();

            // After popup is closed, get the selected chore
            Chore selectedChore = arc.getSelectedChore();
            if (selectedChore != null){
                vm.createRitual(selectedChore, timeOfDay);
            }


        } catch (IOException e) {
            System.out.println(e.getMessage());
        }

    }
    //possibly move to popup service or viewmodel
    private void handleXClick(ChoreCard<Ritual> c){
        RitualCard card = (RitualCard) c;
        Rectangle overlay = new Rectangle();
        overlay.setWidth(root.getWidth());
        overlay.setHeight(root.getHeight());
        overlay.setFill(Color.rgb(255, 255, 255, .45));
        overlay.setOnMouseClicked(e -> e.consume()); // block clicks behind


        Rectangle border = new Rectangle();
        border.setWidth(500);
        border.setHeight(300);
        border.setFill(Color.rgb(255, 255, 255, .0));
        border.setStroke(Color.web("#281902"));
        border.setArcHeight(20);
        border.setArcWidth(20);
        border.setStrokeWidth(10);
        border.setMouseTransparent(true); // block clicks behind

        // --- Popup panel ---
        VBox popup = new VBox(30);
        popup.setAlignment(Pos.CENTER);
        popup.setMaxSize(500, 300);
        popup.getStyleClass().addAll("delete-ritual", "container");


        Label message = new Label("Are you sure you want to delete this ritual?");
        message.setWrapText(true);
        message.setMaxWidth(450);
        message.setTextAlignment(TextAlignment.CENTER);
        message.setAlignment(Pos.CENTER);
        message.setPadding(new Insets(-20, 0,-20, 0));
        message.setLineSpacing(-20); // reduces extra vertical spacing between lines

        message.getStyleClass().add("delete-ritual-message");

        HBox buttons = new HBox(10);
        buttons.setAlignment(Pos.CENTER);

        Button yesBtn = new Button("Yes");
        yesBtn.getStyleClass().add("delete-ritual-button");
        yesBtn.setPrefWidth(100);
        Button noBtn = new Button("No");
        noBtn.getStyleClass().add("delete-ritual-button");
        noBtn.setPrefWidth(100);

        buttons.getChildren().addAll(yesBtn, noBtn);
        popup.getChildren().addAll(message, buttons);

        StackPane.setAlignment(popup, Pos.CENTER);

        StackPane popupContainer = new StackPane(popup, border);

        // Add overlay + popup to root
        root.getChildren().addAll(overlay, popupContainer);

        // --- Button behavior ---
        yesBtn.setOnAction(e -> {
            vm.deleteRitual(card.getData());     // your delete logic
            root.getChildren().removeAll(overlay, popupContainer);
        });

        noBtn.setOnAction(e -> {
            root.getChildren().removeAll(overlay, popupContainer);
        });
    }

}

