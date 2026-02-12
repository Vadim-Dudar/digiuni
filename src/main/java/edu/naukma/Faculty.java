package edu.naukma;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Faculty {

    private final int code;
    private String name;
    private String shortName;
    private Teacher dean;
    private String contacts;

    private List<Department> departments;

    /**
     * Creates a Faculty object and initializes the departments list.
     *
     * @param code      faculty code
     * @param name      full faculty name
     * @param shortName faculty abbreviation
     * @param dean      faculty dean
     * @param contacts  contact information
     */
    public Faculty(int code, String name, String shortName, Teacher dean, String contacts) {
        this.code = code;
        this.name = name;
        this.shortName = shortName;
        this.dean = dean;
        this.contacts = contacts;
        this.departments = new ArrayList<>();
    }

    /**
     * Adds a department to the faculty.
     *
     * @param department department to add
     */
    public void addDepartment(Department department) {
        if (department == null) throw new IllegalArgumentException("Department cannot be null.");
        departments.add(department);
    }

    /**
     * Removes a department by its code.
     *
     * @param code department code
     * @return true if removed, false otherwise
     */
    public boolean removeDepartmentByCode(int code) {
        for (int i = 0; i < departments.size(); i++) {
            if (departments.get(i).getCode() == code) {
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
    public Department findDepartmentByCode(int code) {
        for (Department d : departments) {
            if (d.getCode() == code)
                return d;
        }
        return null;
    }

    /**
     * Sets the faculty dean.
     *
     * @param dean dean to assign
     */
    public void setDean(Teacher dean) {
        if (dean == null) throw new IllegalArgumentException("Dean cannot be null.");
        this.dean = dean;
    }

    /**
     * Sets the faculty name.
     *
     * @param newName new faculty name
     */
    public void setName(String newName) {
        if (name == null || name.trim().isEmpty())
            throw new IllegalArgumentException("Faculty name cannot be null or empty.");
        this.name = newName;
    }

    /**
     * Returns the faculty name.
     *
     * @return faculty name
     */
    public String getName() {
        return name;
    }

    /**
     * Sets the faculty short name.
     *
     * @param shortName new short name
     */
    public void setShortName(String shortName) {
        if (shortName == null || shortName.trim().isEmpty())
            throw new IllegalArgumentException("Faculty short name cannot be null or empty.");
        this.shortName = shortName;
    }

    /**
     * Sets faculty contact information.
     *
     * @param contacts contact data
     */
    public void setContacts(String contacts) {
        if (contacts == null || contacts.trim().isEmpty())
            throw new IllegalArgumentException("Faculty contacts cannot be null or empty.");
        this.contacts = contacts;
    }

    /**
     * Returns the faculty code.
     *
     * @return faculty code
     */
    public int getCode() {
        return code;
    }

    /**
     * Returns the list of departments.
     *
     * @return departments list
     */
    public List<Department> getDepartments() {
        return List.copyOf(departments);
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
