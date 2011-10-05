package controllers;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import controllers.*;

import play.*;
import play.mvc.*;

import models.*;
@With(Secure.class)
public class Application extends Controller {

    public static void index() {
    	getCalendars(Security.connected());
    }
    
    public static void getUsers() {
    	User me = Database.users.get(Security.connected());
    	List<User> users = Database.getUserList();
        render(me, users);
    }
    
    public static void getCalendars(String username) {
    	User me = Database.users.get(Security.connected());
    	User user = Database.users.get(username);
    	List<Calendar> calendars = new ArrayList<Calendar>();
    	if(me != null && user != null) {
    		calendars = me.getCalendarList(user);
    	}
    	render(me, user, calendars);
    }
    
    public static void getEvents(String username, String calendarName) {
    	User me = Database.users.get(Security.connected());
    	User user = Database.users.get(username);
    	List<Event> events = new ArrayList<Event>();
    	Calendar calendar = me.getCalendarByName(user, calendarName);
    	if(me != null && user != null && calendar != null) {
    		events = calendar.getEventList(new Date(0), me);
    	}
    	render(me, calendar, events);
    }
    
  public static void editEvent(String calendarName, String eventName) {
	User me = Database.users.get(Security.connected());
	Calendar calendar = me.getCalendarByName(me, calendarName);
	Event event = null;
	if(me != null && calendar != null) {
		event = calendar.getEventByName(eventName);
		if(event == null) {
			event = new Event("", new Date(), new Date(), false, me);
		}
	}
	render(me, calendar, event);
}
    
    public static void saveEvent(String calendarName, String oldEventName, String newEventName, String start, String end, boolean isPublic) {
    	User me = Database.users.get(Security.connected());
    	Calendar calendar = me.getCalendarByName(me, calendarName);
    	Event event = null;
    	if(me != null && calendar != null) {
    		event = calendar.getEventByName(oldEventName);
    		if(event == null) {
    			event = new Event("", new Date(), new Date(), false, me);
    		}
   			DateFormat dateFormat = new SimpleDateFormat("MM/dd/yyyy");
    		Date newStart = null;
    		Date newEnd = null;
    		try {
    			newStart = dateFormat.parse(start);
			} catch (ParseException e) {
				System.out.println("Error: Could not parse Date. Old one will be used.");
				newStart = event.getStart();
			}
   			try {
   				newEnd = dateFormat.parse(end);
			} catch (ParseException e) {
				System.out.println("Error: Could not parse Date. Old one will be used.");
				newEnd = event.getEnd();
			}
    		calendar.removeEvent(event, me);
    		event = new Event(newEventName, newStart, newEnd, isPublic, me);
    		calendar.addEvent(event, me);
    	}
    	getEvents(me.getName(), calendarName);
    }

}