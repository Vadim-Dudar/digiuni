package edu.naukma;


import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class University {

    private String fullName;
    private String shortName;
    private String city;
    private String address;

    private List<Faculty> faculties;

    public University(String fullName, String shortName, String city, String address) {
        validateString(fullName, "Повна назва університету");
        validateString(shortName, "Скорочена назва університету");
        validateString(city, "Місто");
        validateString(address, "Адреса");

        this.fullName = fullName;
        this.shortName = shortName;
        this.city = city;
        this.address = address;
        this.faculties = new ArrayList<>();
    }

    public void addFaculty(Faculty faculty) {
        if (faculty == null)
            throw new IllegalArgumentException("Назва факультету не може бути порожньою");
        faculties.add(faculty);
    }

    public void updateFacultyByCode(String code, String newName, String newShortName, Teacher newDean, String newContacts) {
        Faculty faculty = findFacultyByCode(code);

        if (faculty == null)
            throw new IllegalArgumentException();

        if (newName != null && !newName.trim().isEmpty())
            faculty.setName(newName);

        if (newShortName != null && !newShortName.trim().isEmpty())
            faculty.setShortName(newShortName);

        if (newDean != null)
            faculty.setDean(newDean);

        if (newContacts != null && !newContacts.trim().isEmpty())
            faculty.setContacts(newContacts);

    }

    public Faculty createFaculty() {
        Scanner scanner = new Scanner(System.in);

        System.out.println("Введіть код факультету: ");
        String code = scanner.nextLine();

        System.out.println("Введіть повну назву факультету: ");
        String fullName = scanner.nextLine();

        System.out.println("Введіть абревіатуру факультету: ");
        String shortName = scanner.nextLine();

        System.out.println("Введіть контакти факультету: ");
        String contacts = scanner.nextLine();

        return new Faculty(code, fullName, shortName, null, contacts);
    }

    public boolean removeFacultyByCode(String code) {
        validateString(code, "Код факультету");

        for (int i = 0; i < faculties.size(); i++) {
            if (faculties.get(i).getCode().equals(code)) {
                faculties.remove(i);
                return true;
            }
        }
        return false;
    }

    public Faculty findFacultyByCode(String code) {
        validateString(code, "Код факультету");

        for (Faculty f : faculties) {
            if (f.getCode().equals(code))
                return f;
        }
        return null;
    }



    public List<Faculty> getFaculties() {
        return faculties;
    }

    private void validateString(String value, String fieldName) {
        if (value == null || value.trim().isEmpty()) {
            throw new IllegalArgumentException(fieldName + " не може бути порожнім");
        }
    }
}

