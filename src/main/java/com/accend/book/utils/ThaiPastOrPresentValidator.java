package com.accend.book.utils;

import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;

import java.time.LocalDate;
import java.time.chrono.ThaiBuddhistDate;
import java.time.format.DateTimeParseException;

public class ThaiPastOrPresentValidator implements ConstraintValidator<ThaiPastOrPresent, LocalDate> {

    @Override
    public boolean isValid(LocalDate value, ConstraintValidatorContext context) {
        if (value == null) return true;

        try {
            ThaiBuddhistDate inputDate = ThaiBuddhistDate.of(value.getYear(), value.getMonthValue(), value.getDayOfMonth());
            ThaiBuddhistDate today = ThaiBuddhistDate.from(LocalDate.now());
            return !inputDate.isAfter(today) && value.getYear() > 1000;
        } catch (DateTimeParseException e) {
            return false;
        }
    }
}
