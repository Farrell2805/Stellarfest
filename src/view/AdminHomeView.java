package view;

import controller.AdminController;
import controller.UserController;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.VBox;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.stage.Stage;
import model.User;
import util.PageNavigator;
import util.UserSession;

public class AdminHomeView {
	// view all events, view event details, delete events
	// view all users, delete users
	private AdminController adminController;
	private Stage stage;
	private User user;
	
	public AdminHomeView(AdminController adminController) {
		this.adminController = new AdminController();
	}
	
	
	public Scene getAdminScene() {
		Label titleLabel = new Label("Admin Home Page");
		titleLabel.setFont(Font.font("Arial", FontWeight.BOLD, 24));
		user = UserSession.getCurrentUser();
		Label name = new Label (user.getUserName()+ " (" + user.getUserRole() + ")");
		
		Button manageUserBtn = new Button("Manage User");
		Button manageEventBtn = new Button("Manage Event");
		Button logout = new Button("Logout");
		manageUserBtn.setOnAction(e->{
			PageNavigator.showUserView();
		});
		
		manageEventBtn.setOnAction(e->{
			PageNavigator.showEventView();
		});
		
		logout.setOnAction(e->{
			UserSession.clearSession();
			PageNavigator.showLoginView();
		});
		
		GridPane gp = new GridPane();
		gp.setAlignment(Pos.CENTER); 
        gp.setPadding(new Insets(20));
        gp.setHgap(10);
        gp.setVgap(10);
        
        gp.add(manageEventBtn, 0, 0);
        gp.add(manageUserBtn, 1, 0);
        gp.add(logout, 0, 1);
        
        VBox layout = new VBox(20);
        layout.setAlignment(Pos.TOP_CENTER);
        layout.setPadding(new Insets(10));
        layout.getChildren().addAll(titleLabel, name,  gp);
		return new Scene(layout);
		
	}
}
