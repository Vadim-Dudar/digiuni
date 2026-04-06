package edu.naukma.service;

import edu.naukma.domain.University;
import org.junit.jupiter.api.*;

import java.nio.file.Path;

import static org.junit.jupiter.api.Assertions.*;

class DataServiceTest {

    University university = new University("Full Name", "FN", "City", "Some st.");
    Path testPath = Path.of("test.ser");

    @Test
    @Order(1)
    @DisplayName("Successfully saved")
    void saved() {
        assertDoesNotThrow(() -> DataService.saveUniversity(university, testPath));
    }

    @Test
    @Order(2)
    @DisplayName("Successfully load")
    void load() {
        University fromFile = DataService.loadUniversity(testPath);
        assertEquals(fromFile, university);
    }

    @Test
    @DisplayName("Can't save NULL University")
    void saveNullUniversity() {
        assertThrowsExactly(IllegalArgumentException.class, () -> DataService.saveUniversity(null));
    }

    @Test
    void loadUniversity() {
    }

    @AfterAll
    static void afterAll() {

    }
}