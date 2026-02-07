package edu.naukma;

import java.util.Scanner;

public class ConsoleMenu {

    private University university;
    private Scanner scanner = new Scanner(System.in);

    public ConsoleMenu(University university) {
        this.university = university;
    }

    public void start() {
        boolean running = true;

        while (running) {
            showMainMenu();
            int choice = readInt();

            switch (choice) {
                case 1: facultiesMenu(); break;
                case 2: departmentsMenu(); break;
                case 3: studentsMenu(); break;
                case 4: teachersMenu(); break;
                case 5: showAllInfo(); break; // Додав зручний перегляд
                case 0:
                    running = false;
                    System.out.println("Вихід з програми...");
                    break;
                default:
                    System.out.println("Невірний вибір. Спробуйте ще раз.");
            }
        }
    }

    private void showMainMenu() {
        System.out.println("\n===== ГОЛОВНЕ МЕНЮ (" + university.getFaculties().size() + " факультетів) =====");
        System.out.println("1 - Керування факультетами");
        System.out.println("2 - Керування кафедрами");
        System.out.println("3 - Керування студентами");
        System.out.println("4 - Керування викладачами");
        System.out.println("5 - Показати повну структуру університету");
        System.out.println("0 - Вихід");
        System.out.print("Ваш вибір: ");
    }

    /* ================= ФАКУЛЬТЕТИ ================= */

    private void facultiesMenu() {
        System.out.println("\n--- ФАКУЛЬТЕТИ ---");
        System.out.println("1 - Створити факультет");
        System.out.println("2 - Видалити факультет");
        System.out.println("3 - Список факультетів");
        System.out.println("4 - Редагувати факультет");
        System.out.println("0 - Назад");

        int choice = readInt();
        switch (choice) {
            case 1:
                Faculty f = university.createFaculty();
                university.addFaculty(f);
                System.out.println("Факультет успішно додано.");
                break;
            case 2:
                System.out.print("Введіть код факультету для видалення: ");
                String code = scanner.nextLine();
                if (university.removeFacultyByCode(code)) System.out.println("Видалено.");
                else System.out.println("Помилка: код не знайдено.");
                break;
            case 3:
                showFaculties();
                break;
            case 4:
                System.out.println("Введіть код факультету, дані якого потрібно відредагувати: ");
                String code1 = scanner.nextLine();
                university.updateFacultyByCode(code1);
                break;
        }
    }

    /* ================= КАФЕДРИ ================= */

    private void departmentsMenu() {
        Faculty faculty = chooseFaculty();
        if (faculty == null) return;

        System.out.println("\n--- КАФЕДРИ ФАКУЛЬТЕТУ: " + faculty.getShortName() + " ---");
        System.out.println("1 - Створити кафедру");
        System.out.println("2 - Видалити кафедру");
        System.out.println("3 - Список кафедр");
        System.out.println("4 - Редагувати кафедру");
        System.out.println("0 - Назад");

        int choice = readInt();
        switch (choice) {
            case 1:
                Department dep = faculty.createDepartment(university);
                faculty.addDepartment(dep);
                System.out.println("Кафедру створено та додано до факультету " + faculty.getShortName());
                break;
            case 2:
                System.out.print("Введіть код кафедри: ");
                String code = scanner.nextLine();
                if (faculty.removeDepartmentByCode(code)) System.out.println("Видалено.");
                else System.out.println("Кафедру не знайдено.");
                break;
            case 3:
                for (Department d : faculty.getDepartments()) {
                    System.out.println(d.getCode() + " - " + d.getName());
                }
                break;
            case 4:
                System.out.println("Введіть код кафедри, дані якої потрібно відредагувати: ");
                String code1 = scanner.nextLine();
                faculty.updateDepartmentByCode(code1);
                break;
        }
    }

    /* ================= СТУДЕНТИ ================= */

    private void studentsMenu() {
        Department dep = chooseDepartment();
        if (dep == null) return;

        System.out.println("\n--- СТУДЕНТИ КАФЕДРИ: " + dep.getName() + " ---");
        System.out.println("1 - Додати студента");
        System.out.println("2 - Видалити студента (за ID)");
        System.out.println("3 - Список студентів");
        System.out.println("4 - Редагувати студента");
        System.out.println("0 - Назад");

        int choice = readInt();
        switch (choice) {
            case 1:
                dep.addStudent(dep.createStudent());
                System.out.println("Студента зараховано.");
                break;
            case 2:
                System.out.print("Введіть ID студента: ");
                int id = readInt();
                if (dep.removeStudentById(id)) System.out.println("Студента видалено.");
                else System.out.println("ID не знайдено.");
                break;
            case 3:
                for (Student s : dep.getStudents()) System.out.println(s);
                break;
            case 4:
                System.out.println("Введіть ID студента, дані якого потрібно відредагувати: ");
                String sId = scanner.nextLine();
                university.updateFacultyByCode(sId);
                break;
        }
    }

    /* ================= ВИКЛАДАЧІ ================= */

    private void teachersMenu() {
        Department dep = chooseDepartment();
        if (dep == null) return;

        System.out.println("\n--- ВИКЛАДАЧІ КАФЕДРИ: " + dep.getName() + " ---");
        System.out.println("1 - Додати викладача");
        System.out.println("2 - Видалити викладача (за ID)");
        System.out.println("3 - Список викладачів");
        System.out.println("4 - Редагувати викладача");
        System.out.println("0 - Назад");

        int choice = readInt();
        switch (choice) {
            case 1:
                dep.addTeacher(dep.createTeacher());
                System.out.println("Викладача додано.");
                break;
            case 2:
                System.out.print("Введіть ID викладача: ");
                int id = readInt();
                if (dep.removeTeacherById(id)) System.out.println("Викладача видалено.");
                else System.out.println("ID не знайдено.");
                break;
            case 3:
                for (Teacher t : dep.getTeachers()) System.out.println(t);
                break;
            case 4:
                System.out.println("Введіть ID викладача, дані якого потрібно відредагувати: ");
                String tId = scanner.nextLine();
                university.updateFacultyByCode(tId);
                break;
        }
    }

    /* ================= ДОПОМІЖНІ МЕТОДИ ================= */

    private Faculty chooseFaculty() {
        showFaculties();
        if (university.getFaculties().isEmpty()) return null;

        System.out.print("Введіть код факультету: ");
        String code = scanner.nextLine();
        Faculty f = university.findFacultyByCode(code);
        if (f == null) System.out.println("Помилка: факультет не знайдено.");
        return f;
    }

    private Department chooseDepartment() {
        Faculty f = chooseFaculty();
        if (f == null) return null;

        if (f.getDepartments().isEmpty()) {
            System.out.println("На цьому факультеті ще немає кафедр.");
            return null;
        }

        System.out.println("Кафедри факультету " + f.getShortName() + ":");
        for (Department d : f.getDepartments()) System.out.println("- " + d.getCode() + " (" + d.getName() + ")");

        System.out.print("Введіть код кафедри: ");
        String code = scanner.nextLine();
        Department dep = f.findDepartmentByCode(code);
        if (dep == null) System.out.println("Помилка: кафедру не знайдено.");
        return dep;
    }

    private void showFaculties() {
        if (university.getFaculties().isEmpty()) {
            System.out.println("Список факультетів порожній.");
            return;
        }
        System.out.println("Доступні факультети:");
        for (Faculty f : university.getFaculties()) {
            System.out.println("[" + f.getCode() + "] " + f.getShortName());
        }
    }

    private void showAllInfo() {
        System.out.println("\n=== СТРУКТУРА УНІВЕРСИТЕТУ ===");
        for (Faculty f : university.getFaculties()) {
            System.out.println("Факультет: " + f.getShortName());
            for (Department d : f.getDepartments()) {
                System.out.println("  └── Кафедра: " + d.getName() + " (Студентів: " + d.getStudents().size() + ")");
            }
        }
    }

    private int readInt() {
        while (true) {
            try {
                String input = scanner.nextLine();
                return Integer.parseInt(input);
            } catch (NumberFormatException e) {
                System.out.print("Будь ласка, введіть число: ");
            }
        }
    }
}