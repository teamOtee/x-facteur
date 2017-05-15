public class Shipment {
	private String address;
	private Boolean driving;

	public Shipment(String address, Boolean driving){
		this.address = address;
		this.driving = driving;
	}

	public String toString(){
		if driving{
			return  "driving " + this.address;
		}else{
			return "walking "+ this.address;
		}
	}

	public String getAddress(){
		return this.address;
	}

    public Boolean getDriver(){
		return this.driver;
	}
	
	public String getCity(){
	}
	
	public String getStreet(){
	}
	
	public String getNumber(){
	}
}

