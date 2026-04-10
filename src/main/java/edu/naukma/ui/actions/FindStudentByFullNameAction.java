package edu.naukma.ui.actions;

import edu.naukma.repository.Repository;
import edu.naukma.domain.Student;
import edu.naukma.util.InputUtil;
import edu.naukma.service.StudentService;

public class FindStudentByFullNameAction implements Action {
    Repository<Student> studentRepository;

    public FindStudentByFullNameAction(Repository<Student> studentRepository) {
        if (studentRepository == null) throw new IllegalArgumentException("Student repository can not be null!");

        this.studentRepository = studentRepository;
    }

    @Override
    public void execute() {
        String name = InputUtil.readString("Enter student's full name: ");
        StudentService.listStudents(studentRepository.findBy(s -> s.getFullName().equalsIgnoreCase(name)));
    }
}
