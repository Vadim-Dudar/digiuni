package edu.naukma.ui.actions;

import edu.naukma.domain.Teacher;
import edu.naukma.repository.Repository;
import edu.naukma.domain.Student;
import edu.naukma.service.TeacherService;
import edu.naukma.util.InputUtil;
import edu.naukma.service.StudentService;

public class FindTeacherByFullNameAction implements Action {
    Repository<Teacher> teacherRepository;

    public FindTeacherByFullNameAction(Repository<Teacher> teacherRepository) {
        if (teacherRepository == null) throw new IllegalArgumentException("Teacher repository can not be null!");

        this.teacherRepository = teacherRepository;
    }

    @Override
    public void execute() {
        String name = InputUtil.readString("Enter teacher's full name: ");
        TeacherService.listTeachers(teacherRepository.findBy(s -> s.getFullName().equalsIgnoreCase(name)));
    }
}

