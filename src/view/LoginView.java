package view;

import controller.AdminController;
import controller.UserController;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.VBox;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.stage.Stage;
import model.User;
import util.PageNavigator;
import util.UserSession;

public class LoginView {
	private UserController userController;
	private Stage stage;
	private AdminController adminController;
	
	public LoginView(UserController userController) {
		this.userController = userController;
	}
	
	public Scene getLoginScene() {
		Label titleLabel = new Label("Login Page");
		titleLabel.setFont(Font.font("Arial", FontWeight.BOLD, 24));
		
		Label emailLabel = new Label("Email : ");
		Label passwordLabel = new Label("Password : ");
		
		TextField emailTF = new TextField();
		PasswordField passTF = new PasswordField();
		
		emailTF.setPromptText("Enter Email!");
		passTF.setPromptText("Enter Password!");
		
		Label msgLabel = new Label("");
		
		Button loginBtn = new Button("Login");
		loginBtn.setOnAction(e->{
			String email = emailTF.getText();
			String password = passTF.getText();	
			
			User user = userController.login(email, password);
			UserSession.setCurrentUser(user);
			if(user == null) {
				msgLabel.setText("Email and password wrong!");
				msgLabel.setStyle("-fx-text-fill : red");
				return;
			}
			
			msgLabel.setText("Login Success!");
			msgLabel.setStyle("-fx-text-fill : green");
			
			String role = user.getUserRole();
			
			switch (role) {
			case "Admin":
				PageNavigator.showAdminHomeView();
				break;
			case "Event Organizer":
//				stage.setScene(EventOrganizerHomeView);
				break;
			case "Guest":
//				stage.setScene(GuestHomeView);
				break;
			case "Vendor":
//				stage.setScene(VendorHomeView);
				break;
			default:
				break;
			}
			
			
			
		});
		
		Label regisLabel = new Label("Dont have an account?");
		Button regisBtn = new Button("Register Here!");
		regisBtn.setOnAction(e->{
			PageNavigator.showRegisView();
		});
		
		GridPane gp = new GridPane();
		gp.setAlignment(Pos.CENTER); 
        gp.setPadding(new Insets(20));
        gp.setHgap(10);
        gp.setVgap(10);

        gp.add(emailLabel, 0, 0);
        gp.add(emailTF, 1, 0);
        gp.add(passwordLabel, 0, 1);
        gp.add(passTF, 1, 1);
        gp.add(msgLabel, 0, 2);
        gp.add(regisLabel, 0, 3);
        gp.add(regisBtn, 1, 3);
        gp.add(loginBtn, 1, 4);
		
        VBox layout = new VBox(20);
        layout.setAlignment(Pos.TOP_CENTER);
        layout.setPadding(new Insets(10));
        layout.getChildren().addAll(titleLabel, gp);
		return new Scene(layout);
	}
}
