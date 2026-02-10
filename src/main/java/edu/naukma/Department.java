package edu.naukma;


public class Department {

    private final int code;
    private String name;
    private Faculty faculty;
    private Teacher head;
    private String location;

    /**
     * Creates a Department object and initializes student and teacher lists.
     *
     * @param code department code
     * @param name department name
     * @param faculty faculty to which the department belongs
     * @param head head of the department
     * @param location department location
     */
    public Department(int code, String name, Faculty faculty, Teacher head, String location) {
        this.code = code;
        this.name = name;
        this.faculty = faculty;
        this.head = head;
        this.location = location;
    }

    /** Sets the department head. */
    public void setHead(Teacher head) { if (head != null) this.head = head; }

    /** Sets the department name. */
    public void setName(String name) { if (name != null && !name.trim().isEmpty()) this.name = name; }

    /** Sets the faculty of the department. */
    public void setFaculty(Faculty faculty) { if (faculty != null) this.faculty = faculty; }

    /** Sets the department location. */
    public void setLocation(String location) { if (location != null && !location.trim().isEmpty()) this.location = location; }

    /** @return department code */
    public int getCode() { return code; }

    /** @return department name */
    public String getName() { return name; }

    /** @return faculty */
    public Faculty getFaculty() { return faculty; }

    /** @return department head */
    public Teacher getHead() { return head; }

    /** @return department location */
    public String getLocation() { return location; }

    /**
     * Returns string representation of the department.
     *
     * @return department description
     */
    @Override
    public String toString() {
        return "Department{" +
                "code='" + code + '\'' +
                ", name='" + name + '\'' +
                ", faculty=" + faculty +
                ", head=" + head +
                ", location='" + location + '\'' +
                '}';
    }
}
