package com.accend.book.utils;

import jakarta.validation.Constraint;
import jakarta.validation.Payload;

import java.lang.annotation.*;

@Target({ElementType.FIELD})
@Retention(RetentionPolicy.RUNTIME)
@Documented
@Constraint(
        validatedBy = {ThaiPastOrPresentValidator.class}
)
public @interface ThaiPastOrPresent {
    String message() default "วันที่ต้องไม่เกินวันนี้ (พ.ศ.) และมาก ปี ต้องมากว่า 1000";

    Class<?>[] groups() default {};

    Class<? extends Payload>[] payload() default {};
}

