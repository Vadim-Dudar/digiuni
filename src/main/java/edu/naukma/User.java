package edu.naukma;
/**
 * The User class represents a user in the system with a login, password, and user role.
 * It provides methods for checking the password, changing the password, and managing the user role.
 */

public class User {

    public final String login;
    private String password;
    private UserRole userRole;

    public User (String login, String password, UserRole userRole) {
        if (login == null || login.trim().isEmpty() || password == null || password.trim().isEmpty())
            throw new IllegalArgumentException("Login / Password can't be empty");

        this.login = login;
        this.password = password;
        this.userRole = userRole;
    }

    public boolean checkPassword(String password) {
        return password.equals(this.password);
    }

    public UserRole getUserRole() {
        return userRole;
    }

    public void changePassword(String oldPassword, String newPassword) {
        if (!password.equals(oldPassword) || oldPassword.equals(newPassword))
            throw new IllegalArgumentException("Incorrect old password or old equals to new");

        password = newPassword;
    }

    public void setUserRole(UserRole userRole) {
        this.userRole = userRole;
    }

    @Override
    public boolean equals(Object obj) {
        if (obj instanceof User){
            User user = (User) obj;
            return login.equals(user.login);
        }
        return false;
    }

    @Override
    public int hashCode() {
        return login.hashCode();
    }

    @Override
    public String toString() {
        return "User: "+login+" | Password: "+password + " | Role: " + userRole;
    }
}
