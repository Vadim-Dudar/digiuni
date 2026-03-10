package edu.naukma;

public class Main {
    public static void main(String[] args) {
        University university = new University(
                "National University of Kyiv-Mohyla Academy",
                "NaUKMA",
                "Kyiv",
                "2 Skovorody Street"
        );

// Faculty 1 with one department
        university.addFaculty(new Faculty(1, "Faculty of Computer Science", "FCS", null, "+380123456789"));
        university.getFaculty(1).addDepartment(new Department(1, "Department of Software Engineering", university.getFaculty(1), null, "2 Skovorody Street, room 101"));

// Faculty 2 with two departments
        university.addFaculty(new Faculty(2, "Faculty of Applied Mathematics", "FAM", null, "+380123450001"));
        university.getFaculty(2).addDepartment(new Department(2, "Department of Mathematical Analysis", university.getFaculty(2), null, "5 Nauky Street, room 201"));
        university.getFaculty(2).addDepartment(new Department(3, "Department of Applied Mathematics", university.getFaculty(2), null, "5 Nauky Street, room 202"));

// Faculty 3 with two departments
        university.addFaculty(new Faculty(3, "Faculty of Physics", "FP", null, "+380123450002"));
        university.getFaculty(3).addDepartment(new Department(4, "Department of Theoretical Physics", university.getFaculty(3), null, "1 Enerhii Street, room 301"));
        university.getFaculty(3).addDepartment(new Department(5, "Department of Experimental Physics", university.getFaculty(3), null, "1 Enerhii Street, room 302"));

// Teachers for Faculty 1
        university.addTeacher(new Teacher("Ivan", "Petrov", "Serhiiovych", "1980.01.01", "+380123456789", "ivan.petrov@edu.com", 1, TeacherPosition.PROFESSOR, AcademicDegree.DOCTOR_OF_SCIENCE, AcademicStage.PHD, "2010.09.01", 20, university.getFaculty(1),  university.getDepartment(1)));
        university.addTeacher(new Teacher("Maria", "Ivanova", "Oleksandrivna", "1985.10.02", "+380987654321", "maria@edu.com", 2, TeacherPosition.ASSOCIATE_PROFESSOR, AcademicDegree.BACHELOR, AcademicStage.POSTGRADUATE, "2015.08.23", 15, university.getFaculty(1), university.getDepartment(4)));

        university.getFaculty(1).setDean(university.getTeacher(1));
        university.getDepartment(1).setHead(university.getTeacher(2));

// Teachers for Faculty 2
        university.addTeacher(new Teacher("Petro", "Kovalenko", "Ivanovych", "1975.12.12", "+380501112233", "petro.kovalenko@edu.com", 3, TeacherPosition.PROFESSOR, AcademicDegree.DOCTOR_OF_SCIENCE, AcademicStage.PHD, "01.09.2005", 25, university.getFaculty(2), university.getDepartment(2)));
        university.addTeacher(new Teacher("Svitlana", "Bondarenko", "Mykolaivna", "1980.03.03", "+380501112234", "svitlana.bondarenko@edu.com", 4, TeacherPosition.ASSOCIATE_PROFESSOR, AcademicDegree.DOCTOR_OF_SCIENCE, AcademicStage.POSTGRADUATE, "01.09.2010", 16, university.getFaculty(2), university.getDepartment(2)));
        university.addTeacher(new Teacher("Oleh", "Melnyk", "Petrovych", "1982.07.02", "+380501112235", "oleh.melnyk@edu.com", 5, TeacherPosition.SENIOR_LECTURER, AcademicDegree.MASTER, AcademicStage.POSTGRADUATE, "01.09.2012", 12, university.getFaculty(2), university.getDepartment(3)));

        university.getFaculty(2).setDean(university.getTeacher(3));
        university.getDepartment(2).setHead(university.getTeacher(4));

// Teachers for Faculty 3
        university.addTeacher(new Teacher("Natalia", "Honchar", "Petrivna", "1978.05.05", "+380501112236", "natalia.honchar@edu.com", 6, TeacherPosition.PROFESSOR, AcademicDegree.DOCTOR_OF_SCIENCE, AcademicStage.PHD, "01.09.2003", 22, university.getFaculty(3), university.getDepartment(4)));
        university.addTeacher(new Teacher("Mykhailo", "Kravchuk", "Oleksandrovych", "1981.09.09", "+380501112237", "mykhailo.kravchuk@edu.com", 7, TeacherPosition.ASSOCIATE_PROFESSOR, AcademicDegree.DOCTOR_OF_SCIENCE, AcademicStage.POSTGRADUATE, "01.09.2008", 17, university.getFaculty(3), university.getDepartment(4)));
        university.addTeacher(new Teacher("Anna", "Feschenko", "Serhiivna", "1987.06.30", "+380501112238", "anna.feschenko@edu.com", 8, TeacherPosition.LECTURER, AcademicDegree.MASTER, AcademicStage.GRADUATE, "01.09.2014", 9, university.getFaculty(3), university.getDepartment(5)));

        university.getFaculty(3).setDean(university.getTeacher(6));
        university.getDepartment(4).setHead(university.getTeacher(7));

// Students
        university.addStudent(new Student("Oleksii", "Sydorov", "Petrovych", "2000.10.10", "+380111222333", "sydorov@gmail.com", 1, 1, university.getFaculty(3), university.getDepartment(3), 645, 2025, StudyForm.STATE_FUNDED, StudentStatus.STUDYING));
        university.addStudent(new Student("Viktor", "Lysenko", "Ihorevych", "2001.02.01", "+380501000001", "viktor.lysenko@gmail.com", 2, 2, university.getFaculty(2), university.getDepartment(1),123, 2024, StudyForm.CONTRACT, StudentStatus.STUDYING));
        university.addStudent(new Student("Olena", "Shevchenko", "Oleksiivna", "1999.04.12", "+380501000002", "olena.shevchenko@gmail.com", 3, 3, university.getFaculty(2), university.getDepartment(3), 124, 2023, StudyForm.STATE_FUNDED, StudentStatus.EXPELLED));
        university.addStudent(new Student("Roman", "Tkachenko", "Volodymyrovych", "2002.08.20", "+380501000003", "roman.tkachenko@gmail.com", 4, 1, university.getFaculty(3), university.getDepartment(2), 125, 2026, StudyForm.CONTRACT, StudentStatus.STUDYING));
        university.addStudent(new Student("Inna", "Koval", "Serhiivna", "2003.10.15", "+380501000004", "inna.koval@gmail.com", 5, 4, university.getFaculty(3), university.getDepartment(1), 126, 2025, StudyForm.STATE_FUNDED, StudentStatus.ACADEMIC_LEAVE));
        university.addStudent(new Student("Dmytro", "Babenko", "Petrovych", "2000.02.23", "+380501000005", "dmytro.babenko@gmail.com", 6, 2, university.getFaculty(2), university.getDepartment(1),127, 2022, StudyForm.CONTRACT, StudentStatus.EXPELLED));

        ConsoleMenu menu = new ConsoleMenu(university);
        menu.start();
    }
}
