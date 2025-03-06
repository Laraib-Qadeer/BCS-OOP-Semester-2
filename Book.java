package LibraryManagementSystem;
public class Book{
	private String title;
	private String issn;
	private Date publicationDate;
	private Person author;
	public Book(String title,String issn,Date publicationDate,Person author){
		this.title=title;
		this.issn=issn;
		this.publicationDate=new Date(publicationDate);
		this.author=new Person(author);
	}
	public Book(Book other){
		this.title=other.title;
		this.issn=other.issn;
		this.publicationDate=new Date(other.publicationDate);
		this.author=new Person(other.author);
	}
    	public String getTitle() {
		return title;
	}
    	public void setTitle(String title) {
		this.title = title; 
	}

    	public String getIssn() { 
		return issn; 
	}
    	public void setIssn(String issn) {
		this.issn = issn; 
	}

    	public Date getPublicationDate() {
		return publicationDate; 
	}
    	public void setPublicationDate(Date publicationDate) {
		this.publicationDate = new Date(publicationDate); 
	}

    	public Person getAuthor() {
		return author;
	}
    	public void setAuthor(Person author) {
		this.author = new Person(author);
	}

    	public boolean equals(Object obj) {
        	if (this == obj) return true;
        	if (obj == null || getClass() != obj.getClass()) return false;
        	Book book = (Book) obj;
        	return title.equals(book.title) &&
               	issn.equals(book.issn) &&
               	publicationDate.equals(book.publicationDate) &&
               	author.equals(book.author);
    	}

	public void display() {
    		System.out.println("Book Title: " + title);
   		System.out.println("ISSN: " + issn);
    		publicationDate.display();
    		System.out.println("Author Details:");
    		author.display();
	}


}