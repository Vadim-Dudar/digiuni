package edu.naukma;

import java.util.ArrayList;
import java.util.List;
import java.util.function.Predicate;
import java.util.stream.Collectors;

/**
 * A generic repository for managing items that implement the Identifiable interface.
 *
 * @param <T> the type of items in the repository
 */
public class Repository<T extends Identifiable> {
    private final List<T> items = new ArrayList<>();

    /**
     * Adds an item to the repository.
     *
     * @param item the item to add
     * @throws IllegalArgumentException if the item is null
     */
    public void addItem(T item) {
        if (item == null) throw new IllegalArgumentException("Item can't be null!");
        items.add(item);
    }

    /**
     * Removes an item by its ID.
     *
     * @param id the ID of the item to remove
     * @return true if the item was removed, false otherwise
     */
    public boolean remove(int id) {
        return items.removeIf(item -> item.getId() == id);
    }

    /**
     * Retrieves all items in the repository.
     *
     * @return a list of all items
     */
    public List<T> getAll() {
        return items;
    }

    /**
     * Finds items that match the given filter.
     *
     * @param filter a predicate to filter items
     * @return a list of items that match the filter
     */
    public List<T> findBy(Predicate<T> filter) {
        return items.stream()
                .filter(filter)
                .collect(Collectors.toList());
    }
}
