package edu.naukma.service;

import edu.naukma.domain.University;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class DataServiceTest {

    @Test
    @DisplayName("Can't save NULL University")
    void saveUniversity() {
        assertThrowsExactly(IllegalArgumentException.class, () -> DataService.saveUniversity(null));
    }

    @Test
    void loadUniversity() {
    }
}