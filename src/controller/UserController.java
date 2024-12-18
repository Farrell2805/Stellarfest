package controller;

import model.User;
import util.Connect;

public class UserController {
	private Connect connect;
	private User user;
	
	public UserController() {
		this.user = new User();
		this.connect = Connect.getInstance();
	}
	
	public void register(String name, String password, String email, String role) {
		user.register(email, name, password, role);
	}
	
	public User login(String name, String password) {
		return user.login(name, password);
	}
	
	public void changeProfile(String name, String password, String oldPass, String newPass) {
		user.changeProfile(password, name, oldPass, newPass);
	}
	
	public User getUserByEmail(String email) {
		return user.getUserByEmail(email);
	}
	
	public User getUserByUsername(String name) {
		return user.getUserByUsername(name);
	}
	
	public String checkRegisterInput(String email, String name, String pass, String role) {
		return user.checkRegisterInput(name, pass, email, role);
	}
	
	public boolean checkChangeProfileInput(String email, String name, String oldPass, String newPass) {
		return user.checkChangeProfileInput(email, name, oldPass, newPass);
	}

	
}
