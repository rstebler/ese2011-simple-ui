import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

import play.test.UnitTest;

import models.*;

public class UserTest extends UnitTest {
	
	private User user;
	private Calendar calendar;
	
	@Before
	public void setUp() throws Exception {
		this.user = new User("User1", "password1");
		this.calendar = new Calendar("MyCalendar", user);
	}
	
	@Test
	public void testGetName() {
		assertEquals("User1", user.getName());
	}

	@Test
	public void testSetName() {
		user.setName("New Name 1");
		assertEquals("New Name 1", user.getName());
	}

	@Test
	public void testGetPassword() {
		assertEquals("password1", user.getPassword());
	}

	@Test
	public void testSetPassword() {
		user.setPassword("new password");
		assertEquals("new password", user.getPassword());
	}

	@Test
	public void testGetCalendarIterator() {
		assertNotNull(user.getCalendarIterator());
	}

	@Test
	public void testGetCalendarIteratorUser() {
		User user1 = new User("User1", "password");
		assertNotNull(user.getCalendarIterator(user1));
	}

	@Test
	public void testGetCalendarList() {
		assertNotNull(user.getCalendarList());
	}

	@Test
	public void testGetCalendarListUser() {
		User user1 = new User("User1", "password");
		assertNotNull(user.getCalendarList(user1));
	}
	
	@Test
	public void GetNumberOfCalendars() {
		Integer i = user.GetNumberOfCalendars();
		assertTrue(i >= 0);
		user.addCalendar("New calendar", user);
		assertEquals(i+1, user.GetNumberOfCalendars());
	}

	@Test
	public void testAddCalendarStringUser() {
		assertEquals(1, user.GetNumberOfCalendars());
		user.addCalendar("New calendar", user);
		assertEquals(2, user.GetNumberOfCalendars());
	}

	@Test
	public void testAddCalendarCalendar() {
		assertEquals(1, user.GetNumberOfCalendars());
		user.addCalendar(calendar);
		assertEquals(2, user.GetNumberOfCalendars());
	}

	@Test
	public void testRemoveCalendar() {
		user.addCalendar(calendar);
		assertEquals(2, user.GetNumberOfCalendars());
		user.removeCalendar(calendar);
		assertEquals(1, user.GetNumberOfCalendars());
	}

	@Test
	public void testGetCalendarByNameString() {
		user.addCalendar(new Calendar("My new Calendar", user));
		assertNotNull(user.getCalendarByName("My new Calendar"));
	}

	@Test
	public void testGetCalendarByNameUserString() {
		User user2 = new User("New User2", "password2");
		user2.addCalendar(new Calendar("My new Calendar", user2));
		assertNotNull(user.getCalendarByName(user2, "My new Calendar"));
	}

}
