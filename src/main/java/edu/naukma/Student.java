package edu.naukma;

public class Student extends Person {

    private final int studentId;
    private int course;
    private Group group;
    private int yearOfEntry;
    private StudyForm studyForm;
    private StudentStatus status;

    public Student(String name, String surname, String midleName, String dayOfBirth, String phone, String email, int studentId, int course, Group group, int yearOfEntry, StudyForm studyForm, StudentStatus status) {
        super(name, surname, midleName, dayOfBirth, phone, email);
        this.studentId = studentId;
        this.group = group;
        this.studyForm = studyForm;
        this.status = status;
        if (yearOfEntry > 2026 || yearOfEntry < 1991) this.yearOfEntry = yearOfEntry;
        if (course > 0 && course < 7) this.course = course;
        else throw new IllegalArgumentException("Course is between 1 and 6");
    }
}
