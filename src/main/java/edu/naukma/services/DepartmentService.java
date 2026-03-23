package edu.naukma.services;

import edu.naukma.Department;
import edu.naukma.Faculty;
import edu.naukma.Teacher;
import edu.naukma.console.InputUtils;
import lombok.extern.slf4j.Slf4j;

import java.util.List;

@Slf4j
public class DepartmentService {
    public static void listDepartments(List<Department> departments) {
        if (departments.isEmpty()) throw new IllegalArgumentException("Department list can not be empty or null!");
        System.out.println("List of departments:");
        int i = 1;
        for (Department department : departments) {
            System.out.println(i++ + " - " + department);
        }
    }

    public static Department chooseDepartment(List<Department> departments) {
        if (departments.isEmpty()) throw new IllegalArgumentException("Department list can not be empty or null!");

        listDepartments(departments);

        while (true) {
            int choice = InputUtils.readInt("Choose: ");
            if (choice > 0 && choice <= departments.size()) return departments.get(choice - 1);
            System.out.println("Enter proper number from list");
        }
    }

    public static void editDepartment(List<Department> departments, List<Faculty> faculties, List<Teacher> teachers) {
        if (departments == null) throw new IllegalArgumentException("Departments list can not be null!");
        else if (faculties == null) throw new IllegalArgumentException("Faculties list can not be null!");
        else if (teachers == null) throw new IllegalArgumentException("Teachers list can not be null!");

        Department department = chooseDepartment(departments);

        department.setName(InputUtils.readString("Enter new name: "));
        department.setFaculty(FacultyService.chooseFaculty(faculties));
        department.setHead(TeacherService.chooseTeacher(teachers));
        department.setLocation(InputUtils.readString("Enter new location: "));

        System.out.println("Department successfully changed: " + department);
    }

    public static void addDepartment(List<Department> departments, List<Faculty> faculties, List<Teacher> teachers) {
        if (departments == null) throw new IllegalArgumentException("Departments list can not be null!");
        else if (faculties == null) throw new IllegalArgumentException("Faculties list can not be null!");
        else if (teachers == null) throw new IllegalArgumentException("Teachers list can not be null!");

        int code = InputUtils.readInt("Enter department code: ");
        String name = InputUtils.readString("Enter department name: ");
        Faculty faculty = FacultyService.chooseFaculty(faculties);
        Teacher head = TeacherService.chooseTeacher(teachers);
        String location = InputUtils.readString("Enter department location: ");

        Department department = new Department(code, name, faculty, head, location);
        departments.add(department);

        System.out.println("Department successfully added: " + department);
    }

    public static void deleteDepartment(List<Department> departments) {
        if (departments == null) throw new IllegalArgumentException("Departments list can not be null!");

        Department department = chooseDepartment(departments);
        departments.remove(department);

        System.out.println("Department successfully deleted: " + department);

        log.info("Department deleted: {}", department);
    }
}
