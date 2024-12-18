package main;

import controller.UserController;
import javafx.application.Application;
import javafx.stage.Stage;
import view.RegisterView;

public class Main extends Application{
	
	public static void main(String[] args) {
		launch(args);
	}

	@Override
	public void start(Stage primaryStage) throws Exception {
		UserController userController = new UserController();
		RegisterView registerView = new RegisterView(userController, primaryStage);
		
		primaryStage.setScene(registerView.getRegisterScene());
		primaryStage.setWidth(800);
		primaryStage.setHeight(600);
		primaryStage.show();
	}
}
