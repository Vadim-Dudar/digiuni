package edu.naukma;

import edu.naukma.domain.Identifiable;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class SomeClass implements Identifiable {
    private final int id;
    String name;

    public SomeClass(int id, String name) {
        this.id = id;
        this.name = name;
    }

    public int getId() {
        return id;
    }
}

class RepositoryTest {

    Repository<SomeClass> repository;

    @BeforeEach
    void setUp() {
        repository = new Repository<>();
        repository.addItem(new SomeClass(1, "Item 1"));
        repository.addItem(new SomeClass(2, "Item 2"));
    }

    @Test
    @DisplayName("Test addItem method of Repository with null item")
    void addNullItem() {
        assertThrows(IllegalArgumentException.class, () -> repository.addItem(null));
    }

    @Test
    @DisplayName("Test addItem method of Repository with valid item")
    void addItem() {
        SomeClass newItem = new SomeClass(3, "Item 3");
        repository.addItem(newItem);
        assertTrue(repository.getAll().contains(newItem));
    }

    @Test
    @DisplayName("Test remove method of Repository")
    void remove() {
        assertTrue(repository.remove(1));
    }
}