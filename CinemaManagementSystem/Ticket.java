package CinemaManagementSystem;
public class Ticket{
	private int ticketId;
	private Customer customer;
	private int screenNumber;
	private int seatNumber;
	private String movieTitle;
	private double price;
	
	public Ticket(Customer customer, int screenNumber, int seatNumber, String movieTitle, double price) {
        	this.customer = customer;
        	this.screenNumber = screenNumber;
        	this.seatNumber = seatNumber;
        	this.movieTitle = movieTitle;
        	this.price = price;
   	}
	public void displayTicket() {
        	System.out.println("Ticket Info:");
        	System.out.println("Customer: " + customer.getName() + ", Movie: " + movieTitle + ", Seat: " + seatNumber + ", Price: $" + price);
    	}

    	public int getTicketId() {
        	return ticketId;
    	}

    	public Customer getCustomer() {
        	return customer;
    	}

    	public int getScreenNumber() {
        	return screenNumber;
    	}

    	public int getSeatNumber() {
        	return seatNumber;
    	}

    	public String getMovieTitle() {
        	return movieTitle;
    	}

    	public double getPrice() {
        	return price;
    	}

    	public void setTicketId(int ticketId) {
        	this.ticketId = ticketId;
    	}

    	public void setCustomer(Customer customer) {
        	this.customer = customer;
    	}

    	public void setScreenNumber(int screenNumber) {
        	this.screenNumber = screenNumber;
    	}

    	public void setSeatNumber(int seatNumber) {
        	this.seatNumber = seatNumber;
    	}

    	public void setMovieTitle(String movieTitle) {
        	this.movieTitle = movieTitle;
    	}

    	public void setPrice(double price) {
        	this.price = price;
    	}
}