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

    public Department(String code, String name, Faculty faculty, Teacher head, String location) {
        this.code = code;
        this.name = name;
        this.faculty = faculty;
        this.head = head;
        this.location = location;
        this.students = new ArrayList<>();
        this.teachers = new ArrayList<>();
    }

    private String readRequiredString(String prompt) {
        while (true) {
            System.out.print(prompt);
            String input = scanner.nextLine();
            if (input != null && !input.trim().isEmpty()) {
                return input;
            }
            System.out.println("Помилка: поле не може бути порожнім.");
        }
    }

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

    private int readIntInRange(String prompt, int min, int max) {
        while (true) {
            int val = readSafeInt(prompt);
            if (val >= min && max >= val) {
                return val;
            }
            System.out.println("Помилка: введіть число від " + min + " до " + max + ".");
        }
    }

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

    public void updateStudentById(int id) {
        Student s = null;
        for (Student student : students) {
            if (student.getId() == id) { s = student; break; }
        }

        if (s == null) {
            System.out.println("Студента з ID " + id + " не знайдено.");
            return;
        }

        System.out.println("Редагування: " + s.getSurname() + ". (Enter щоб пропустити)");

        System.out.print("Нове прізвище: ");
        String sn = scanner.nextLine();
        if (!sn.trim().isEmpty()) s.setSurname(sn);

        System.out.print("Перевести на наступний курс? (т/н): ");
        if (scanner.nextLine().equalsIgnoreCase("т")) s.moveToNextYear();

        System.out.print("Змінити групу? (т/н): ");
        if (scanner.nextLine().equalsIgnoreCase("т")) s.changeGroup(chooseFromEnum(Group.values(), "Оберіть нову групу:"));
    }

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

    public void addStudent(Student s) {
        if (s != null) students.add(s);
    }

    public void addTeacher(Teacher t) {
        if (t != null) teachers.add(t);
    }

    public boolean removeStudentById(int id) {
        return students.removeIf(s -> s.getId() == id);
    }

    public boolean removeTeacherById(int id) {
        return teachers.removeIf(t -> t.getId() == id);
    }

    public Teacher findTeacherById(int id) {
        for (Teacher t : teachers)
            if (t.getId() == id) return t;
        return null;
    }

    public void setHead(Teacher head) { if (head != null) this.head = head; }
    public void setCode(String code) { if (code != null && !code.trim().isEmpty()) this.code = code; }
    public void setName(String name) { if (name != null && !name.trim().isEmpty()) this.name = name; }
    public void setFaculty(Faculty faculty) { if (faculty != null) this.faculty = faculty; }
    public void setLocation(String location) { if (location != null && !location.trim().isEmpty()) this.location = location; }

    public String getCode() { return code; }
    public String getName() { return name; }
    public List<Student> getStudents() { return students; }
    public List<Teacher> getTeachers() { return teachers; }
    public Faculty getFaculty() { return faculty; }
    public Teacher getHead() { return head; }
    public String getLocation() { return location; }
}