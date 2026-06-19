import java.io.*;
import java.util.*;
import java.util.stream.Collectors;

public class ContactManager {
    private static final String CONTACT_FILE = "contacts.txt";
    private static final Scanner scanner = new Scanner(System.in);

    public static void main(String[] args) {
        List<Contact> contacts = new ArrayList<>();
        Map<String, Contact> contactByPhone = new HashMap<>();
        Set<String> emailDomains = new HashSet<>();

        loadContacts(contacts, contactByPhone, emailDomains);

        while (true) {
            print("\nContact Manager");
            print("1. Add Contact");
            print("2. List Contacts");
            print("3. Search by Name");
            print("4. Remove Contact");
            print("5. Show Email Domains");
            print("6. Save and Exit");
            print("Enter choice: ");

            int choice = 0;
            try {
                choice = Integer.parseInt(scanner.nextLine());
            } catch (NumberFormatException e) {
                print("Invalid choice. Please enter a number.");
                continue;
            }

            switch (choice) {
                case 1:
                    addContact(contacts, contactByPhone, emailDomains);
                    break;
                case 2:
                    listContacts(contacts);
                    break;
                case 3:
                    searchContacts(contacts);
                    break;
                case 4:
                    removeContact(contacts, contactByPhone, emailDomains);
                    break;
                case 5:
                    showEmailDomains(emailDomains);
                    break;
                case 6:
                    saveContacts(contacts);
                    print("Contacts saved. Exiting.");
                    return;
                default:
                    print("Please choose a valid option.");
            }
        }
    }

    private static void print(Object... values) {
        for (Object value : values) {
            System.out.println(value);
        }
    }

    private static void loadContacts(List<Contact> contacts,
                                     Map<String, Contact> contactByPhone,
                                     Set<String> emailDomains) {
        File file = new File(CONTACT_FILE);
        if (!file.exists()) {
            return;
        }

        try (BufferedReader reader = new BufferedReader(new FileReader(file))) {
            String line;
            while ((line = reader.readLine()) != null) {
                String[] parts = line.split(",");
                if (parts.length == 3) {
                    Contact contact = new Contact(parts[0], parts[1], parts[2]);
                    contacts.add(contact);
                    contactByPhone.put(parts[1], contact);
                    emailDomains.add(getDomain(parts[2]));
                }
            }
        } catch (IOException e) {
            print("Error loading contacts: " + e.getMessage());
        }
    }

    private static void saveContacts(List<Contact> contacts) {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(CONTACT_FILE))) {
            for (Contact contact : contacts) {
                writer.write(contact.getName() + "," + contact.getPhone() + "," + contact.getEmail());
                writer.newLine();
            }
        } catch (IOException e) {
            print("Error saving contacts: " + e.getMessage());
        }
    }

    private static void addContact(List<Contact> contacts,
                                   Map<String, Contact> contactByPhone,
                                   Set<String> emailDomains) {
        print("Enter name:");
        String name = scanner.nextLine().trim();
        print("Enter phone number:");
        String phone = scanner.nextLine().trim();
        print("Enter email:");
        String email = scanner.nextLine().trim();

        if (name.isEmpty() || phone.isEmpty() || email.isEmpty()) {
            print("All fields are required.");
            return;
        }

        if (contactByPhone.containsKey(phone)) {
            print("Phone number already exists.");
            return;
        }

        Contact contact = new Contact(name, phone, email);
        contacts.add(contact);
        contactByPhone.put(phone, contact);
        emailDomains.add(getDomain(email));
        print("Contact added.");
    }

    private static void listContacts(List<Contact> contacts) {
        if (contacts.isEmpty()) {
            print("No contacts available.");
            return;
        }
        for (Contact contact : contacts) {
            print(contact);
        }

        long gmailCount = contacts.stream()
                .filter(c -> c.getEmail().toLowerCase().contains("@gmail.com"))
                .count();
        print("Gmail contacts: " + gmailCount);
    }

    private static void searchContacts(List<Contact> contacts) {
        print("Enter name to search:");
        String search = scanner.nextLine().trim().toLowerCase();
        if (search.isEmpty()) {
            print("Search text cannot be empty.");
            return;
        }
        List<Contact> result = contacts.stream()
                .filter(c -> c.getName().toLowerCase().contains(search))
                .collect(Collectors.toList());

        if (result.isEmpty()) {
            print("No contacts found.");
            return;
        }
        for (Contact contact : result) {
            print(contact);
        }
    }

    private static void removeContact(List<Contact> contacts,
                                      Map<String, Contact> contactByPhone,
                                      Set<String> emailDomains) {
        print("Enter phone number to delete:");
        String phone = scanner.nextLine().trim();
        if (!contactByPhone.containsKey(phone)) {
            print("Contact not found.");
            return;
        }
        Contact removed = contactByPhone.remove(phone);
        contacts.remove(removed);
        emailDomains.clear();
        for (Contact contact : contacts) {
            emailDomains.add(getDomain(contact.getEmail()));
        }
        print("Contact removed.");
    }

    private static void showEmailDomains(Set<String> emailDomains) {
        if (emailDomains.isEmpty()) {
            print("No email domains available.");
            return;
        }
        print("Email domains:");
        for (String domain : emailDomains) {
            print(domain);
        }
    }

    private static String getDomain(String email) {
        int at = email.indexOf("@");
        if (at >= 0 && at < email.length() - 1) {
            return email.substring(at + 1).toLowerCase();
        }
        return "unknown";
    }

    static class Contact {
        private final String name;
        private final String phone;
        private final String email;

        public Contact(String name, String phone, String email) {
            this.name = name;
            this.phone = phone;
            this.email = email;
        }

        public String getName() {
            return name;
        }

        public String getPhone() {
            return phone;
        }

        public String getEmail() {
            return email;
        }

        @Override
        public String toString() {
            return "Name: " + name + ", Phone: " + phone + ", Email: " + email;
        }
    }
}
