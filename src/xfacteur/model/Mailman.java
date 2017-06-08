package xfacteur.model;

import java.util.ArrayList;

public class Mailman {
	private boolean driver;
	private String lastName;
	private String name;
	private ArrayList<Shipment> path;

	public Mailman(boolean d, String l,String n) {
		this.driver = d;
		this.lastName = l;
		this.name = n;
		this.path = new ArrayList<Shipment>(100);
	}

	public void add(Shipment s) {
		path.add(s);
	}

	public String toString() {
		return (isDriver() ? "(Permis) - " : "") + lastName + ", " + name;
	}
	public boolean isDriver() {
		return driver;
	}

	public String getname() {
		return name;
	}

	public String getlastname() {
		return lastName;
	}

	
}
