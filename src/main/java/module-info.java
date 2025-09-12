module edu.farmingdale.taskmanager {
    requires javafx.controls;
    requires javafx.fxml;


    opens edu.farmingdale.taskmanager to javafx.fxml;
    exports edu.farmingdale.taskmanager;
}