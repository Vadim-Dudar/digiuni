package edu.naukma.services;

import edu.naukma.Faculty;
import edu.naukma.console.InputUtils;

import java.util.List;

public class FacultyService {
    public static Faculty chooseFaculty(List<Faculty> faculties) {
        if (faculties.isEmpty()) {
            System.out.println("Faculty list is empty!");
            return null;
        }
        System.out.println("Available faculties:");
        for (Faculty f : faculties) {
            System.out.println("[" + f.getId() + "] " + f.getShortName() + " : " + f.getContacts());
        }
        while (true) {
            int choise = InputUtils.readInt("Choose: ");
            if (choise > 0 && choise <= faculties.size()) return faculties.get(choise-1);
            else System.out.println("Choose correct faculty! ");
        }
    }
}
