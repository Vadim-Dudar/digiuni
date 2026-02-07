package edu.naukma;

public class Teacher extends Person{
    private final int teacherId;
    private TeacherPosition position;
    private AcademicDegree degree;
    private AcademicStage stage;
    private final String dateOfHiring;
    private int rate;

    /**
     * Constructor for Teacher class.
     */
    public Teacher(String name, String surname, String midleName, String dayOfBirth, String phone, String email, int teacherId, TeacherPosition position, AcademicDegree degree, AcademicStage stage, String dateOfHiring, int rate) {
        super(name, surname, midleName, dayOfBirth, phone, email);
        this.teacherId = teacherId;
        this.position = position;
        this.degree = degree;
        this.stage = stage;
        this.dateOfHiring = dateOfHiring;
        if (rate > 0) this.rate = rate;
        else throw new IllegalArgumentException("Rate can't be less than zero!");
    }

    /**
     * Setters for Teacher rate of salary.
     */
    public void setRate(int rate) {
        if (rate > 0) this.rate = rate;
        else throw new IllegalArgumentException("Rate can't be less than zero!");
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

    @Override
    public String toString() {
        return getId() + " Teacher -> " + getFullName() + dateOfHiring +
                position + degree + stage + getEmail() + getPhone();
    }
}
