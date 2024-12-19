package model;

import util.Connect;

public class Guest extends User{
	private String acceptedInvitations;
	private static Connect connect;
	
	static {
		connect = Connect.getInstance();
	}
	
	
}
