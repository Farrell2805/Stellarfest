package view;

import controller.UserController;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.layout.VBox;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.stage.Stage;
import model.User;
import util.UserSession;

public class ChangeProfileView {
	private UserController userController;
	private Stage stage;
	
	public ChangeProfileView(UserController userController) {
		this.userController = userController;
	}
	
	public Scene getChangeProfileScene() {
		User currentUser = UserSession.getCurrentUser();
		Label titleLabel = new Label("Change Profile Page");
		titleLabel.setFont(Font.font("Arial", FontWeight.BOLD, 24));
		
		Label emailLabel = new Label("Email");
		Label nameLabel = new Label("Name");
		Label oldPassLabel = new Label("Old Password");
		Label newPassLabel = new Label("New Password");
		
		TextField emailTF = new TextField();
		TextField nameTF = new TextField();
		PasswordField oldPassTF = new PasswordField();
		PasswordField newPassTF = new PasswordField();
		
		Button changeBtn = new Button("Change Profile");
		Button backBtn = new Button("Back");
		
		
		
		VBox layout = new VBox();
		return new Scene(layout);
	}
}
