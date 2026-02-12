package edu.naukma;

public class Main {
    public static void main(String[] args) {
        University university = new University(
                "Національний університет 'Києво-Могилянська академія' ",
                "НаУКМА",
                "Київ",
                "вул. Сковороди, 2"
        );
// Faculty 1 with one department
        university.addFaculty(new Faculty(1, "Факультет комп'ютерних наук", "ФКН", null, "+380123456789"));
        university.getFaculty(1).addDepartment(new Department(1, "Кафедра програмної інженерії", university.getFaculty(1), null, "вул. Сковороди, 2, ауд. 101"));

// Faculty 2 with two departments
        university.addFaculty(new Faculty(2, "Факультет прикладної математики", "ФПМ", null, "+380123450001"));
        university.getFaculty(2).addDepartment(new Department(2, "Кафедра математичного аналізу", university.getFaculty(2), null, "вул. Науки, 5, ауд. 201"));
        university.getFaculty(2).addDepartment(new Department(3, "Кафедра прикладної математики", university.getFaculty(2), null, "вул. Науки, 5, ауд. 202"));

// Faculty 3 with two departments
        university.addFaculty(new Faculty(3, "Факультет фізики", "ФФ", null, "+380123450002"));
        university.getFaculty(3).addDepartment(new Department(4, "Кафедра теоретичної фізики", university.getFaculty(3), null, "вул. Енергії, 1, ауд. 301"));
        university.getFaculty(3).addDepartment(new Department(5, "Кафедра експериментальної фізики", university.getFaculty(3), null, "вул. Енергії, 1, ауд. 302"));
// Teachers for Faculty 1
        university.addTeacher(new Teacher("Іван", "Петров", "Сергійович", "01.01.1980", "+380123456789", "ivan.petrov@edu.com", 1, TeacherPosition.PROFESSOR, AcademicDegree.DOCTOR_OF_SCIENCE, AcademicStage.PHD, "01.09.2010", 20, university.getDepartment(1)));
        university.addTeacher(new Teacher("Марія", "Іванова", "Олександрівна", "15.05.1985", "+380987654321", "maria@edu.com", 2, TeacherPosition.ASSOCIATE_PROFESSOR, AcademicDegree.BACHELOR, AcademicStage.POSTGRADUATE, "01.09.2015", 15, university.getDepartment(1)));

        university.getFaculty(1).setDean(university.getTeacher(1));
        university.getDepartment(1).setHead(university.getTeacher(2));

// Teachers for Faculty 2 (one dean, one head of department, one lecturer)
        university.addTeacher(new Teacher("Петро", "Коваленко", "Іванович", "12.12.1975", "+380501112233", "petro.kovalenko@edu.com", 3, TeacherPosition.PROFESSOR, AcademicDegree.DOCTOR_OF_SCIENCE, AcademicStage.PHD, "01.09.2005", 25, university.getDepartment(2)));
        university.addTeacher(new Teacher("Світлана", "Бондаренко", "Миколаївна", "03.03.1980", "+380501112234", "svitlana.bondarenko@edu.com", 4, TeacherPosition.ASSOCIATE_PROFESSOR, AcademicDegree.DOCTOR_OF_SCIENCE, AcademicStage.POSTGRADUATE, "01.09.2010", 16, university.getDepartment(2)));
        university.addTeacher(new Teacher("Олег", "Мельник", "Петрович", "22.07.1982", "+380501112235", "oleg.melnyk@edu.com", 5, TeacherPosition.SENIOR_LECTURER, AcademicDegree.MASTER, AcademicStage.POSTGRADUATE, "01.09.2012", 12, university.getDepartment(3)));

        university.getFaculty(2).setDean(university.getTeacher(3));
        university.getDepartment(2).setHead(university.getTeacher(4));

// Teachers for Faculty 3 (one dean, one head of department, one lecturer)
        university.addTeacher(new Teacher("Наталія", "Гончар", "Петрівна", "05.05.1978", "+380501112236", "natalia.gonchar@edu.com", 6, TeacherPosition.PROFESSOR, AcademicDegree.DOCTOR_OF_SCIENCE, AcademicStage.PHD, "01.09.2003", 22, university.getDepartment(4)));
        university.addTeacher(new Teacher("Михайло", "Кравчук", "Олександрович", "09.09.1981", "+380501112237", "mykhailo.kravchuk@edu.com", 7, TeacherPosition.ASSOCIATE_PROFESSOR, AcademicDegree.DOCTOR_OF_SCIENCE, AcademicStage.POSTGRADUATE, "01.09.2008", 17, university.getDepartment(4)));
        university.addTeacher(new Teacher("Анна", "Фещенко", "Сергіївна", "30.06.1987", "+380501112238", "anna.feschenko@edu.com", 8, TeacherPosition.LECTURER, AcademicDegree.MASTER, AcademicStage.GRADUATE, "01.09.2014", 9, university.getDepartment(5)));

        university.getFaculty(3).setDean(university.getTeacher(6));
        university.getDepartment(4).setHead(university.getTeacher(7));

// 6 students
        university.addStudent(new Student("Олексій", "Сидоров", "Петрович", "10.10.2000", "+380111222333", "sydorov@gmail.com", 1, 1, university.getFaculty(1), 645, 2025, StudyForm.STATE_FUNDED, StudentStatus.STUDYING));
        university.addStudent(new Student("Віктор", "Лисенко", "Ігоревич", "01.02.2001", "+380501000001", "viktor.lysenko@gmail.com", 2, 2, university.getFaculty(2), 123, 2024, StudyForm.CONTRACT, StudentStatus.STUDYING));
        university.addStudent(new Student("Олена", "Шевченко", "Олексіївна", "14.04.1999", "+380501000002", "olena.shevchenko@gmail.com", 3, 3, university.getFaculty(2), 124, 2023, StudyForm.STATE_FUNDED, StudentStatus.EXPELLED));
        university.addStudent(new Student("Роман", "Ткаченко", "Володимирович", "20.08.2002", "+380501000003", "roman.tkachenko@gmail.com", 4, 1, university.getFaculty(3), 125, 2026, StudyForm.CONTRACT, StudentStatus.STUDYING));
        university.addStudent(new Student("Інна", "Коваль", "Сергіївна", "11.11.2003", "+380501000004", "inna.koval@gmail.com", 5, 2, university.getFaculty(3), 126, 2025, StudyForm.STATE_FUNDED, StudentStatus.ACADEMIC_LEAVE));
        university.addStudent(new Student("Дмитро", "Бабенко", "Петрович", "25.12.2000", "+380501000005", "dmytro.babenko@gmail.com", 6, 4, university.getFaculty(1), 127, 2022, StudyForm.CONTRACT, StudentStatus.EXPELLED));

        ConsoleMenu menu = new ConsoleMenu(university);
        menu.start();
    }
}

