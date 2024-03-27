import java.util.Scanner;

public class Main {

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        
        while (true) {
            System.out.println("\n1. Add Contact");
            System.out.println("2. View Contacts");
            System.out.println("3. Update Contact");
            System.out.println("4. Delete Contact");
            System.out.println("5. Search Contact");
            System.out.println("6. Exit");
            System.out.print("Enter your choice: ");
            int choice = scanner.nextInt();

            switch (choice) {
                case 1:
                    addContact(scanner);
                    break;
                case 2:
                    viewContacts();
                    break;
                case 3:
                    updateContact(scanner);
                    break;
                case 4:
                    deleteContact(scanner);
                    break;
                case 5:
                    searchContact(scanner);
                    break;
                case 6:
                    System.out.println("Exiting...");
                    System.exit(0);
                default:
                    System.out.println("Invalid choice, please try again.");
            }
        }
    }

    private static void addContact(Scanner scanner) {
        System.out.print("Enter Name: ");
        String name = scanner.next();
        System.out.print("Enter Phone: ");
        String phone = scanner.next();
        System.out.print("Enter Email: ");
        String email = scanner.next();

        ContactDAO.addContact(name, phone, email);
    }

    private static void viewContacts() {
        ContactDAO.viewContacts();
    }

    private static void updateContact(Scanner scanner) {
        System.out.print("Enter ID of the contact to update: ");
        int id = scanner.nextInt();
        System.out.print("Enter new Name: ");
        String name = scanner.next();
        System.out.print("Enter new Phone: ");
        String phone = scanner.next();
        System.out.print("Enter new Email: ");
        String email = scanner.next();

        ContactDAO.updateContact(id, name, phone, email);
    }

    private static void deleteContact(Scanner scanner) {
        System.out.print("Enter ID of the contact to delete: ");
        int id = scanner.nextInt();

        ContactDAO.deleteContact(id);
    }

    private static void searchContact(Scanner scanner) {
        System.out.print("Enter name, phone, or email to search: ");
        String searchKeyword = scanner.next();

        ContactDAO.searchContact(searchKeyword);
    }
}
