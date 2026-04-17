package edu.naukma.ui.actions;

import edu.naukma.domain.Faculty;
import edu.naukma.repository.Repository;
import edu.naukma.util.InputUtil;

import java.util.List;

public class AddFacultyAction implements Action {
    private Repository<Faculty> faculties;

    public AddFacultyAction(Repository<Faculty> faculties) {
        if (faculties.getAll() == null || faculties.getAll().isEmpty()) throw new IllegalArgumentException("Faculties can not be empty or null!");

        this.faculties = faculties;
    }

    @Override
    public void execute() {
        int code = InputUtil.readInt("Enter faculty code: ");
        String name = InputUtil.readString("Enter faculty name: ");
        String shortName = InputUtil.readString("Enter faculty short name: ");
        String contacts = InputUtil.readString("Enter faculty contacts: ");

        Faculty newFaculty = new Faculty(code, name, shortName, null, contacts);
        faculties.addItem(newFaculty);
        System.out.println("Faculty successfully added: " + newFaculty);
    }
}
