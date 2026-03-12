package edu.naukma;

import edu.naukma.actions.AddFacultyAction;
import edu.naukma.actions.EditFacultyAction;
import edu.naukma.actions.EditUniversityAction;
import edu.naukma.actions.ShowUniversityAction;
import edu.naukma.console.*;
import edu.naukma.console.MenuItem;
import edu.naukma.services.FacultyService;

import java.awt.*;

public class Main {
    public static void main(String[] args) {
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
        university.addTeacher(new Teacher("Petro", "Kovalenko", "Ivanovych", "1975.12.12", "+380501112233", "petro.kovalenko@edu.com", 3, TeacherPosition.PROFESSOR, AcademicDegree.DOCTOR_OF_SCIENCE, AcademicStage.PHD, "01.09.2005", 25, university.getFaculty(2).orElseThrow(), university.getDepartment(2).orElseThrow()));
        university.addTeacher(new Teacher("Svitlana", "Bondarenko", "Mykolaivna", "1980.03.03", "+380501112234", "svitlana.bondarenko@edu.com", 4, TeacherPosition.ASSOCIATE_PROFESSOR, AcademicDegree.DOCTOR_OF_SCIENCE, AcademicStage.POSTGRADUATE, "01.09.2010", 16, university.getFaculty(2).orElseThrow(), university.getDepartment(2).orElseThrow()));
        university.addTeacher(new Teacher("Oleh", "Melnyk", "Petrovych", "1982.07.02", "+380501112235", "oleh.melnyk@edu.com", 5, TeacherPosition.SENIOR_LECTURER, AcademicDegree.MASTER, AcademicStage.POSTGRADUATE, "01.09.2012", 12, university.getFaculty(2).orElseThrow(), university.getDepartment(3).orElseThrow()));

        university.getFaculty(2).orElseThrow().setDean(university.getTeacher(3).orElseThrow());
        university.getDepartment(2).orElseThrow().setHead(university.getTeacher(4).orElseThrow());

        // Teachers for Faculty 3
        university.addTeacher(new Teacher("Natalia", "Honchar", "Petrivna", "1978.05.05", "+380501112236", "natalia.honchar@edu.com", 6, TeacherPosition.PROFESSOR, AcademicDegree.DOCTOR_OF_SCIENCE, AcademicStage.PHD, "01.09.2003", 22, university.getFaculty(3).orElseThrow(), university.getDepartment(4).orElseThrow()));
        university.addTeacher(new Teacher("Mykhailo", "Kravchuk", "Oleksandrovych", "1981.09.09", "+380501112237", "mykhailo.kravchuk@edu.com", 7, TeacherPosition.ASSOCIATE_PROFESSOR, AcademicDegree.DOCTOR_OF_SCIENCE, AcademicStage.POSTGRADUATE, "01.09.2008", 17, university.getFaculty(3).orElseThrow(), university.getDepartment(4).orElseThrow()));
        university.addTeacher(new Teacher("Anna", "Feschenko", "Serhiivna", "1987.06.30", "+380501112238", "anna.feschenko@edu.com", 8, TeacherPosition.LECTURER, AcademicDegree.MASTER, AcademicStage.GRADUATE, "01.09.2014", 9, university.getFaculty(3).orElseThrow(), university.getDepartment(5).orElseThrow()));

        university.getFaculty(3).orElseThrow().setDean(university.getTeacher(6).orElseThrow());
        university.getDepartment(4).orElseThrow().setHead(university.getTeacher(7).orElseThrow());

        // Students
        university.addStudent(new Student("Oleksii", "Sydorov", "Petrovych", "2000.10.10", "+380111222333", "sydorov@gmail.com", 1, 1, university.getFaculty(3).orElseThrow(), university.getDepartment(3).orElseThrow(), 645, 2025, StudyForm.STATE_FUNDED, StudentStatus.STUDYING));
        university.addStudent(new Student("Viktor", "Lysenko", "Ihorevych", "2001.02.01", "+380501000001", "viktor.lysenko@gmail.com", 2, 2, university.getFaculty(2).orElseThrow(), university.getDepartment(1).orElseThrow(),123, 2024, StudyForm.CONTRACT, StudentStatus.STUDYING));
        university.addStudent(new Student("Olena", "Shevchenko", "Oleksiivna", "1999.04.12", "+380501000002", "olena.shevchenko@gmail.com", 3, 3, university.getFaculty(2).orElseThrow(), university.getDepartment(3).orElseThrow(), 124, 2023, StudyForm.STATE_FUNDED, StudentStatus.EXPELLED));
        university.addStudent(new Student("Roman", "Tkachenko", "Volodymyrovych", "2002.08.20", "+380501000003", "roman.tkachenko@gmail.com", 4, 1, university.getFaculty(3).orElseThrow(), university.getDepartment(2).orElseThrow(), 125, 2026, StudyForm.CONTRACT, StudentStatus.STUDYING));
        university.addStudent(new Student("Inna", "Koval", "Serhiivna", "2003.10.15", "+380501000004", "inna.koval@gmail.com", 5, 4, university.getFaculty(3).orElseThrow(), university.getDepartment(1).orElseThrow(), 126, 2025, StudyForm.STATE_FUNDED, StudentStatus.ACADEMIC_LEAVE));
        university.addStudent(new Student("Dmytro", "Babenko", "Petrovych", "2000.02.23", "+380501000005", "dmytro.babenko@gmail.com", 6, 2, university.getFaculty(2).orElseThrow(), university.getDepartment(1).orElseThrow(),127, 2022, StudyForm.CONTRACT, StudentStatus.EXPELLED));


        MenuInterface menuInterface = new MenuInterface();
        menuInterface.setUniversity(university);
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
        MenuBranch users = new MenuBranch("Manage Users");
        menuInterface.addMenuBranch(universityBranch);
        menuInterface.addMenuBranch(faculties);
        menuInterface.addMenuBranch(departments);
        menuInterface.addMenuBranch(teachers);
        menuInterface.addMenuBranch(students);
        menuInterface.addMenuBranch(reports);
        menuInterface.addMenuBranch(users);

        // Add menu items to University branch
        MenuItem showUniversity = new MenuItem(1, "Show University", new ShowUniversityAction(university), UserRole.EXPLORER);
        MenuItem editUniversity = new MenuItem(2, "Edit University", new EditUniversityAction(university), UserRole.ADMIN);
        universityBranch.addMenuItem(showUniversity);
        universityBranch.addMenuItem(editUniversity);

        // Add menu items to Faculties branch
        MenuItem listFaculties = new MenuItem(1, "List Faculties", () -> FacultyService.listFaculties(university.getFaculties()), UserRole.EXPLORER);
        MenuItem addFaculty = new MenuItem(2, "Add Faculty", new AddFacultyAction(university.getFaculties()), UserRole.ADMIN);
        MenuItem deleteFaculty = new MenuItem(3, "Delete Faculty", () -> FacultyService.deleteFaculty(university.getFaculties()), UserRole.ADMIN);
        MenuItem editFaculty = new MenuItem(4, "Edit Faculty", new EditFacultyAction(university.getFaculties(), university.getTeachers()), UserRole.ADMIN);
        faculties.addMenuItem(listFaculties);
        faculties.addMenuItem(addFaculty);
        faculties.addMenuItem(deleteFaculty);
        faculties.addMenuItem(editFaculty);

        // Add menu items to Departments branch
        MenuItem listDepartments = new MenuItem(1, "List Departments", () -> System.out.println("Not realized yet!"), UserRole.EXPLORER);
        MenuItem addDepartment = new MenuItem(2, "Add Department", () -> System.out.println("Not realized yet!"), UserRole.ADMIN);
        MenuItem deleteDepartment = new MenuItem(3, "Delete Department", () -> System.out.println("Not realized yet!"), UserRole.ADMIN);
        MenuItem editDepartment = new MenuItem(4, "Edit Department", () -> System.out.println("Not realized yet!"), UserRole.ADMIN);
        departments.addMenuItem(listDepartments);
        departments.addMenuItem(addDepartment);
        departments.addMenuItem(deleteDepartment);
        departments.addMenuItem(editDepartment);

        // Add menu items to Teachers branch
        MenuItem listTeachers = new MenuItem(1, "List Teachers", () -> System.out.println("Not realized yet!"), UserRole.EXPLORER);
        MenuItem findTeacherByDepartment = new MenuItem(2, "Find Teacher by Department", () -> System.out.println("Not realized yet!"), UserRole.EXPLORER);
        MenuItem addTeacher = new MenuItem(3, "Add Teacher", () -> System.out.println("Not realized yet!"), UserRole.ADMIN);
        MenuItem deleteTeacher = new MenuItem(4, "Delete Teacher", () -> System.out.println("Not realized yet!"), UserRole.ADMIN);
        MenuItem editTeacher = new MenuItem(5, "Edit Teacher", () -> System.out.println("Not realized yet!"), UserRole.ADMIN);
        teachers.addMenuItem(listTeachers);
        teachers.addMenuItem(findTeacherByDepartment);
        teachers.addMenuItem(addTeacher);
        teachers.addMenuItem(deleteTeacher);
        teachers.addMenuItem(editTeacher);

        // Add menu items to Students branch
        MenuItem listStudents = new MenuItem(1, "List Students", () -> System.out.println("Not realized yet!"), UserRole.EXPLORER);
        MenuItem findStudentByFullName = new MenuItem(2, "Find Student by Full Name", () -> System.out.println("Not realized yet!"), UserRole.EXPLORER);
        MenuItem findStudentByCourse = new MenuItem(3, "Find Student by Course", () -> System.out.println("Not realized yet!"), UserRole.EXPLORER);
        MenuItem findStudentByGroup = new MenuItem(4, "Find Student by Group", () -> System.out.println("Not realized yet!"), UserRole.EXPLORER);
        MenuItem addStudent = new MenuItem(5, "Add Student", () -> System.out.println("Not realized yet!"), UserRole.ADMIN);
        MenuItem deleteStudent = new MenuItem(6, "Delete Student", () -> System.out.println("Not realized yet!"), UserRole.ADMIN);
        MenuItem editStudent = new MenuItem(7, "Edit Student", () -> System.out.println("Not realized yet!"), UserRole.ADMIN);
        students.addMenuItem(listStudents);
        students.addMenuItem(findStudentByFullName);
        students.addMenuItem(findStudentByCourse);
        students.addMenuItem(findStudentByGroup);
        students.addMenuItem(addStudent);
        students.addMenuItem(deleteStudent);
        students.addMenuItem(editStudent);

        // Add menu items to Reports branch
        MenuItem studentsInFacultyReport = new MenuItem(1, "Students in Faculty Report", () -> System.out.println("Not realized yet!"), UserRole.EXPLORER);
        MenuItem sortStudent = new MenuItem(2, "Sort Students", () -> System.out.println("Not realized yet!"), UserRole.EXPLORER);
        MenuItem sortTeachers = new MenuItem(3, "Sort Teachers", () -> System.out.println("Not realized yet!"), UserRole.EXPLORER);
        reports.addMenuItem(studentsInFacultyReport);
        reports.addMenuItem(sortStudent);
        reports.addMenuItem(sortTeachers);

        // Add menu items to Users branch
        MenuItem listUsers = new MenuItem(1, "List Users", () -> System.out.println("Not realized yet!"), UserRole.TECH_ADMIN);
        MenuItem createUser = new MenuItem(2, "Create User", () -> System.out.println("Not realized yet!"), UserRole.TECH_ADMIN);
        MenuItem changePassword = new MenuItem(3, "Change Password", () -> System.out.println("Not realized yet!"), UserRole.TECH_ADMIN);
        MenuItem deleteUser = new MenuItem(5, "Delete User", () -> System.out.println("Not realized yet!"), UserRole.TECH_ADMIN);
        users.addMenuItem(listUsers);
        users.addMenuItem(createUser);
        users.addMenuItem(deleteUser);
        users.addMenuItem(changePassword);

        mainBanner();
        menuInterface.run();
    }

    /**
     * Prints the main banner of the application to the console.
     */
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
        System.out.println("*        Created by: Dudar Vadim & Demkiv Max       *");
        System.out.println("*                                                   *");
        System.out.println("*****************************************************");
    }
}

