package LibraryManagementSystem;
public class Library{
	private Person staff;
	private Book book;
	private String name;
	private Person incharge;

	public Library(String name, Book book, Person incharge, Person staff) {
        	this.name = name;
        	this.book = new Book(book);
        	this.incharge = new Person(incharge);
        	this.staff = new Person(staff);
    	}

    	public Library(Library other) {
        	this.name = other.name;
        	this.book = new Book(other.book);
        	this.incharge = new Person(other.incharge);
        	this.staff = new Person(other.staff);
    	}
	
    	public String getName() {
		return name; 
	}
    	public void setName(String name) {
		this.name = name;
	}

    	public Book getBook() {
		return book;
	}
    	public void setBook(Book book) { 
		this.book = new Book(book); 
	}

    	public Person getIncharge() { 
		return incharge; 
	}
    	public void setIncharge(Person incharge) { 
		this.incharge = new Person(incharge); 
	}

    	public Person getStaff() { 
		return staff; 
	}
    	public void setStaff(Person staff) { 
		this.staff = new Person(staff); 
	}
	public boolean equals(Object obj) {
        	if (this == obj) return true;
        	if (obj == null || getClass() != obj.getClass()) return false;
        	Library library = (Library) obj;
        	return name.equals(library.name) &&
               	book.equals(library.book) &&
               	incharge.equals(library.incharge) &&
               	staff.equals(library.staff);
    	}

	public void display() {
    		System.out.println("Library Name: " + name);
    		System.out.println("\nBook Information:");
    		book.display();
    		System.out.println("\nIncharge Information:");
    		incharge.display();
    		System.out.println("\nStaff Information:");
    		staff.display();
	}

	
}