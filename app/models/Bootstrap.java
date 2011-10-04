package models;

import java.util.Date;

import play.jobs.*;

@OnApplicationStart
public class Bootstrap extends Job {
    
    public void doJob() {
    	
    	User user;
    	Event event;
    	Date now = new Date();
    	
    	user = new User("Sepp", "test");
    	event=new Event("Sepp's Event 1", now, now, true, user);
    	user.getCalendarByName(user.getName()+"'s Calendar").addEvent(event, user);
    	event=new Event("Sepp's Event 2", now, now, false, user);
    	user.getCalendarByName(user.getName()+"'s Calendar").addEvent(event, user);
    	Database.addUser(user);
    	
    	user = new User("Fritz", "123");
    	event=new Event("Fritz's Event 1", now, now, false, user);
    	user.getCalendarByName(user.getName()+"'s Calendar").addEvent(event, user);
    	event=new Event("Fritz's Event 2", now, now, true, user);
    	user.getCalendarByName(user.getName()+"'s Calendar").addEvent(event, user);
    	Database.addUser(user);
    	
    	user = new User("Hans", "hello");
    	event=new Event("Hans's Event 1", now, now, true, user);
    	user.getCalendarByName(user.getName()+"'s Calendar").addEvent(event, user);
    	event=new Event("Hans's Event 2", now, now, true, user);
    	user.getCalendarByName(user.getName()+"'s Calendar").addEvent(event, user);
    	Database.addUser(user);
    	
    	user = new User("Kurt", "password");
    	event=new Event("Kurt's Event 1", now, now, false, user);
    	Calendar cal1 = new Calendar("Kurt's Kalender", user);
    	user.addCalendar(cal1);
    	user.getCalendarByName(cal1.getName()).addEvent(event, user);
    	event=new Event("Kurt's Event 2", now, now, true, user);
    	user.getCalendarByName(user.getName()+"'s Calendar").addEvent(event, user);
    	Database.addUser(user);
    	
    	
    	
    	/*Database.addUser("Sepp", "test");
    	Database.addUser("Fritz", "123");
    	Database.addUser("Hans", "hello");
    	Database.addUser("Kurt", "password");*/
    	
    }
    
}