package edu.naukma;

public class Teacher extends Person {
    private final int teacherId;
    private TeacherPosition position;
    private AcademicDegree degree;
    private AcademicStage stage;
    private final String dateOfHiring;
    private int rate;
    private Department department;

    /**
     * Constructor for Teacher class.
     */
    public Teacher(String name, String surname, String midleName, String dayOfBirth, String phone, String email, int teacherId, TeacherPosition position, AcademicDegree degree, AcademicStage stage, String dateOfHiring, int rate, Department department) {
        super(name, surname, midleName, dayOfBirth, phone, email);
        this.teacherId = teacherId;
        this.position = position;
        this.degree = degree;
        this.stage = stage;
        this.dateOfHiring = dateOfHiring;
        if (rate > 0) {
            this.rate = rate;
        } else {
            this.rate = 10;
        }
        this.department = department;
    }

    public int getTeacherId() {
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
     * Returns string representation of the teacher.
     *
     * @return teacher description
     */
    @Override
    public String toString() {
        return getTeacherId() + " Teacher -> " + getFullName() + " | Department: " + department.getName() + " | Hiring Date: " + dateOfHiring +
                " | Position: " + position + " | Degree: " + degree + " | Stage: " + stage +
                " | Email: " + getEmail() + " | Phone: " + getPhone();
    }
}