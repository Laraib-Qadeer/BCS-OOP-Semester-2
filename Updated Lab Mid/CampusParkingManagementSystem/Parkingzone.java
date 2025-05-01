public class Parkingzone{
	private String zoneId;
	private int counter=1;
	private Vehicle[] vehicles;
	private int vehicleIndex=0;

	public Parkingzone( ){
		this.zoneId="Z"+counter++;
		this.vehicles=new Vehicle[5];
	}
	
	public String getzoneId(){
		return zoneId;
	}
	
	public void addVehicle(Vehicle v){
		if (v != null && v.isValid()) {
			if(vehicleIndex < vehicles.length){
				vehicles[vehicleIndex++]=v;
				System.out.println("Vehicle added to zone " + zoneId + ": " + v);
			}else {
                		System.out.println("Zone " + zoneId + " is full. Cannot add more vehicles.");
            		}
		}
	}
	@Override
	public String toString(){
		String result="\nZone id:"+zoneId+"\n Vehicles";
		for(int i=0;i<vehicleIndex;i++){
			result+=vehicles[i];
		}
		return result + "\n";
	}
	


}