package edu.naukma;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class University {

    private String fullName;
    private String shortName;
    private String city;
    private String address;
    private Scanner scanner = new Scanner(System.in);
    private List<Faculty> faculties;

    public University(String fullName, String shortName, String city, String address) {
        this.fullName = fullName;
        this.shortName = shortName;
        this.city = city;
        this.address = address;
        this.faculties = new ArrayList<>();
    }

    private String readRequiredString(String prompt) {
        while (true) {
            System.out.print(prompt);
            String input = scanner.nextLine();
            if (input != null && !input.trim().isEmpty()) {
                return input;
            }
            System.out.println("Помилка: це поле є обов'язковим. Спробуйте ще раз.");
        }
    }

    public void addFaculty(Faculty faculty) {
        if (faculty != null) {
            faculties.add(faculty);
        } else {
            System.out.println("Помилка: неможливо додати порожній об'єкт факультету.");
        }
    }

    public void updateFacultyByCode(String code) {
        Faculty faculty = findFacultyByCode(code);
        if (faculty == null) {
            System.out.println("Помилка: Факультет з кодом " + code + " не знайдено.");
            return;
        }

        System.out.println("Редагування факультету: " + faculty.getShortName());
        System.out.println("Якщо дані не потрібно змінювати — просто натисніть Enter.");

        System.out.print("Новий код (поточний: " + faculty.getCode() + "): ");
        String newCode = scanner.nextLine();
        if (!newCode.trim().isEmpty()) faculty.setCode(newCode);

        System.out.print("Нова назва: ");
        String newName = scanner.nextLine();
        if (!newName.trim().isEmpty()) faculty.setName(newName);

        System.out.print("Нова абревіатура: ");
        String newShortName = scanner.nextLine();
        if (!newShortName.trim().isEmpty()) faculty.setShortName(newShortName);

        System.out.print("Нові контакти: ");
        String newContacts = scanner.nextLine();
        if (!newContacts.trim().isEmpty()) faculty.setContacts(newContacts);

        System.out.println("Дані успішно оновлено.");
    }

    public Faculty createFaculty() {
        String code = readRequiredString("Введіть код факультету: ");
        String fullName = readRequiredString("Введіть повну назву факультету: ");
        String shortName = readRequiredString("Введіть абревіатуру факультету: ");
        String contacts = readRequiredString("Введіть контакти факультету: ");

        return new Faculty(code, fullName, shortName, null, contacts);
    }

    public boolean removeFacultyByCode(String code) {
        if (code == null || code.trim().isEmpty()) return false;

        for (int i = 0; i < faculties.size(); i++) {
            if (faculties.get(i).getCode().equals(code)) {
                faculties.remove(i);
                return true;
            }
        }
        return false;
    }

    public Faculty findFacultyByCode(String code) {
        if (code == null || code.trim().isEmpty()) return null;

        for (Faculty f : faculties) {
            if (f.getCode().equals(code))
                return f;
        }
        return null;
    }

    public List<Faculty> getFaculties() {
        return faculties;
    }

    private boolean isInvalid(String value) {
        return value == null || value.trim().isEmpty();
    }
}