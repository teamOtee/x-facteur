package xfacteur.model;

public class Mailman implements java.io.Serializable{
	private boolean driver;
	private String lastName;
	private String name;

	public Mailman(boolean d, String l,String n) {
		this.driver = d;
		this.lastName = l;
		this.name = n;
	}
	public String toString() {
		return (isDriver() ? "(en voiture)" : "(à pied)") + " - " + lastName + ", " + name;
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
