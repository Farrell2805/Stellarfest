package util;

import java.util.HashMap;

import controller.UserController;
import javafx.scene.Scene;
import javafx.stage.Stage;
import view.RegisterView;

public class PageManager {
	private Stage stage;
	private HashMap<String, Scene> pages;
	
	public PageManager(Stage stage) {
		this.stage = stage;
	}
	
	public void showRegisterPage() {
		UserController userController = new UserController();
		RegisterView registerView = new RegisterView(userController);
		
	}
}
