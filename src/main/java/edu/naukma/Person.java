package edu.naukma;

public class Person {
    private static int lastId = 0;

    private final int id;
    private String name;
    private String surname;
    private String midleName;
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

    public String getFullName() {
        return name + " " + surname + " " + midleName;
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

    public static void setLastId(int lastId) {
        Person.lastId = lastId;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setMidleName(String midleName) {
        this.midleName = midleName;
    }

    public void setSurname(String surname) {
        this.surname = surname;
    }

    public String getName() {
        return name;
    }

    public String getSurname() {
        return surname;
    }

    public String getMidleName() {
        return midleName;
    }

    public String getDayOfBirth() {
        return dayOfBirth;
    }

    @Override
    public String toString() {
        return "Person #" + id + ". " +
                name + ". " +
                surname + ". " +
                midleName + ". " +
                dayOfBirth + ". " +
                phone + ". " +
                email;
    }
}
