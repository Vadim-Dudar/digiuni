package edu.naukma.console;

import edu.naukma.User;
import edu.naukma.UserRole;

import java.awt.*;

public class Try {
    public static void main(String[] args) {
        MenuInterface menuInterface = new MenuInterface();
        menuInterface.addUser(new User("super", "1234", UserRole.TECH_ADMIN));
        menuInterface.addUser(new User("admin", "1234", UserRole.ADMIN));
        menuInterface.addUser(new User("explorer", "1234", UserRole.EXPLORER));

        // Create menu branches
        MenuBranch university = new MenuBranch("Manage University");
        MenuBranch faculties = new MenuBranch("Manage Faculties");
        MenuBranch departments = new MenuBranch("Manage Departments");
        MenuBranch teachers = new MenuBranch("Manage Teachers");
        MenuBranch students = new MenuBranch("Manage Students");
        MenuBranch reports = new MenuBranch("Reports");
        MenuBranch users = new MenuBranch("Manage Users");
        menuInterface.addMenuBranch(university);
        menuInterface.addMenuBranch(faculties);
        menuInterface.addMenuBranch(departments);
        menuInterface.addMenuBranch(teachers);
        menuInterface.addMenuBranch(students);
        menuInterface.addMenuBranch(reports);
        menuInterface.addMenuBranch(users);

        // Add menu items to University branch
        MenuItem showUniversity = new MenuItem(1, "Show University", () -> System.out.println("Not realized yet!"), UserRole.EXPLORER);
        university.addMenuItem(showUniversity);

        // Add menu items to Faculties branch
        MenuItem listFaculties = new MenuItem(1, "List Faculties", () -> System.out.println("Not realized yet!"), UserRole.EXPLORER);
        MenuItem addFaculty = new MenuItem(2, "Add Faculty", () -> System.out.println("Not realized yet!"), UserRole.ADMIN);
        MenuItem deleteFaculty = new MenuItem(3, "Delete Faculty", () -> System.out.println("Not realized yet!"), UserRole.ADMIN);
        MenuItem editFaculty = new MenuItem(4, "Edit Faculty", () -> System.out.println("Not realized yet!"), UserRole.ADMIN);
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

        menuInterface.run();
    }
}

