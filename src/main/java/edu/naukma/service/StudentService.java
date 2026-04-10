package edu.naukma.service;

import edu.naukma.util.InputUtil;
import edu.naukma.domain.*;
import lombok.extern.slf4j.Slf4j;

import java.util.ArrayList;
import java.util.List;

@Slf4j
public class StudentService {
    public static List<Student> sortByCourse(List<Student> students) {
        List<Student> result = new ArrayList<>(students);
        result.sort(StudentComparators.byCourse);

        return result;
    }

    public static List<Student> sortByGroup(List<Student> students) {
        List<Student> result = new ArrayList<>(students);
        result.sort(StudentComparators.bySurname);

        return result;
    }

    public static void listStudents(List<Student> students) {
        if (students.isEmpty()) throw new IllegalArgumentException("Student list can not be empty or null!");
        System.out.println("List of students:");
        int i = 1;
        for (Student student : students) {
            System.out.println(i++ + " - " + student);
        }
    }

    public static Student chooseStudent(List<Student> students) {
        if (students.isEmpty()) throw new IllegalArgumentException("Student list can not be empty or null!");

        listStudents(students);

        while (true) {
            int choice = InputUtil.readInt("Choose: ");
            if (choice > 0 && choice <= students.size()) return students.get(choice - 1);
            System.out.println("Enter proper number from list");
        }
    }

    public static void addStudent(List<Student> students, List<Faculty> faculties, List<Department> departments) {
        Person person = PersonService.createPerson();
        int studentId = InputUtil.readInt("Enter student ID: ");
        int course = InputUtil.readInt("Enter course: ");
        Faculty faculty = FacultyService.chooseFaculty(faculties);
        Department department = DepartmentService.chooseDepartment(departments);
        int group = InputUtil.readInt("Enter group: ");
        int yearOfEntry = InputUtil.readInt("Enter year of entry: ");
        StudyForm studyForm = InputUtil.chooseEnum(StudyForm.class);
        StudentStatus status = InputUtil.chooseEnum(StudentStatus.class);

        Student student = new Student(person.getName(), person.getSurname(), person.getMiddleName(), person.getDayOfBirth().toString(),
                person.getPhone(), person.getEmail(), studentId, course, faculty, department, group, yearOfEntry, studyForm, status);

        System.out.println("Student successfully added: " + student);
        students.add(student);

        log.info("Student created: {}", student);
    }

    public static void deleteStudent(List<Student> students) {
        Student student = chooseStudent(students);
        students.remove(student);
        System.out.println("Student successfully deleted: " + student);

        log.info("Student deleted: {}", student);
    }

    public static void editStudent(List<Student> students, List<Faculty> faculties, List<Department> departments) {
        Student student = chooseStudent(students);

        PersonService.editPerson(student);
        student.setCourse(InputUtil.readInt("Enter new course: "));
        student.setFaculty(FacultyService.chooseFaculty(faculties));
        student.setDepartment(DepartmentService.chooseDepartment(departments));
        student.setGroup(InputUtil.readInt("Enter new group: "));
        student.setStudyForm(InputUtil.chooseEnum(StudyForm.class));
        student.setStatus(InputUtil.chooseEnum(StudentStatus.class));

        System.out.println("Student successfully changed: " + student);

        log.info("Student edited: {}", student);
    }
}
