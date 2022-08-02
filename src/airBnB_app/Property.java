package airBnB_app;

import java.io.Serializable;

public class Property implements Serializable{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private String adderUsername;
	private String name;
	private String address;
	private float pricePerNight;
	private Calendar calendar;
	
	public String getName() {
		return this.name;
	}
	public String getAddress() {
		return this.address;
	}
	public float getPricePerNight() {
		return this.pricePerNight;
	}
	public String getManager() {
		return this.adderUsername;
	}
	public Property(String adderUsername, String name, String address, float pricePerNight, String calendar) {
		this.adderUsername=adderUsername;
		this.name=name;
		this.address=address;
		this.pricePerNight=pricePerNight;
		this.calendar=new Calendar(calendar);
	}
	public Property(String adderUsername, String name, String address, float pricePerNight) {
		this.adderUsername=adderUsername;
		this.name=name;
		this.address=address;
		this.pricePerNight=pricePerNight;
		calendar=new Calendar();
	}
	public Boolean enquiry(int start, int noOfDays) {
		for(int i=start-1;i<start+noOfDays-1;i++) {
			if(!calendar.getDays().get(i))
				return false;
		}
		return true;
	}
	public void cancelBooking(Booking b) {
		for(int i=b.getStartDate()-1;i<b.getStartDate()+b.getNoOfDays()-1;i++) {
			this.calendar.getDays().set(i, true);
		}
	}
	public void setBooked(int start, int noOfDays) {
		for(int i=start-1;i<start+noOfDays-1;i++) {
			this.calendar.getDays().set(i, false);
		}
	}
	@Override
	public String toString() {
		return this.name+" At:- "+this.address+" \nPrice per night is: " +this.pricePerNight;
	}
	public Calendar getCalendar() {
		return this.calendar;
	}
}
