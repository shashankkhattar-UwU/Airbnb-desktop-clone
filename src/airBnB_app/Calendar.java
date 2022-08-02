package airBnB_app;
import java.io.Serializable;
import java.util.ArrayList;

public class Calendar implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private ArrayList<Boolean> dates=new ArrayList<Boolean>();
	public ArrayList<Boolean> getDays(){
		return this.dates;
	}
	public Calendar(String binaryrep) {
		for(int i=0;i<30;i++) {
			if(binaryrep.charAt(i)=='1') {
				dates.add(i, true);
			}
			else
				dates.add(i, false);
		}
	}
	public Calendar() {
		for(int i=0;i<30;i++) {
			dates.add(i, true);
		}
	}
}
