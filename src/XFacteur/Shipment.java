package XFacteur;

public class Shipment {
	private String address;
	private boolean driving;

	public Shipment(String address, boolean driving){
		this.address = address;
		this.driving = driving;
	}

	public String toString(){
		return (isDriving() ? "(en voiture)" : "(à pied)") + " " + address;
	}

	public String getAddress(){
		return this.address;
	}

    public boolean isDriving(){
		return this.driving;
	}
	
	/*public String getCity(){
	}
	
	public String getStreet(){
	}
	
	public String getNumber(){
	}*/
}

