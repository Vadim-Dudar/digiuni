package edu.naukma;

import java.util.ArrayList;
import java.util.List;

public class TeacherService {

    public static List<Teacher> sortByName(List<Teacher> teachers) {
        List<Teacher> result = new ArrayList<>(teachers);
        result.sort(TeacherComparators.bySurname);

        return result;
    }
}
