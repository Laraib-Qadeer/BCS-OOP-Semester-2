package UniversityManagementSystem;
public class PC{
	private int pcId;
	private String pcVersion;
	private int ramSize;
	private int diskSize;
	public PC(int pcId, String pcVersion, int ramSize, int diskSize){
		this.pcId=pcId;
		this.pcVersion=pcVersion;
		this.ramSize=ramSize;
		this.diskSize=diskSize;
	}
	public void displayPC(){
		System.out.println("PC ID: " +pcId+ ", Version: "+pcVersion+ ",RAM: "+ramSize+"GB, Disk: "+diskSize+ "GB");
	}
}