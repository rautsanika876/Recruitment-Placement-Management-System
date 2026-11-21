package com.rpms;

import java.util.Scanner;
import java.io.Console;

public class CreateRecruiter {

    public static void main(String[] args) throws Exception {

        String username = "recruiter";
        String fullName = "RPMS Recruiter";
        String email = "recruiter@rpms.local";
        int roleId = 2;

        Console console = System.console();
        String password;

        if (console != null) {
            password = new String(
                console.readPassword("Set Recruiter password: ")
            );
        } else {
            Scanner sc = new Scanner(System.in);
            System.out.print("Set Recruiter password: ");
            password = sc.nextLine();
        }

        String passwordHash =
                PasswordUtil.hashPassword(password);

        User recruiter = new User(
            username,
            passwordHash,
            fullName,
            email,
            roleId
        );

        UserDAO userDAO = new UserDAO();
        userDAO.add(recruiter);

        System.out.println(
            "Recruiter account created successfully!"
        );
        System.out.println("Username: recruiter");
    }
}