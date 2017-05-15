public class Shipment {
	private String address;
	private String type;

	public Shipment(String address, String type){
		this.address = address;
		this.type = type;
		if (! (type.equals("driving") || type.equals("walking"))) {
			type = "driving";
		}
	}

	public String toString(){
		return this.type + " " + this.address;
	}

	public String getAddress(){
		return this.address;
	}

    public String getType(){
		return this.type;
	}
}

