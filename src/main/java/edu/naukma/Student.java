package edu.naukma;

public class Student extends Person {

    private final int studentId;
    private Faculty faculty;
    private int course;
    private int group;
    private final int yearOfEntry;
    private StudyForm studyForm;
    private StudentStatus status;

    public Student(String name, String surname, String midleName, String dayOfBirth, String phone, String email, int studentId, int course, Faculty faculty, int group, int yearOfEntry, StudyForm studyForm, StudentStatus status) {
        super(name, surname, midleName, dayOfBirth, phone, email);
        this.studentId = studentId;
        this.group = group;
        this.studyForm = studyForm;
        this.status = status;
        this.yearOfEntry = yearOfEntry;
        this.course = course;
        this.faculty = faculty;
    }

    @Override
    public String toString() {
        return "Student: " + studentId + " " + getFullName() + " " + group + " " + yearOfEntry + " " +
                " studies at course " + course + " on a " + studyForm +
                " at status " + status + " " + getPhone() + " " + getEmail();
    }

    /**
     * Move student to the next year of study.
     */
    public void moveToNextYear(){
        course++;
    }

    /**
     * Change student group.
     * @param group New group for the student.
     */
    public void changeGroup(int group){
        this.group = group;
    }

    /**
     * Get student group.
     * @return Current group of the student.
     */
    public int getGroup() {
        return group;
    }

    public void setGroup(int group) {
        this.group = group;
    }

    public int getId() {
        return this.studentId;
    }

    /**
     * Change student status.
     * @param status New status for the student.
     */
    public void changeStatus(StudentStatus status){
        this.status = status;
    }

    /**
     * Get student study form.
     * @return Current study form of the student.
     */
    public StudyForm getStudyForm() {
        return studyForm;
    }

    public void setStudyForm(StudyForm studyForm) {
        this.studyForm = studyForm;
    }

    /**
     * Change student study form.
     * @param studyForm New study form for the student.
     */
    public void changeStudyForm(StudyForm studyForm){
        this.studyForm = studyForm;
    }

    /**
     * Get student status.
     * @return Current status of the student.
     */
    public StudentStatus getStatus() {
        return status;
    }

    public void setStatus(StudentStatus status) {
        this.status = status;
    }

    /**
     * Get year of entry.
     * @return Year when the student entered the university.
     */
    public int getYearOfEntry() {
        return yearOfEntry;
    }

    /**
     * Get current course of the student.
     * @return Current course of the student.
     */
    public int getCourse() {
        return course;
    }

    public void setCourse(int course) {
        this.course = course;
    }

    public void setFaculty(Faculty faculty) {
        this.faculty = faculty;
    }

    public Faculty getFaculty() {
        return faculty;
    }

}
