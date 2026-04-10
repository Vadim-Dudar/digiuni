package edu.naukma.domain;

import edu.naukma.repository.Repository;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.Optional;

public class University implements Serializable {

    private String fullName;
    private String shortName;
    private String city;
    private String address;
    private final Repository<Faculty> faculties;
    private final Repository<Student> students;
    private final Repository<Teacher> teachers;

    /**
     * Creates a University object and initializes the faculties list.
     *
     * @param fullName  full name of the university
     * @param shortName short name of the university
     * @param city      city where the university is located
     * @param address   university address
     */
    public University(String fullName, String shortName, String city, String address) {
        if (fullName == null || fullName.trim().isEmpty()
                || shortName == null || shortName.trim().isEmpty() ||
                city == null || city.trim().isEmpty()
                || address == null || address.trim().isEmpty())
            throw new IllegalArgumentException("University full name cannot be null or empty.");

        this.fullName = fullName;
        this.shortName = shortName;
        this.city = city;
        this.address = address;
        this.faculties = new Repository<>();
        this.students = new Repository<>();
        this.teachers = new Repository<>();
    }

    /**
     * Adds a faculty to the university.
     *
     * @param faculty faculty object to add
     */
    public void addFaculty(Faculty faculty) {
        if (faculty == null) throw new IllegalArgumentException("Faculty cannot be null.");
        faculties.addItem(faculty);
    }

    /**
     * Removes a faculty by its code.
     *
     * @param code faculty code
     * @return true if the faculty was removed, false otherwise
     */
    public boolean removeFacultyByCode(int code) {
        return faculties.remove(code);
    }

    /**
     * Finds a faculty by its code.
     *
     * @param code faculty code
     * @return faculty if found, otherwise null
     */
    public Optional<Faculty> getFaculty(int code) {
        return faculties.getById(code);
    }

    /**
     * Returns the list of faculties of the university.
     *
     * @return list of faculties
     */
    public List<Faculty> getFaculties() {
        return faculties.getAll();
    }

    /**
     * Finds the faculty of a given department.
     *
     * @param department department to find the faculty for
     * @return faculty if found, otherwise null
     */
    public Optional<Faculty> getFaculty(Department department) {
        List<Faculty> facultyList = faculties.findBy(faculty -> faculty.getDepartments().contains(department));

        if (facultyList.isEmpty())
            return Optional.empty();
        return Optional.of(facultyList.get(0));
    }

    /**
     * Returns the list of departments of the university.
     *
     * @return list of departments
     */
    public List<Department> getDepartments() {
        List<Department> departments = new ArrayList<>();

        for (Faculty faculty : faculties.getAll()) {
            departments.addAll(faculty.getDepartments());
        }

        return departments;
    }

    /**
     * Finds a department by its code.
     *
     * @param code department code
     * @return department if found, otherwise null
     */
    public Optional<Department> getDepartment(int code) {
        for (Department department : getDepartments()) {
            if (department.getId() == code)
                return Optional.of(department);
        }

        return Optional.empty();
    }

    /**
     * Adds a student to the university.
     *
     * @param student student object to add
     */
    public void addStudent(Student student) {
        students.addItem(student);
    }

    /**
     * Removes a student by their ID.
     *
     * @param id student ID
     * @return true if the student was removed, false otherwise
     */
    public boolean removeStudent(int id) {
        return students.remove(id);
    }

    /**
     * Finds a student by their ID.
     *
     * @param id student ID
     * @return student if found, otherwise null
     */
    public Optional<Student> getStudent(int id) {
        return students.getById(id);
    }

    /**
     * Finds a student by their full name.
     *
     * @param fullName student full name
     * @return student if found, otherwise null
     */
    public Optional<Student> getStudent(String fullName) {
        List<Student> studentList = students.findBy(student -> student.getFullName().equals(fullName));

        if (studentList.isEmpty())
            return Optional.empty();
        return Optional.of(studentList.get(0));
    }

    /**
     * Returns the list of students of the university.
     *
     * @return list of students
     */
    public List<Student> getStudents() {
        return students.getAll();
    }

    public Repository<Student> getStudentRepository() {
        return students;
    }

    /**
     * Finds the students of a given faculty.
     *
     * @param faculty faculty to find the students for
     * @return list of students in the faculty
     */
    public List<Student> getStudents(Faculty faculty) {
        return students.findBy(student -> student.getFaculty() == faculty);
    }

    /**
     * Finds the students of a given course.
     *
     * @param course course to find the students for
     * @return list of students in the course
     */
    public List<Student> getStudentsByCourse(int course) {
        return students.findBy(student -> student.getCourse() == course);
    }

    /**
     * Finds the students of a given group.
     *
     * @param group group to find the students for
     * @return list of students in the group
     */
    public List<Student> getStudentsByGroup(int group) {
        return students.findBy(student -> student.getGroup() == group);
    }

    /**
     * Adds a teacher to the university.
     *
     * @param teacher teacher object to add
     */
    public void addTeacher(Teacher teacher) {
        teachers.addItem(teacher);
    }

    /**
     * Removes a teacher by their ID.
     *
     * @param id teacher ID
     * @return true if the teacher was removed, false otherwise
     */
    public boolean removeTeacher(int id) {
        return teachers.remove(id);
    }

    /**
     * Finds a teacher by their ID.
     *
     * @param id teacher ID
     * @return teacher if found, otherwise null
     */
    public Optional<Teacher> getTeacher(int id) {
        return teachers.getById(id);
    }

    /**
     * Returns the list of teachers of the university.
     *
     * @return list of teachers
     */
    public List<Teacher> getTeachers() {
        return teachers.getAll();
    }

    /**
     * Finds the teachers of a given department.
     *
     * @param department department to find the teachers for
     * @return list of teachers in the department
     */
    public List<Teacher> getTeachers(Department department) {
        return teachers.findBy(teacher -> teacher.getDepartment() == department);
    }
    /**
     * Sets the full name of the university.
     *
     * @param fullName new full name of the university
     */
    public void setFullName(String fullName) {
        this.fullName = fullName;
    }

    /**
     * Sets the short name of the university.
     *
     * @param shortName new short name
     */
    public void setShortName(String shortName) {
        this.shortName = shortName;
    }

    /**
     * Sets the city where the university is located.
     *
     * @param city city name
     */
    public void setCity(String city) {
        this.city = city;
    }

    /**
     * Sets the address of the university.
     *
     * @param address university address
     */
    public void setAddress(String address) {
        this.address = address;
    }

    /**
     * Returns the full name of the university.
     *
     * @return full name
     */
    public String getFullName() {
        return fullName;
    }

    /**
     * Returns the short name of the university.
     *
     * @return short name
     */
    public String getShortName() {
        return shortName;
    }

    /**
     * Returns the city where the university is located.
     *
     * @return city name
     */
    public String getCity() {
        return city;
    }

    /**
     * Returns the address of the university.
     *
     * @return university address
     */
    public String getAddress() {
        return address;
    }

    @Override
    public boolean equals(Object o) {
        if (o == null || getClass() != o.getClass()) return false;
        University that = (University) o;
        return Objects.equals(fullName, that.fullName) && Objects.equals(shortName, that.shortName) && Objects.equals(city, that.city) && Objects.equals(address, that.address);
    }

    @Override
    public int hashCode() {
        return Objects.hash(fullName, shortName, city, address);
    }

    @Override
    public String toString() {
        return "University{" +
                "fullName='" + fullName + '\'' +
                ", shortName='" + shortName + '\'' +
                ", city='" + city + '\'' +
                ", address='" + address + '\'' +
                '}';
    }
}