public class Supervisor extends Person{
	private int yearsofexp;

	public Supervisor(String name, int yearsofexp){
		super(name);
		this.yearsofexp=yearsofexp;
	}
	
	public int getYearsofexp(){
		return yearsofexp;
	}
	@Override
	public String toString(){
		return super.toString()+", Years of Experience: "+yearsofexp; 
	}

}