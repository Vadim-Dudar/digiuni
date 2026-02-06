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
        if (name == null || surname == null || midleName == null || dayOfBirth == null
                || name.isEmpty() || surname.isEmpty() || midleName.isEmpty() || dayOfBirth.isEmpty()
                || phone == null || phone.isEmpty() || email == null || email.isEmpty()) {
            throw new IllegalArgumentException("Arguments cannot be null or empty");
        }

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

    public String getFullName() {
        return name + surname + midleName;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        if (phone == null || phone.length() < 5) throw new IllegalArgumentException("Invalid phone number");

        this.phone = phone;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        if (email == null || !email.contains("@")) throw new IllegalArgumentException("Invalid email address");

        this.email = email;
    }

    public int getId() {
        return id;
    }

    @Override
    public String toString() {
        return "Person #" + id +
                name +
                surname +
                midleName +
                dayOfBirth +
                phone +
                email;
    }
}
