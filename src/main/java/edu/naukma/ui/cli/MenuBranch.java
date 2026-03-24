package edu.naukma.ui.cli;

import java.util.ArrayList;
import java.util.List;

/**
 * The MenuBranch class represents a branch in the console menu. It contains a name and a list of menu items.
 * It provides methods to get the name of the branch, get the list of menu items, and add new menu items to the branch.
 */
public class MenuBranch implements Described {
    private final String description;
    private final List<MenuItem> items = new ArrayList<>();

    public MenuBranch(String description) {
        if (description == null || description.trim().isEmpty())
            throw new IllegalArgumentException("Menu branch name cannot be null or empty!");

        this.description = description;
    }

    /**
     * Get the name of the menu branch.
     *
     * @return The name of the menu branch.
     */
    public String getDescription() {
        return description;
    }

    /**
     * Get the list of menu items in the branch.
     *
     * @return List of menu items in the branch.
     */
    public List<MenuItem> getItems() {
        return items;
    }

    /**
     * Add a menu item to the branch.
     *
     * @param item The menu item to add.
     * @throws IllegalArgumentException if the menu item is null.
     */
    public void addMenuItem(MenuItem item) {
        if (item == null) throw new IllegalArgumentException("Menu item cannot be null!");
        items.add(item);
    }
}
