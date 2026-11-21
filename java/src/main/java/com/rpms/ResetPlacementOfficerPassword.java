
package com.rpms;

import java.io.Console;
import java.util.Scanner;
import java.sql.Connection;
import java.sql.PreparedStatement;

public class ResetPlacementOfficerPassword {

    public static void main(String[] args) throws Exception {

        String username = "placementofficer";
        String newPassword;

        Console console = System.console();

        if (console != null) {
            newPassword = new String(
                console.readPassword("Set new Placement Officer password: ")
            );
        } else {
            Scanner sc = new Scanner(System.in);
            System.out.print("Set new Placement Officer password: ");
            newPassword = sc.nextLine();
        }

        if (newPassword.length() < 8) {
            System.out.println("Password must be at least 8 characters.");
            return;
        }

        String passwordHash =
            PasswordUtil.hashPassword(newPassword);

        String sql =
            "UPDATE users SET password_hash = ? WHERE username = ?";

        try (Connection con = DBConnection.getConnection();
             PreparedStatement ps = con.prepareStatement(sql)) {

            ps.setString(1, passwordHash);
            ps.setString(2, username);

            int rows = ps.executeUpdate();

            if (rows == 1) {
                System.out.println(
                    "Placement Officer password updated successfully!"
                );
            } else {
                System.out.println("Placement Officer account not found.");
            }
        }
    }
}
