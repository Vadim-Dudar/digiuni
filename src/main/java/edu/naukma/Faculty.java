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

    public Faculty(String code, String name, String shortName, Teacher dean, String contacts) {
        validateString(code, "Код факультету");
        validateString(name, "Назва факультету");
        validateString(shortName, "Скорочена назва факультету");
        validateString(contacts, "Контакти факультету");

        this.code = code;
        this.name = name;
        this.shortName = shortName;
        this.dean = dean;
        this.contacts = contacts;
        this.departments = new ArrayList<>();
    }

    public void addDepartment(Department department) {
        if (department == null)
            throw new IllegalArgumentException("Назва кафедри не може бути порожньою!");
        departments.add(department);
    }

    public Department createDepartment(University university, Department department) {
        Scanner scanner = new Scanner(System.in);

        System.out.println("Введіть код кафедри: ");
        String code = scanner.nextLine();

        System.out.println("Введіть назву кафедри: ");
        String name = scanner.nextLine();

        System.out.println("Введіть id факультету кафедри: ");
        String facId = scanner.nextLine();
        Faculty faculty = university.findFacultyByCode(facId);

        System.out.println("Введіть id завідувача кафедри: ");
        int headId = scanner.nextInt();
        Teacher head = department.findTeacherById(headId);

        System.out.println("Введіть локацію (корпус/кабінет) кафедри: ");
        String location = scanner.nextLine();

        return new Department(code, name, faculty, head, location);
    }

    public boolean removeDepartmentByCode(String code) {
        validateString(code, "Код кафедри");

        for (int i = 0; i < departments.size(); i++) {
            if (departments.get(i).getCode().equals(code)) {
                departments.remove(i);
                return true;
            }
        }
        return false;
    }

    public Department findDepartmentByCode(String code) {
        validateString(code, "Код кафедри");

        for (Department d : departments) {
            if (d.getCode().equals(code))
                return d;
        }
        return null;
    }

    public void updateDepartmentByCode(String code, String newName, Faculty newFaculty, Teacher newDean, String newLocation) {
        Department department = findDepartmentByCode(code);

        if (department == null)
            throw new IllegalArgumentException();

        if (newName != null && !newName.trim().isEmpty())
            department.setName(newName);

        if (newFaculty != null)
            department.setFaculty(newFaculty);

        if (newDean != null)
            department.setHead(newDean);

        if (newLocation != null && !newLocation.trim().isEmpty())
            department.setLocation(newLocation);

    }


    public String getCode() {
        return code;
    }

    public List<Department> getDepartments() {
        return departments;
    }

    public Teacher getDean() {
        return dean;
    }

    public void setDean(Teacher dean) {
        if (dean == null)
            throw new IllegalArgumentException("Декан не може бути null");
        this.dean = dean;
    }

    private void validateString(String value, String fieldName) {
        if (value == null || value.trim().isEmpty())
            throw new IllegalArgumentException(fieldName + " не може бути порожнім");
    }

    public void setName(String newName) {
        this.name = name;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public void setShortName(String shortName) {
        this.shortName = shortName;
    }

    public void setContacts(String contacts) {
        this.contacts = contacts;
    }

    public String getShortName() {
        return shortName;
    }

    public String getContacts() {
        return contacts;
    }

    @Override
    public String toString() {
        return "Faculty{" +
                "code='" + code + '\'' +
                ", name='" + name + '\'' +
                ", shortName='" + shortName + '\'' +
                ", dean=" + dean +
                ", contacts='" + contacts + '\'' +
                ", departments=" + departments +
                '}';
    }
}
