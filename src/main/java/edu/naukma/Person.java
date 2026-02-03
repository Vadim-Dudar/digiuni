package edu.naukma;

public class Person {
    private static int lastId = 0;

    private final int id;
    private final String name;
    private final String surname;
    private final String midleName;
    private final String dayOfBirth;
    private String phone;
    private String email;

    public Person(String name, String surname, String midleName, String dayOfBirth, String phone, String email) {
        this.id = ++lastId;
        this.name = name;
        this.surname = surname;
        this.midleName = midleName;
        this.dayOfBirth = dayOfBirth;
        this.phone = phone;
        this.email = email;
    }

    public static int getLastId() {
        return lastId;
    }
}
