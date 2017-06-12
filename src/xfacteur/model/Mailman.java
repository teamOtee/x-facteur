package xfacteur.model;

import java.io.Serializable;

public class Mailman implements Serializable {
	private boolean driver;
	private String lastName;
	private String name;

	public Mailman(boolean d, String l,String n) {
		this.driver = d;
		this.lastName = l;
		this.name = n;
	}
	public String toString() {
		return (isDriver() ? "(en voiture)" : "(à pied)") + " – " + name + " " + lastName;
	}
	public boolean isDriver() {
		return driver;
	}

	public String getName() {
		return name;
	}

	public String getLastName() {
		return lastName;
	}
}

