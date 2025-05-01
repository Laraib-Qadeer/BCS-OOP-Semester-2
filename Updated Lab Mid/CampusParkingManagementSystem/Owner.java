public class Owner extends Person {
    private static int count = 0;
    private String ownerId;

    public Owner(String name) {
        super(name);
        this.ownerId = "O" + String.format("%03d", ++count);
    }

    public String getOwnerId() {
        return ownerId;
    }

    @Override
    public String toString() {
        return super.toString() + ", Owner ID: " + ownerId;
    }
}
