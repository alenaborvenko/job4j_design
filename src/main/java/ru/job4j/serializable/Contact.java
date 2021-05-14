package ru.job4j.serializable;

import java.io.*;
import java.nio.file.Paths;
import java.util.Objects;
import java.util.StringJoiner;

public class Contact implements Serializable {
    private static final long serialVersionUID = 1L;
    private final int zipCode;
    private final String phone;

    public Contact(int zipCode, String phone) {
        this.zipCode = zipCode;
        this.phone = phone;
    }

    public int getZipCode() {
        return zipCode;
    }

    public String getPhone() {
        return phone;
    }


    @Override
    public String toString() {
        return new StringJoiner(", ", Contact.class.getSimpleName() + "[", "]")
                .add("zipCode=" + zipCode)
                .add("phone='" + phone + "'")
                .toString();
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        Contact contact = (Contact) o;
        return zipCode == contact.zipCode && Objects.equals(phone, contact.phone);
    }

    @Override
    public int hashCode() {
        return Objects.hash(zipCode, phone);
    }

    public static void serialization(Contact contact, String path) throws IOException {
        /* Запись объекта в файл */
        File tempFile = Paths.get(path).toFile();
        try (FileOutputStream fos = new FileOutputStream(tempFile);
             ObjectOutputStream oos =
                     new ObjectOutputStream(fos)) {
            oos.writeObject(contact);
        }
    }

    public static Contact deserialization(String path) throws IOException, ClassNotFoundException {
        /* Чтение объекта из файла */
        try (FileInputStream fis = new FileInputStream(Paths.get(path).toFile());
             ObjectInputStream ois =
                     new ObjectInputStream(fis)) {
            return (Contact) ois.readObject();
        }
    }

    public static void main(String[] args) throws ClassNotFoundException, IOException {
        final Contact contact = new Contact(123456, "+7 (111) 111-11-11");
        String tempFile = "./src/main/java/ru/job4j/serializable/contact.txt";
        serialization(contact, "./src/main/java/ru/job4j/serializable/contact.txt");
        final Contact contactFromFile = deserialization(tempFile);
        System.out.println(contactFromFile);
    }
}
