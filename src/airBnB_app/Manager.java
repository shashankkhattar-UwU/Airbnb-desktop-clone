package airBnB_app;

import java.io.Serializable;
import java.util.*;

public class Manager extends User implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private ArrayList<Property> addedProperties=new ArrayList<Property>();
	private String emailID;
	public Manager(String username, String name, String password, String emailID) {
		super(username, name, password);
		this.setIsManager(true);
		this.setEmailID(emailID);
	}
	public void addProperty(String name, String address, float pricePerNight) {
		Property tempprop=new Property(this.getUsername(), name, address, pricePerNight);
		this.addedProperties.add(tempprop);
	}
	public String getEmailID() {
		return this.emailID;
	}
	public void setEmailID(String emailID) {
		this.emailID=emailID;
	}
	public void addProperty(Property prop) {
		addedProperties.add(prop);
	}
	public ArrayList<Property> getAddedProperties(){
		return this.addedProperties;
	}
	@Override
	public void payment(Float val) {
		// TODO Auto-generated method stub
		this.setBalance(this.getBalance()+val);
	}
}
