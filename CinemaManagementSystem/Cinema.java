package CinemaManagementSystem;
public class Cinema{
	private String name;
    	private String location;
    	private Screen[] screens;

    	public Cinema(String name, String location, int totalScreens) {
        	this.name = name;
        	this.location = location;
        	this.screens = new Screen[totalScreens];
    	}

    	public void addScreen(int index, Screen screen) {
		if (index >= 0 && index < screens.length) {
			screens[index] = screen;
		} else {
			System.out.println("Invalid screen index: " + index);
		}
	}

    	public void displayCinema() {
        	System.out.println("Cinema: " + name + " located at " + location);
        	for (Screen screen : screens) {
            		screen.displayScreen();
        	}
    	}

    	public String getName() {
        	return name;
    	}

    	public String getLocation() {
        	return location;
    	}

    	public Screen[] getScreens() {
        	return screens;
    	}

    	public void setName(String name) {
        	this.name = name;
    	}

    	public void setLocation(String location) {
        	this.location = location;
    	}

    	public void setScreens(Screen[] screens) {
        	this.screens = screens;
    	}
}