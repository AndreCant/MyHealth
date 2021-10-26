package it.univaq.mwt.myhealth.annotation.validator;

import org.springframework.beans.factory.annotation.Autowired;

import it.univaq.mwt.myhealth.annotation.UsernameUnique;
import it.univaq.mwt.myhealth.business.BusinessException;
import it.univaq.mwt.myhealth.business.UserService;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

public class UsernameUniqueValidator implements ConstraintValidator<UsernameUnique, String> {

    @Autowired
    private UserService userService;

    @Override
    public boolean isValid(String value, ConstraintValidatorContext context) {
        if (value == null || userService == null) return true;
        try {
            return !userService.existsByUsername(value);
        } catch (BusinessException e) {
            e.getStackTrace();
            throw new RuntimeException();
        }
    }
}