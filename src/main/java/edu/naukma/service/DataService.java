package edu.naukma.service;

import edu.naukma.domain.University;
import lombok.extern.slf4j.Slf4j;

import java.io.*;
import java.nio.file.*;

@Slf4j
public class DataService {

    private static final Path DATA_FILE = Path.of("src/main/java/edu/naukma/university_data.ser");

    public static void saveUniversity(University university, Path path) {
        if (university == null) throw new IllegalArgumentException("University can't be null!");

        try (ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(path.toString()))) {
            oos.writeObject(university);
            log.info("University serialized successfully!");
        } catch (IOException e) {
            log.error("Error {} \n to serialize university: \n\t{}", e, university);
            throw new RuntimeException(e);
        }
    }

    public static void saveUniversity(University university) {
        saveUniversity(university, DATA_FILE);
    }

    public static University loadUniversity(Path path) {
        try (ObjectInputStream ois = new ObjectInputStream(new FileInputStream(path.toString()))) {
            return (University) ois.readObject();
        } catch (IOException e) {
            log.error("Error {} \n to find university by Path {}", e, DATA_FILE);
            throw new RuntimeException(e);
        } catch (ClassNotFoundException e) {
            log.error("Error {} of casting", e);
            throw new RuntimeException(e);
        }
    }

    public static University loadUniversity() {
        return loadUniversity(DATA_FILE);
    }

    public static void loadUniversity(University university) {
        university = loadUniversity(DATA_FILE);
    }

}
