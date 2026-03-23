package edu.naukma.services;

import edu.naukma.Faculty;
import edu.naukma.console.InputUtils;
import lombok.extern.slf4j.Slf4j;

import java.util.List;

@Slf4j
public class FacultyService {
    public static void listFaculties(List<Faculty> faculties) {
        if (faculties.isEmpty()) {
            System.out.println("Faculty list is empty!");
            return;
        }
        System.out.println("Available faculties:");
        int i = 1;
        for (Faculty f : faculties) {
            System.out.println(i++ +" - Faculty id:  [" + f.getId() + "] name: " + f.getShortName() + " : " + f.getContacts());
        }
    }

    public static Faculty chooseFaculty(List<Faculty> faculties) {
        listFaculties(faculties);
        while (true) {
            int choise = InputUtils.readInt("Choose: ");
            if (choise > 0 && choise <= faculties.size()) return faculties.get(choise-1);
            else System.out.println("Choose correct faculty! ");
        }
    }

    public static void deleteFaculty(List<Faculty> faculties) {
        Faculty faculty = chooseFaculty(faculties);
        faculties.remove(faculty);
        System.out.println("Faculty successfully deleted: " + faculty);

        log.info("Faculty deleted: {}", faculty);
    }
}
