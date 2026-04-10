package edu.naukma.ui.actions;

import edu.naukma.domain.University;
import edu.naukma.util.InputUtil;

public class EditUniversityAction implements Action {
    private University university;

    public EditUniversityAction(University university) {
        if (university == null) throw new IllegalArgumentException("University can noy be null!");
        this.university = university;
    }

    @Override
    public void execute() {
        university.setFullName(InputUtil.readString("Enter new full name: "));
        university.setShortName(InputUtil.readString("Enter new short name: "));
        university.setCity(InputUtil.readString("Enter new city: "));
        university.setAddress(InputUtil.readString("Enter new address: "));
    }
}
