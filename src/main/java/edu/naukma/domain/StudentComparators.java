package edu.naukma.domain;

import java.util.Comparator;

public class StudentComparators {

    public static Comparator<Student> byCourse = (a, b) -> Integer.compare(a.getCourse(), b.getCourse());
    public static Comparator<Student> bySurname = (a, b) -> a.getSurname().compareTo(b.getSurname());


}
