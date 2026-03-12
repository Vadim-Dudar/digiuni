package edu.naukma.actions;

import edu.naukma.Repository;
import edu.naukma.Student;
import edu.naukma.console.InputUtils;
import edu.naukma.services.StudentService;

public class FindStudentByCourseAction implements Action {
    Repository<Student> studentRepository;

    public FindStudentByCourseAction(Repository<Student> studentRepository) {
        if (studentRepository == null) throw new IllegalArgumentException("Student repository can not be null!");

        this.studentRepository = studentRepository;
    }

    @Override
    public void execute() {
        int course;
        while (true) {
            course = InputUtils.readInt("Enter student's group: ");
            if (course > 0 && course <= 6) break;
            System.out.println("Course must be between 1 and 6. Please try again.");
        }
        int finalCourse = course;
        StudentService.listStudents(studentRepository.findBy(s -> s.getCourse() == finalCourse));
    }
}
