package edu.naukma.console;

import edu.naukma.User;
import edu.naukma.UserRole;
import edu.naukma.actions.Action;
import edu.naukma.exeptions.InvalidMenuItemFieldException;

/**
 * The MenuItem class represents an item in the console menu. It contains an ID, description, action to execute, and the required user role for access.
 * It provides methods to check if a user has access to the menu item based on their role.
 */
public class MenuItem implements Described {
    private final int id;
    private final String description;
    private final Action action;
    private final UserRole requiredRole;

    public MenuItem(int id, String description, Action action, UserRole requiredRole) {
        if (id < 0)
            throw new InvalidMenuItemFieldException("Menu item ID must be non-negative!");
        if (description == null || description.trim().isEmpty())
            throw new InvalidMenuItemFieldException("Menu item description cannot be null or empty!");
        if (action == null)
            throw new InvalidMenuItemFieldException("Menu item action cannot be null!");
        if (requiredRole == null)
            throw new InvalidMenuItemFieldException("Menu item required role cannot be null!");

        this.id = id;
        this.description = description;
        this.action = action;
        this.requiredRole = requiredRole;
    }

    public int getId() {
        return id;
    }

    /**
     * Get the description of the menu item.
     *
     * @return The description of the menu item.
     */
    public String getDescription() {
        return description;
    }

    /**
     * Get the action associated with the menu item.
     *
     * @return The action associated with the menu item.
     */
    public Action getAction() {
        return action;
    }

    /**
     * Get the required user role for accessing the menu item.
     *
     * @return The required user role for accessing the menu item.
     */
    public UserRole getRequiredRole() {
        return requiredRole;
    }

    /**
     * Check if a user role allows access to the menu item.
     *
     * @param userRole The user role to check.
     * @return True if the user role allows access, false otherwise.
     */
    public boolean allowsAccess(UserRole userRole) {
        return userRole.ordinal() <= requiredRole.ordinal();
    }

    /**
     * Check if a user has access to the menu item based on their role.
     *
     * @param user The user to check access for.
     * @return True if the user has access, false otherwise.
     */
    public boolean allowsAccess(User user) {
        UserRole userRole = user.getUserRole();
        return allowsAccess(userRole);
    }
}
