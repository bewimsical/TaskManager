module edu.farmingdale.taskmanager {
    requires javafx.controls;
    requires javafx.fxml;

    requires org.controlsfx.controls;
    requires com.dlsc.formsfx;
    requires org.kordamp.bootstrapfx.core;
    requires firebase.admin;
    requires com.google.auth;
    requires com.google.auth.oauth2;
    requires google.cloud.firestore;
    requires google.cloud.core;
    requires com.google.api.apicommon;
    requires javafx.graphics;

    opens edu.farmingdale.taskmanager to javafx.fxml;
    exports edu.farmingdale.taskmanager;
    exports edu.farmingdale.taskmanager.Controllers;
    opens edu.farmingdale.taskmanager.Controllers to javafx.fxml;
    exports edu.farmingdale.taskmanager.cards;
    opens edu.farmingdale.taskmanager.cards to javafx.fxml;
}