package edu.naukma;

import edu.naukma.domain.User;
import edu.naukma.domain.UserRole;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class UserTest {

    User user;

    @BeforeEach
    void createUser() {
        user = new User("login", "pass", UserRole.TECH_ADMIN);
    }

    @Test
    @DisplayName("Users with same login is equal")
    void testEquals() {
        User u1 = new User("login", "pass1", UserRole.TECH_ADMIN);
        User u2 = new User("login", "pass2", UserRole.EXPLORER);

        assertTrue(u1.equals(u2));
    }

    @Test
    @DisplayName("Password changed correctly")
    void passwordChanging() {
        assertDoesNotThrow(() -> {
            user.changePassword("pass", "anotherPassword");
        });
    }

    @Test
    @DisplayName("Password must be different to change")
    void passwordSameChange() {
        assertThrows(IllegalArgumentException.class,
                () -> {
                    user.changePassword("pass", "pass");
                });
    }

    @Test
    @DisplayName("Access allowed")
    void allowAccess() {
        assertTrue(user.checkPassword("pass"));
    }

    @Test
    @DisplayName("Decline access")
    void declineAccess() {
        assertFalse(user.checkPassword("incorrect"));
    }

    @Test
    @DisplayName("Password is changed")
    void changePassword() {
        user.changePassword("pass", "newPass");
        assertTrue(user.checkPassword("newPass"));
    }
}