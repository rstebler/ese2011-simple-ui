package models;
import java.util.Comparator;
import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;



public class Calendar {
	
	//private List<Event> events = new LinkedList<Event>();
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
	
	public Iterator<Event> getEventIterator(Date date, User caller) {
		return getEventIterator(this, date, caller);
	}
	
	public Iterator<Event> getEventIterator(Calendar calendar, Date date, User caller) {
		return getEventList(calendar, date, caller).iterator();
	}
	
	public List<Event> getEventList(Date date, User caller) {
		return getEventList(this, date, caller);
	}
	
	public List<Event> getEventList(Calendar calendar, Date date, User caller) {
		List<Event> events = new LinkedList<Event>();
		for(Event event: calendar.events.values()) {
			if(event.getEnd().after(date) && (event.getIsPublic() || caller.equals(calendar.owner))) {
				events.add(event);
			    Comparator<Event> comparator = new EventDateComparator();
			    java.util.Collections.sort(events, comparator);
			}
		}
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
