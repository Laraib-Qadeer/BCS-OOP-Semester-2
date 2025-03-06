package LibraryManagementSystem;
class Person{
	private String name;
	private String role;
	private Address address;
	public Person(String name,String role,Address address){
		this.name=name;
		this.role=role;
		this.address=new Address(address);
	}
	public Person(Person other){
		this.name=other.name;
		this.role=other.role;
		this.address=new Address(other.address);
	}
 	public String getName() {
		return name;
 	}
    	public void setName(String name) {
		this.name = name;
 	}

    	public Address getAddress() { 
		return address;
 	}
    	public void setAddress(Address address) {
		this.address = new Address(address);
 	}

    	public String getRole() {
	 	return role;
 	}
    	public void setRole(String role) {
		this.role = role;
 	}
    	public boolean equals(Object obj) {
        	if (this == obj) return true;
        	if (obj == null || getClass() != obj.getClass()) return false;
        	Person person = (Person) obj;
       		return name.equals(person.name) &&
               	address.equals(person.address) && role.equals(person.role);
    	}

	public void display() {
    		System.out.println("Name: " + name);
    		System.out.println("Role: " + role);
    		address.display();
	}


}

