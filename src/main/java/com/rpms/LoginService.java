
package com.rpms;

public class LoginService {

    private UserDAO userDAO = new UserDAO();

    // REGISTER FIRST ADMIN
    public void registerFirstAdmin(String username, String password,
                                   String fullName, String email)
            throws Exception {

        if (userDAO.hasAnyUsers()) {
            System.out.println("Admin setup is already completed!");
            return;
        }

        String hashedPassword = PasswordUtil.hashPassword(password);

        User admin = new User(
                username,
                hashedPassword,
                fullName,
                email,
                1
        );

        userDAO.add(admin);

        System.out.println("First Admin account created successfully!");
    }

    // USER LOGIN
    public User login(String username, String password)
            throws Exception {

        User user = userDAO.findByUsername(username);

        if (user == null) {
            System.out.println("Invalid username or password!");
            return null;
        }

        if (!"ACTIVE".equalsIgnoreCase(user.getStatus())) {
            System.out.println("Your account is inactive!");
            return null;
        }

        if (!PasswordUtil.verifyPassword(
                password, user.getPasswordHash())) {

            System.out.println("Invalid username or password!");
            return null;
        }

        userDAO.updateLastLogin(user.getUserId());

        System.out.println("Login successful! Welcome, "
                + user.getFullName());

        return user;
    }
}
