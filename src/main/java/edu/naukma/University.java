package edu.naukma;

import java.util.ArrayList;
import java.util.Formattable;
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
     * @param fullName  full name of the university
     * @param shortName short name of the university
     * @param city      city where the university is located
     * @param address   university address
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

    /**
     * Finds the faculty of a given department.
     *
     * @param department department to find the faculty for
     * @return faculty if found, otherwise null
     */
    public Faculty getFaculty(Department department) {
        for (Faculty faculty : faculties) {
            for (Department d : faculty.getDepartments()) if (d.getCode() == department.getCode()) return faculty;
        }

        return null;
    }

    /**
     * Returns the list of departments of the university.
     *
     * @return list of departments
     */
    public List<Department> getDepartments() {
        List<Department> departments = new ArrayList<>();

        for (Faculty faculty : faculties) {
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
    public Department getDepartment(int code) {
        List<Department> departments = getDepartments();

        for (Department department : departments) if (department.getCode() == code) return department;

        return null;
    }

    /**
     * Adds a student to the university.
     *
     * @param student student object to add
     */
    public void addStudent(Student student) {
        students.add(student);
    }

    /**
     * Removes a student by their ID.
     *
     * @param code student ID
     * @return true if the student was removed, false otherwise
     */
    public boolean removeStudent(int code) {
        for (Student student : students) {
            if (student.getId() == code) {
                students.remove(student);
                return true;
            }
        }
        return false;
    }

    /**
     * Finds a student by their ID.
     *
     * @param code student ID
     * @return student if found, otherwise null
     */
    public Student getStudent(int code) {
        for (Student student : students) {
            if (student.getId() == code) return student;
        }

        return null;
    }

    /**
     * Finds a student by their full name.
     *
     * @param fullName student full name
     * @return student if found, otherwise null
     */
    public Student getStudent(String fullName) {
        for (Student student : students) if (student.getFullName().equals(fullName)) return student;

        return null;
    }

    /**
     * Returns the list of students of the university.
     *
     * @return list of students
     */
    public List<Student> getStudents() {
        return List.copyOf(students);
    }

    /**
     * Finds the students of a given faculty.
     *
     * @param faculty faculty to find the students for
     * @return list of students in the faculty
     */
    public List<Student> getStudents(Faculty faculty) {
        List<Student> result = new ArrayList<>();

        for (Student student : students) if (student.getFaculty() == faculty) result.add(student);

        return result;
    }

    /**
     * Finds the students of a given course.
     *
     * @param course course to find the students for
     * @return list of students in the course
     */
    public List<Student> getStudentsByCourse(int course) {
        List<Student> result = new ArrayList<>();

        for (Student student : students) {
            if (student.getCourse() == course) result.add(student);
        }

        return result;
    }

    /**
     * Finds the students of a given group.
     *
     * @param group group to find the students for
     * @return list of students in the group
     */
    public List<Student> getStudentsByGroup(int group) {
        List<Student> result = new ArrayList<>();

        for (Student student : students) {
            if (student.getGroup() == group) result.add(student);
        }

        return result;
    }

    /**
     * Adds a teacher to the university.
     *
     * @param teacher teacher object to add
     */
    public void addTeacher(Teacher teacher) {
        teachers.add(teacher);
    }

    /**
     * Removes a teacher by their ID.
     *
     * @param code teacher ID
     * @return true if the teacher was removed, false otherwise
     */
    public boolean removeTeacher(int code) {
        for (Teacher teacher : teachers) {
            if (teacher.getId() == code) {
                teachers.remove(teacher);
                return true;
            }
        }
        return false;
    }

    /**
     * Finds a teacher by their ID.
     *
     * @param id teacher ID
     * @return teacher if found, otherwise null
     */
    public Teacher getTeacher(int id) {
        for (Teacher teacher : teachers) {
            if (teacher.getTeacherId() == id) return teacher;
        }

        return null;
    }

    /**
     * Returns the list of teachers of the university.
     *
     * @return list of teachers
     */
    public List<Teacher> getTeachers() {
        return List.copyOf(teachers);
    }

    /**
     * Finds the teachers of a given department.
     *
     * @param department department to find the teachers for
     * @return list of teachers in the department
     */
    public List<Teacher> getTeachers(Department department) {
        List<Teacher> result = new ArrayList<Teacher>();

        for (Teacher teacher : teachers) {
            if (teacher.getDepartment() == department) result.add(teacher);
        }

        return result;
    }
}
