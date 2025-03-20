package CinemaManagementSystem;
public class Screen{
	private int screenNumber;
	private String movieTitle;
	private Seat[][] seats;
	
	public Screen(int screenNumber,String movieTitle,int rows,int cols){
		this.screenNumber=screenNumber;
		this.movieTitle=movieTitle;
		this.seats=new Seat[rows][cols];
		for (int i=0;i<rows;i++){
			for (int j=0;j<cols;j++){
				seats[i][j]=new Seat(i*cols + j+1,i+1,"Regular",10.0);
			}
		}
	}
	public boolean bookSeat(int row, int col, Customer customer) {
    		if (row <= 0 || col <= 0 || row > seats.length || col > seats[0].length) {
        		System.out.println("Invalid seat selection. Please enter a valid row and column.");
        			return false;
    	}

    	Seat seat = seats[row - 1][col - 1]; 
    		if (seat.bookSeat()) {
        		Ticket ticket = new Ticket(customer, screenNumber, seat.getSeatNumber(), movieTitle, seat.getPrice());
        		ticket.displayTicket();
        		return true;
    	}
    			return false;
	}



	public void displayScreen(){
		System.out.println("Screen No. "+screenNumber+",Movie: "+movieTitle);
			for (int i=0;i<seats.length;i++){
				for (int j=0;j<seats[i].length;j++){
					seats[i][j].display();
				}
			}
	}
	public int getScreenNumber() {
        	return screenNumber;
    	}

    	public String getMovieTitle() {
        	return movieTitle;
    	}

    	public Seat[][] getSeats() {
        	return seats;
    	}

    	public void setScreenNumber(int screenNumber) {
        	this.screenNumber = screenNumber;
    	}

    	public void setMovieTitle(String movieTitle) {
        	this.movieTitle = movieTitle;
    	}

    	public void setSeats(Seat[][] seats) {
        	this.seats = seats;
    	}	
}