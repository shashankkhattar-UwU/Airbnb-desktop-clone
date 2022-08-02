package airBnB_app;

import java.io.Serializable;
import java.util.Collection;

public class Customer extends User implements Serializable{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public Customer(String username, String name, String password) {
		super(username, name, password);
		this.setIsManager(false);
	}

	@Override
	public void addProperty(Property temp) {
		// TODO Auto-generated method stub
		
	}

	@Override
	protected Collection getAddedProperties() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void payment(Float val) {
		// TODO Auto-generated method stub
		this.setBalance(this.getBalance()+val);
	}
}
