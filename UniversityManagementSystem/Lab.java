package UniversityManagementSystem;

public class Lab{
	private String labName;
	private String labStaff;
	private PC[] pcs;
	
	public Lab(String labName, String labStaff){
		this.labName=labName;
		this.labStaff=labStaff;
		this.pcs=new PC[10];
		for(int i=0;i<10;i++){
			pcs[i]=new PC(i+1,"intel i7",8,256);
		}	
	}
	public void displayLab(){
		System.out.println("Lab Name:" +labName+ ",LabStaff" +labStaff);
		for (PC PC:pcs){
			PC.displayPC();
		}
	}
}