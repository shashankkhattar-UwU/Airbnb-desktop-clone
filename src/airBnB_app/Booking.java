package airBnB_app;

import java.io.Serializable;

public class Booking implements Serializable{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	static int TotalIds;
	private int bookingID;
	private User user;
	private Property property;
	private int start, noOfDays;
	public Booking(User user, Property property, int start, int noOfDays) {
		this.user=user;
		this.property=property;
		this.start=start;
		this.noOfDays=noOfDays;
		this.bookingID=++TotalIds;
	}
	public User getUser() {
		return this.user;
	}
	public Property getProperty() {
		return this.property;
	}
	public int getStartDate() {
		return this.start;
	}
	public int getNoOfDays() {
		return this.noOfDays;
	}
	public int getID() {
		return this.bookingID;
	}
	@Override
	public String toString() {
		return "In "+this.property.getName()+" From "+this.start+" for "+this.noOfDays+" days";
	}
}
