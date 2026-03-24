package edu.naukma.service;

import edu.naukma.domain.Person;
import edu.naukma.ui.cli.InputUtils;

public class PersonService {
    public static Person createPerson() {
        return new Person(
                InputUtils.readString("Enter first name: "),
                InputUtils.readString("Enter last name: "),
                InputUtils.readString("Enter middle name: "),
                InputUtils.readDate("Enter date of birth (yyyy-MM-dd): "),
                InputUtils.readPhone("Enter phone number: "),
                InputUtils.readEmail("Enter email address: ")
        );
    }

    public static void editPerson(Person person) {
        if (person == null) throw new IllegalArgumentException("Person can not be null!");
        person.setName(InputUtils.readString("Enter new first name: "));
        person.setSurname(InputUtils.readString("Enter new last name: "));
        person.setMiddleName(InputUtils.readString("Enter new middle name: "));
        person.setPhone(InputUtils.readPhone("Enter new phone number: "));
        person.setEmail(InputUtils.readEmail("Enter new email address: "));
    }
}
