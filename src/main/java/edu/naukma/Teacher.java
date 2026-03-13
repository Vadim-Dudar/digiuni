package edu.naukma;

import edu.naukma.exeptions.LogicalDateExeption;

import java.time.LocalDate;
import java.time.temporal.ChronoUnit;

public class Teacher extends Person implements Identifiable {
    private final int teacherId;
    private TeacherPosition position;
    private AcademicDegree degree;
    private AcademicStage stage;
    private final LocalDate dateOfHiring;
    private int rate;
    private Faculty faculty;
    private Department department;

    /**
     * Constructor for Teacher class.
     */
    public Teacher(String name, String surname, String midleName, String dayOfBirth, String phone, String email, int teacherId, TeacherPosition position, AcademicDegree degree, AcademicStage stage, String dateOfHiring, int rate, Faculty faculty, Department department) {
        super(name, surname, midleName, dayOfBirth, phone, email);

        LocalDate parsedDate;
        String normalizedDate = dateOfHiring.trim().replace('.', '-').replace('/', '-');
        try {
            parsedDate = LocalDate.parse(normalizedDate);
        } catch (Exception e) {
            throw new LogicalDateExeption("Given hire date don't match with pattern yyyy-MM-dd");
        }

        if (parsedDate.isBefore(LocalDate.now().minusYears(60)) || parsedDate.isAfter(LocalDate.now()))
            throw new LogicalDateExeption("Given hire date is logically incorrect: it lies to future or more than 60 years in the past");

        if (teacherId < 1) throw new IllegalArgumentException("Teacher ID must be positive.");
        if (rate < 0) throw new IllegalArgumentException("Rate must be non-negative.");

        this.teacherId = teacherId;
        this.position = position;
        this.degree = degree;
        this.stage = stage;
        this.dateOfHiring = parsedDate;
        this.rate = rate;
        this.faculty = faculty;
        this.department = department;
    }

    /**
     * Getters for Teacher ID.
     */
    @Override
    public int getId() {
        return teacherId;
    }

    /**
     * Setters for Teacher rate of salary.
     */
    public void setRate(int rate) {
        if (rate > 0) {
            this.rate = rate;
        } else {
            System.out.println("Помилка: ставка повинна бути більше 0");
        }
    }

    /**
     * Setters for Teacher position.
     */
    public void setPosition(TeacherPosition position) {
        this.position = position;
    }

    /**
     * Getters for Teacher position.
     */
    public TeacherPosition getPosition() {
        return position;
    }

    /**
     * Setters for Teacher academic degree.
     */
    public void setDegree(AcademicDegree degree) {
        this.degree = degree;
    }

    /**
     * Getters for Teacher academic degree.
     */
    public AcademicDegree getDegree() {
        return degree;
    }

    /**
     * Setters for Teacher academic stage.
     */
    public void setStage(AcademicStage stage) {
        this.stage = stage;
    }

    /**
     * Getters for Teacher academic stage.
     */
    public AcademicStage getStage() {
        return stage;
    }

    /**
     * Getters for Teacher department.
     */
    public Department getDepartment() {
        return department;
    }

    /**
     * Setters for Teacher department.
     */
    public void setDepartment(Department department) {
        this.department = department;
    }

    /**
     * Get faculty of student
     *
     * @return current faculty of student
     */
    public Faculty getFaculty() {
        return faculty;
    }

    /**
     * Set faculty of student
     * @param faculty new faculty of student
     */
    public void setFaculty(Faculty faculty) {
        this.faculty = faculty;
    }
    /**
     * Getters for Teacher date of hiring.
     */
    public LocalDate getDateOfHiring() {
        return dateOfHiring;
    }

    /**
     * Calculates the experience of the teacher in years.
     *
     * @return experience in years
     */
    public int getExperience() {
        int year = (int) ChronoUnit.YEARS.between(dateOfHiring, LocalDate.now());
        return year;
    }

    /**
     * Returns string representation of the teacher.
     *
     * @return teacher description
     */
    @Override
    public String toString() {
        return getId() + " Teacher -> " + getFullName() + " | Faculty: " + faculty.getName() +
                " | Department: " + department.getName() + " | Hiring Date: " + dateOfHiring +
                " | Position: " + position + " | Degree: " + degree + " | Stage: " + stage +
                " | Email: " + getEmail() + " | Phone: " + getPhone();
    }
}