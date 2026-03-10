package edu.naukma;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

class PersonTest {

    @Test
    @DisplayName("Can't create person with nulls")
    void personCreateWithNull() {
        assertThrows(IllegalArgumentException.class,
                () -> {
                    new Person(null, null, null, null, null, null);
                });
    }

    @Test
    @DisplayName("Email validation performed")
    void emailValidation() {
        assertThrows(IllegalArgumentException.class,
                () -> {
                    new Person("Name", "Surname", "MidleName", "10.10.2006", "0688888888", "wrongmail");
                });
    }

}