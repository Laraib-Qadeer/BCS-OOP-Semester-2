package CinemaManagementSystem;
public class Seat{
	private int seatNumber;
	private int rowNumber;
	private String type;
	private double price;
	private boolean isBooked;
	
	public Seat(int seatNumber,int rowNumber,String type,double price){
		this.seatNumber=seatNumber;
		this.rowNumber=rowNumber;
		this.type=type;
		this.price=price;
		this.isBooked=false;
	}
	public boolean bookSeat(){
		if(!isBooked){
			isBooked=true;
			return true;
		}
			return false;
	}
	public void display(){
		System.out.println("Seat No. "+seatNumber+",Row No. "+rowNumber+",Type: "+type+",Price: $"+price);
	}
	public int getSeatNumber(){
		return seatNumber;
	}
	public void setSeatNumber(int seatNumber){
		this.seatNumber=seatNumber;	
	}
	public int getRowNumber(){
		return rowNumber;
	}
	public void setRowNumber(int rowNumber){
		this.rowNumber=rowNumber;
	}
	public String getType(){
		return type;
	}
	public void setType(String type){
		this.type=type;
	}
	public double getPrice(){
		return price;
	}
	public void setPrice(double price){
		this.price=price;
	}
	public boolean isBooked(){
		return isBooked;
	}
	public void setBooked(boolean booked){
		isBooked=booked;
	}
	
}