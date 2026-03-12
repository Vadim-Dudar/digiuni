package edu.naukma.actions;

import edu.naukma.Faculty;
import edu.naukma.Teacher;
import edu.naukma.console.InputUtils;
import edu.naukma.services.FacultyService;
import edu.naukma.services.TeacherService;

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
