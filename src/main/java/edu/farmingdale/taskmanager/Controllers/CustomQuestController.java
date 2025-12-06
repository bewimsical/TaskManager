package edu.farmingdale.taskmanager.Controllers;

import edu.farmingdale.taskmanager.Models.Boss;
import edu.farmingdale.taskmanager.Models.Chore;
import edu.farmingdale.taskmanager.Models.Quest;
import edu.farmingdale.taskmanager.Session;
import edu.farmingdale.taskmanager.TaskManagerApplication;
import edu.farmingdale.taskmanager.factories.BossFactory;
import edu.farmingdale.taskmanager.factories.QuestFactory;
import edu.farmingdale.taskmanager.viewmodels.QuestViewModel;
import javafx.collections.FXCollections;
import javafx.collections.ListChangeListener;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Cursor;
import javafx.scene.control.*;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.scene.shape.Rectangle;
import javafx.util.Callback;

import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;
import java.util.UUID;

public class CustomQuestController implements Initializable {

    @FXML
    private Rectangle cancelBtn;

    @FXML
    private Label cancelLabel;

    @FXML
    private VBox choreContainer;

    @FXML
    private ComboBox<Chore> choreSelector;

    @FXML
    private Label choreTypeLabel;

    @FXML
    private Rectangle createBtn;

    @FXML
    private Label createLabel;

    @FXML
    private TextField customChoreName;

    @FXML
    private TextField nameField;

    @FXML
    private Label nameTypeLabel;

    @FXML
    private StackPane popupRoot;

    @FXML
    private Label typeLabel;

    List<Chore> chores;

    ObservableList<Chore> questChores;

    private Pane parent;

    private final QuestViewModel vm;

    public CustomQuestController(QuestViewModel vm, Pane parent) {
        this.vm = vm;  // injected before initialize()
        this.parent = parent;
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        chores = Session.getInstance().getAvailableChores();
        questChores = FXCollections.observableArrayList();
        questChores.addListener((ListChangeListener<Chore>) change ->{
            choreContainer.getChildren().clear();
            for(Chore c: questChores){
                Image image =  new Image(TaskManagerApplication.class.getResource("images/chore card - pending.png").toExternalForm());
                ImageView imageview = new ImageView(image);
                imageview.setFitHeight(60);
                imageview.setFitWidth(422);

                Label name  = new Label(c.getName());
                name.getStyleClass().add("chore-card-name");
                name.setPadding(new Insets(0, 0, 0, 60));
                name.setMinWidth(395);
                name.setMaxWidth(395);

                Image xImage = new Image(TaskManagerApplication.class.getResource("images/X.png").toExternalForm());
                ImageView xView = new ImageView(xImage);
                xView.setFitWidth(20);
                xView.setFitHeight(20);
                //xView.setVisible(false);

                // Create a bigger hitbox
                StackPane hitbox = new StackPane(xView);
                hitbox.setPrefSize(32, 32); // 32×32 clickable area
                hitbox.setMaxSize(32, 32);
                hitbox.setMinSize(32, 32);
                hitbox.setCursor(Cursor.HAND);
                hitbox.setPickOnBounds(true); // allows clicks in transparent area
                hitbox.setVisible(false);

                hitbox.setOnMouseClicked(e -> {
                    e.consume(); // prevents the card click from firing
                    questChores.remove(c);
                });

                HBox hBox = new HBox(name, hitbox);
                hBox.setAlignment(Pos.CENTER_LEFT);
                hBox.setMinWidth(422);
                hBox.setMaxWidth(422);
                hitbox.setPadding(new Insets(0,20,0,0));

                StackPane root = new StackPane(imageview, hBox);
                root.setOnMouseEntered(e -> hitbox.setVisible(true));
                root.setOnMouseExited(e -> hitbox.setVisible(false));

                choreContainer.getChildren().add(root);
            }
        });

        choreSelector.getItems().addAll(chores);

        // Helper method to create a properly styled ListCell
        Callback<ListView<Chore>, ListCell<Chore>> cellFactory = cb -> createChoreCell();

        choreSelector.setCellFactory(cellFactory);
        choreSelector.setButtonCell(createChoreCell());
    }

    /**
     * Creates a ListCell for Chore with max width, ellipsis, and tooltip.
     */
    private ListCell<Chore> createChoreCell() {
        return new ListCell<>() {
            private final Label label = new Label();

            @Override
            protected void updateItem(Chore chore, boolean empty) {
                label.getStyleClass().add("ritual-item-label");
                super.updateItem(chore, empty);

                if (empty || chore == null) {
                    setGraphic(null);
                    setTooltip(null);
                } else {
                    label.setText(chore.getName());
                    label.setMaxWidth(300);               // limit width
                    label.setEllipsisString("...");       // truncate long text

                    // Tooltip if text is too long
                    if (chore.getName().length() > 30) { // adjust threshold as needed
                        Tooltip tooltip = new Tooltip(chore.getName());
                        setTooltip(tooltip);
                    } else {
                        setTooltip(null);
                    }

                    setGraphic(label);
                }
            }
        };
    }


    @FXML
    void cancel(MouseEvent event) {
        // remove popup
        parent.getChildren().remove(popupRoot);
        // remove overlay (it’s now the last child)
        parent.getChildren().remove(parent.getChildren().size() - 1);

    }

    @FXML
    void createQuest(MouseEvent event) {
        List<Chore> chores = new ArrayList<>(questChores);
        Quest q = QuestFactory.generate(nameField.getText(), chores);
        vm.addCustomQuest(q);

        // remove popup
        parent.getChildren().remove(popupRoot);
        // remove overlay (it’s now the last child)
        parent.getChildren().remove(parent.getChildren().size() - 1);

    }

    @FXML
    void addCustomChore(ActionEvent event) {
       String name =  customChoreName.getText();
       String id = UUID.randomUUID().toString();

       Chore chore = new Chore(id, name);
       questChores.add(chore);
       customChoreName.setText("");

    }

    @FXML
    void addDefaultChore(ActionEvent event) {
        Chore chore = choreSelector.getValue();
        questChores.add(chore);
    }
}
