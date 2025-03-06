package LibraryManagementSystem;
public class Address{
	private String city;
	private String street;
	public Address(String street,String city){
		this.street=street;
		this.city=city;
	}

	public Address(Address other){
		this.street=other.street;
		this.city=other.city;
	}	
	public String getStreet(){
		return street;
 	}
    	public void setStreet(String street){
		 this.street = street;
	}
    	public String getCity(){
	return city;
	 }
    	public void setCity(String city){
		this.city = city;
	 }

    	public boolean equals(Object obj) {
        	if (this == obj) return true;
        	if (obj == null || getClass() != obj.getClass()) return false;
        	Address address = (Address) obj;
        	return street.equals(address.street) && city.equals(address.city);
    	}

   	public void display(){
   	System.out.println("Address: " + street + ", " + city);
	}


}

