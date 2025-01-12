package vipcontactmanager;
import java.util.HashMap;
import java.util.Scanner;
public class VIPContactManagerApp {
    public static void main(String[] args) {
        HashMap<String, Contact> contacts = new HashMap<>();
        Scanner scanner = new Scanner(System.in);
        int choice;

        do {
            System.out.println("\n=== VIP Contact Manager ===");
            System.out.println("1. New Contact");
            System.out.println("2. Show All Contacts");
            System.out.println("3. Delete Contact");
            System.out.println("4. Exit");
            System.out.print("Enter your choice: ");
            choice = scanner.nextInt();
            scanner.nextLine(); // Consume newline

            switch (choice) {
                case 1:
                    newContact(contacts, scanner);
                    break;
                case 2:
                    showAllContacts(contacts);
                    break;
                case 3:
                    deleteContact(contacts, scanner);
                    break;
                case 4:
                    System.out.println("Exiting... Thank you for using the VIP Contact Manager!");
                    break;
                default:
                    System.out.println("Invalid choice. Please try again.");
            }
        } while (choice != 4);
    }

    private static void newContact(HashMap<String, Contact> contacts, Scanner scanner) {
        System.out.print("Enter Name: ");
        String name = scanner.nextLine();
        System.out.print("Enter Phone Number: ");
        String phoneNumber = scanner.nextLine();
        System.out.print("Enter Email: ");
        String email = scanner.nextLine();

        contacts.put(name, new Contact(phoneNumber, email));
        System.out.println("Contact saved successfully.");
    }

    private static void showAllContacts(HashMap<String, Contact> contacts) {
        if (contacts.isEmpty()) {
            System.out.println("No contacts available.");
        } else {
            System.out.println("\n=== All Contacts ===");
            for (String name : contacts.keySet()) {
                Contact contact = contacts.get(name);
                System.out.println("Name: " + name);
                System.out.println("Phone Number: " + contact.getPhoneNumber());
                System.out.println("Email: " + contact.getEmail());
                System.out.println("-----------------------");
            }
        }
    }

    private static void deleteContact(HashMap<String, Contact> contacts, Scanner scanner) {
        System.out.print("Enter the name of the contact to delete: ");
        String name = scanner.nextLine();
        if (contacts.containsKey(name)) {
            contacts.remove(name);
            System.out.println("Contact deleted successfully.");
        } else {
            System.out.println("Contact not found.");
        }
    }
}

class Contact {
    private String phoneNumber;
    private String email;

    public Contact(String phoneNumber, String email) {
        this.phoneNumber = phoneNumber;
        this.email = email;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public String getEmail() {
        return email;
    }
}

