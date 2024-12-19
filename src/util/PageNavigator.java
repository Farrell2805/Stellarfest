package util;

import controller.AdminController;
import controller.UserController;
import javafx.scene.Scene;
import javafx.stage.Stage;
import model.Admin;
import view.AdminHomeView;
import view.EventView;
import view.LoginView;
import view.RegisterView;
import view.UserView;

public class PageNavigator {
	private static Stage stage;
	
	public static void init(Stage primaryStage) {
		stage = primaryStage;
	}
	
	public static void changeScene(Scene scene) {
		if(stage != null) {
			stage.setScene(scene);
		}
	}
	
	public static Stage getStage() {
		return stage;
	}
	
	public static void showLoginView() {
		UserController u = new UserController();
		LoginView loginView = new LoginView(u);
		changeScene(loginView.getLoginScene());
	}
	
	public static void showRegisView() {
		UserController u = new UserController();
		RegisterView registerView = new RegisterView(u);
		changeScene(registerView.getRegisterScene());
	}
	
	public static void showUserView() {
		AdminController a = new AdminController();
		UserView userView = new UserView(a);
		changeScene(userView.getUserScene());
	}
	
	public static void showEventView() {
		AdminController a = new AdminController();
		EventView eventView = new EventView(a);
		changeScene(eventView.getEventScene());
	}
	
	public static void showAdminHomeView() {
		AdminController a = new AdminController();
		AdminHomeView adminHomeView = new AdminHomeView(a);
		changeScene(adminHomeView.getAdminScene());
	}
}
