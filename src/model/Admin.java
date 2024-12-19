package model;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import util.Connect;

public class Admin extends User{
	public Admin() {
		
	}
	
	private static final Connect connect;
	
	static {
		connect = Connect.getInstance();
	}
	
	public List<Event> getAllEvents(){
		String query = "SELECT * FROM events";
		List<Event> events = new ArrayList<>();
		Event event = null;
		
		
		try(PreparedStatement ps = connect.prepareStatement(query)) {
			ResultSet rs = ps.executeQuery();
			while(rs.next()) {
				event = new Event();
				event.setEventDate(rs.getString("eventDate"));
				event.setEventDescription(rs.getString("eventDescription"));
				event.setEventId(rs.getString("eventId"));
				event.setEventLocation(rs.getString("eventLocation"));
				event.setEventName(rs.getString("eventName"));
				event.setOrganizerId(rs.getString("organizerId"));
				
				events.add(event);
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return events;
	}
	
	
	public List<User> getAllUsers(){
		String query = "SELECT * FROM users";
		List<User> users = new ArrayList<>();
		User user = null;
		
		
		try (PreparedStatement ps = connect.prepareStatement(query)){
			ResultSet rs = ps.executeQuery();
			while(rs.next()) {
				user = new User();
				user.setUserEmail(rs.getString("userEmail"));
				user.setUserId(rs.getString("userId"));
				user.setUserName(rs.getString("userName"));
				user.setUserPassword(rs.getString("userPassword"));
				user.setUserRole(rs.getString("userRole"));
				users.add(user);
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return users;
	}
	
	public boolean deleteUser(String userId) {
		String query = "DELETE FROM users WHERE userId = ?";
		
		
		try(PreparedStatement ps = connect.prepareStatement(query)) {
			ps.setString(1, userId);
			ps.executeUpdate();
			return true;
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return false;
	}
	
	public boolean deleteEvent(String eventId) {
		String query = "DELETE FROM events WHERE eventId = ?";
		
		
		try(PreparedStatement ps = connect.prepareStatement(query)) {
			ps.setString(1, eventId);
			ps.executeUpdate();
			return true;
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return false;
	}
	
	
	
	
	
	
}
