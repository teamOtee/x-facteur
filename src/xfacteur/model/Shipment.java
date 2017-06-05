package xfacteur.model;

public class Shipment {
	protected String street;
	protected String city;
	protected boolean driving;

	public Shipment(String street, String city, boolean driving){
		this.street = street;
		this.city = city;
		this.driving = driving;
	}

	public String toString() {
		return (isDriving() ? "(en voiture)" : "(à pied)") + " " + street + ", " + city;
	}

	public String getStreet() { return street; }
	public String getCity() { return city; }
	public String getAddress() { return street + ", " + city; }
  public boolean isDriving() { return driving; }
}

