package xfacteur.model;

public class Shipment implements java.io.Serializable {
	protected String street;
	protected String city;
	protected boolean driven;

	public Shipment(String street, String city, boolean driven) {
		this.street = street;
		this.city = city;
		this.driven = driven;
	}

	public String toString() {
		return (isDriven() ? "(colis)" : "(lettre)") + " - " + street + ", " + city;
	}

	public String getStreet() {
		return street;
	}

	public String getCity() {
		return city;
	}

	public String getAddress() {
		return street + ", " + city;
	}

	public boolean isDriven() {
		return driven;
	}
}
