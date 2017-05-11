public abstract class Shipment{
	private String address;
	private String type;

	public Shipment(String a,String t){
		this.address=a;
		this.type=t;
	}

	public String toString(){
		return this.type +" "+this.address;
	}

	public String getAddress(){
		return this.address;
	}

    public String getType(){
		return this.type;
	}
}


