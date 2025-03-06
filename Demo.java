package LibraryManagementSystem;

public class Demo {
    public static void main(String args[]) {
        Address authorAddress = new Address("123 Main St", "New York");
        Person author = new Person("Nemrah Ahmed", "Author", authorAddress);

        Date pubDate = new Date(10, 5, 2020);
        Book book = new Book("AI and Future", "1234-5678", pubDate, author);

        Person incharge = new Person("Dr. Ali", "Head Librarian", new Address("456 abc St", "Lahore"));
        Person staff = new Person("Haseeb", "Librarian Assistant", new Address("789 def St", "Karachi"));

        Library library1 = new Library("Central Library", book, incharge, staff);
        Library library2 = new Library(library1);

        System.out.println("\nLibrary Information:");
        library1.display();

        System.out.println("\nComparing Two Libraries:");
        System.out.println(library1.equals(library2) ? "Libraries are identical." : "Libraries are different.");
    }
}
