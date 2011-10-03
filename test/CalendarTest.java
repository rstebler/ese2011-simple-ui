import static org.junit.Assert.*;

import models.*;

import java.util.Date;
import java.util.Iterator;
import java.util.List;

import org.junit.Before;
import org.junit.Test;

import play.test.UnitTest;

public class CalendarTest extends UnitTest {
	
	private User owner;
	private Calendar calendar;
	private Event event;

	@Before
	public void setUp() throws Exception {
		this.owner = new User("Sepp", "password1");
		this.calendar = new Calendar("Sepp's Calendar", this.owner);
		this.event = new Event("NewEvent", new Date(), new Date(), false, this.owner);
	}

	@Test
	public void testAddEventEventUser() {
		assertEquals(0, calendar.GetNumberOfEvents());
		calendar.addEvent(event, owner);
		assertEquals(1, calendar.GetNumberOfEvents());
	}

	@Test
	public void testRemoveEvent() {
		calendar.addEvent(event, owner);
		assertEquals(1, calendar.GetNumberOfEvents());
		calendar.removeEvent(event, owner);
		assertEquals(0, calendar.GetNumberOfEvents());
	}
	
	@Test
	public void testGetName() {
		assertEquals("Sepp's Calendar", calendar.getName());
	}

	@Test
	public void testSetName() {
		calendar.setName("Calendar Name");
		assertEquals("Calendar Name", calendar.getName());
	}

	@Test
	public void testGetOwner() {
		assertEquals(owner, calendar.getOwner());
	}

	@Test
	public void testGetEventIteratorDateUser() {
		Iterator<Event> events = calendar.getEventIterator(new Date(), owner);
		assertNotNull(events);
	}

	@Test
	public void testGetEventIteratorCalendarDateUser() {
		Iterator<Event> events = calendar.getEventIterator(calendar, new Date(), owner);
		assertNotNull(events);
	}

	@Test
	public void testGetEventListDateUser() {
		List<Event> events = calendar.getEventList(new Date(), owner);
		assertNotNull(events);
	}

	@Test
	public void testGetEventListCalendarDateUser() {
		List<Event> events = calendar.getEventList(calendar, new Date(), owner);
		assertNotNull(events);
	}

	@Test
	public void testGetNumberOfEvents() {
		Integer i = calendar.GetNumberOfEvents();
		assertTrue(i >= 0);
		calendar.addEvent(new Event("Test", new Date(), new Date(), false, owner), owner);
		assertEquals(i+1, calendar.GetNumberOfEvents());
	}

	@Test
	public void testGetEventByName() {
		calendar.addEvent(new Event("Event1", new Date(), new Date(), false, owner), owner);
		assertNotNull(calendar.getEventByName("Event1"));
	}

}
