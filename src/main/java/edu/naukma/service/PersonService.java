package edu.naukma.service;

import edu.naukma.domain.Person;
import edu.naukma.util.InputUtil;

public class PersonService {
    public static Person createPerson() {
        return new Person(
                InputUtil.readString("Enter first name: "),
                InputUtil.readString("Enter last name: "),
                InputUtil.readString("Enter middle name: "),
                InputUtil.readDate("Enter date of birth (yyyy-MM-dd): "),
                InputUtil.readPhone("Enter phone number: "),
                InputUtil.readEmail("Enter email address: ")
        );
    }

    public static void editPerson(Person person) {
        if (person == null) throw new IllegalArgumentException("Person can not be null!");
        person.setName(InputUtil.readString("Enter new first name: "));
        person.setSurname(InputUtil.readString("Enter new last name: "));
        person.setMiddleName(InputUtil.readString("Enter new middle name: "));
        person.setPhone(InputUtil.readPhone("Enter new phone number: "));
        person.setEmail(InputUtil.readEmail("Enter new email address: "));
    }
}
