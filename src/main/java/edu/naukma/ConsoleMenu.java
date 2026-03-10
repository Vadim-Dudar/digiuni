package edu.naukma;

import java.util.*;

public class ConsoleMenu {

    private final University university;
    private final Set<User> users = new HashSet<>();
    private User curentUser = null;
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
        users.add(new User("admin", "1111", UserRole.ADMIN));
        users.add(new User("moder", "1234", UserRole.EXPLORER));
        users.add(new User("super", "qwerty", UserRole.TECH_ADMIN));
    }

    /**
     * Starts the main application loop and displays the main menu.
     */
    public void start() {
        boolean running = true;

        login();

        while (running) {
            System.out.println("\n===== MAIN MENU =====");
            System.out.println("1 - Manage University");
            System.out.println("2 - Manage Faculties");
            System.out.println("3 - Manage Departments");
            System.out.println("4 - Manage Students");
            System.out.println("5 - Manage Teachers");
            System.out.println("6 - Reports");
            if (isTechAdmin()) {
                System.out.println("\n7 - Manage Users");
            }
            System.out.println("0 - Exit");
            System.out.print("Chose: ");

            int choice = readInt();

            switch (choice) {
                case 1:
                    universityMenu();
                    break;
                case 2:
                    facultiesMenu();
                    break;
                case 3:
                    departmentsMenu();
                    break;
                case 4:
                    studentsMenu();
                    break;
                case 5:
                    teachersMenu();
                    break;
                case 6:
                    reportsMenu();
                    break;
                case 7:
                    if (!isTechAdmin()) {
                        System.out.println("Invalid choice. Please enter a number from the menu.");
                        return;
                    }
                    userMenu();
                    break;
                case 0:
                    System.out.print("Exit total (type 0) or exit from user (type 1): ");
                    choice = readInt();
                    if (choice == 1) {
                        curentUser = null;
                        login();
                    } else {
                        running = false;
                        System.out.println("Exiting... Goodbye!");
                    }

                    break;
                default:
                    System.out.println("Invalid choice. Please enter a number from the menu.");
            }
        }
    }

    /**
     * Checks if the current user has the ADMIN role.
     *
     * @return true if the current user is an admin, false otherwise
     */
    public boolean isAdmin() {
        return curentUser.getUserRole() == UserRole.ADMIN;
    }

    /**
     * Checks if the current user has the TECH_ADMIN role.
     *
     * @return true if the current user is a tech admin, false otherwise
     */
    public boolean isTechAdmin() {
        return curentUser.getUserRole() == UserRole.TECH_ADMIN;
    }

    /**
     * Handles the user login process, allowing users to enter their credentials and access the system.
     * Continues to prompt until a valid login is provided.
     */
    public void login() {
        while (true) {
            System.out.println("\n--- Enter to system ---");

            System.out.print("Login: ");
            String login = scanner.nextLine();

            for (User user : users) {
                if (user.login.equals(login.trim())) {
                    System.out.print("Password: ");
                    String password = scanner.nextLine();

                    if (user.checkPassword(password.trim())) {
                        curentUser = user;
                        System.out.println("Welcome!");
                        return;
                    }
                    System.out.println("[Password incorrect.]");
                }
            }

            System.out.println("[Login failed! Try again.]");
        }
    }

    /**
     * Displays and handles university editing menu.
     */
    private void universityMenu() {
        System.out.println("\n--- UNIVERSITY MENU ---");
        System.out.println("1 - Show University");
        if (isAdmin()) {
            System.out.println("2 - Edit University");
        }
        System.out.println("0 - Back");

        switch (readInt("Chose: ")) {
            case 2:
                if (!isAdmin()) return;

                System.out.println("Enter new full name:");
                university.setFullName(readString());

                System.out.println("Enter new short name:");
                university.setShortName(readString());

                System.out.println("Enter new city:");
                university.setCity(readString());

                System.out.println("Enter new address:");
                university.setAddress(readString());
                break;
            case 1:
                System.out.println(university.getFullName() + " (" + university.getShortName() + "); Address: " + university.getCity() + " " + university.getAddress());
                break;
        }
    }

    /**
     * Displays and handles the faculties management menu.
     */
    private void facultiesMenu() {
        System.out.println("\n--- FACULTIES ---");
        System.out.println("1 - List faculties");
        if (isAdmin()) {
            System.out.println("2 - Create new faculty");
            System.out.println("3 - Delete faculty (by code)");
            System.out.println("4 - Edit faculty");
        }
        System.out.println("0 - Back");

        switch (readInt("Chose: ")) {
            case 2: {
                if (!isAdmin()) return;

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
            case 3: {
                if (!isAdmin()) return;

                System.out.print("Enter faculty code to delete: ");
                int code = readInt();
                if (university.removeFacultyByCode(code))
                    System.out.println("Deleted.");
                else
                    System.out.println("Faculty not found.");
                break;
            }
            case 1:
                showFaculties();
                break;
            case 4: {
                if (!isAdmin()) return;

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
        System.out.println("1 - List departments");
        if (isAdmin()) {
            System.out.println("2 - Create new department");
            System.out.println("3 - Delete department (by code)");
            System.out.println("4 - Edit department");
        }
        System.out.println("0 - Back");

        switch (readInt("Chose: ")) {
            case 2: {
                if (!isAdmin()) return;

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
            case 3: {
                if (!isAdmin()) return;

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
            case 1: {
                for (Department d : university.getDepartments())
                    System.out.println(d.getCode() + " - " + d.getName());
                break;
            }
            case 4: {
                if (!isAdmin()) return;

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
        System.out.println("1 - Student list");
        System.out.println("2 - Find student by full name");
        System.out.println("3 - Find by course");
        System.out.println("4 - Find by group");
        if (isAdmin()) {
            System.out.println("5 - Create new student");
            System.out.println("6 - Delete student (by ID)");
            System.out.println("7 - Edit student");
        }

        System.out.println("0 - Back");

        switch (readInt("Chose: ")) {
            case 5: {
                if (!isAdmin()) return;

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
                Faculty faculty = chooseFaculty();

                System.out.println("Enter department id: ");
                Department department = chooseDepartment();

                System.out.println("Enter course:");
                int course = readInt();
                if (course < 1 || course > 6) {
                    System.out.println("Course must be between 1 and 6.");
                    return;
                }

                System.out.println("Enter group:");
                int group = readInt();
                if (group < 1) {
                    System.out.println("Group number must be positive.");
                    return;
                }

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

                Student student = new Student(name, surname, middleName, dayOfBirth, phone, email, studentId, course, faculty, department, group, yearOfEntry, studyForm, studentStatus);
                university.addStudent(student);

                System.out.println("Student added -> " + student.toString());
                break;
            }
            case 6: {
                if (!isAdmin()) return;

                System.out.print("Enter student ID to delete: ");
                int idDel = readInt();
                if (university.removeStudent(idDel))
                    System.out.println("Student deleted.");
                else
                    System.out.println("Student ID not found.");
                break;
            }
            case 1: {
                List<Student> students = university.getStudents();

                if (students.isEmpty()) {
                    System.out.println("Student list is empty.");
                } else {
                    for (Student s : students)
                        System.out.println(s);
                }
                break;
            }
            case 7: {
                if (!isAdmin()) return;

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
                int group = readInt();
                if (group < 1) {
                    System.out.println("Group number must be positive.");
                    return;
                }
                student.setGroup(group);

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
            case 2: {
                System.out.println("Enter full name: ");
                String fullName = readString();

                System.out.println(university.getStudent(fullName));
                break;
            }
            case 3: {
                System.out.println("Enter course: ");
                int course = readInt();

                List<Student> students = university.getStudentsByCourse(course);
                if (!students.isEmpty()) for (Student student : students) System.out.println(student);
                else System.out.println("Has no student with this course.");

                break;
            }
            case 4: {
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
        System.out.println("1 - List teachers");
        System.out.println("2 - Find teachers by department");
        if (isAdmin()) {
            System.out.println("3 - Delete teacher (by ID)");
            System.out.println("4 - Create new teacher");
            System.out.println("5 - Edit teacher");
        }
        System.out.println("0 - Back");

        switch (readInt("Chose:")) {
            case 4: {
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
                TeacherPosition position = chooseEnum(TeacherPosition.class);

                System.out.println("Choose academic degree: ");
                AcademicDegree degree = chooseEnum(AcademicDegree.class);

                System.out.println("Choose academic stage: ");
                AcademicStage stage = chooseEnum(AcademicStage.class);

                System.out.println("Enter date of hiring:");
                String dateOfHiring = readString();

                System.out.println("Enter rate:");
                int rate = readInt();

                System.out.println("Choose faculty:");
                Faculty faculty = chooseFaculty();

                System.out.println("Choose department:");
                Department department = chooseDepartment();

                Teacher teacher = new Teacher(name, surname, middleName, dayOfBirth, phone, email, teacherId, position, degree, stage, dateOfHiring, rate, faculty, department);
                university.addTeacher(teacher);

                System.out.println("Teacher added -> " + teacher);
                break;
            }
            case 3: {
                System.out.print("Enter teacher ID to delete: ");
                int idDel = readInt();
                if (university.removeTeacher(idDel))
                    System.out.println("Teacher deleted.");
                else
                    System.out.println("Teacher ID not found.");
                break;
            }
            case 1: {
                List<Teacher> teachers = university.getTeachers();

                if (teachers.isEmpty())
                    System.out.println("Teacher list is empty.");
                for (Teacher t : teachers)
                    System.out.println(t);
                break;
            }
            case 5: {
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
                teacher.setPosition(chooseEnum(TeacherPosition.class));

                System.out.println("Choose academic degree: ");
                teacher.setDegree(chooseEnum(AcademicDegree.class));

                System.out.println("Choose academic stage: ");
                teacher.setStage(chooseEnum(AcademicStage.class));

                System.out.println("Enter rate:");
                teacher.setRate(readInt());

                System.out.println("Choose department:");
                teacher.setDepartment(chooseDepartment());

                System.out.println("Teacher updated -> " + teacher);
                break;
            }
            case 2: {
                System.out.println("Choose department:");
                Department department = chooseDepartment();

                for (Teacher teacher : university.getTeachers(department)) System.out.println(teacher);
                break;
            }

        }
    }

    /**
     * Displays and handles the reports menu.
     */
    private void reportsMenu() {
        System.out.println("\n--- REPORTS ---");
        System.out.println("1 - Students in faculties");
        System.out.println("2 - Sort student");
        System.out.println("3 - Sort teachers");
        System.out.println("0 - Exit");

        switch (readInt()) {
            case 1:
                for (Faculty faculty : university.getFaculties()) {
                    List<Student> students = university.getStudents(faculty);
                    System.out.println(faculty.getName() + ": " + students.size() + " students");
                }
                break;
            case 2:
                sortStudentMenu();
                break;
            case 3:
                sortTeachersMenu();
                break;
        }
    }

    /**
     * Displays and handles the teacher sorting menu, allowing users to sort teachers by surname within faculties or departments.
     */
    private void sortTeachersMenu() {
        System.out.println("---SORT TEACHERS---");
        System.out.println("1 - sort teachers in faculty by surname");
        System.out.println("2 - sort teachers in department by surname");
        System.out.println("0 - Exit");

        switch (readInt()) {
            case 1: {
                List<Teacher> teachers = new ArrayList<>();
                Faculty faculty = chooseFaculty();
                for (Teacher t: university.getTeachers()) {
                    if (faculty.getCode() == t.getFaculty().getCode())
                        teachers.add(t);
                }

                List<Teacher> sortedBySurInFac = TeacherService.sortByName(teachers);
                for (Teacher t: sortedBySurInFac)
                    System.out.println(t);
                break;
            }
            case 2: {
                List<Teacher> teachers = new ArrayList<>();
                Department department = chooseDepartment();
                for (Teacher t: university.getTeachers()) {
                    if (department.getCode() == t.getDepartment().getCode())
                        teachers.add(t);
                }

                List<Teacher> sortedBySurInDep = TeacherService.sortByName(teachers);
                for (Teacher t: sortedBySurInDep)
                    System.out.println(t);
                break;
            }
        }
    }

    /**
     * Displays and handles the student sorting menu, allowing users to sort students by various criteria.
     */
    private void sortStudentMenu() {
        System.out.println("---SORT STUDENTS---");
        System.out.println("1 - sort all students by course");
        System.out.println("2 - sort students in faculty by surname");
        System.out.println("3 - sort students in department by course");
        System.out.println("4 - sort students in department by surname");
        System.out.println("5 - find students by course in department and sort by surname");
        System.out.println("0 - Exit");

        switch (readInt()) {
            case 1: {
                List<Student> sortedByCourse = StudentService.sortByCourse(university.getStudents());
                for (Student s : sortedByCourse)
                    System.out.println(s);
                break;
            }
            case 2: {
                List<Student> studentsInFaculty = new ArrayList<>();
                Faculty faculty = chooseFaculty();
                for (Student s : university.getStudents()) {
                    if (s.getFaculty().getCode() == faculty.getCode())
                        studentsInFaculty.add(s);
                }
                //------------------ перевірку в метод пасувало би занести і інші кейси перевіряти --------------------------
                if (!studentsInFaculty.isEmpty()) {
                    List<Student> sortedBySurInFac = StudentService.sortByGroup(studentsInFaculty);
                    for (Student s : sortedBySurInFac)
                        System.out.println(s);
                } else {
                    System.out.println("This faculty has no students");
                }
                break;
            }
            case 3: {
                List<Student> studentsInDepartment = new ArrayList<>();
                Department department = chooseDepartment();
                for (Student s : university.getStudents()) {
                    if (s.getDepartment().getCode() == department.getCode())
                        studentsInDepartment.add(s);
                }

                List<Student> sortedByCourseInDep = StudentService.sortByCourse(studentsInDepartment);
                for (Student s : sortedByCourseInDep)
                    System.out.println(s);
                break;
            }
            case 4: {
                List<Student> studentsInDepartment = new ArrayList<>();
                Department department = chooseDepartment();
                for (Student s : university.getStudents()) {
                    if (s.getDepartment().getCode() == department.getCode())
                        studentsInDepartment.add(s);
                }

                List<Student> sortedBySurInDep = StudentService.sortByGroup(studentsInDepartment);
                for (Student s : sortedBySurInDep)
                    System.out.println(s);
                break;
            }

            case 5: {
                List<Student> studentsInDepartment = new ArrayList<>();
                Department department = chooseDepartment();
                int course = chooseCourse();
                for (Student s : university.getStudents()) {
                    if (s.getDepartment().getCode() == department.getCode() && course == s.getCourse())
                        studentsInDepartment.add(s);
                }

                List<Student> sortedBySurInDep = StudentService.sortByGroup(studentsInDepartment);
                for (Student s : sortedBySurInDep)
                    System.out.println(s);
                break;
            }

        }
    }

    /**
     * Prompts the user to choose a course number between 1 and 4.
     *
     * @return the chosen course number
     */
    private int chooseCourse() {
        int course = 0;
        while (course < 1 || course > 4) {
            System.out.println("Choose course (1-4): ");
            course = readInt();
        }

        return course;
    }

    /**
     * Displays and handles the user management menu for tech admins.
     */
    private void userMenu() {
        System.out.println("\n--- USERS ---");
        System.out.println("1 - List users");
        System.out.println("2 - Create User");
        System.out.println("3 - Change password");
        System.out.println("4 - Delete User");
        System.out.println("0 - Exit");

        switch (readInt("Chose: ")) {
            case 1: {
                for (User user : users) {
                    System.out.println(user);
                }
                break;
            }
            case 2: {
                String login = readString("Enter user login: ");
                String password = readString("Enter user password: ");
                UserRole userRole = chooseEnum(UserRole.class);

                User user = new User(login, password, userRole);
                users.add(user);
                System.out.println("User added -> " + user);
                break;
            }
            case 3: {
                User user = askUser();

                System.out.println("Enter old password: ");
                String oldPassword = readString();

                System.out.println("Enter new password: ");
                String newPassword = readString();

                try {
                    user.changePassword(oldPassword, newPassword);
                    System.out.println("Password successfully changed!");
                } catch (IllegalArgumentException e) {
                    System.out.println(e.getMessage());
                }
                break;
            }
            case 4: {
                users.remove(askUser());
                System.out.println("User successfully removed!");
                break;
            }
        }
    }

    /**
     * Prompts the user to enter a login and returns the corresponding User object if found.
     *
     * @return the User object corresponding to the entered login
     */
    private User askUser() {
        String login;
        while (true) {
            login = readString("Enter user login: ");
            if (users.contains(new User(login, "1111", UserRole.EXPLORER))) break;
            System.out.println("User with login: " + login + " - not found!");
        }
        for (User user: users) {
            if (user.login.equals(login)) return user;
        }
        return null;
    }

    /**
     * Generic method to allow the user to select an enum value from a list of options.
     *
     * @param enumClass the class of the enum to choose from
     * @param <T>       the type of the enum
     * @return the selected enum value
     */
    private <T extends Enum<T>> T chooseEnum(Class<T> enumClass) {
        T[] elements = enumClass.getEnumConstants();

        while (true) {
            for (int i = 0; i < elements.length; i++) {
                System.out.println((i+1) + " - " + elements[i]);
            }

            int choice = readInt("Enter number: ");

            if (choice > 0 && choice <= elements.length) return elements[choice-1];
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
                System.out.println("Error: faculty not found.");
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
                System.out.println("Error: department not found.");
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
     * Safely reads an integer from console input with a prompt.
     *
     * @param prompt the message to display to the user before input
     * @return integer value entered by the user
     */
    private int readInt(String prompt) {
        while (true) {
            try {
                System.out.print(prompt);

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

    /**
     * Safely reads a non-empty string from console input with a prompt.
     *
     * @param prompt the message to display to the user before input
     * @return non-empty string entered by the user
     */
    private String readString(String prompt) {
        while (true) {
            System.out.print(prompt);
            String result = scanner.nextLine();
            if (result.trim().isEmpty()) {
                System.out.print("Input cannot be empty. Please enter a valid string: ");
                continue;
            }
            return result;
        }
    }
}
