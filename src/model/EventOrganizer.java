package model;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import util.Connect;

public class EventOrganizer extends User{
	private String eventsCreated;
	private static final Connect connect;
	
	static {
		connect = Connect.getInstance();
	}
	
	public EventOrganizer() {
		
	}

	public String getEventsCreated() {
		return eventsCreated;
	}

	public void setEventsCreated(String eventsCreated) {
		this.eventsCreated = eventsCreated;
	}
	
	public List<Guest> getGuests(String eventId){
		List<Guest> guests = new ArrayList<>();
		String query = "SELECT * FROM users WHERE userRole = ?";
		Guest guest = null;
		PreparedStatement ps = connect.prepareStatement(query);
		try {
			ps.setString(1, "Guest");
			ResultSet rs = ps.executeQuery();
			if(rs.next()) {
				guest = new Guest();
				guest.setUserEmail(rs.getString("userEmail"));
				guest.setUserId(rs.getString("userId"));
				guest.setUserName(rs.getString("userName"));
				guest.setUserPassword(rs.getString("userPassword"));
				guest.setUserRole(rs.getString("userRole"));
				
				guests.add(guest);
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return guests;
	}
	
	public List<Vendor> getVendors(String eventId){
		List<Vendor> vendors = new ArrayList<>();
		String query = "SELECT * FROM users WHERE userRole = ?";
		Vendor vendor = null;
		PreparedStatement ps = connect.prepareStatement(query);
		try {
			ps.setString(1, "Vendor");
			ResultSet rs = ps.executeQuery();
			if(rs.next()) {
				vendor = new Vendor();
				vendor.setUserEmail(rs.getString("userEmail"));
				vendor.setUserId(rs.getString("userId"));
				vendor.setUserName(rs.getString("userName"));
				vendor.setUserPassword(rs.getString("userPassword"));
				vendor.setUserRole(rs.getString("userRole"));
				
				vendors.add(vendor);
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return vendors;

	}
	
	public boolean addVendor(String eventId, String userEmail) {
		User user = getUserByEmail(userEmail);
		
		if(user == null) {
			return false;
		}
		
		Invitation invitation = null;
		String invitationId = invitation.generateInvitationId();
		
		String query = "INSERT INTO invitations (invitationId, eventId, userId, invitationStatus, invitationRole) VALUES(?, ?, ?, ?, ?)";
		PreparedStatement ps = connect.prepareStatement(query);
		try {
			ps.setString(1, invitationId);
			ps.setString(2, eventId);
			ps.setString(3, user.getUserId());
			ps.setString(4, "Pending");
			ps.setString(5, "Vendor");
			
			int rowsAff = ps.executeUpdate();
			if(rowsAff > 0) {
				return true;
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return false;
	}
	
	public boolean addGuest(String eventId, String userEmail) {
		User user = getUserByEmail(userEmail);
		
		if(user == null) {
			return false;
		}
		
		Invitation invitation = null;
		String invitationId = invitation.generateInvitationId();
		
		String query = "INSERT INTO invitations (invitationId, eventId, userId, invitationStatus, invitationRole) VALUES(?, ?, ?, ?, ?)";
		PreparedStatement ps = connect.prepareStatement(query);
		try {
			ps.setString(1, invitationId);
			ps.setString(2, eventId);
			ps.setString(3, user.getUserId());
			ps.setString(4, "Pending");
			ps.setString(5, "Guest");
			
			int rowsAff = ps.executeUpdate();
			if(rowsAff > 0) {
				return true;
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return false;
	}
	
	
}
