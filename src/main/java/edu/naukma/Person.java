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

    /**
     * Constructor for Person class. It initializes all fields and assigns a unique ID to each person.
     *
     * @param name       The first name of the person.
     * @param surname    The last name of the person.
     * @param midleName  The middle name of the person.
     * @param dayOfBirth The date of birth of the person in the format "dd.MM.yyyy".
     * @param phone      The phone number of the person.
     * @param email      The email address of the person.
     * @throws IllegalArgumentException if any of the parameters are null or empty.
     */
    public Person(String name, String surname, String midleName, String dayOfBirth, String phone, String email) {
        if (name == null || surname == null || midleName == null || dayOfBirth == null || phone == null || email == null ||
                name.isEmpty() || surname.isEmpty() || midleName.isEmpty() || dayOfBirth.isEmpty() || phone.isEmpty() || email.isEmpty())
            throw new IllegalArgumentException("Person field can't be null or empty!");

        this.id = ++lastId;
        this.name = name;
        this.surname = surname;
        this.midleName = midleName;
        this.dayOfBirth = dayOfBirth;
        this.phone = phone;
        this.email = email;
    }

    /**
     * Get the last assigned ID for any person.
     *
     * @return The last assigned ID.
     */
    private static int getLastId() {
        return lastId;
    }

    /**
     * Get the full name of the person in the format "Name Surname MidleName".
     *
     * @return The full name of the person.
     */
    public String getFullName() {
        return surname + " " + name + " " + midleName;
    }

    /**
     * Get the person's phone number.
     *
     * @return The phone number of the person.
     */
    public String getPhone() {
        return phone;
    }

    /**
     * Set the person's phone number with basic validation.
     *
     * @param phone The new phone number to set.
     * @throws IllegalArgumentException if the phone number is null or too short.
     */
    public void setPhone(String phone) {
        if (phone == null || phone.length() < 5) throw new IllegalArgumentException("Invalid phone number");

        this.phone = phone;
    }

    /**
     * Get the person's email address.
     *
     * @return The email address of the person.
     */
    public String getEmail() {
        return email;
    }

    /**
     * Set the person's email address with basic validation.
     *
     * @param email The new email address to set.
     * @throws IllegalArgumentException if the email is null or does not contain an "@" symbol.
     */
    public void setEmail(String email) {
        if (email == null || !email.contains("@")) throw new IllegalArgumentException("Invalid email address");

        this.email = email;
    }

    /**
     * Get the unique ID of the person.
     *
     * @return The unique ID of the person.
     */
    public int getId() {
        return id;
    }

    /**
     * Set the last assigned ID for any person. This method can be used to reset the ID counter if needed.
     *
     * @param lastId The new last assigned ID to set.
     */
    private static void setLastId(int lastId) {
        Person.lastId = lastId;
    }

    /**
     * Set the person's name.
     *
     * @param name The new name to set.
     */
    public void setName(String name) {
        if (name == null || name.isEmpty()) throw new IllegalArgumentException("Name cannot be null or empty");

        this.name = name;
    }

    /**
     * Set the person's middle name.
     *
     * @param midleName The new middle name to set.
     */
    public void setMidleName(String midleName) {
        if (midleName == null || midleName.isEmpty()) throw new IllegalArgumentException("Middle name cannot be null or empty");

        this.midleName = midleName;
    }

    /**
     * Set the person's surname.
     *
     * @param surname The new surname to set.
     */
    public void setSurname(String surname) {
        if (surname == null || surname.isEmpty()) throw new IllegalArgumentException("Surname cannot be null or empty");

        this.surname = surname;
    }

    /**
     * Get the person's name.
     *
     * @return The name of the person.
     */
    public String getName() {
        return name;
    }

    /**
     * Get the person's surname.
     *
     * @return The surname of the person.
     */
    public String getSurname() {
        return surname;
    }

    /**
     * Get the person's middle name.
     *
     * @return The middle name of the person.
     */
    public String getMidleName() {
        return midleName;
    }

    /**
     * Get the person's date of birth.
     *
     * @return The date of birth of the person.
     */
    public String getDayOfBirth() {
        return dayOfBirth;
    }

    /**
     * Returns a string representation of the person, including their ID, name, surname, middle name, date of birth, phone number, and email.
     *
     * @return A string representation of the person.
     */
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
