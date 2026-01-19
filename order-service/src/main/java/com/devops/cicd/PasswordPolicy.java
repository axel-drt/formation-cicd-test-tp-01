package com.devops.cicd;

public class PasswordPolicy {

    public static boolean isStrong(String password) {
        if (password == null) {
            return false;
        }
        
        // Vérifier la longueur minimale (au moins 8 caractères)
        if (password.length() < 8) {
            return false;
        }
        
        boolean hasUppercase = false;
        boolean hasLowercase = false;
        boolean hasDigit = false;
        boolean hasSpecialChar = false;
        
        // Parcourir chaque caractère
        for (char c : password.toCharArray()) {
            if (Character.isUpperCase(c)) {
                hasUppercase = true;
            } else if (Character.isLowerCase(c)) {
                hasLowercase = true;
            } else if (Character.isDigit(c)) {
                hasDigit = true;
            } else if (!Character.isWhitespace(c)) {
                // Tout caractère qui n'est ni lettre, ni chiffre, ni espace
                hasSpecialChar = true;
            }
        }
        
        // Retourner true seulement si toutes les conditions sont remplies
        return hasUppercase && hasLowercase && hasDigit && hasSpecialChar;
    }
}