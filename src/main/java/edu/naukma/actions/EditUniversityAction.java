package edu.naukma.actions;

import edu.naukma.University;
import edu.naukma.console.InputUtils;

public class EditUniversityAction implements Action {
    private University university;

    public EditUniversityAction(University university) {
        if (university == null) throw new IllegalArgumentException("University can noy be null!");
        this.university = university;
    }

    @Override
    public void execute() {
        university.setFullName(InputUtils.readString("Enter new full name: "));
        university.setShortName(InputUtils.readString("Enter new short name: "));
        university.setCity(InputUtils.readString("Enter new city: "));
        university.setAddress(InputUtils.readString("Enter new address: "));
    }
}
