package edu.naukma;

import edu.naukma.domain.AcademicDegree;
import edu.naukma.domain.AcademicStage;
import edu.naukma.domain.Teacher;
import edu.naukma.domain.TeacherPosition;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

import java.time.LocalDate;

import static org.junit.jupiter.api.Assertions.*;

class TeacherTest {

    @ParameterizedTest
    @CsvSource({
            "12, 1",
            "0, 0",
            "10, 0",
            "5, 0",
            "24, 2",
            "36, 3",
            "48, 4",
            "13, 1"
    })
    @DisplayName("Experience is calculated correctly ")
    void getExperience(int experience, int expected) {
        Teacher teacher = new Teacher("John", "Doe", "Smith", "1980-01-01", "0688888888", "some@mail.com", 1,  TeacherPosition.LECTURER, AcademicDegree.DOCTOR_OF_SCIENCE, AcademicStage.DOCTOR_OF_SCIENCE, LocalDate.now().minusMonths(experience).toString(), 10, null, null);
        assertEquals(expected, teacher.getExperience());
    }
}