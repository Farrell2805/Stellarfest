package view;

import controller.UserController;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.VBox;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.stage.Stage;
import model.User;

public class RegisterView {
	private UserController userController;
	private Stage stage;
	
	public RegisterView(UserController userController, Stage stage) {
		this.userController = userController;
		this.stage = stage;
	}
	
	public Scene getRegisterScene() {
		Label titleLabel = new Label("Registration Page");
		titleLabel.setFont(Font.font("Arial", FontWeight.BOLD, 24));
		
		Label emailLabel = new Label("Email : ");
		Label nameLabel = new Label("Name : ");
		Label passLabel = new Label("Password : ");
		Label roleLabel = new Label("Role : ");
		
		TextField emailTF = new TextField();
		TextField nameTF = new TextField();
		PasswordField passTF = new PasswordField();
		ComboBox<String> roleCB = new ComboBox<>();
		roleCB.getItems().addAll("Admin", "Vendor", "Guest", "Event Organizer");
		
		emailTF.setPromptText("Enter Email");
		nameTF.setPromptText("Enter Name");
		passTF.setPromptText("Enter Password");
		roleCB.setPromptText("Select a Role");
		
		Label msgLabel = new Label();

		Button regisBtn = new Button("Register");
		regisBtn.setOnAction(e->{
			String name = nameTF.getText();
			String pass = passTF.getText();
			String email = emailTF.getText();
			String role = roleCB.getValue();
			
			String status = userController.checkRegisterInput(email, name, pass, role);
			
			if(status != null) {
				msgLabel.setText(status);
				msgLabel.setStyle("-fx-text-fill : red");
				return;
			}
			
			userController.register(name, pass, email, role);
			msgLabel.setText("Register Successfull!");
			msgLabel.setStyle("-fx-text-fill : green");
		});
		Label loginLbl = new Label("Already have an account? ");
		Button loginBtn = new Button("Login Here!");
		loginBtn.setOnAction(e->{
			LoginView loginView = new LoginView(userController, stage);
			stage.setScene(loginView.getLoginScene());
		});
		
		GridPane gp = new GridPane();
		gp.setAlignment(Pos.CENTER);
		gp.setPadding(new Insets(20));
		gp.setHgap(10);
		gp.setVgap(10);
		
		gp.add(emailLabel, 0, 0);
		gp.add(emailTF, 1, 0);
		
		gp.add(nameLabel, 0, 1);
        gp.add(nameTF, 1, 1);
        
        gp.add(passLabel, 0, 2);
        gp.add(passTF, 1, 2);

        gp.add(roleLabel, 0, 3);
        gp.add(roleCB, 1, 3);
        
        gp.add(msgLabel, 0, 4);
        gp.add(loginLbl,0,5);
        gp.add(loginBtn, 1, 5);
        gp.add(regisBtn, 1, 6);
        
        VBox layout = new VBox(20);
        layout.setAlignment(Pos.TOP_CENTER);
        layout.setPadding(new Insets(10));
        layout.getChildren().addAll(titleLabel, gp);
        return new Scene(layout);
	}
}
