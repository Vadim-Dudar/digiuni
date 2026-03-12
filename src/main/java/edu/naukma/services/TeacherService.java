package edu.naukma.services;

import edu.naukma.Teacher;
import edu.naukma.TeacherComparators;
import edu.naukma.console.InputUtils;

import java.util.ArrayList;
import java.util.List;

public class TeacherService {

    public static List<Teacher> sortByName(List<Teacher> teachers) {
        List<Teacher> result = new ArrayList<>(teachers);
        result.sort(TeacherComparators.bySurname);

        return result;
    }

    public static Teacher chooseTeacher(List<Teacher> teachers) {
        if (teachers.isEmpty())
            throw new IllegalArgumentException("Teacher list can not be empty or null!");
        int i = 1;
        for (Teacher teacher : teachers) {
            System.out.println(i++ + " - " + teacher.getFullName());
        }
        while (true) {
            int choice = InputUtils.readInt("Choose: ");
            if (choice > 0 && choice <= teachers.size()) return teachers.get(choice-1);
            System.out.println("Enter proper number from list");
        }
    }
}
