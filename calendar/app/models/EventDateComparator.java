package models;

import java.util.Comparator;

public class EventDateComparator implements Comparator<Event> {

	@Override
	public int compare(Event arg0, Event arg1) {
		if(arg0.getStart().after(arg1.getStart())) {
			return 1;
		}
		
		return 0;
	}

}
