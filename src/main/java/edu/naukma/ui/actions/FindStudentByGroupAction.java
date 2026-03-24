package edu.naukma.ui.actions;

import edu.naukma.repository.Repository;
import edu.naukma.domain.Student;
import edu.naukma.ui.cli.InputUtils;
import edu.naukma.service.StudentService;

public class FindStudentByGroupAction implements Action {
    Repository<Student> studentRepository;

    public FindStudentByGroupAction(Repository<Student> studentRepository) {
        if (studentRepository == null) throw new IllegalArgumentException("Student repository can not be null!");

        this.studentRepository = studentRepository;
    }

    @Override
    public void execute() {
        int group = InputUtils.readInt("Enter student's group: ");
        StudentService.listStudents(studentRepository.findBy(s -> s.getGroup() == group));
    }
}
