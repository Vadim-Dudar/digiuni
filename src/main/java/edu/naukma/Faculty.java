package edu.naukma;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Faculty {

    private String code;
    private String name;
    private String shortName;
    private Teacher dean;
    private String contacts;

    private List<Department> departments;
    private Scanner scanner = new Scanner(System.in);

    /**
     * Creates a Faculty object and initializes the departments list.
     *
     * @param code faculty code
     * @param name full faculty name
     * @param shortName faculty abbreviation
     * @param dean faculty dean
     * @param contacts contact information
     */
    public Faculty(String code, String name, String shortName, Teacher dean, String contacts) {
        this.code = code;
        this.name = name;
        this.shortName = shortName;
        this.dean = dean;
        this.contacts = contacts;
        this.departments = new ArrayList<>();
    }

    /**
     * Reads a required string value from console input.
     * Repeats input until a non-empty value is entered.
     *
     * @param prompt message shown to the user
     * @return non-empty input string
     */
    private String readRequiredString(String prompt) {
        while (true) {
            System.out.print(prompt);
            String input = scanner.nextLine();
            if (input != null && !input.trim().isEmpty())
                return input;
            System.out.println("Помилка: це поле обов'язкове для заповнення.");
        }
    }

    /**
     * Adds a department to the faculty.
     *
     * @param department department to add
     */
    public void addDepartment(Department department) {
        if (department != null)
            departments.add(department);
        else
            System.out.println("Помилка: неможливо додати порожню кафедру.");
    }

    /**
     * Creates a new department using user input.
     *
     * @param university university to which the department belongs
     * @return created Department object
     */
    public Department createDepartment(University university) {
        System.out.println("--- Створення нової кафедри ---");

        String code = readRequiredString("Введіть код кафедри: ");
        String name = readRequiredString("Введіть назву кафедри: ");
        String location = readRequiredString("Введіть локацію (корпус/кабінет) кафедри: ");

        return new Department(code, name, this, null, location);
    }

    /**
     * Removes a department by its code.
     *
     * @param code department code
     * @return true if removed, false otherwise
     */
    public boolean removeDepartmentByCode(String code) {
        if (code == null || code.trim().isEmpty())
            return false;

        for (int i = 0; i < departments.size(); i++) {
            if (departments.get(i).getCode().equals(code)) {
                departments.remove(i);
                return true;
            }
        }
        return false;
    }

    /**
     * Finds a department by its code.
     *
     * @param code department code
     * @return department if found, otherwise null
     */
    public Department findDepartmentByCode(String code) {
        if (code == null || code.trim().isEmpty())
            return null;

        for (Department d : departments) {
            if (d.getCode().equals(code))
                return d;
        }
        return null;
    }

    /**
     * Updates department data by its code.
     * Empty input values are ignored.
     *
     * @param code department code
     */
    public void updateDepartmentByCode(String code) {
        Department department = findDepartmentByCode(code);
        if (department == null) {
            System.out.println("Помилка: Кафедру з кодом " + code + " не знайдено.");
            return;
        }

        System.out.println("Редагування кафедри: " + department.getName());
        System.out.println("Натисніть Enter, щоб залишити без змін.");

        System.out.print("Новий код (поточний: " + department.getCode() + "): ");
        String newCode = scanner.nextLine();
        if (!newCode.trim().isEmpty())
            department.setCode(newCode);

        System.out.print("Нова назва: ");
        String newName = scanner.nextLine();
        if (!newName.trim().isEmpty())
            department.setName(newName);

        System.out.print("Нова локація: ");
        String newLocation = scanner.nextLine();
        if (!newLocation.trim().isEmpty())
            department.setLocation(newLocation);

        System.out.println("Дані кафедри успішно оновлено.");
    }

    /**
     * Sets the faculty dean.
     *
     * @param dean dean to assign
     */
    public void setDean(Teacher dean) {
        if (dean != null)
            this.dean = dean;
    }

    /**
     * Sets the faculty name.
     *
     * @param newName new faculty name
     */
    public void setName(String newName) {
        if (newName != null && !newName.trim().isEmpty())
            this.name = newName;
    }

    /**
     * Sets the faculty code.
     *
     * @param code new faculty code
     */
    public void setCode(String code) {
        if (code != null && !code.trim().isEmpty())
            this.code = code;
    }

    /**
     * Sets the faculty short name.
     *
     * @param shortName new short name
     */
    public void setShortName(String shortName) {
        if (shortName != null && !shortName.trim().isEmpty())
            this.shortName = shortName;
    }

    /**
     * Sets faculty contact information.
     *
     * @param contacts contact data
     */
    public void setContacts(String contacts) {
        if (contacts != null && !contacts.trim().isEmpty())
            this.contacts = contacts;
    }

    /**
     * Returns the faculty code.
     *
     * @return faculty code
     */
    public String getCode() {
        return code;
    }

    /**
     * Returns the list of departments.
     *
     * @return departments list
     */
    public List<Department> getDepartments() {
        return departments;
    }

    /**
     * Returns the faculty dean.
     *
     * @return dean
     */
    public Teacher getDean() {
        return dean;
    }

    /**
     * Returns the faculty short name.
     *
     * @return short name
     */
    public String getShortName() {
        return shortName;
    }

    /**
     * Returns faculty contact information.
     *
     * @return contacts
     */
    public String getContacts() {
        return contacts;
    }

    /**
     * Returns string representation of the faculty.
     *
     * @return faculty description
     */
    @Override
    public String toString() {
        return "Факультет: " + name + " [" + shortName + "], Код: " + code +
                ", Декан: " + dean + ", Контакти: " + contacts;
    }
}
