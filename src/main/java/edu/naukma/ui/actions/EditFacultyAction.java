package edu.naukma.ui.actions;

import edu.naukma.domain.Faculty;
import edu.naukma.domain.Teacher;
import edu.naukma.ui.cli.InputUtils;
import edu.naukma.service.FacultyService;
import edu.naukma.service.TeacherService;

import java.util.List;

public class EditFacultyAction implements Action {

    private List<Faculty> faculties;
    private List<Teacher> teachers;

    public EditFacultyAction(List<Faculty> faculties, List<Teacher> teachers) {
        if (faculties == null) throw new IllegalArgumentException("Faculty can not be null!");
        this.faculties = faculties;
        this.teachers = teachers;
    }

    @Override
    public void execute() {
        Faculty faculty = FacultyService.chooseFaculty(faculties);

        faculty.setName(InputUtils.readString("Enter new name: "));
        faculty.setShortName(InputUtils.readString("Enter new short name: "));
        faculty.setDean(TeacherService.chooseTeacher(teachers));
        faculty.setContacts(InputUtils.readString("Enter new contacts: "));

        System.out.println("Faculty successfully changed: " + faculty);
    }
}
