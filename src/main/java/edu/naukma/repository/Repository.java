package edu.naukma.repository;

import edu.naukma.domain.Identifiable;

import java.util.*;
import java.util.function.Predicate;
import java.util.stream.Collectors;

/**
 * A generic repository for managing items that implement the Identifiable interface.
 *
 * @param <T> the type of items in the repository
 */
public class Repository<T extends Identifiable> {
    private final Map<Integer, T> items = new HashMap<>();

    /**
     * Adds an item to the repository.
     *
     * @param item the item to add
     * @throws IllegalArgumentException if the item is null
     */
    public void addItem(T item) {
        if (item == null) throw new IllegalArgumentException("Item can't be null!");
        items.put(item.getId(), item);
    }

    /**
     * Removes an item by its ID.
     *
     * @param id the ID of the item to remove
     * @return true if the item was removed, false otherwise
     */
    public boolean remove(int id) {
        return items.remove(id) != null;
    }

    /**
     * Retrieves all items in the repository.
     *
     * @return a list of all items
     */
    public List<T> getAll() {
        return new ArrayList<>(items.values());
    }

    /**
     * Finds items that match the given filter.
     *
     * @param filter a predicate to filter items
     * @return a list of items that match the filter
     */
    public List<T> findBy(Predicate<T> filter) {
        return items.values().stream()
                .filter(filter)
                .collect(Collectors.toList());
    }

    public Optional<T> getById(int id) {
        return Optional.ofNullable(items.get(id));
    }
}