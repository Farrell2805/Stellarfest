package model;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import util.Connect;

public class Guest extends User{
	private String acceptedInvitations;
	private static Connect connect;
	
	static {
		connect = Connect.getInstance();
	}

	public String getAcceptedInvitations() {
		return acceptedInvitations;
	}

	public void setAcceptedInvitations(String acceptedInvitations) {
		this.acceptedInvitations = acceptedInvitations;
	}
	
	public List<Invitation> viewInvitations(String userId){
		String query = "SELECT * FROM invitations WHERE userId = ? AND invitationStatus = ?";
		List<Invitation>  invitations = new ArrayList<>();
		
		PreparedStatement ps = connect.prepareStatement(query);
		try {
			ps.setString(1, userId);
			ps.setString(2, "pending");
			ResultSet rs = ps.executeQuery();
			if(rs.next()) {
				Invitation i = new Invitation();
				i.setEventId(rs.getString("eventId"));
				i.setInvitationId(rs.getString("invitationId"));
				i.setInvitationRole(rs.getString("invitationRole"));
				i.setInvitationStatus(rs.getString("invitationStatus"));
				i.setUserId(rs.getString("userId"));
				invitations.add(i);				
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return invitations;
	}
	
	public boolean acceptInvitation(String eventId, String userId) {
		String query = "UPDATE invitations set invitationStatus = ? WHER userId = ? AND eventId = ?";
		PreparedStatement ps = connect.prepareStatement(query);
		try {
			ps.setString(1, "Accepted");
			ps.setString(2, userId);
			ps.setString(3, eventId);
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
