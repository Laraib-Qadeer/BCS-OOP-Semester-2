package UniversityManagementSystem;

public class Department{
	private String departmentName;
	private Person incharge;
	private Lab[] labs;
	public Department(String departmentName,String inchargeName){
		this.departmentName=departmentName;
		this.incharge=new Person(inchargeName);
		this.labs=new Lab[4];
		for(int i=0;i<4;i++){
			labs[i]=new Lab("Lab "+(i+1)," Staff"+(i+1));
		}
		
	}
	public void displayDepartment(){
		System.out.println("\nDepartment: "+departmentName);
		System.out.println("InCharge"+incharge.getName());
		for(Lab lab:labs){
			lab.displayLab();
		}
	}	
}