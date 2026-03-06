package edu.naukma;

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
    public int hashCode() {
        return login.hashCode();
    }
}
