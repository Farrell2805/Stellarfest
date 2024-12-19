package view;

import java.util.List;

import controller.AdminController;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.VBox;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import model.Event;
import util.PageNavigator;

public class EventView {
	private AdminController adminController;
	List<Event> events;
	TableView<Event> eventTable;
	ObservableList<Event> eventObservable;
	public EventView(AdminController adminController) {
		this.adminController = adminController;
	}
	
	public Scene getEventScene() {
		events = adminController.getAllEvents();
		eventObservable = FXCollections.observableArrayList(events);
		
		Label titleLabel = new Label("Manage Events");
		titleLabel.setFont(Font.font("Arial", FontWeight.BOLD, 24));
		
		Button backBtn = new Button("Back");
		backBtn.setOnAction(e->{
			PageNavigator.showAdminHomeView();
		});
		
		setTable();
		
		
		Button deleteEvent = new Button ("Delete");
		deleteEvent.setOnAction(e->{
			Event selectedEvent = eventTable.getSelectionModel().getSelectedItem();
			if(selectedEvent != null) {
				boolean status = adminController.deleteEvent(selectedEvent.getEventId());
				if(status) {
					events = adminController.getAllEvents();
					eventObservable.clear();
					eventObservable.addAll(events);
				} else {
					System.out.println("Failed to delete event");
				}
			} else {
				System.out.println("No event selected");
			} 
		});
		
		
		
		VBox layout = new VBox(20);
        layout.setAlignment(Pos.TOP_CENTER);
        layout.setPadding(new Insets(10));
        layout.getChildren().addAll(titleLabel, eventTable, deleteEvent, backBtn);
		return new Scene(layout);
	}
	
	private void setTable() {
		eventTable = new TableView<>();
		
		TableColumn<Event, String> idColumn= new TableColumn<>("Event Id");
		TableColumn<Event, String> nameColumn = new TableColumn<>("Event Name");
		TableColumn<Event, String> dateColumn = new TableColumn<>("Event Date");
		TableColumn<Event, String> locationColumn= new TableColumn<>("Event Location");
		TableColumn<Event, String> descriptionColumn = new TableColumn<>("Event Description");
		TableColumn<Event, String> organizerID = new TableColumn<>("OrganizerID");
		
		idColumn.setCellValueFactory(new PropertyValueFactory<>("eventId"));
		nameColumn.setCellValueFactory(new PropertyValueFactory<>("eventName"));
		dateColumn.setCellValueFactory(new PropertyValueFactory<>("eventDate"));
		locationColumn.setCellValueFactory(new PropertyValueFactory<>("eventLocation"));
		descriptionColumn.setCellValueFactory(new PropertyValueFactory<>("eventDescription"));
		organizerID.setCellValueFactory(new PropertyValueFactory<>("organizerId"));
		
		eventTable.getColumns().addAll(idColumn, nameColumn, dateColumn, locationColumn, descriptionColumn, organizerID);
		eventTable.setItems(eventObservable);
	}
	
	
}
