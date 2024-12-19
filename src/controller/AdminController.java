package controller;

import java.util.List;

import model.Admin;
import model.Event;
import model.Guest;
import model.User;
import model.Vendor;
import util.Connect;

public class AdminController {
	private Admin admin;
	private Event event;
	private static Connect connect;
	
	public AdminController() {
		this.admin = new Admin();
		this.connect = Connect.getInstance();
	}
	
	public Event viewEventDetails(String eventId) {
		return event.viewEventDetails(eventId);
	}
	
	public boolean deleteEvent(String eventId) {
		return admin.deleteEvent(eventId);
	}
	
	public boolean deleteUser(String userId) {
		return admin.deleteUser(userId);
	}
	
	public List<User> getAllUsers() {
		return admin.getAllUsers();
	}
//	
	public List<Event> getAllEvents() {
		return admin.getAllEvents();
	}

}
