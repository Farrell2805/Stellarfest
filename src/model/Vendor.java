package model;

import util.Connect;

public class Vendor extends User{
	private String acceptedInvitations;
	private static Connect connect;
	
	static {
		connect = Connect.getInstance();
	}
	
	public Vendor() {
		
	}

	public String getAcceptedInvitations() {
		return acceptedInvitations;
	}

	public void setAcceptedInvitations(String acceptedInvitations) {
		this.acceptedInvitations = acceptedInvitations;
	}
	
	

}