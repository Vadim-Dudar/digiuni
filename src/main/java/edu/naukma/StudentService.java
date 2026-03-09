package edu.naukma;

import java.util.ArrayList;
import java.util.List;

public class StudentService {
    public static List<Student> sortByCourse(List<Student> students) {
        List<Student> result = new ArrayList<>(students);
        result.sort(StudentComparators.byCourse);

        return result;
    }

    public static List<Student> sortByGroup(List<Student> students) {
        List<Student> result = new ArrayList<>(students);
        result.sort(StudentComparators.bySurname);

        return result;
    }
}
