package model;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import util.Connect;

public class Event {
	private String eventId;
	private String eventName;
	private String eventDate;
	private String eventLocation;
	private String eventDescription;
	private String organizerId;
	
	private static final Connect connect;
	
	static {
		connect = Connect.getInstance();
	}
	
	public Event() {
	}

	public String getEventId() {
		return eventId;
	}

	public void setEventId(String eventId) {
		this.eventId = eventId;
	}

	public String getEventName() {
		return eventName;
	}

	public void setEventName(String eventName) {
		this.eventName = eventName;
	}

	public String getEventDate() {
		return eventDate;
	}

	public void setEventDate(String eventDate) {
		this.eventDate = eventDate;
	}

	public String getEventLocation() {
		return eventLocation;
	}

	public void setEventLocation(String eventLocation) {
		this.eventLocation = eventLocation;
	}

	public String getEventDescription() {
		return eventDescription;
	}

	public void setEventDescription(String eventDescription) {
		this.eventDescription = eventDescription;
	}

	public String getOrganizerId() {
		return organizerId;
	}

	public void setOrganizerId(String organizerId) {
		this.organizerId = organizerId;
	}
	
	public Event viewEventDetails(String eventId) {
		String query = "SELECT * FROM events WHERE eventId = ?";
		Event event = null;
		
		PreparedStatement ps = connect.prepareStatement(query);
		try {
			ps.setString(1, eventId);
			ResultSet rs = ps.executeQuery();
			if(rs.next()) {
				event = new Event();
				event.setEventName(rs.getString("eventName"));
				event.setEventDate(rs.getString("eventDate"));
				event.setEventId(rs.getString("eventId"));
				event.setEventLocation(rs.getString("eventLocation"));
				event.setOrganizerId(rs.getString("organizerId"));
				
				
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return event;
	}
	public String generateEventId() {
		String query = "SELECT COUNT(*) as total FROM events";
		int total = 1;
		
		ResultSet rs = connect.execQuery(query);
		try {
			if(rs.next()) {
				total = rs.getInt("total") + 1;
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		
		return String.format("EV%05d", total);
	}
	public boolean createEvent(String name, String date, String location, String description, String organizerId) {
		String id = generateEventId();
		String query = "INSERT INTO events (eventId, eventName, eventDate, eventLocation, eventDescription, organizerId) VALUES (?, ?, ?, ?, ?, ?)";
		
		PreparedStatement ps = connect.prepareStatement(query);
		try {
			ps.setString(1, id);
			ps.setString(2, name);
			ps.setString(3, date);
			ps.setString(4, location);
			ps.setString(5, description);
			ps.setString(6, organizerId);
			
			int rowsAffected = ps.executeUpdate();
			
			if(rowsAffected > 0) {
				return true;
			} 
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			
		}
		return false;
		
	}
	
	
}
