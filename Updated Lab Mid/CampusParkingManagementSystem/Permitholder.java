public class Permitholder extends Person{
	private String permitId;
	private static int count=0;

	public Permitholder(String name){
		super(name);
		this.permitId="P" + String.format("%03d", ++count);
	}
	public String getpermitId(){
		return permitId;
	}
	@Override
	public String toString(){
		return super.toString()+"Permit-Id: "+permitId;
	}	
}