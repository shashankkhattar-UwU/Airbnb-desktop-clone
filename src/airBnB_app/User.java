package airBnB_app;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collection;

abstract public class User implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private float balance=2000;
	private ArrayList<Booking> userBookings;
	private String name;
	private String username;
	private String password;
	private Boolean isManager;
	public User(String username, String name, String password){
		this.name=name;
		this.username=username;
		this.password=password;
		userBookings=new ArrayList<Booking>();
	}
	public void book(Booking b) {
		userBookings.add(b);
		this.balance-=b.getProperty().getPricePerNight()*b.getNoOfDays();
	}
	public float getBalance() {
		return this.balance;
	}
	public void cancelBooking(Booking b) {
		this.payment((b.getProperty().getPricePerNight()-100)*b.getNoOfDays());
		userBookings.remove(b);
	}
	public String getName() {
		return this.name;
	}
	public String getUsername() {
		return this.username;
	}
	public String getPassword() {
		return this.password;
	}
	public ArrayList<Booking> getUserBookingsList(){
		return this.userBookings;
	}
	public Boolean getIsManager() {
		return this.isManager;
	}
	public void setIsManager(Boolean x) {
		this.isManager=x;
	}
	public void setBalance(Float balance) {
		this.balance=balance;
	}
	public abstract void addProperty(Property temp);
	protected abstract Collection getAddedProperties();
	public abstract void payment(Float val);
}
