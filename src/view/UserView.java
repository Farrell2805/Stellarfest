package view;

import java.util.List;

import controller.AdminController;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.VBox;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import model.User;
import util.PageNavigator;

public class UserView {
	private AdminController adminController;
	List<User> users;
	TableView<User> userTable;
	private ObservableList<User> userObservable;
	public UserView( AdminController adminController) {
		this.adminController = adminController;
	}
	
	public Scene getUserScene() {
		users = adminController.getAllUsers();
		userObservable = FXCollections.observableArrayList(users);
		
		Label titleLabel = new Label("Manage Users");
		titleLabel.setFont(Font.font("Arial", FontWeight.BOLD, 24));
		
		Button backBtn = new Button("Back");
		backBtn.setOnAction(e->{
			PageNavigator.showAdminHomeView();
		});
		setTable();
		
		Button deleteUser = new Button("Delete");
		
		deleteUser.setOnAction(e -> {
            User selectedUser = userTable.getSelectionModel().getSelectedItem();
            if (selectedUser != null) {
                boolean status = adminController.deleteUser(selectedUser.getUserId());
                if (status) {
                    // Refresh the table after deletion
                    users = adminController.getAllUsers(); // Re-fetch the updated list of users
                    userObservable.clear(); // Clear the existing ObservableList
                    userObservable.addAll(users); // Add the new list of users
                } else {
                    System.out.println("Failed to delete user.");
                }
            } else {
                System.out.println("No user selected.");
            }
        });
		
		
		
		
		
		VBox layout = new VBox(20);
        layout.setAlignment(Pos.TOP_CENTER);
        layout.setPadding(new Insets(10));
        layout.getChildren().addAll(titleLabel, userTable, deleteUser, backBtn);
		return new Scene(layout);
	}
	
	private void setTable() {
        userTable = new TableView<>();
        
        TableColumn<User, String> idColumn = new TableColumn<>("User Id");
        TableColumn<User, String> nameColumn = new TableColumn<>("Username");
        TableColumn<User, String> emailColumn = new TableColumn<>("Email");
        TableColumn<User, String> roleColumn = new TableColumn<>("Role");

        idColumn.setCellValueFactory(new PropertyValueFactory<>("userId"));
        nameColumn.setCellValueFactory(new PropertyValueFactory<>("userName"));
        emailColumn.setCellValueFactory(new PropertyValueFactory<>("userEmail"));
        roleColumn.setCellValueFactory(new PropertyValueFactory<>("userRole"));

        userTable.getColumns().addAll(idColumn, nameColumn, emailColumn, roleColumn);

        userTable.setItems(userObservable);
    }
}
