public class ParkingSystem{
	private static ParkingSystem instance;
	private String campusname;
	private Supervisor supervisor;
	private Permitholder[] permitholder;
	private Parkingzone[] parkingzone;
	private int parkingzoneIndex=0;
	private int permitholderIndex=0;

	private ParkingSystem(String campusname, Supervisor supervisor){
		if (supervisor == null) {
            		System.out.println("Error: Supervisor must be assigned before initializing the system.");
            	return;
        	}
		this.campusname=campusname;
		this.supervisor=supervisor;
		this.permitholder=new Permitholder[2];
		this.parkingzone=new Parkingzone[2];
	}
	
	public static ParkingSystem getInstance(String campusname,Supervisor supervisor){
		if(instance == null){
			instance = new ParkingSystem(campusname,supervisor);
			return instance;
		}else{
			System.out.println("Warning:ParkingSystem instance already exists.");
		}
		return instance;
	}
	
	public String getCampusName(){
		return campusname;
	}
	
	public Supervisor getSupervisor(){
		return supervisor;
	}

	public void addzone(Parkingzone pz){
		if(parkingzoneIndex < parkingzone.length){
			parkingzone[parkingzoneIndex++]=pz;
			System.out.println("Parkingzone added. " + pz.getzoneId());
		}

	}
	
	public void addPermitholder(Permitholder ph){
		if(permitholderIndex < permitholder.length){
			permitholder[permitholderIndex++]=ph;
			System.out.println("Permitholder added: " + ph.getpermitId());
		}
	}

	@Override
    	public String toString() {
        	StringBuilder sb = new StringBuilder();
        	sb.append("Campus Name: ").append(campusname).append("\n");
        	sb.append("Supervisor: ").append(supervisor).append("\n\n");
        	sb.append("Parking Zones:\n");
        	for (int i = 0; i < parkingzoneIndex; i++) {
            		sb.append(parkingzone[i]);
        	}
        	sb.append("\nPermit Holders:\n");
        	for (int i = 0; i < permitholderIndex; i++) {
            		sb.append(permitholder[i]).append("\n");
        	}
        	return sb.toString();		

	}	
}