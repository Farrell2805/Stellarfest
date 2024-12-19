package controller;

import model.Event;

public class EventController {
	private Event event;
	
	public EventController() {
		
	}
	
	public boolean createEvent(String name, String date, String location, String description, String organizerId) {
		return event.createEvent(name, date, location, description, organizerId);
	}
}
