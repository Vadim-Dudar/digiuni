package edu.naukma;

import edu.naukma.ui.actions.*;
import edu.naukma.ui.cli.MenuBranch;
import edu.naukma.ui.cli.MenuInterface;
import edu.naukma.ui.cli.MenuItem;
import edu.naukma.domain.*;
import edu.naukma.service.*;
import edu.naukma.util.InputUtil;
import lombok.extern.slf4j.Slf4j;

import java.awt.*;
import java.util.Comparator;
import java.util.stream.Collectors;

@Slf4j
public class Main {

    public static void main(String[] args) {

        final University university;

        University tempUniversity;
        try {
            tempUniversity = DataService.loadUniversity();
            log.debug("Loaded from file");
        } catch (RuntimeException e) {
            log.error(String.valueOf(e));
            tempUniversity = createUniversity();
            log.debug("Created from hardcode method");
        }
        university = tempUniversity;

        MenuInterface menuInterface = new MenuInterface();
        menuInterface.addUser(new User("super", "1234", UserRole.TECH_ADMIN));
        menuInterface.addUser(new User("admin", "1234", UserRole.ADMIN));
        menuInterface.addUser(new User("explorer", "1234", UserRole.EXPLORER));

        // Create menu branches
        MenuBranch universityBranch = new MenuBranch("Manage University");
        MenuBranch faculties = new MenuBranch("Manage Faculties");
        MenuBranch departments = new MenuBranch("Manage Departments");
        MenuBranch teachers = new MenuBranch("Manage Teachers");
        MenuBranch students = new MenuBranch("Manage Students");
        MenuBranch reports = new MenuBranch("Reports");
        MenuBranch sorting = new MenuBranch("Sorting");
        MenuBranch users = new MenuBranch("Manage Users");

        menuInterface.addMenuBranch(universityBranch);
        menuInterface.addMenuBranch(faculties);
        menuInterface.addMenuBranch(departments);
        menuInterface.addMenuBranch(teachers);
        menuInterface.addMenuBranch(students);
        menuInterface.addMenuBranch(reports);
        menuInterface.addMenuBranch(sorting);
        menuInterface.addMenuBranch(users);

        // Add menu items to University branch
        MenuItem showUniversity = new MenuItem(1, "Show University", new ShowUniversityAction(university), UserRole.EXPLORER);
        MenuItem editUniversity = new MenuItem(2, "Edit University", new EditUniversityAction(university), UserRole.ADMIN);
        MenuItem loadUniversity = new MenuItem(3, "Load University from file", () -> DataService.loadUniversity(university), UserRole.ADMIN);
        MenuItem saveUniversity = new MenuItem(4, "Save University", () -> DataService.saveUniversity(university), UserRole.ADMIN);
        MenuItem templateUniversity = new MenuItem(5, "Use template University", Main::createUniversity, UserRole.TECH_ADMIN);
        universityBranch.addMenuItem(showUniversity);
        universityBranch.addMenuItem(editUniversity);
        universityBranch.addMenuItem(loadUniversity);
        universityBranch.addMenuItem(saveUniversity);
        universityBranch.addMenuItem(templateUniversity);

        // Add menu items to Faculties branch
        faculties.addMenuItem(new MenuItem(1, "List Faculties", () -> FacultyService.listFaculties(university.getFaculties()), UserRole.EXPLORER));
        faculties.addMenuItem(new MenuItem(2, "Add Faculty", new AddFacultyAction(university.getFacultiesRepository()), UserRole.ADMIN));
        faculties.addMenuItem(new MenuItem(3, "Delete Faculty", () -> FacultyService.deleteFaculty(university.getFacultiesRepository()), UserRole.ADMIN));
        faculties.addMenuItem(new MenuItem(4, "Edit Faculty", new EditFacultyAction(university.getFacultiesRepository(), university.getTeachersRepository()), UserRole.ADMIN));

        // Add menu items to Departments branch
        departments.addMenuItem(new MenuItem(1, "List Departments", () -> DepartmentService.listDepartments(university.getDepartments()), UserRole.EXPLORER));
        departments.addMenuItem(new MenuItem(2, "Add Department", () -> DepartmentService.addDepartment(university.getFacultiesRepository(), university.getTeachers()), UserRole.ADMIN));
        departments.addMenuItem(new MenuItem(3, "Delete Department", () -> DepartmentService.deleteDepartment(university.getDepartments(), university.getFacultiesRepository()), UserRole.ADMIN));
        departments.addMenuItem(new MenuItem(4, "Edit Department", () -> DepartmentService.editDepartment(university.getDepartments(), university.getFaculties(), university.getTeachers()), UserRole.ADMIN));

        // Add menu items to Teachers branch
        teachers.addMenuItem(new MenuItem(1, "List Teachers", () -> TeacherService.listTeachers(university.getTeachers()), UserRole.EXPLORER));
        teachers.addMenuItem(new MenuItem(2, "Find Teacher by Department", () -> TeacherService.listTeachers(university.getTeachers(DepartmentService.chooseDepartment(university.getDepartments()))), UserRole.EXPLORER));
        teachers.addMenuItem(new MenuItem(3, "Add Teacher", () -> TeacherService.addTeacher(university.getTeachersRepository(), university.getFaculties(), university.getDepartments()), UserRole.ADMIN));
        teachers.addMenuItem(new MenuItem(4, "Delete Teacher", () -> TeacherService.deleteTeacher(university.getTeachersRepository()), UserRole.ADMIN));
        teachers.addMenuItem(new MenuItem(5, "Edit Teacher", () -> TeacherService.editTeacher(university.getTeachers(), university.getFaculties(), university.getDepartments()), UserRole.ADMIN));

        // Add menu items to Students branch
        students.addMenuItem(new MenuItem(1, "List Students", () -> StudentService.listStudents(university.getStudents()), UserRole.EXPLORER));
        students.addMenuItem(new MenuItem(2, "Find Student by Full Name", () -> new FindStudentByFullNameAction(university.getStudentsRepository()), UserRole.EXPLORER));
        students.addMenuItem(new MenuItem(3, "Find Student by Course", () -> new FindStudentByCourseAction(university.getStudentsRepository()), UserRole.EXPLORER));
        students.addMenuItem(new MenuItem(4, "Find Student by Group", () -> new FindStudentByCourseAction(university.getStudentsRepository()), UserRole.EXPLORER));
        students.addMenuItem(new MenuItem(5, "Add Student", () -> StudentService.addStudent(university.getStudentsRepository(), university.getFaculties(), university.getDepartments()), UserRole.ADMIN));
        students.addMenuItem(new MenuItem(6, "Delete Student", () -> StudentService.deleteStudent(university.getStudentsRepository()), UserRole.ADMIN));
        students.addMenuItem(new MenuItem(7, "Edit Student", () -> StudentService.editStudent(university.getStudents(), university.getFaculties(), university.getDepartments()), UserRole.ADMIN));

        // --- REPORTS BRANCH ---
        MenuItem studentsInFacultyReport = new MenuItem(1, "Students in Faculty Report (Stream)", () -> {
            university.getFaculties().stream()
                    .forEach(f -> {
                        long count = university.getStudents().stream()
                                .filter(s -> s.getFaculty().getId() == f.getId())
                                .count();
                        System.out.println("Faculty: " + f.getName() + " | Students: " + count);
                    });
        }, UserRole.EXPLORER);

        MenuItem studentStatusReport = new MenuItem(2, "Student Status Report (Grouping)", () -> {
            System.out.println("--- Summary by Status ---");
            university.getStudents().stream()
                    .collect(Collectors.groupingBy(Student::getStatus, Collectors.counting()))
                    .forEach((status, count) -> System.out.println(status + ": " + count));
        }, UserRole.EXPLORER);

        MenuItem facultyReport = new MenuItem(3, "Faculty Quick Info (Record)", () -> {
            System.out.println("--- Express help by faculties ---");
            university.getFaculties().stream()
                    .map(f -> new FacultyInfo(
                            f.getId(),
                            f.getName(),
                            f.getDean() != null ? f.getDean().getFullName() : "Not assigned"
                    ))
                    .forEach(info -> System.out.println(
                            "ID: " + info.id() + " | Name: " + info.name() + " | Dean: " + info.deanName()
                    ));
        }, UserRole.EXPLORER);

        MenuItem teacherFullNameSearch = new MenuItem(4, "Search teacher by full name", () -> new FindTeacherByFullNameAction(university.getTeachersRepository()).execute(), UserRole.EXPLORER);
        MenuItem studentFullNameSearch = new MenuItem(5, "Search student by full name", () -> new FindStudentByFullNameAction(university.getStudentsRepository()).execute(), UserRole.EXPLORER);
        MenuItem studentsByCourse = new MenuItem(6, "Search students by course", () -> new FindStudentByCourseAction(university.getStudentsRepository()).execute(), UserRole.EXPLORER);
        MenuItem studentsByCourseInDep = new MenuItem(8, "Students in Department by Course", () -> {
            Department chosenDept = DepartmentService.chooseDepartment(university.getDepartments());
            int course = InputUtil.readCourse("Enter course number (1-6): ");
            System.out.println("--- Students of " + chosenDept.getName() + " | Course: " + course + " ---");
            university.getStudents().stream()
                    .filter(s -> s.getDepartment().getId() == chosenDept.getId())
                    .filter(s -> s.getCourse() == course)
                    .toList()
                    .forEach(info -> System.out.println(
                            "id: " + info.getId() + " | Name: " + info.getFullName() + " | phone number: " + info.getPhone()
                    ));
        }, UserRole.EXPLORER);

        MenuItem studentsByGroup = new MenuItem(8, "Search students by group", () -> new FindStudentByGroupAction(university.getStudentsRepository()).execute(), UserRole.EXPLORER);
        reports.addMenuItem(studentsInFacultyReport);
        reports.addMenuItem(studentStatusReport);
        reports.addMenuItem(facultyReport);
        reports.addMenuItem(teacherFullNameSearch);
        reports.addMenuItem(studentFullNameSearch);
        reports.addMenuItem(studentsByCourse);
        reports.addMenuItem(studentsByCourseInDep);
        reports.addMenuItem(studentsByGroup);


        // --- SORTING BRANCH ---
        sorting.addMenuItem(new MenuItem(1, "Sort Students by Course", () -> StudentService.listStudents(StudentService.sortByCourse(university.getStudents())), UserRole.EXPLORER));
        sorting.addMenuItem(new MenuItem(2, "Sort Students by Group", () -> StudentService.listStudents(StudentService.sortByGroup(university.getStudents())), UserRole.EXPLORER));
        sorting.addMenuItem(new MenuItem(3, "Sort Teachers by Surname", () -> TeacherService.listTeachers(TeacherService.sortByName(university.getTeachers())), UserRole.EXPLORER));
        sorting.addMenuItem(new MenuItem(4, "Sort Teachers by Surname in Department", () -> TeacherService.listTeachers(TeacherService.sortByName(university.getTeachers(DepartmentService.chooseDepartment(university.getDepartments())))), UserRole.EXPLORER));
        sorting.addMenuItem(new MenuItem(5, "Sort Students by Surname in Department", () -> StudentService.listStudents(StudentService.sortByName(university.getStudents(DepartmentService.chooseDepartment(university.getDepartments())))), UserRole.EXPLORER));
        sorting.addMenuItem(new MenuItem(6, "Sort Students by Course in Department", () -> StudentService.listStudents(StudentService.sortByCourse(university.getStudents(DepartmentService.chooseDepartment(university.getDepartments())))), UserRole.EXPLORER));
        sorting.addMenuItem(new MenuItem(7, "Sort Teachers by Surname in Faculty", () -> TeacherService.listTeachers(TeacherService.sortByName(university.getTeachers(FacultyService.chooseFaculty(university.getFaculties())))), UserRole.EXPLORER));
        sorting.addMenuItem(new MenuItem(8, "Sort Students by Surname in Faculty", () -> StudentService.listStudents(StudentService.sortByName(university.getStudents(FacultyService.chooseFaculty(university.getFaculties())))), UserRole.EXPLORER));

        MenuItem studentsByCourseInDepBySurname = new MenuItem(9, "Students in Department by Course by Surname", () -> {
            Department chosenDept = DepartmentService.chooseDepartment(university.getDepartments());
            int course = InputUtil.readCourse("Enter course number (1-6): ");
            System.out.println("--- Students of " + chosenDept.getName() + " | Course: " + course + " ---");
            university.getStudents().stream()
                    .filter(s -> s.getDepartment().getId() == chosenDept.getId())
                    .filter(s -> s.getCourse() == course)
                    .sorted(Comparator.comparing(Student::getFullName))
                    .toList()
                    .forEach(info -> System.out.println(
                            "id: " + info.getId() + " | Name: " + info.getFullName() + " | phone number: " + info.getPhone()
                    ));
        }, UserRole.EXPLORER);
        sorting.addMenuItem(studentsByCourseInDepBySurname);


        // Add menu items to Users branch
        users.addMenuItem(new MenuItem(1, "List Users", () -> UserService.listUsers(menuInterface.getUsers()), UserRole.TECH_ADMIN));
        users.addMenuItem(new MenuItem(2, "Create User", () -> UserService.createUser(menuInterface.getUsers()), UserRole.TECH_ADMIN));
        users.addMenuItem(new MenuItem(3, "Change Password", () -> UserService.changePassword(menuInterface.getUsers()), UserRole.TECH_ADMIN));
        users.addMenuItem(new MenuItem(5, "Delete User", () -> UserService.deleteUser(menuInterface.getUsers()), UserRole.TECH_ADMIN));

        mainBanner();
        menuInterface.run();

        log.info("Application terminated.");
    }

    private static void mainBanner() {
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
        System.out.println("*       Created by: Dudar Vadim & Demkiv Max        *");
        System.out.println("*                                                   *");
        System.out.println("*****************************************************");
    }

    private static University createUniversity() {
        University university = new University(
                "National University of Kyiv-Mohyla Academy",
                "NaUKMA",
                "Kyiv",
                "2 Skovorody Street"
        );

        // Faculty 1 with one department
        university.addFaculty(new Faculty(1, "Faculty of Computer Science", "FCS", null, "+380123456789"));
        university.getFaculty(1).orElseThrow().addDepartment(new Department(1, "Department of Software Engineering", university.getFaculty(1).orElseThrow(), null, "2 Skovorody Street, room 101"));

        // Faculty 2 with two departments
        university.addFaculty(new Faculty(2, "Faculty of Applied Mathematics", "FAM", null, "+380123450001"));
        university.getFaculty(2).orElseThrow().addDepartment(new Department(2, "Department of Mathematical Analysis", university.getFaculty(2).orElseThrow(), null, "5 Nauky Street, room 201"));
        university.getFaculty(2).orElseThrow().addDepartment(new Department(3, "Department of Applied Mathematics", university.getFaculty(2).orElseThrow(), null, "5 Nauky Street, room 202"));

        // Faculty 3 with two departments
        university.addFaculty(new Faculty(3, "Faculty of Physics", "FP", null, "+380123450002"));
        university.getFaculty(3).orElseThrow().addDepartment(new Department(4, "Department of Theoretical Physics", university.getFaculty(3).orElseThrow(), null, "1 Enerhii Street, room 301"));
        university.getFaculty(3).orElseThrow().addDepartment(new Department(5, "Department of Experimental Physics", university.getFaculty(3).orElseThrow(), null, "1 Enerhii Street, room 302"));

        // Teachers for Faculty 1
        university.addTeacher(new Teacher("Ivan", "Petrov", "Serhiiovych", "1980.01.01", "+380123456789", "ivan.petrov@edu.com", 1, TeacherPosition.PROFESSOR, AcademicDegree.DOCTOR_OF_SCIENCE, AcademicStage.PHD, "2010.09.01", 20, university.getFaculty(1).orElseThrow(),  university.getDepartment(1).orElseThrow()));
        university.addTeacher(new Teacher("Maria", "Ivanova", "Oleksandrivna", "1985.10.02", "+380987654321", "maria@edu.com", 2, TeacherPosition.ASSOCIATE_PROFESSOR, AcademicDegree.BACHELOR, AcademicStage.POSTGRADUATE, "2015.08.23", 15, university.getFaculty(1).orElseThrow(), university.getDepartment(4).orElseThrow()));

        university.getFaculty(1).orElseThrow().setDean(university.getTeacher(1).orElseThrow());
        university.getDepartment(1).orElseThrow().setHead(university.getTeacher(2).orElseThrow());

        // Teachers for Faculty 2
        university.addTeacher(new Teacher("Petro", "Kovalenko", "Ivanovych", "1975.12.12", "+380501112233", "petro.kovalenko@edu.com", 3, TeacherPosition.PROFESSOR, AcademicDegree.DOCTOR_OF_SCIENCE, AcademicStage.PHD, "2005.09.01", 25, university.getFaculty(2).orElseThrow(), university.getDepartment(2).orElseThrow()));
        university.addTeacher(new Teacher("Svitlana", "Bondarenko", "Mykolaivna", "1980.03.03", "+380501112234", "svitlana.bondarenko@edu.com", 4, TeacherPosition.ASSOCIATE_PROFESSOR, AcademicDegree.DOCTOR_OF_SCIENCE, AcademicStage.POSTGRADUATE, "2010.09.01", 16, university.getFaculty(2).orElseThrow(), university.getDepartment(2).orElseThrow()));
        university.addTeacher(new Teacher("Oleh", "Melnyk", "Petrovych", "1982.07.02", "+380501112235", "oleh.melnyk@edu.com", 5, TeacherPosition.SENIOR_LECTURER, AcademicDegree.MASTER, AcademicStage.POSTGRADUATE, "2012.09.02", 12, university.getFaculty(2).orElseThrow(), university.getDepartment(3).orElseThrow()));

        university.getFaculty(2).orElseThrow().setDean(university.getTeacher(3).orElseThrow());
        university.getDepartment(2).orElseThrow().setHead(university.getTeacher(4).orElseThrow());

        // Teachers for Faculty 3
        university.addTeacher(new Teacher("Natalia", "Honchar", "Petrivna", "1978.05.05", "+380501112236", "natalia.honchar@edu.com", 6, TeacherPosition.PROFESSOR, AcademicDegree.DOCTOR_OF_SCIENCE, AcademicStage.PHD, "2003.09.01", 22, university.getFaculty(3).orElseThrow(), university.getDepartment(4).orElseThrow()));
        university.addTeacher(new Teacher("Mykhailo", "Kravchuk", "Oleksandrovych", "1981.09.09", "+380501112237", "mykhailo.kravchuk@edu.com", 7, TeacherPosition.ASSOCIATE_PROFESSOR, AcademicDegree.DOCTOR_OF_SCIENCE, AcademicStage.POSTGRADUATE, "2008.09.01", 17, university.getFaculty(3).orElseThrow(), university.getDepartment(4).orElseThrow()));
        university.addTeacher(new Teacher("Anna", "Feschenko", "Serhiivna", "1987.06.30", "+380501112238", "anna.feschenko@edu.com", 8, TeacherPosition.LECTURER, AcademicDegree.MASTER, AcademicStage.GRADUATE, "2014.09.01", 9, university.getFaculty(3).orElseThrow(), university.getDepartment(5).orElseThrow()));

        university.getFaculty(3).orElseThrow().setDean(university.getTeacher(6).orElseThrow());
        university.getDepartment(4).orElseThrow().setHead(university.getTeacher(7).orElseThrow());

        // Students
        university.addStudent(new Student("Oleksii", "Sydorov", "Petrovych", "2000.10.10", "+380111222333", "sydorov@gmail.com", 1, 1, university.getFaculty(3).orElseThrow(), university.getDepartment(3).orElseThrow(), 645, 2025, StudyForm.STATE_FUNDED, StudentStatus.STUDYING));
        university.addStudent(new Student("Viktor", "Lysenko", "Ihorevych", "2001.02.01", "+380501000001", "viktor.lysenko@gmail.com", 2, 2, university.getFaculty(2).orElseThrow(), university.getDepartment(1).orElseThrow(),123, 2024, StudyForm.CONTRACT, StudentStatus.STUDYING));
        university.addStudent(new Student("Olena", "Shevchenko", "Oleksiivna", "1999.04.12", "+380501000002", "olena.shevchenko@gmail.com", 3, 3, university.getFaculty(2).orElseThrow(), university.getDepartment(3).orElseThrow(), 124, 2023, StudyForm.STATE_FUNDED, StudentStatus.EXPELLED));
        university.addStudent(new Student("Roman", "Tkachenko", "Volodymyrovych", "2002.08.20", "+380501000003", "roman.tkachenko@gmail.com", 4, 1, university.getFaculty(3).orElseThrow(), university.getDepartment(2).orElseThrow(), 125, 2026, StudyForm.CONTRACT, StudentStatus.STUDYING));
        university.addStudent(new Student("Inna", "Koval", "Serhiivna", "2003.10.15", "+380501000004", "inna.koval@gmail.com", 5, 4, university.getFaculty(3).orElseThrow(), university.getDepartment(1).orElseThrow(), 126, 2025, StudyForm.STATE_FUNDED, StudentStatus.ACADEMIC_LEAVE));
        university.addStudent(new Student("Dmytro", "Babenko", "Petrovych", "2000.02.23", "+380501000005", "dmytro.babenko@gmail.com", 6, 2, university.getFaculty(2).orElseThrow(), university.getDepartment(1).orElseThrow(),127, 2022, StudyForm.CONTRACT, StudentStatus.EXPELLED));

        return university;
    }
}

