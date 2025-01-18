package com.week2assignment.homework.customannotation;



import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;

public class PasswordValidator implements ConstraintValidator<ValidPassword, String> {

    @Override
    public boolean isValid(String password, ConstraintValidatorContext context) {
        if (password == null || password.length() < 10) {
            return false;
        }

        // Check for at least one uppercase letter
        boolean hasUppercase = password.chars().anyMatch(Character::isUpperCase);

        // Check for at least one lowercase letter
        boolean hasLowercase = password.chars().anyMatch(Character::isLowerCase);

        // Check for at least one special character
        boolean hasSpecial = password.chars().anyMatch(ch -> 
            "!@#$%^&*()-_+=<>?/{}~".indexOf((char) ch) >= 0);

        return hasUppercase && hasLowercase && hasSpecial;
    }
}
