package com.example.demo3.Utilities;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

public class PasswordHashing {
    public static String hashPassword(String password) {
        try {
            // Use SHA-512 algorithm for hashing
            MessageDigest md = MessageDigest.getInstance("SHA-512");

            // Get the bytes of the password
            byte[] passwordBytes = password.getBytes();

            // Compute the hash
            byte[] hashedBytes = md.digest(passwordBytes);

            // Convert the hashed bytes to a hexadecimal representation
            StringBuilder sb = new StringBuilder();
            for (byte b : hashedBytes) {
                sb.append(String.format("%02x", b));
            }

            return sb.toString();
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        }
        return null;
    }

    public static boolean verifyPassword(String inputPassword, String storedHashedPassword) {
        String hashedInputPassword = hashPassword(inputPassword);
        return hashedInputPassword.equals(storedHashedPassword);
    }
}

