package edu.naukma.service;

import edu.naukma.domain.University;
import lombok.extern.slf4j.Slf4j;

import java.io.*;
import java.nio.file.*;

@Slf4j
public class DataService {

    private static final Path DATA_FILE = Path.of("university_data.ser");

    public static void saveUniversity(University university) {
        if (university == null) throw new IllegalArgumentException("University can't be null!");

        try (ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(DATA_FILE.toString()))) {
            oos.writeObject(university);
            log.info("University serialized successfully!");
        } catch (IOException e) {
            log.error("Error {} \n to serialize university: \n\t{}", e, university);
            throw new RuntimeException(e);
        }
    }

    public static University loadUniversity() {
        return null;
    }

}
