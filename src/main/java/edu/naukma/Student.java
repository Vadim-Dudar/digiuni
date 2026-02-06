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

    @Override
    public String toString() {
        return "Student " + studentId + getFullName() + group + yearOfEntry +
                " studies at course " + course + " on a " + studyForm +
                " at status " + status + getPhone() + getEmail();
    }

    /**
     * Move student to the next year of study.
     * If the student is already in the 6th year, an exception is thrown.
     */
    public void moveToNextYear(){
        if (course < 6) course++;
        else throw new IllegalArgumentException("Max course is 6th");
    }

    /**
     * Change student group.
     * @param group New group for the student.
     */
    public void changeGroup(Group group){
        this.group = group;
    }

    /**
     * Change student status.
     * @param status New status for the student.
     */
    public void changeStatus(StudentStatus status){
        this.status = status;
    }

    /**
     * Change student study form.
     * @param studyForm New study form for the student.
     */
    public void changeStudyForm(StudyForm studyForm){
        this.studyForm = studyForm;
    }
}
