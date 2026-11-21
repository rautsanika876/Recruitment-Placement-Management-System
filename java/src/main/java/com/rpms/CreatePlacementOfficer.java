
package com.rpms;

import java.util.Scanner;
import java.io.Console;

public class CreatePlacementOfficer {

    public static void main(String[] args) throws Exception {

        String username = "placementofficer";
        String fullName = "RPMS Placement Officer";
        String email = "placementofficer@rpms.local";
        int roleId = 3;

        Console console = System.console();
        String password;

        if (console != null) {
            password = new String(
                console.readPassword(
                    "Set Placement Officer password: "
                )
            );
        } else {
            Scanner sc = new Scanner(System.in);
            System.out.print(
                "Set Placement Officer password: "
            );
            password = sc.nextLine();
        }

        String passwordHash =
                PasswordUtil.hashPassword(password);

        User placementOfficer = new User(
            username,
            passwordHash,
            fullName,
            email,
            roleId
        );

        UserDAO userDAO = new UserDAO();
        userDAO.add(placementOfficer);

        System.out.println(
            "Placement Officer account created successfully!"
        );
        System.out.println("Username: placementofficer");
    }
}
