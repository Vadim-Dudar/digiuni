package edu.naukma;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Department {

    private String code;
    private String name;
    private Faculty faculty;
    private Teacher head;
    private String location;

    private List<Student> students;
    private List<Teacher> teachers;
    private Scanner scanner = new Scanner(System.in);

    /**
     * Creates a Department object and initializes student and teacher lists.
     *
     * @param code department code
     * @param name department name
     * @param faculty faculty to which the department belongs
     * @param head head of the department
     * @param location department location
     */
    public Department(String code, String name, Faculty faculty, Teacher head, String location) {
        this.code = code;
        this.name = name;
        this.faculty = faculty;
        this.head = head;
        this.location = location;
        this.students = new ArrayList<>();
        this.teachers = new ArrayList<>();
    }

    /**
     * Reads a required non-empty string from console input.
     *
     * @param prompt message shown to the user
     * @return non-empty input string
     */
    private String readRequiredString(String prompt) {
        while (true) {
            System.out.print(prompt);
            String input = scanner.nextLine();
            if (input != null && !input.trim().isEmpty())
                return input;
            System.out.println("Помилка: поле не може бути порожнім.");
        }
    }

    /**
     * Safely reads an integer value from console input.
     * Repeats input until a valid integer is entered.
     *
     * @param prompt message shown to the user
     * @return integer value
     */
    private int readSafeInt(String prompt) {
        while (true) {
            System.out.print(prompt);
            try {
                return Integer.parseInt(scanner.nextLine());
            } catch (NumberFormatException e) {
                System.out.println("Помилка: введіть ціле число.");
            }
        }
    }

    /**
     * Reads an integer value that must be within the specified range.
     *
     * @param prompt message shown to the user
     * @param min minimum allowed value
     * @param max maximum allowed value
     * @return integer value within range
     */
    private int readIntInRange(String prompt, int min, int max) {
        while (true) {
            int val = readSafeInt(prompt);
            if (val >= min && max >= val)
                return val;
            System.out.println("Помилка: введіть число від " + min + " до " + max + ".");
        }
    }

    /**
     * Allows the user to choose a value from an enum.
     *
     * @param values enum values
     * @param message message shown to the user
     * @param <T> enum type
     * @return selected enum value
     */
    private <T extends Enum<T>> T chooseFromEnum(T[] values, String message) {
        while (true) {
            try {
                System.out.println(message);
                for (int i = 0; i < values.length; i++)
                    System.out.println((i + 1) + ". " + values[i]);

                int choice = Integer.parseInt(scanner.nextLine());
                return values[choice - 1];
            } catch (Exception e) {
                System.out.println("Помилка: оберіть номер зі списку.");
            }
        }
    }

    /**
     * Creates a new Student object using user input.
     *
     * @return created Student object
     */
    public Student createStudent() {
        System.out.println("--- Реєстрація студента ---");
        String name = readRequiredString("Ім'я: ");
        String surname = readRequiredString("Прізвище: ");
        String middleName = readRequiredString("По-батькові: ");
        String dob = readRequiredString("День народження (ДД.ММ.РРРР): ");
        String phone = readRequiredString("Телефон: ");
        String email = readRequiredString("Email: ");

        int stId = readSafeInt("ID студента: ");
        int course = readIntInRange("Курс (1-6): ", 1, 6);
        int year = readIntInRange("Рік вступу (1991-2026): ", 1991, 2026);

        Group group = chooseFromEnum(Group.values(), "Оберіть групу:");
        StudyForm sf = chooseFromEnum(StudyForm.values(), "Оберіть форму навчання:");
        StudentStatus ss = chooseFromEnum(StudentStatus.values(), "Оберіть статус:");

        return new Student(name, surname, middleName, dob, phone, email, stId, course, group, year, sf, ss);
    }

    /**
     * Creates a new Teacher object using user input.
     *
     * @return created Teacher object
     */
    public Teacher createTeacher() {
        System.out.println("--- Реєстрація викладача ---");
        String name = readRequiredString("Ім'я: ");
        String surname = readRequiredString("Прізвище: ");
        String middleName = readRequiredString("По-батькові: ");
        String dob = readRequiredString("День народження: ");
        String phone = readRequiredString("Телефон: ");
        String email = readRequiredString("Email: ");

        int tId = readSafeInt("ID викладача: ");

        TeacherPosition tp = chooseFromEnum(TeacherPosition.values(), "Посада:");
        AcademicDegree ad = chooseFromEnum(AcademicDegree.values(), "Ступінь:");
        AcademicStage as = chooseFromEnum(AcademicStage.values(), "Рівень:");

        String dateHired = readRequiredString("Дата прийняття: ");
        int rate = readSafeInt("Ставка: ");

        return new Teacher(name, surname, middleName, dob, phone, email, tId, tp, ad, as, dateHired, rate);
    }

    /**
     * Updates a student's data by student ID.
     *
     * @param id student ID
     */
    public void updateStudentById(int id) {
        Student s = null;
        for (Student student : students) {
            if (student.getId() == id) {
                s = student;
                break;
            }
        }

        if (s == null) {
            System.out.println("Студента з ID " + id + " не знайдено.");
            return;
        }

        System.out.println("Редагування: " + s.getSurname() + ". (Enter щоб пропустити)");

        System.out.print("Нове прізвище: ");
        String sn = scanner.nextLine();
        if (!sn.trim().isEmpty())
            s.setSurname(sn);

        System.out.print("Перевести на наступний курс? (т/н): ");
        if (scanner.nextLine().equalsIgnoreCase("т"))
            s.moveToNextYear();

        System.out.print("Змінити групу? (т/н): ");
        if (scanner.nextLine().equalsIgnoreCase("т"))
            s.changeGroup(chooseFromEnum(Group.values(), "Оберіть нову групу:"));
    }

    /**
     * Updates a teacher's data by teacher ID.
     *
     * @param id teacher ID
     */
    public void updateTeacherById(int id) {
        Teacher t = findTeacherById(id);
        if (t == null) {
            System.out.println("Викладача не знайдено.");
            return;
        }

        System.out.print("Нове прізвище: ");
        String sn = scanner.nextLine();
        if (!sn.trim().isEmpty()) t.setSurname(sn);

        System.out.print("Нова ставка: ");
        String rateStr = scanner.nextLine();
        if (!rateStr.trim().isEmpty()) {
            try {
                t.setRate(Integer.parseInt(rateStr));
            } catch (NumberFormatException e) {
                System.out.println("Некоректний формат числа, ставку не змінено.");
            }
        }
    }

    /**
     * Adds a student to the department.
     *
     * @param s student to add
     */
    public void addStudent(Student s) {
        if (s != null) students.add(s);
    }

    /**
     * Adds a teacher to the department.
     *
     * @param t teacher to add
     */
    public void addTeacher(Teacher t) {
        if (t != null) teachers.add(t);
    }

    /**
     * Removes a student by ID.
     *
     * @param id student ID
     * @return true if removed, false otherwise
     */
    public boolean removeStudentById(int id) {
        return students.removeIf(s -> s.getId() == id);
    }

    /**
     * Removes a teacher by ID.
     *
     * @param id teacher ID
     * @return true if removed, false otherwise
     */
    public boolean removeTeacherById(int id) {
        return teachers.removeIf(t -> t.getId() == id);
    }

    /**
     * Finds a teacher by ID.
     *
     * @param id teacher ID
     * @return teacher if found, otherwise null
     */
    public Teacher findTeacherById(int id) {
        for (Teacher t : teachers)
            if (t.getId() == id) return t;
        return null;
    }

    /** Sets the department head. */
    public void setHead(Teacher head) { if (head != null) this.head = head; }

    /** Sets the department code. */
    public void setCode(String code) { if (code != null && !code.trim().isEmpty()) this.code = code; }

    /** Sets the department name. */
    public void setName(String name) { if (name != null && !name.trim().isEmpty()) this.name = name; }

    /** Sets the faculty of the department. */
    public void setFaculty(Faculty faculty) { if (faculty != null) this.faculty = faculty; }

    /** Sets the department location. */
    public void setLocation(String location) { if (location != null && !location.trim().isEmpty()) this.location = location; }

    /** @return department code */
    public String getCode() { return code; }

    /** @return department name */
    public String getName() { return name; }

    /** @return list of students */
    public List<Student> getStudents() { return students; }

    /** @return list of teachers */
    public List<Teacher> getTeachers() { return teachers; }

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
