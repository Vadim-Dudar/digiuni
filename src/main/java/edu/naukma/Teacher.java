package edu.naukma;

public class Teacher extends Person{
    private TeacherPosition position;
    private AcademicDegree degree;
    private AcademicStage stage;
    private final String dateOfHiring;
    private int rate;

    public Teacher(String name, String surname, String midleName, String dayOfBirth, String phone, String email, TeacherPosition position, AcademicDegree degree, AcademicStage stage, String dateOfHiring, int rate) {
        super(name, surname, midleName, dayOfBirth, phone, email);
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
     * Setters for Teacher academic degree.
     */
    public void setDegree(AcademicDegree degree) {
        this.degree = degree;
    }

    /**
     * Setters for Teacher academic stage.
     */
    public void setStage(AcademicStage stage) {
        this.stage = stage;
    }

    @Override
    public String toString() {
        return getId() + " Teacher -> " + getFullName() + dateOfHiring +
                position + degree + stage + getEmail() + getPhone();
    }
}
