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

        if (studentId < 1) throw new IllegalArgumentException("Student ID must be positive.");
        if (course < 1 || course > 6) throw new IllegalArgumentException("Course must be positive and less than or equal to 6.");
        if (group < 1) throw new IllegalArgumentException("Group number must be positive.");

        this.studentId = studentId;
        this.group = group;
        this.studyForm = studyForm;
        this.status = status;
        this.yearOfEntry = yearOfEntry;
        this.course = course;
        this.faculty = faculty;
    }

    /**
     * Returns string representation of the student.
     *
     * @return student description
     */
    @Override
    public String toString() {
        return "Student: " + studentId + " " + getFullName() + " " + group + " " + yearOfEntry + " " +
                " studies at course " + course + " on a " + studyForm +
                " at status " + status + " " + getPhone() + " " + getEmail();
    }

    /**
     * Move student to the next year of study.
     */
    public void moveToNextYear() {
        course++;
    }

    /**
     * Get student group.
     *
     * @return Current group of the student.
     */
    public int getGroup() {
        return group;
    }

    public void setGroup(int group) {
        if (group < 1) throw new IllegalArgumentException("Group number must be positive.");

        this.group = group;
    }

    /**
     * Get student ID.
     *
     * @return Student ID.
     */
    public int getId() {
        return this.studentId;
    }

    /**
     * Get student study form.
     *
     * @return Current study form of the student.
     */
    public StudyForm getStudyForm() {
        return studyForm;
    }

    public void setStudyForm(StudyForm studyForm) {
        this.studyForm = studyForm;
    }

    /**
     * Get student status.
     *
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
     *
     * @return Year when the student entered the university.
     */
    public int getYearOfEntry() {
        return yearOfEntry;
    }

    /**
     * Get current course of the student.
     *
     * @return Current course of the student.
     */
    public int getCourse() {
        return course;
    }

    /**
     * Set current course of the student.
     *
     * @param course New course for the student.
     */
    public void setCourse(int course) {
        this.course = course;
    }

    /**
     * Set student faculty.
     *
     * @param faculty New faculty for the student.
     */
    public void setFaculty(Faculty faculty) {
        this.faculty = faculty;
    }

    /**
     * Get student faculty.
     *
     * @return Current faculty of the student.
     */
    public Faculty getFaculty() {
        return faculty;
    }

}
