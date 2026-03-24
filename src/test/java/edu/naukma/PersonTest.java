package edu.naukma;

import edu.naukma.domain.Person;
import edu.naukma.exсeptions.LogicalDateExeption;
import edu.naukma.exсeptions.InvalidPersonFieldException;
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
        assertThrows(InvalidPersonFieldException.class,
                () -> {
                    new Person("Name", "Surname", "MidleName", "2006.10.10", "0688888888", "wrongmail");
                });
    }

    @Test
    @DisplayName("Day of birth exeption")
    void incorrectPattern() {
        assertThrows(LogicalDateExeption.class,
                () -> {
                    new Person("Name", "Surename", "Midlename", "10.10.2006", "0688888888", "some@mail.com");
                });
    }

}