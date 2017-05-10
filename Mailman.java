public class Mailman {

	private Booleen driver;
	private String lastName;
	private String name;
	private ArrayList <Shipments> path;

	public Mailman(Booleen d, String l, String n){
		this.driver=d;
		this.lastName=l;
		this.name=n;
		path=new ArrayList <Shipment> (100);
	}

	public void add(Shipment s){
		path.add(s);
	}

	public String toString(){
		String aff=lastName+" "+name+" ";
		if driver{
			return aff+"Permis";
		}else{return aff;}
	}
}
