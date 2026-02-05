package edu.naukma;

import java.util.ArrayList;
import java.util.List;

public class Department {

    private String code;
    private String name;
    private Faculty faculty;
    private Teacher head;
    private String location;

    private List<Student> students;
    private List<Teacher> teachers;

    public Department(String code, String name, Faculty faculty, String location) {
        validateString(code, "Код кафедри");
        validateString(name, "Назва кафедри");
        validateString(location, "Локація кафедри");

        if (faculty == null)
            throw new IllegalArgumentException("Факультет не може бути null");

        this.code = code;
        this.name = name;
        this.faculty = faculty;
        this.location = location;
        this.students = new ArrayList<>();
        this.teachers = new ArrayList<>();
    }

    public void addStudent(Student student) {
        if (student == null)
            throw new IllegalArgumentException("Студент не може бути null");
        students.add(student);
    }

    public boolean removeStudentById(String id) {
        validateString(id, "ID студента");

        for (int i = 0; i < students.size(); i++) {
            if (students.get(i).getId().equals(id)) {
                students.remove(i);
                return true;
            }
        }
        return false;
    }

    public void addTeacher(Teacher teacher) {
        if (teacher == null)
            throw new IllegalArgumentException("Викладач не може бути null");
        teachers.add(teacher);
    }

    public boolean removeTeacherById(String id) {
        validateString(id, "ID викладача");

        for (int i = 0; i < teachers.size(); i++) {
            if (teachers.get(i).getId().equals(id)) {
                teachers.remove(i);
                return true;
            }
        }
        return false;
    }

    public String getCode() {
        return code;
    }

    public List<Student> getStudents() {
        return students;
    }

    public List<Teacher> getTeachers() {
        return teachers;
    }

    public Teacher getHead() {
        return head;
    }

    public void setHead(Teacher head) {
        if (head == null)
            throw new IllegalArgumentException("Завідувач кафедри не може бути null");
        this.head = head;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setFaculty(Faculty faculty) {
        this.faculty = faculty;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public String getName() {
        return name;
    }

    public Faculty getFaculty() {
        return faculty;
    }

    public String getLocation() {
        return location;
    }

    private void validateString(String value, String fieldName) {
        if (value == null || value.trim().isEmpty())
            throw new IllegalArgumentException(fieldName + " не може бути порожнім");
    }
}

