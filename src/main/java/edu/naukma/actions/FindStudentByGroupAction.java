package edu.naukma.actions;

import edu.naukma.Repository;
import edu.naukma.Student;
import edu.naukma.console.InputUtils;
import edu.naukma.services.StudentService;

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
