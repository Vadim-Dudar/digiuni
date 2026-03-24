package edu.naukma.service;

import edu.naukma.console.InputUtils;
import edu.naukma.domain.*;
import lombok.extern.slf4j.Slf4j;

import java.util.ArrayList;
import java.util.List;

@Slf4j
public class TeacherService {

    public static List<Teacher> sortByName(List<Teacher> teachers) {
        List<Teacher> result = new ArrayList<>(teachers);
        result.sort(TeacherComparators.bySurname);

        return result;
    }

    public static Teacher chooseTeacher(List<Teacher> teachers) {
        if (teachers.isEmpty())
            throw new IllegalArgumentException("Teacher list can not be empty or null!");

        listTeachers(teachers);

        while (true) {
            int choice = InputUtils.readInt("Choose: ");
            if (choice > 0 && choice <= teachers.size()) return teachers.get(choice - 1);
            System.out.println("Enter proper number from list");
        }
    }

    public static void listTeachers(List<Teacher> teachers) {
        if (teachers.isEmpty()) throw new IllegalArgumentException("Teacher list can not be empty or null!");
        System.out.println("Available teachers:");
        int i = 1;
        for (Teacher teacher : teachers) {
            System.out.println(i++ + " - " + teacher);
        }
    }

    public static void deleteTeacher(List<Teacher> teachers) {
        Teacher teacher = chooseTeacher(teachers);
        teachers.remove(teacher);
        System.out.println("Teacher successfully deleted: " + teacher);

        log.info("Teacher deleted: {}", teacher);
    }

    public static void editTeacher(List<Teacher> teachers, List<Faculty> faculties, List<Department> departments) {
        Teacher teacher = chooseTeacher(teachers);

        PersonService.editPerson(teacher);
        teacher.setPosition(InputUtils.chooseEnum(TeacherPosition.class));
        teacher.setDegree(InputUtils.chooseEnum(AcademicDegree.class));
        teacher.setStage(InputUtils.chooseEnum(AcademicStage.class));
        teacher.setRate(InputUtils.readInt("Enter new rate: "));
        teacher.setFaculty(FacultyService.chooseFaculty(faculties));
        teacher.setDepartment(DepartmentService.chooseDepartment(departments));

        System.out.println("Teacher successfully changed: " + teacher);
        log.info("Teacher edited: {}", teacher);
    }

    public static void addTeacher(List<Teacher> teachers, List<Faculty> faculties, List<Department> departments) {
        System.out.println("Enter teacher's personal information:");
        Person person = PersonService.createPerson();

        int teacherId = InputUtils.readInt("Enter teacher ID: ");
        TeacherPosition position = InputUtils.chooseEnum(TeacherPosition.class);
        AcademicDegree degree = InputUtils.chooseEnum(AcademicDegree.class);
        AcademicStage stage = InputUtils.chooseEnum(AcademicStage.class);
        String dayOfHire = InputUtils.readDate("Enter date of hire (yyyy-MM-dd): ");
        int rate = InputUtils.readInt("Enter rate: ");
        Faculty faculty = FacultyService.chooseFaculty(faculties);
        Department department = DepartmentService.chooseDepartment(departments);

        Teacher teacher = new Teacher(person.getName(), person.getSurname(), person.getMiddleName(),
                person.getDayOfBirth().toString(), person.getPhone(), person.getEmail(),
                teacherId, position, degree, stage, dayOfHire, rate, faculty, department);

        teachers.add(teacher);
        System.out.println("Teacher successfully added: " + teacher);
        log.info("Teacher created: {}", teacher);
    }
}
