public class Main{
	public static void main(String args[]){
		Supervisor supervisor=new Supervisor("Dr.Ayesha",5);
		ParkingSystem system=ParkingSystem.getInstance("CUI Lahore",supervisor);
		ParkingSystem secondAttempt=ParkingSystem.getInstance("Shouldnotwork",supervisor);
		
		Parkingzone zone1=new Parkingzone();
		Parkingzone zone2=new Parkingzone();

		Owner owner1= new Owner("Ali");
		Owner owner2= new Owner("Zara");

		Vehicle v1=new Vehicle("LEA 123","Car",owner1);
		Vehicle v2=new Vehicle("LEb 456","Bike",owner2);
		Vehicle v3=new Vehicle("LEA 123","Car",owner1);
			
		zone1.addVehicle(v1);
		zone1.addVehicle(v2);
		zone2.addVehicle(v3);

		system.addzone(zone1);
		system.addzone(zone2);
		
		Permitholder ph1=new Permitholder("Sara");
		Permitholder ph2=new Permitholder("Hassan");

		system.addPermitholder(ph1);
		system.addPermitholder(ph2);
		System.out.println(system);


	}
}