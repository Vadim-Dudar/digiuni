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

    public Faculty(String code, String name, String shortName, Teacher dean, String contacts) {
        this.code = code;
        this.name = name;
        this.shortName = shortName;
        this.dean = dean;
        this.contacts = contacts;
        this.departments = new ArrayList<>();
    }

    private String readRequiredString(String prompt) {
        while (true) {
            System.out.print(prompt);
            String input = scanner.nextLine();
            if (input != null && !input.trim().isEmpty()) {
                return input;
            }
            System.out.println("Помилка: це поле обов'язкове для заповнення.");
        }
    }

    public void addDepartment(Department department) {
        if (department != null) {
            departments.add(department);
        } else {
            System.out.println("Помилка: неможливо додати порожню кафедру.");
        }
    }

    public Department createDepartment(University university) {
        System.out.println("--- Створення нової кафедри ---");

        String code = readRequiredString("Введіть код кафедри: ");
        String name = readRequiredString("Введіть назву кафедри: ");
        String location = readRequiredString("Введіть локацію (корпус/кабінет) кафедри: ");

        return new Department(code, name, this, null, location);
    }

    public boolean removeDepartmentByCode(String code) {
        if (code == null || code.trim().isEmpty()) return false;

        for (int i = 0; i < departments.size(); i++) {
            if (departments.get(i).getCode().equals(code)) {
                departments.remove(i);
                return true;
            }
        }
        return false;
    }

    public Department findDepartmentByCode(String code) {
        if (code == null || code.trim().isEmpty()) return null;

        for (Department d : departments) {
            if (d.getCode().equals(code))
                return d;
        }
        return null;
    }

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

    public void setDean(Teacher dean) {
        if (dean != null) {
            this.dean = dean;
        }
    }

    public void setName(String newName) {
        if (newName != null && !newName.trim().isEmpty()) this.name = newName;
    }

    public void setCode(String code) {
        if (code != null && !code.trim().isEmpty()) this.code = code;
    }

    public void setShortName(String shortName) {
        if (shortName != null && !shortName.trim().isEmpty()) this.shortName = shortName;
    }

    public void setContacts(String contacts) {
        if (contacts != null && !contacts.trim().isEmpty()) this.contacts = contacts;
    }

    public String getCode() { return code; }
    public List<Department> getDepartments() { return departments; }
    public Teacher getDean() { return dean; }
    public String getShortName() { return shortName; }
    public String getContacts() { return contacts; }

    @Override
    public String toString() {
        return "Факультет: " + name + " [" + shortName + "], Код: " + code + ", Декан: " + dean;
    }
}