package edu.farmingdale.taskmanager.Controllers;

import edu.farmingdale.taskmanager.Models.Chore;
import edu.farmingdale.taskmanager.Models.MapMark;
import edu.farmingdale.taskmanager.Models.Quest;
import edu.farmingdale.taskmanager.TaskManagerApplication;
import edu.farmingdale.taskmanager.cards.AttackCard;
import edu.farmingdale.taskmanager.cards.ChoreCard;
import edu.farmingdale.taskmanager.cards.QuestCard;
import edu.farmingdale.taskmanager.viewmodels.MapMarkViewModel;
import edu.farmingdale.taskmanager.viewmodels.QuestViewModel;
import javafx.beans.binding.Bindings;
import javafx.collections.ListChangeListener;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.FlowPane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.shape.Line;

import java.net.URL;
import java.util.*;

public class QuestController implements Initializable {

    @FXML
    private Label activeButton;

    @FXML
    private Label completeButton;

    @FXML
    private ImageView customQuestCard;

    @FXML
    private Label failedButton;

    @FXML
    private VBox mainContainer;

    @FXML
    private FlowPane objectivesContainer;

    @FXML
    private ImageView questImageView;

    @FXML
    private Label questName;


    @FXML
    private AnchorPane mapView;

    @FXML
    private AnchorPane lineView;

    @FXML
    private VBox sideQuestsContainer;

    private Label[] buttons;

    private Image doneImage;
    private Image emptyImage;

    private final QuestViewModel vm = new QuestViewModel();

    @Override
    public void initialize(URL location, ResourceBundle resources) {

        buttons = new Label[]{activeButton, completeButton, failedButton};

        doneImage  = new Image(TaskManagerApplication.class.getResource("images/quest-gem-complete.png").toExternalForm());
        emptyImage = new Image(TaskManagerApplication.class.getResource("images/quest-gem-empty.png").toExternalForm());


        //bind name
        questName.textProperty().bind(vm.nameProperty());
        setUpLists(vm.getVisibleQuests(), mainContainer);
        setUpLists(vm.getVisibleChores(), objectivesContainer);

        vm.selectedQuestProperty().addListener((obs, oldQuest, newQuest) -> {
            // Clear old markers
            mapView.getChildren().clear();
            vm.getChoreMarks().clear();
            if (newQuest != null) {
                for (MapMark mark : newQuest.getMapMarks()) {
                    MapMarkViewModel markVM = new MapMarkViewModel(mark);
                    vm.getChoreMarks().put(mark.getChoreId(), markVM);


                    ImageView iv = new ImageView();
                    iv.setFitHeight(30);
                    iv.setFitWidth(30);
                    iv.setLayoutX(mark.getX());
                    iv.setLayoutY(mark.getY());

                    // Bind image to completion
                    iv.imageProperty().bind(
                            Bindings.when(markVM.completedProperty())
                                    .then(doneImage)
                                    .otherwise(emptyImage)
                    );

                    mapView.getChildren().add(iv);
                }
            }
        });

        vm.setUpView();
        drawPathLines(vm.getChoreMarks());

    }

    private void drawPathLines(Map<String,MapMarkViewModel> marks) {
        lineView.getChildren().clear();
        // You must order the marks however you want the path drawn.
        // Example: use quest order (already sorted in MapMark list)
        List<MapMarkViewModel> ordered = marks.values().stream()
                .filter(m -> m.getCompletionIndex() >= 0)
                .sorted(Comparator.comparingInt(MapMarkViewModel::getCompletionIndex))
                .toList();

        List<MapMarkViewModel> incomplete = marks.values().stream()
                .filter(m -> m.getCompletionIndex() < 0)
                .toList();

        Optional<MapMarkViewModel> lastOpt =
                ordered.stream().max(Comparator.comparingInt(MapMarkViewModel::getCompletionIndex));

        Color pathColor = Color.web("#472C01");

        if (ordered.size() > 1) {
            for (int i = 0; i < ordered.size() - 1; i++) {
                MapMarkViewModel a = ordered.get(i);
                MapMarkViewModel b = ordered.get(i + 1);

                Line line = new Line();
                line.startXProperty().bind(Bindings.add(a.xProperty(), 15) );
                line.startYProperty().bind(Bindings.add(a.yProperty(),15));
                line.endXProperty().bind(Bindings.add(b.xProperty(), 15));
                line.endYProperty().bind(Bindings.add(b.yProperty(),15));

                line.setStrokeWidth(3);
                line.setStroke(pathColor);

                // Style based on completion:
                updateLineStyle(a, b, line);

                // Re-check whenever either mark changes
                a.completionIndexProperty().addListener((o, ov, nv) -> updateLineStyle(a, b, line));
                b.completionIndexProperty().addListener((o, ov, nv) -> updateLineStyle(a, b, line));

                // Add line under markers
                lineView.getChildren().add(line);
            }
        }

        lastOpt.ifPresent(last -> {
            // This block runs only if a MapMark exists
            // Example: highlight the last completed marker
            MapMarkViewModel a = last;

            for (int i = 0; i < incomplete.size(); i++) {
                MapMarkViewModel b = incomplete.get(i);

                Line line = new Line();
                line.startXProperty().bind(Bindings.add(a.xProperty(), 15) );
                line.startYProperty().bind(Bindings.add(a.yProperty(),15));
                line.endXProperty().bind(Bindings.add(b.xProperty(), 15));
                line.endYProperty().bind(Bindings.add(b.yProperty(),15));

                line.setStroke(pathColor);

                // Style based on completion:
                updateLineStyle(a, b, line);

                // Re-check whenever either mark changes
                a.completionIndexProperty().addListener((o, ov, nv) -> updateLineStyle(a, b, line));
                b.completionIndexProperty().addListener((o, ov, nv) -> updateLineStyle(a, b, line));

                // Add line under markers
                line.setStrokeWidth(2);
                lineView.getChildren().add(line);
            }
        });

    }

    private void updateLineStyle(MapMarkViewModel a, MapMarkViewModel b, Line line) {
        boolean bothDone = a.getCompletionIndex() >= 0 && b.getCompletionIndex() >= 0;

        if (bothDone) {
            line.getStrokeDashArray().clear();         // solid line
        } else {
            line.getStrokeDashArray().setAll(5.0, 5.0); // dotted line
        }
    }

    @FXML
    void createCustomQuest(MouseEvent event) {

    }

    @FXML
    void toggleActive(MouseEvent e) {
        for(Label label:buttons){
            label.getStyleClass().remove("active");
        }
        Label button = (Label) e.getSource();
        button.getStyleClass().add("active");
        vm.switchCategory(button.getText());
    }

    private void setUpLists(ObservableList<Quest> list, VBox container){
        // reactively render lists:
        list.addListener((ListChangeListener<Quest>) change -> {
            container.getChildren().clear();
            //TODO ?????
            vm.setCurrentQuestCard(null);
            for (Quest q : list) {
                ChoreCard<Quest> card = new QuestCard(q, this::cardClick);
                if (vm.getCurrentQuestCard() == null){
                    vm.setCurrentQuestCard(card);
                }
                container.getChildren().add(card.createView());
            }
        });
    }

    private void cardClick(ChoreCard<Quest> card){
        vm.cardClick(card);
        drawPathLines(vm.getChoreMarks());
    }

    private void setUpLists(ObservableList<Chore> list, FlowPane container){
        // reactively render lists:
        list.addListener((ListChangeListener<Chore>) change -> {
            container.getChildren().clear();
            for (Chore q : list) {
                AttackCard card = new AttackCard(q, this::completeChore);
                container.getChildren().add(card.createView());
            }
        });
    }

    private void completeChore(ChoreCard<Chore> card){
        vm.completeChore(card);
        drawPathLines(vm.getChoreMarks());
    }

}
