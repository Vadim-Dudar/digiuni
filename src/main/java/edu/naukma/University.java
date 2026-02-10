package edu.naukma;

import java.util.ArrayList;
import java.util.List;

public class University {

    private String fullName;
    private String shortName;
    private String city;
    private String address;
    private List<Faculty> faculties;
    private List<Student> students;
    private List<Teacher> teachers;

    /**
     * Creates a University object and initializes the faculties list.
     *
     * @param fullName full name of the university
     * @param shortName short name of the university
     * @param city city where the university is located
     * @param address university address
     */
    public University(String fullName, String shortName, String city, String address) {
        this.fullName = fullName;
        this.shortName = shortName;
        this.city = city;
        this.address = address;
        this.faculties = new ArrayList<>();
        this.students = new ArrayList<>();
        this.teachers = new ArrayList<>();
    }

    /**
     * Adds a faculty to the university.
     *
     * @param faculty faculty object to add
     */
    public void addFaculty(Faculty faculty) {
        if (faculty != null)
            faculties.add(faculty);
        else
            System.out.println("Помилка: неможливо додати порожній об'єкт факультету.");
    }

    /**
     * Removes a faculty by its code.
     *
     * @param code faculty code
     * @return true if the faculty was removed, false otherwise
     */
    public boolean removeFacultyByCode(int code) {
        for (int i = 0; i < faculties.size(); i++) {
            if (faculties.get(i).getCode() == code) {
                faculties.remove(i);
                return true;
            }
        }
        return false;
    }

    /**
     * Finds a faculty by its code.
     *
     * @param code faculty code
     * @return faculty if found, otherwise null
     */
    public Faculty getFaculty(int code) {
        for (Faculty f : faculties) {
            if (f.getCode() == code)
                return f;
        }
        return null;
    }

    /**
     * Returns the list of faculties of the university.
     *
     * @return list of faculties
     */
    public List<Faculty> getFaculties() {
        return List.copyOf(faculties);
    }

    public Faculty getFaculty(Department department) {
        for (Faculty faculty: faculties) {
            for (Department d: faculty.getDepartments()) if (d.getCode() == department.getCode()) return faculty;
        }

        return null;
    }

    public List<Department> getDepartments() {
        List<Department> departments = new ArrayList<>();

        for (Faculty faculty: faculties) {
            departments.addAll(faculty.getDepartments());
        }

        return departments;
    }

    public Department getDepartment(int code) {
        List<Department> departments = getDepartments();

        for (Department department: departments) if (department.getCode() == code) return department;

        return null;
    }

    public void addStudent(Student student) {
        students.add(student);
    }

    public boolean removeStudent(int code) {
        for (Student student: students) {
            if (student.getId() == code) {
                students.remove(student);
                return true;
            }
        }
        return false;
    }

    public Student getStudent(int code) {
        for (Student student: students){
            if (student.getId() == code) return student;
        }

        return null;
    }

    public List<Student> getStudents() {
        return List.copyOf(students);
    }

    public void addTeacher(Teacher teacher){
        teachers.add(teacher);
    }

    public boolean removeTeacher(int code) {
        for (Teacher teacher: teachers) {
            if (teacher.getId() == code) {
                teachers.remove(teacher);
                return true;
            }
        }
        return false;
    }

    public Teacher getTeacher(int id) {
        for (Teacher teacher: teachers) {
            if (teacher.getTeacherId() == id) return teacher;
        }

        return null;
    }

    public List<Teacher> getTeachers() {
        return List.copyOf(teachers);
    }
}
