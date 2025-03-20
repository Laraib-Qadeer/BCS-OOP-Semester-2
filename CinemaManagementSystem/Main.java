package CinemaManagementSystem;
public class Main{
	public static void main(String [] args){
	
	Cinema cinema=new Cinema("Universal Cinemas","Lahore",2);
	Screen screen1=new Screen(1,"Avengers",3,3);
	Screen screen2=new Screen(2,"Tangled",3,3);

	cinema.addScreen(0,screen1);
	cinema.addScreen(1,screen2);

	Customer customer1=new Customer(1,"Haseeb","0301-1112223","haseeb79@gmail.com");
	cinema.displayCinema();

	screen1.bookSeat(1,1,customer1);
}
}