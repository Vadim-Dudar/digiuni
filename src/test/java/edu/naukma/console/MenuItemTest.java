package edu.naukma.console;

import edu.naukma.domain.UserRole;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

import static org.junit.jupiter.api.Assertions.*;

class MenuItemTest {

    @ParameterizedTest
    @CsvSource({
            "ADMIN, TECH_ADMIN, true",
            "ADMIN, ADMIN, true",
            "ADMIN, EXPLORER, false",
            "TECH_ADMIN, EXPLORER, false",
            "TECH_ADMIN, ADMIN, false",
            "TECH_ADMIN, TECH_ADMIN, true",
            "EXPLORER, EXPLORER, true",
            "EXPLORER, ADMIN, true",
            "EXPLORER, TECH_ADMIN, true"
    })
    void testAllowsAccess(UserRole itemAcesLevel, UserRole userRole, boolean expected) {
        MenuItem menuItem = new MenuItem(1, "Test Item", () -> {}, itemAcesLevel);

        boolean actual = menuItem.allowsAccess(userRole);
        assertEquals(expected, actual);
    }
}