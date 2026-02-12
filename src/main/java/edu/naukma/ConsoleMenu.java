package edu.naukma;

import java.util.List;
import java.util.Scanner;

public class ConsoleMenu {

    private final University university;
    private final Scanner scanner = new Scanner(System.in);

    /**
     * Creates a console menu for the given university.
     *
     * @param university university instance to manage
     */
    public ConsoleMenu(University university) {
        System.out.println("*****************************************************");
        System.out.println("*                                                   *");
        System.out.println("*   ██████╗ ██╗ ██████╗ ██╗██╗   ██╗███╗   ██╗██╗   *");
        System.out.println("*   ██╔══██╗██║██╔════╝ ██║██║   ██║████╗  ██║██║   *");
        System.out.println("*   ██║  ██║██║██║  ███╗██║██║   ██║██╔██╗ ██║██║   *");
        System.out.println("*   ██║  ██║██║██║   ██║██║██║   ██║██║╚██╗██║██║   *");
        System.out.println("*   ██████╔╝██║╚██████╔╝██║╚██████╔╝██║ ╚████║██║   *");
        System.out.println("*   ╚═════╝ ╚═╝ ╚═════╝ ╚═╝ ╚═════╝ ╚═╝  ╚═══╝╚═╝   *");
        System.out.println("*                                                   *");
        System.out.println("*         Welcome to the University Manager         *");
        System.out.println("*        Created by: Dudar Vadim & Demkiv Max       *");
        System.out.println("*                                                   *");
        System.out.println("*****************************************************");

        this.university = university;
    }

    /**
     * Starts the main application loop and displays the main menu.
     */
    public void start() {
        boolean running = true;

        while (running) {

            System.out.println("\n===== MAIN MENU =====");
            System.out.println("1 - Manage Faculties");
            System.out.println("2 - Manage Departments");
            System.out.println("3 - Manage Students");
            System.out.println("4 - Manage Teachers");
            System.out.println("5 - Reports");
            System.out.println("0 - Exit");
            System.out.print("Chose: ");

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
                case 5:
                    reportsMenu();
                    break;
                case 0:
                    running = false;
                    System.out.println("Exiting... Goodbye!");
                    break;
                default:
                    System.out.println("Invalid choice. Please enter a number from the menu.");
            }
        }
    }

    /**
     * Displays and handles the faculties management menu.
     */
    private void facultiesMenu() {
        System.out.println("\n--- FACULTIES ---");
        System.out.println("1 - Create new faculty");
        System.out.println("2 - Delete faculty (by code)");
        System.out.println("3 - List faculties");
        System.out.println("4 - Edit faculty");
        System.out.println("0 - Back");
        System.out.print("Chose: ");

        int choice = readInt();
        switch (choice) {
            case 1: {
                System.out.println("---Create new faculty");

                System.out.println("Enter code:");
                int code = readInt();

                System.out.println("Enter full name:");
                String name = readString();

                System.out.println("Enter short name:");
                String shortName = readString();

                System.out.println("Enter dean ID:");
                String teacherIdStr = readString();
                int teacherId = Integer.parseInt(teacherIdStr);
                Teacher teacher = university.getTeacher(teacherId);

                System.out.println("Enter contacts:");
                String contacts = readString();

                Faculty f = new Faculty(code, name, shortName, teacher, contacts);
                university.addFaculty(f);
                System.out.println("Faculty added -> " + f);
                break;
            }
            case 2: {
                System.out.print("Enter faculty code to delete: ");
                int code = readInt();
                if (university.removeFacultyByCode(code))
                    System.out.println("Deleted.");
                else
                    System.out.println("Faculty not found.");
                break;
            }
            case 3:
                showFaculties();
                break;
            case 4: {
                System.out.println("Enter faculty code to edit: ");
                int code = readInt();
                Faculty faculty = university.getFaculty(code);

                if (faculty == null) {
                    System.out.println("Faculty not found.");
                    return;
                }

                System.out.println("Enter new name: ");
                faculty.setName(readString());

                System.out.println("Enter new short name: ");
                faculty.setShortName(readString());

                System.out.println("Enter teacher code of new dean");
                int teacherId = readInt();
                faculty.setDean(university.getTeacher(teacherId));

                System.out.println("Enter new contacts: ");
                faculty.setContacts(readString());

                break;
            }
        }
    }

    /**
     * Displays and handles the departments management menu.
     */
    private void departmentsMenu() {
        System.out.println("\n--- DEPARTMENTS ---");
        System.out.println("1 - Create new department");
        System.out.println("2 - Delete department (by code)");
        System.out.println("3 - List departments");
        System.out.println("4 - Edit department");
        System.out.println("0 - Back");
        System.out.print("Chose: ");

        int choice = readInt();
        switch (choice) {
            case 1: {
                System.out.println("Enter code:");
                int code = readInt();

                System.out.println("Enter name:");
                String name = readString();

                System.out.print("Enter faculty code: ");
                Faculty faculty = chooseFaculty();
                if (faculty == null) {
                    System.out.println("Cannot create department without faculty. Please create a faculty first.");
                    return;
                }

                System.out.println("Enter head teacher id:");
                int teacherId = readInt();
                Teacher head = university.getTeacher(teacherId);

                System.out.println("Enter location:");
                String location = readString();

                Department dep = new Department(code, name, faculty, head, location);
                faculty.addDepartment(dep);

                System.out.println("Department created ->" + dep);
                break;
            }
            case 2: {
                System.out.print("Enter department code to delete: ");
                int code = readInt();
                Department department = university.getDepartment(code);
                Faculty faculty = university.getFaculty(department);
                if (faculty.removeDepartmentByCode(code))
                    System.out.println("Deleted.");
                else
                    System.out.println("Department not found.");
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
        System.out.println("\n--- STUDENTS ---");
        System.out.println("1 - Create new student");
        System.out.println("2 - Delete student (by ID)");
        System.out.println("3 - Student list");
        System.out.println("4 - Edit student");
        System.out.println("5 - Find student by full name");
        System.out.println("6 - Find by course");
        System.out.println("7 - Find by group");
        System.out.println("0 - Back");
        System.out.print("Chose: ");

        int choice = readInt();
        switch (choice) {
            case 1: {
                System.out.println("\n--Add new student");

                System.out.println("Enter name:");
                String name = readString();

                System.out.println("Enter surname:");
                String surname = readString();

                System.out.println("Enter middle name:");
                String middleName = readString();

                System.out.println("Enter day of birth:");
                String dayOfBirth = readString();

                System.out.println("Enter phone:");
                String phone = readString();

                System.out.println("Enter email:");
                String email = readString();

                System.out.println("Enter student id:");
                int studentId = readInt();

                System.out.println("Enter faculty id: ");
                int facultyId = readInt();
                Faculty faculty = university.getFaculty(facultyId);

                System.out.println("Enter course:");
                int course = readInt();
                if (course < 1 || course > 6) {
                    System.out.println("Course must be between 1 and 6.");
                    return;
                }

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

                Student student = new Student(name, surname, middleName, dayOfBirth, phone, email, studentId, course, faculty, group, yearOfEntry, studyForm, studentStatus);
                university.addStudent(student);

                System.out.println("Student added -> " + student);
                break;
            }
            case 2: {
                System.out.print("Enter student ID to delete: ");
                int idDel = readInt();
                if (university.removeStudent(idDel))
                    System.out.println("Student deleted.");
                else
                    System.out.println("Student ID not found.");
                break;
            }
            case 3: {
                List<Student> students = university.getStudents();

                if (students.isEmpty())
                    System.out.println("Student list is empty.");
                for (Student s : students)
                    System.out.println(s);
                break;
            }
            case 4: {
                System.out.print("Enter student ID to edit: ");
                int idEdit = readInt();
                Student student = university.getStudent(idEdit);

                System.out.println("Enter name:");
                student.setName(readString());

                System.out.println("Enter surname:");
                student.setSurname(readString());

                System.out.println("Enter middle name:");
                student.setMiddleName(readString());

                System.out.println("Enter phone:");
                student.setPhone(readString());

                System.out.println("Enter email:");
                student.setEmail(readString());

                System.out.println("Enter faculty id: ");
                int facultyId = readInt();
                student.setFaculty(university.getFaculty(facultyId));

                System.out.println("Enter course:");
                int course = readInt();
                if (course < 1 || course > 6) {
                    System.out.println("Course must be between 1 and 6.");
                    return;
                }
                student.setCourse(course);


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

                System.out.println("Student updated -> " + student);
                break;
            }
            case 5: {
                System.out.println("Enter full name: ");
                String fullName = readString();

                System.out.println(university.getStudent(fullName));
                break;
            }
            case 6: {
                System.out.println("Enter course: ");
                int course = readInt();

                List<Student> students = university.getStudentsByCourse(course);
                if (!students.isEmpty()) for (Student student : students) System.out.println(student);
                else System.out.println("Has no student with this course.");

                break;
            }
            case 7: {
                System.out.println("Enter group: ");
                int group = readInt();

                List<Student> students = university.getStudentsByGroup(group);
                if (!students.isEmpty()) for (Student student : students) System.out.println(student);
                else System.out.println("Has no student with this course.");

                break;
            }
        }
    }

    /**
     * Displays and handles the teachers management menu.
     */
    private void teachersMenu() {
        System.out.println("\n--- TEACHERS ---");
        System.out.println("1 - Create new teacher");
        System.out.println("2 - Delete teacher (by ID)");
        System.out.println("3 - List teachers");
        System.out.println("4 - Edit teacher");
        System.out.println("5 - Find teachers by department");
        System.out.println("0 - Back");
        System.out.print("Chose: ");

        int choice = readInt();
        switch (choice) {
            case 1: {
                System.out.println("Enter name:");
                String name = readString();

                System.out.println("Enter surname:");
                String surname = readString();

                System.out.println("Enter middle name:");
                String middleName = readString();

                System.out.println("Enter day of birth:");
                String dayOfBirth = readString();

                System.out.println("Enter phone:");
                String phone = readString();

                System.out.println("Enter email:");
                String email = readString();

                System.out.println("Enter teacher id: ");
                int teacherId = readInt();

                System.out.println("Choose teacher position: ");
                TeacherPosition position = chooseTeacherPositon();

                System.out.println("Choose academic degree: ");
                AcademicDegree degree = chooseAcademicDegree();

                System.out.println("Choose academic stage: ");
                AcademicStage stage = chooseAcademicStage();

                System.out.println("Enter date of hiring:");
                String dateOfHiring = readString();

                System.out.println("Enter rate:");
                int rate = readInt();

                System.out.println("Choose department:");
                Department department = chooseDepartment();

                Teacher teacher = new Teacher(name, surname, middleName, dayOfBirth, phone, email, teacherId, position, degree, stage, dateOfHiring, rate, department);
                university.addTeacher(teacher);

                System.out.println("Teacher added -> " + teacher);
                break;
            }
            case 2:
                System.out.print("Enter teacher ID to delete: ");
                int idDel = readInt();
                if (university.removeTeacher(idDel))
                    System.out.println("Teacher deleted.");
                else
                    System.out.println("Teacher ID not found.");
                break;
            case 3:
                List<Teacher> teachers = university.getTeachers();

                if (teachers.isEmpty())
                    System.out.println("Teacher list is empty.");
                for (Teacher t : teachers)
                    System.out.println(t);
                break;
            case 4: {
                System.out.print("Enter teacher ID to edit: ");
                int idEdit = readInt();

                Teacher teacher = university.getTeacher(idEdit);

                System.out.println("Enter name:");
                teacher.setName(readString());

                System.out.println("Enter surname:");
                teacher.setSurname(readString());

                System.out.println("Enter middle name:");
                teacher.setMiddleName(readString());

                System.out.println("Enter phone:");
                teacher.setPhone(readString());

                System.out.println("Enter email:");
                teacher.setEmail(readString());

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

                System.out.println("Teacher updated -> " + teacher);
                break;
            }
            case 5:
                System.out.println("Choose department:");
                Department department = chooseDepartment();

                for (Teacher teacher : university.getTeachers(department)) System.out.println(teacher);

        }
    }

    /**
     * Displays and handles the reports menu.
     */
    private void reportsMenu() {
        System.out.println("\n--- REPORTS ---");
        System.out.println("1 - Students in faculties");
        System.out.println("0 - Exit");

        switch (readInt()) {
            case 1:
                for (Faculty faculty : university.getFaculties()) {
                    List<Student> students = university.getStudents(faculty);
                    System.out.println(faculty.getName() + ": " + students.size() + " students");
                }
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
                System.out.println((i + 1) + " - " + degrees[i]);
            }

            System.out.println("Enter number: ");
            int choice = scanner.nextInt();
            scanner.nextLine(); // to clear enter without this next call of scanner will end

            if (choice > 0 && choice <= degrees.length) return degrees[choice - 1];
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
                System.out.println((i + 1) + " - " + academicStages[i]);
            }

            System.out.println("Enter number: ");
            int choice = scanner.nextInt();
            scanner.nextLine(); // to clear enter without this next call of scanner will end

            if (choice > 0 && choice <= academicStages.length) return academicStages[choice - 1];
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
                System.out.println((i + 1) + " - " + positions[i]);
            }

            System.out.println("Enter number: ");
            int choice = scanner.nextInt();
            scanner.nextLine(); // to clear enter without this next call of scanner will end

            if (choice > 0 && choice <= positions.length) return positions[choice - 1];
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
            if (f == null) {
                System.out.println("Помилка: факультет не знайдено.");
                continue;
            }
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
            if (d == null) {
                System.out.println("Помилка: кафедру не знайдено.");
                continue;
            }
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
                int result = Integer.parseInt(scanner.nextLine());
                if (result < 0) {
                    System.out.print("Please enter a non-negative number: ");
                    continue;
                }
                return result;
            } catch (NumberFormatException e) {
                System.out.print("Please enter valid number: ");
            }
        }
    }

    /**
     * Safely reads a non-empty string from console input.
     *
     * @return non-empty string entered by the user
     */
    private String readString() {
        while (true) {
            String result = scanner.nextLine();
            if (result.trim().isEmpty()) {
                System.out.print("Input cannot be empty. Please enter a valid string: ");
                continue;
            }
            return result;
        }
    }
}
