package edu.naukma.actions;

import edu.naukma.Repository;
import edu.naukma.Student;
import edu.naukma.console.InputUtils;
import edu.naukma.services.StudentService;

public class FindStudentByFullNameAction implements Action {
    Repository<Student> studentRepository;

    public FindStudentByFullNameAction(Repository<Student> studentRepository) {
        if (studentRepository == null) throw new IllegalArgumentException("Student repository can not be null!");

        this.studentRepository = studentRepository;
    }

    @Override
    public void execute() {
        String name = InputUtils.readString("Enter student's full name: ");
        StudentService.listStudents(studentRepository.findBy(s -> s.getFullName().equalsIgnoreCase(name)));
    }
}
