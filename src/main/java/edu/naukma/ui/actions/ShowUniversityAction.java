package edu.naukma.ui.actions;

import edu.naukma.domain.University;

public class ShowUniversityAction implements Action {
    private University university;

    public ShowUniversityAction(University university) {
        if (university == null) throw new IllegalArgumentException("University can not be null");
        this.university = university;
    }

    @Override
    public void execute() {
        System.out.println(university.getFullName() + " (" + university.getShortName() + "); Address: " + university.getCity() + " " + university.getAddress());
    }
}
