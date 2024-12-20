package main;

import controller.AdminController;
import controller.UserController;
import javafx.application.Application;
import javafx.stage.Stage;
import util.PageNavigator;
import view.RegisterView;

public class Main extends Application{
	
	public static void main(String[] args) {
		launch(args);
	}

	@Override
	public void start(Stage primaryStage) throws Exception {
		PageNavigator.init(primaryStage);
		PageNavigator.showRegisView();
		primaryStage.setWidth(800);
		primaryStage.setHeight(600);
		primaryStage.show();
	}
}
