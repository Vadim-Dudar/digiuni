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
    Scanner scanner = new Scanner(System.in);

    public Department(String code, String name, Faculty faculty, Teacher head, String location) {
        validateString(code, "Код кафедри");
        validateString(name, "Назва кафедри");
        validateString(location, "Локація кафедри");

        if (faculty == null)
            throw new IllegalArgumentException("Факультет не може бути null");

        this.code = code;
        this.name = name;
        this.faculty = faculty;
        this.head = head;
        this.location = location;
        this.students = new ArrayList<>();
        this.teachers = new ArrayList<>();
    }

    public void addStudent(Student student) {
        if (student == null)
            throw new IllegalArgumentException("Студент не може бути null");
        students.add(student);
    }

    public Student createStudent() {
        System.out.println("Введіть ім'я студента: ");
        String name = scanner.nextLine();

        System.out.println("Введіть прізвище студента: ");
        String surname = scanner.nextLine();

        System.out.println("Введіть по-батькові студента: ");
        String middleName = scanner.nextLine();

        System.out.println("Введіть день народження студента: ");
        String dayOfBirth = scanner.nextLine();

        System.out.println("Введіть номер телефону студента: ");
        String phone = scanner.nextLine();

        System.out.println("Введіть електронну пошту студента: ");
        String email = scanner.nextLine();

        System.out.println("Введіть id студента: ");
        int stId = scanner.nextInt();

        System.out.println("Введіть курс студента: ");
        int course = scanner.nextInt();

        System.out.println("Оберіть групу студента:");
        Group[] groups = Group.values(); // Отримуємо масив усіх значень енаму
        for (int i = 0; i < groups.length; i++)
            System.out.println(i + 1 + ". " + groups[i]);

        int choice = scanner.nextInt();
        Group group = groups[choice - 1]; // Отримуємо групу за індексом

        System.out.println("Введіть рік вступу в університет студента: ");
        int yearOfEntry = scanner.nextInt();

        StudyForm sf = chooseFromEnum(StudyForm.values(), "Оберіть форму навчання:");
        StudentStatus ss = chooseFromEnum(StudentStatus.values(), "Оберіть статус студента:");

        return new Student(name, surname, middleName, dayOfBirth, phone, email, stId, course, group, yearOfEntry, sf, ss);
    }

    public boolean removeStudentById(int id) {
        for (int i = 0; i < students.size(); i++) {
            if (students.get(i).getId() == id) {
                students.remove(i);
                return true;
            }
        }
        return false;
    }

    public void addTeacher(Teacher teacher) {
        if (teacher == null)
            throw new IllegalArgumentException("Викладач не може бути null");
        teachers.add(teacher);
    }

    public Teacher createTeacher() {
        System.out.println("Введіть ім'я: ");
        String name = scanner.nextLine();

        System.out.println("Введіть прізвище: ");
        String surname = scanner.nextLine();

        System.out.println("Введіть по-батькові: ");
        String middleName = scanner.nextLine();

        System.out.println("Введіть день народження: ");
        String dayOfBirth = scanner.nextLine();

        System.out.println("Введіть номер телефону: ");
        String phone = scanner.nextLine();

        System.out.println("Введіть електронну пошту: ");
        String email = scanner.nextLine();

        System.out.println("Введіть id вчителя: ");
        int tId = scanner.nextInt();

        TeacherPosition tp = chooseFromEnum(TeacherPosition.values(), "Оберіть призначення викладача: ");
        AcademicDegree ad = chooseFromEnum(AcademicDegree.values(), "оберіть ступінь викладача: ");
        AcademicStage as = chooseFromEnum(AcademicStage.values(), "Оберіть академічний рівень викладача: ");

        System.out.println("Введіть дату прийняття вчителя на посаду: ");
        String dateOfHiring = scanner.nextLine();

        System.out.println("Введіть cтавку/навантаження вчителя: ");
        int rate = scanner.nextInt();

        return new Teacher(name, surname, middleName, dayOfBirth, phone, email, tId, tp, ad, as, dateOfHiring, rate);
    }

    public boolean removeTeacherById(int id) {
        for (int i = 0; i < teachers.size(); i++) {
            if (teachers.get(i).getId() == id) {
                teachers.remove(i);
                return true;
            }
        }
        return false;
    }

    public Teacher findTeacherById(int id) {
        for (int i = 0; i < teachers.size(); i++) {
            if (teachers.get(i).getId() == id)
                return teachers.get(i);
        }
        return null;
    }

    public String getCode() {
        return code;
    }

    public List<Student> getStudents() {
        return students;
    }

    public List<Teacher> getTeachers() {
        return teachers;
    }

    public Teacher getHead() {
        return head;
    }

    public void setHead(Teacher head) {
        if (head == null)
            throw new IllegalArgumentException("Завідувач кафедри не може бути null");
        this.head = head;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setFaculty(Faculty faculty) {
        this.faculty = faculty;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public String getName() {
        return name;
    }

    public Faculty getFaculty() {
        return faculty;
    }

    public String getLocation() {
        return location;
    }

    private <T extends Enum<T>> T chooseFromEnum(T[] values, String message) {
        System.out.println(message);
        for (int i = 0; i < values.length; i++)
            System.out.println((i + 1) + ". " + values[i]);

        // Зчитуємо як рядок і перетворюємо в int для безпеки
        int choice = Integer.parseInt(scanner.nextLine());
        return values[choice - 1];
    }

    private void validateString(String value, String fieldName) {
        if (value == null || value.trim().isEmpty())
            throw new IllegalArgumentException(fieldName + " не може бути порожнім");
    }
}

