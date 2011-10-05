package models;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Comparator;
import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;



public class Calendar {
	
	private HashMap<String, Event> events = new HashMap<String, Event>();
	private String name;
	private User owner;
	
	public Calendar(String name, User owner) {
		this.name = name;
		this.owner = owner;
	}
	
	public void addEvent(String name, Date start, Date end, boolean isPublic, User caller) {
		Event event = new Event(name, start, end, isPublic, caller);
		addEvent(event, caller);
	}
	
	public void addEvent(Event event, User caller) {
		if(caller.equals(this.owner)) {
			events.put(event.getName(), event);
		}
	}
	
	public void removeEvent(Event event, User caller) {
		if(caller.equals(this.owner)) {
			events.remove(event.getName());
		}
	}
	
	public void setName(String name) {
		this.name = name;
	}
	
	public String getName() {
		return this.name;
	}
	
	public User getOwner() {
		return this.owner;
	}
	
	// Iterator of events for a specific date
	public Iterator<Event> getEventIterator(Date date, User caller) {
		List<Event> events = new LinkedList<Event>();
	    DateFormat dateFormat = new SimpleDateFormat("MM/dd/yyyy");
		for(Event event: this.events.values()) {
			if(dateFormat.format(event.getStart()).equals(dateFormat.format(date)) && (event.getIsPublic() || caller.equals(this.owner))) {
				events.add(event);
			}
		}
	    Comparator<Event> comparator = new EventDateComparator();
		java.util.Collections.sort(events, comparator);
		return events.iterator();
	}
	
	// List of events starting from a specific date
	public List<Event> getEventList(Date date, User caller) {
		List<Event> events = new LinkedList<Event>();
		for(Event event: this.events.values()) {
			if(event.getStart().after(date) && (event.getIsPublic() || caller.equals(this.owner))) {
				events.add(event);
			}
		}
	    Comparator<Event> comparator = new EventDateComparator();
		java.util.Collections.sort(events, comparator);
		return events;
	}
	
	
	public int GetNumberOfEvents() {
		return this.events.size();
	}

	
	public Event getEventByName(String eventName) {
		if(this.events.containsKey(eventName)) {
			return this.events.get(eventName);
		}
		return null;
	}
	
}
