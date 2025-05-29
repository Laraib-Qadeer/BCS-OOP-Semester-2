package gui;

import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import models.User;
import services.AuthServices;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class ManageUsersScreen {

    private final Map<String, User> userMap = new LinkedHashMap<>();
    private final Map<String, Boolean> userDeletedMap = new LinkedHashMap<>();

    public void start(Stage stage) {
        Label title = new Label("Manage Users");
        title.setStyle("-fx-font-size: 20; -fx-font-weight: bold; -fx-text-fill: white;");

        TextField searchField = new TextField();
        searchField.setPromptText("Search by name or email...");
        searchField.setMaxWidth(300);

        ComboBox<String> roleFilter = new ComboBox<>();
        roleFilter.getItems().addAll("All", "Admin", "Police", "Citizen");
        roleFilter.setValue("All");
        roleFilter.setPrefWidth(300);

        ListView<String> userListView = new ListView<>();
        loadUsersIntoList(userListView, "", "All");

        searchField.textProperty().addListener((obs, oldVal, newVal) -> {
            loadUsersIntoList(userListView, newVal, roleFilter.getValue());
        });

        roleFilter.setOnAction(e -> {
            loadUsersIntoList(userListView, searchField.getText(), roleFilter.getValue());
        });

        userListView.setOnMouseClicked(event -> {
            String selectedKey = userListView.getSelectionModel().getSelectedItem();
            if (selectedKey != null) {
                if (userDeletedMap.getOrDefault(selectedKey, false)) {
                    showAlert("This user was already deleted and cannot be managed.");
                    return;
                }

                User selectedUser = userMap.get(selectedKey);
                if (selectedUser != null) {
                    UserDetailPopup.show(selectedUser,
                            () -> loadUsersIntoList(userListView, searchField.getText(), roleFilter.getValue()), // onUpdate
                            () -> {
                                userDeletedMap.put(selectedKey, true);
                                showAlert("User has been deleted.\n(Still visible for audit reference)");
                            }
                    );
                }
            }
        });

        Button backBtn = new Button("Back");
        backBtn.setStyle("-fx-background-color: #6c757d; -fx-text-fill: white;");
        backBtn.setOnAction(e -> new AdminDashboard().start(stage));

        VBox layout = new VBox(15, title, searchField, roleFilter, userListView, backBtn);
        layout.setPadding(new Insets(30));
        layout.setAlignment(Pos.CENTER);
        layout.setStyle("-fx-background-color: #001f3f;");
        layout.setPrefSize(600, 600);

        Scene scene = new Scene(layout);
        stage.setScene(scene);
        stage.setTitle("Manage Users");
        stage.show();
    }

    private void loadUsersIntoList(ListView<String> listView, String search, String roleFilter) {
        listView.getItems().clear();

        Map<String, Boolean> oldDeletedMap = new LinkedHashMap<>(userDeletedMap);

        userMap.clear();
        userDeletedMap.clear();

        AuthServices auth = new AuthServices();
        List<User> allUsers = auth.getAllUsers();

        List<User> filtered = allUsers.stream()
                .filter(user -> {
                    boolean matchesSearch = search == null || search.isEmpty() ||
                            user.getName().toLowerCase().contains(search.toLowerCase()) ||
                            user.getEmail().toLowerCase().contains(search.toLowerCase());
                    boolean matchesRole = roleFilter.equalsIgnoreCase("All") ||
                            user.getRole().equalsIgnoreCase(roleFilter);
                    return matchesSearch && matchesRole;
                })
                .collect(Collectors.toList());

        for (User user : filtered) {
            String label = user.getRole().toUpperCase() + ": " + user.getName() + " (" + user.getEmail() + ")";
            userMap.put(label, user);
            userDeletedMap.put(label, oldDeletedMap.getOrDefault(label, false));
            listView.getItems().add(label);
        }
    }

    private void showAlert(String msg) {
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setHeaderText(null);
        alert.setContentText(msg);
        alert.showAndWait();
    }
}
