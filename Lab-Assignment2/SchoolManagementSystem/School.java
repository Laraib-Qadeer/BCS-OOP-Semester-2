import java.util.ArrayList;

public class School {
    private String name;
    private String address;
    private Principal principal;
    private ArrayList<ClassRoom> classRooms;

    public School(String name, String address, Principal principal) {
        this.name = name;
        this.address = address;
        this.principal = principal;
        this.classRooms = new ArrayList<>();
    }

    public void addClassRoom(ClassRoom room) {
        classRooms.add(room);
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("School: ").append(name).append("\nAddress: ").append(address).append("\n");
        sb.append(principal.toString()).append("\n\nClassRooms:\n");
        for (ClassRoom room : classRooms) {
            sb.append(room.toString()).append("\n");
        }
        return sb.toString();
    }
}
