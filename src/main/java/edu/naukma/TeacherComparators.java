package edu.naukma;

import java.util.Comparator;

public class TeacherComparators {

    public static Comparator<Teacher> bySurname = (a, b) -> a.getSurname().compareTo(b.getSurname());

}
