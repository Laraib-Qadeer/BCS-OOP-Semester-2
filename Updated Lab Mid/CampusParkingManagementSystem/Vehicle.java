public class Vehicle {
    	private static Vehicle[] allVehicles = new Vehicle[100];
    	private static int count = 0;
    	private String licensePlate;
    	private String type;
    	private Owner owner;
    	private boolean valid = true;

    	public Vehicle(String licensePlate, String type, Owner owner) {
        	for (int i = 0; i < count; i++) {
            		if (allVehicles[i] != null && allVehicles[i].licensePlate.equals(licensePlate)) {
                		System.out.println("Error: Duplicate license plate " + licensePlate);
                		valid = false;
                		return;
            		}
        	}
       		this.licensePlate = licensePlate;
        	this.type = type;
        	this.owner = owner;
        	allVehicles[count++] = this;
    	}

    	public boolean isValid() {
        	return valid;
    	}

    	public Vehicle shallowCopy() {
        	return new Vehicle(this.licensePlate + "_copy", this.type, this.owner);
    	}

    	public Vehicle deepCopy() {
        	Owner newOwner = new Owner(this.owner.getName());
        	return new Vehicle(this.licensePlate + "_copy", this.type, newOwner);
    	}

    	@Override
    		public String toString() {
        		return "\nVehicle: " + licensePlate + ", Type: " + type + ", Owner: [" + owner + "]";
   	}
}
