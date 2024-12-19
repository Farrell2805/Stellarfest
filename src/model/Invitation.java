package model;

import java.sql.ResultSet;
import java.sql.SQLException;

import util.Connect;

public class Invitation {
	private String invitationId;
	private String eventId;
	private String userId;
	private String invitationStatus;
	private String invitationRole;
	private static final Connect connect;
	static {
		connect = Connect.getInstance();
	}
	public String getInvitationId() {
		return invitationId;
	}
	public void setInvitationId(String invitationId) {
		this.invitationId = invitationId;
	}
	public String getEventId() {
		return eventId;
	}
	public void setEventId(String eventId) {
		this.eventId = eventId;
	}
	public String getUserId() {
		return userId;
	}
	public void setUserId(String userId) {
		this.userId = userId;
	}
	public String getInvitationStatus() {
		return invitationStatus;
	}
	public void setInvitationStatus(String invitationStatus) {
		this.invitationStatus = invitationStatus;
	}
	public String getInvitationRole() {
		return invitationRole;
	}
	public void setInvitationRole(String invitationRole) {
		this.invitationRole = invitationRole;
	}
	
	public String generateInvitationId() {
		String query = "SELECT COUNT(*) as total FROM invitations";
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
		
		
		return String.format("IN%05d", total);
	}
}
