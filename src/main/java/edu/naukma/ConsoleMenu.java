package edu.naukma;

import java.util.List;
import java.util.Scanner;

public class ConsoleMenu {

    private University university;
    private Scanner scanner = new Scanner(System.in);

    /**
     * Creates a console menu for the given university.
     *
     * @param university university instance to manage
     */
    public ConsoleMenu(University university) {
        this.university = university;
    }

    /**
     * Starts the main application loop and displays the main menu.
     */
    public void start() {
        boolean running = true;

        while (running) {
            showMainMenu();
            int choice = readInt();

            switch (choice) {
                case 1:
                    facultiesMenu();
                    break;
                case 2:
                    departmentsMenu();
                    break;
                case 3:
                    studentsMenu();
                    break;
                case 4:
                    teachersMenu();
                    break;
                case 0:
                    running = false;
                    System.out.println("Вихід з програми...");
                    break;
                default:
                    System.out.println("Невірний вибір. Спробуйте ще раз.");
            }
        }
    }

    /**
     * Displays the main menu options.
     */
    private void showMainMenu() {
        System.out.println("\n===== ГОЛОВНЕ МЕНЮ (" + university.getFaculties().size() + " факультетів) =====");
        System.out.println("1 - Керування факультетами");
        System.out.println("2 - Керування кафедрами");
        System.out.println("3 - Керування студентами");
        System.out.println("4 - Керування викладачами");
        System.out.println("0 - Вихід");
        System.out.print("Ваш вибір: ");
    }

    /**
     * Displays and handles the faculties management menu.
     */
    private void facultiesMenu() {
        System.out.println("\n--- ФАКУЛЬТЕТИ ---");
        System.out.println("1 - Створити факультет");
        System.out.println("2 - Видалити факультет");
        System.out.println("3 - Список факультетів");
        System.out.println("4 - Редагувати факультет");
        System.out.println("0 - Назад");

        int choice = readInt();
        switch (choice) {
            case 1: {
                System.out.println("---Create new faculty");

                System.out.println("Enter code:");
                int code = readInt();

                System.out.println("Enter full name:");
                String name = scanner.nextLine();

                System.out.println("Enter short name:");
                String shortName = scanner.nextLine();

                System.out.println("Enter dean ID:");
                String teacherIdStr = scanner.nextLine();
                int teacherId = Integer.parseInt(teacherIdStr);
                Teacher teacher = university.getTeacher(teacherId);

                System.out.println("Enter contacts:");
                String contacts = scanner.nextLine();

                Faculty f = new Faculty(code, name, shortName, teacher, contacts);
                university.addFaculty(f);
                System.out.println("Faculty added successfully!");
                break;
            }
            case 2: {
                System.out.print("Введіть код факультету для видалення: ");
                int code = readInt();
                if (university.removeFacultyByCode(code))
                    System.out.println("Видалено.");
                else
                    System.out.println("Помилка: код не знайдено.");
                break;
            }
            case 3:
                showFaculties();
                break;
            case 4: {
                System.out.println("Введіть код факультету, дані якого потрібно відредагувати: ");
                int code = readInt();
                Faculty faculty = university.getFaculty(code);

                System.out.println("Enter new name: ");
                faculty.setName(scanner.nextLine());

                System.out.println("Enter new short name: ");
                faculty.setShortName(scanner.nextLine());

                System.out.println("Enter teacher code of new dean");
                int teacherId = readInt();
                faculty.setDean(university.getTeacher(teacherId));

                System.out.println("Enter new contacts: ");
                faculty.setContacts(scanner.nextLine());

                break;
            }
        }
    }

    /**
     * Displays and handles the departments management menu.
     */
    private void departmentsMenu() {
        System.out.println("\n--- КАФЕДРИ ФАКУЛЬТЕТУ ---");
        System.out.println("1 - Створити кафедру");
        System.out.println("2 - Видалити кафедру");
        System.out.println("3 - Список кафедр");
        System.out.println("4 - Редагувати кафедру");
        System.out.println("0 - Назад");

        int choice = readInt();
        switch (choice) {
            case 1: {
                System.out.println("Enter code:");
                int code = readInt();

                System.out.println("Enter name:");
                String name = scanner.nextLine();

                System.out.print("Введіть код факультету: ");
                Faculty faculty = chooseFaculty();
                if (faculty == null) {
                    System.out.println("Create faculty first!");
                    return;
                }

                System.out.println("Enter head teacher id:");
                int teacherId = readInt();
                Teacher head = university.getTeacher(teacherId);

                System.out.println("Enter location:");
                String location = scanner.nextLine();

                Department dep = new Department(code, name, faculty, head, location);
                faculty.addDepartment(dep);

                System.out.println("Кафедру створено та додано до факультету " + faculty.getShortName());
                break;
            }
            case 2: {
                System.out.print("Введіть код кафедри: ");
                int code = readInt();
                Department department = university.getDepartment(code);
                Faculty faculty = university.getFaculty(department);
                if (faculty.removeDepartmentByCode(code))
                    System.out.println("Видалено.");
                else
                    System.out.println("Кафедру не знайдено.");
                break;
            }
            case 3:
                for (Department d : university.getDepartments())
                    System.out.println(d.getCode() + " - " + d.getName());
                break;
            case 4: {
                System.out.println("Введіть код кафедри, дані якої потрібно відредагувати: ");
                int code = readInt();

                university.getFaculty(code);

                break;
            }
        }
    }

    /**
     * Displays and handles the students management menu.
     */
    private void studentsMenu() {
        System.out.println("\n--- СТУДЕНТИ: ---");
        System.out.println("1 - Додати студента");
        System.out.println("2 - Видалити студента (за ID)");
        System.out.println("3 - Список студентів");
        System.out.println("4 - Редагувати студента");
        System.out.println("0 - Назад");

        int choice = readInt();
        switch (choice) {
            case 1: {
                System.out.println("--Add new student");

                System.out.println("Enter name:");
                String name = scanner.nextLine();

                System.out.println("Enter surname:");
                String surname = scanner.nextLine();

                System.out.println("Enter midlename:");
                String midlename = scanner.nextLine();

                System.out.println("Enter day of birth:");
                String dayOfBirth = scanner.nextLine();

                System.out.println("Enter phone:");
                String phone = scanner.nextLine();

                System.out.println("Enter email:");
                String email = scanner.nextLine();

                System.out.println("Enter student id:");
                int studentId = readInt();

                System.out.println("Enter faculty id: ");
                int facultyId = readInt();
                Faculty faculty = university.getFaculty(facultyId);

                System.out.println("Enter course:");
                int course = readInt();

                System.out.println("Enter group:");
                int group = readInt();

                System.out.println("Enter year of entry:");
                int yearOfEntry = readInt();

                System.out.println("Enter form of study (1 - STATE_FUNDED / 2 - CONTRACT)");
                StudyForm studyForm;
                if (readInt() == 1) studyForm = StudyForm.STATE_FUNDED;
                else studyForm = StudyForm.CONTRACT;

                System.out.println("Enter status (1 - STUDYING / 2 - ACADEMIC_LEAVE / 3 - EXPELLED)");
                StudentStatus studentStatus;
                int c = readInt();
                if (c == 1) studentStatus = StudentStatus.STUDYING;
                else if (c == 2) studentStatus = StudentStatus.ACADEMIC_LEAVE;
                else studentStatus = StudentStatus.EXPELLED;

                Student student = new Student(name, surname, midlename, dayOfBirth, phone, email, studentId, course, faculty, group, yearOfEntry, studyForm, studentStatus);
                break;
            }
            case 2:
                System.out.print("Введіть ID студента для видалення: ");
                int idDel = readInt();
                if (university.removeStudent(idDel))
                    System.out.println("Студента видалено.");
                else
                    System.out.println("ID не знайдено.");
                break;
            case 3:
                List<Student> students = university.getStudents();

                if (students.isEmpty())
                    System.out.println("Список порожній.");
                for (Student s : students)
                    System.out.println(s);
                break;
            case 4:
                System.out.print("Введіть ID студента для редагування: ");
                int idEdit = readInt();
                Student student = university.getStudent(idEdit);

                System.out.println("Enter name:");
                student.setName(scanner.nextLine());

                System.out.println("Enter surname:");
                student.setSurname(scanner.nextLine());

                System.out.println("Enter midlename:");
                student.setMidleName(scanner.nextLine());

                System.out.println("Enter phone:");
                student.setPhone(scanner.nextLine());

                System.out.println("Enter email:");
                student.setEmail(scanner.nextLine());

                System.out.println("Enter faculty id: ");
                int facultyId = readInt();
                student.setFaculty(university.getFaculty(facultyId));

                System.out.println("Enter course:");
                student.setCourse(readInt());

                System.out.println("Enter group:");
                student.setGroup(readInt());

                System.out.println("Enter form of study (1 - STATE_FUNDED / 2 - CONTRACT)");
                StudyForm studyForm;
                if (readInt() == 1) studyForm = StudyForm.STATE_FUNDED;
                else studyForm = StudyForm.CONTRACT;
                student.setStudyForm(studyForm);

                System.out.println("Enter status (1 - STUDYING / 2 - ACADEMIC_LEAVE / 3 - EXPELLED)");
                StudentStatus studentStatus;
                int c = readInt();
                if (c == 1) studentStatus = StudentStatus.STUDYING;
                else if (c == 2) studentStatus = StudentStatus.ACADEMIC_LEAVE;
                else studentStatus = StudentStatus.EXPELLED;
                student.setStatus(studentStatus);

                break;
        }
    }

    /**
     * Displays and handles the teachers management menu.
     */
    private void teachersMenu() {
        System.out.println("\n--- ВИКЛАДАЧІ ---");
        System.out.println("1 - Додати викладача");
        System.out.println("2 - Видалити викладача (за ID)");
        System.out.println("3 - Список викладачів");
        System.out.println("4 - Редагувати викладача");
        System.out.println("0 - Назад");

        int choice = readInt();
        switch (choice) {
            case 1:
                System.out.println("Enter name:");
                String name = scanner.nextLine();

                System.out.println("Enter surname:");
                String surname = scanner.nextLine();

                System.out.println("Enter midlename:");
                String midlename = scanner.nextLine();

                System.out.println("Enter day of birth:");
                String dayOfBirth = scanner.nextLine();

                System.out.println("Enter phone:");
                String phone = scanner.nextLine();

                System.out.println("Enter email:");
                String email = scanner.nextLine();

                System.out.println("Enter teacher id: ");
                int teacherId = readInt();

                System.out.println("Choose teacher position: ");
                TeacherPosition position = chooseTeacherPositon();

                System.out.println("Choose academic degree: ");
                AcademicDegree degree = chooseAcademicDegree();

                System.out.println("Choose academic stage: ");
                AcademicStage stage = chooseAcademicStage();

                System.out.println("Enter date of hiring:");
                String dateOfHiring = scanner.nextLine();

                System.out.println("Enter rate:");
                int rate = readInt();

                System.out.println("Choose department:");
                Department department = chooseDepartment();

                university.addTeacher(new Teacher(name, surname, midlename, dayOfBirth, phone, email, teacherId, position, degree, stage, dateOfHiring, rate, department));

                System.out.println("Викладача додано.");
                break;
            case 2:
                System.out.print("Введіть ID викладача для видалення: ");
                int idDel = readInt();
                if (university.removeTeacher(idDel))
                    System.out.println("Викладача видалено.");
                else
                    System.out.println("ID не знайдено.");
                break;
            case 3:
                List<Teacher> teachers = university.getTeachers();

                if (teachers.isEmpty())
                    System.out.println("Список порожній.");
                for (Teacher t : teachers)
                    System.out.println(t);
                break;
            case 4:
                System.out.print("Введіть ID викладача для редагування: ");
                int idEdit = readInt();

                Teacher teacher = university.getTeacher(idEdit);

                System.out.println("Enter name:");
                teacher.setName(scanner.nextLine());

                System.out.println("Enter surname:");
                teacher.setSurname(scanner.nextLine());

                System.out.println("Enter midlename:");
                teacher.setMidleName(scanner.nextLine());

                System.out.println("Enter phone:");
                teacher.setPhone(scanner.nextLine());

                System.out.println("Enter email:");
                teacher.setEmail(scanner.nextLine());

                System.out.println("Choose teacher position: ");
                teacher.setPosition(chooseTeacherPositon());

                System.out.println("Choose academic degree: ");
                teacher.setDegree(chooseAcademicDegree());

                System.out.println("Choose academic stage: ");
                teacher.setStage(chooseAcademicStage());

                System.out.println("Enter rate:");
                teacher.setRate(readInt());

                System.out.println("Choose department:");
                teacher.setDepartment(chooseDepartment());

                break;
        }
    }

    /**
     * Allows the user to select an academic degree from the available options.
     *
     * @return selected AcademicDegree
     */
    private AcademicDegree chooseAcademicDegree() {
        AcademicDegree[] degrees = AcademicDegree.values();

        while (true) {
            for (int i = 0; i < degrees.length; i++) {
                System.out.println((i+1) + " - " + degrees[i]);
            }

            System.out.println("Enter number: ");
            int choise = scanner.nextInt();
            scanner.nextLine(); // to clear enter without this next call of scanner will end

            if (choise > 0 && choise <= degrees.length) return degrees[choise-1];
            else System.out.println("[Enter proper variant!]");
        }
    }

    /**
     * Allows the user to select an academic stage from the available options.
     *
     * @return selected AcademicStage
     */
    private AcademicStage chooseAcademicStage() {
        AcademicStage[] academicStages = AcademicStage.values();


        while (true) {
            for (int i = 0; i < academicStages.length; i++) {
                System.out.println((i+1) + " - " + academicStages[i]);
            }

            System.out.println("Enter number: ");
            int choise = scanner.nextInt();
            scanner.nextLine(); // to clear enter without this next call of scanner will end

            if (choise > 0 && choise <= academicStages.length) return academicStages[choise-1];
            else System.out.println("[Enter proper variant!]");
        }
    }

    /**
     * Allows the user to select a teacher position from the available options.
     *
     * @return selected TeacherPosition
     */
    private TeacherPosition chooseTeacherPositon() {
        TeacherPosition[] positions = TeacherPosition.values();

        while (true) {
            for (int i = 0; i < positions.length; i++) {
                System.out.println((i+1) + " - " + positions[i]);
            }

            System.out.println("Enter number: ");
            int choise = scanner.nextInt();
            scanner.nextLine(); // to clear enter without this next call of scanner will end

            if (choise > 0 && choise <= positions.length) return positions[choise-1];
            else System.out.println("[Enter proper variant!]");
        }
    }

    /**
     * Allows the user to select a faculty by its code.
     *
     * @return selected Faculty or null if not found
     */
    private Faculty chooseFaculty() {
        while (true) {
            showFaculties();
            if (university.getFaculties().isEmpty()) return null;

            int code = readInt();
            Faculty f = university.getFaculty(code);
            if (f == null)
                System.out.println("Помилка: факультет не знайдено.");
            return f;
        }
    }

    /**
     * Allows the user to select a department by its code.
     *
     * @return selected Department or null if not found
     */
    private Department chooseDepartment() {
        while (true) {
            for (Department d : university.getDepartments())
                System.out.println(d.getCode() + " - " + d.getName());

            int code = readInt();
            Department d = university.getDepartment(code);
            if (d == null)
                System.out.println("Помилка: кафедру не знайдено.");
            return d;
        }
    }

    /**
     * Displays the list of faculties.
     */
    private void showFaculties() {
        if (university.getFaculties().isEmpty()) {
            System.out.println("Список факультетів порожній.");
            return;
        }
        System.out.println("Доступні факультети:");
        for (Faculty f : university.getFaculties()) {
            System.out.println("[" + f.getCode() + "] " + f.getShortName() + " : " + f.getContacts());
        }
    }

    /**
     * Safely reads an integer from console input.
     *
     * @return integer value entered by the user
     */
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
