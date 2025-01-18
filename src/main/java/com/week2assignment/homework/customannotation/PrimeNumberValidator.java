package com.week2assignment.homework.customannotation;

import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;

public class PrimeNumberValidator implements ConstraintValidator<PrimeNumberValidation,Long>{

    @Override
    public boolean isValid(Long aLong, ConstraintValidatorContext constraintValidatorContext) {
        if (aLong <= 1)
            return false;

        // Check divisibility from 2 to n-1
        for (int i = 2; i < aLong; i++)
            if (aLong % i == 0)
                return false;
        return true;
    }
}
