import java.io.*;
import java.util.ArrayList;
import java.util.List;

public class PhoneBook {
    private List<Contact> contacts;

    public PhoneBook() {
        contacts = new ArrayList<>();
    }

    public void addContact(Contact contact) {
        contacts.add(contact);
    }

    public void removeContact(Contact contact) {
        contacts.remove(contact);
    }

    public List<Contact> getContacts() {
        return contacts;
    }

    public void importContacts(String filename) {
        try (BufferedReader reader = new BufferedReader(new FileReader(filename))) {
            String line;
            while ((line = reader.readLine()) != null) {
                if (!line.isEmpty()) {
                    String[] parts = line.split(";");
                    if (parts.length == 2) {
                        String name = parts[0].trim();
                        String phoneNumber = parts[1].trim();
                        Contact contact = new Contact(name, phoneNumber);
                        contacts.add(contact);
                    }
                }
            }
            System.out.println("Контакты успешно импортированы.");
        } catch (IOException e) {
            System.out.println("Произошла ошибка при импорте контактов: " + e.getMessage());
        }
    }

    public void exportContacts(String filename) {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(filename))) {
            for (Contact contact : contacts) {
                writer.write(contact.getName() + ";" + contact.getPhoneNumber());
                writer.newLine();
            }
            System.out.println("Контакты успешно экспортированы.");
        } catch (IOException e) {
            System.out.println("Произошла ошибка при экспорте контактов: " + e.getMessage());
        }
    }

    public static void main(String[] args) {
        PhoneBook phoneBook = new PhoneBook();

        phoneBook.addContact(new Contact("Иван Иванов", "111"));
        phoneBook.addContact(new Contact("Петр Петров", "222"));
        phoneBook.addContact(new Contact("Сергей Сергеев", "333"));

        phoneBook.exportContacts("contacts.txt");

        phoneBook.getContacts().clear();

        phoneBook.importContacts("contacts.txt");

        List<Contact> importedContacts = phoneBook.getContacts();
        for (Contact contact : importedContacts) {
            System.out.println(contact.getName() + " - " + contact.getPhoneNumber());
        }
    }
}