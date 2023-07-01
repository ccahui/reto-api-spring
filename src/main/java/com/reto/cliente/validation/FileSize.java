package com.reto.cliente.validation;

import javax.validation.Constraint;
import javax.validation.Payload;
import java.lang.annotation.*;

@Constraint(validatedBy = { FileSizeValidator.class })
@Target({ElementType.FIELD, ElementType.PARAMETER})
@Retention(RetentionPolicy.RUNTIME)
public @interface FileSize {
    String message() default "File size exceeds the maximum allowed";
    Class<?>[] groups() default { };
    Class<? extends Payload>[] payload() default { };
    long maxSize() default  3 * 1024 * 1024;
}