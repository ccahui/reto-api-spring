package com.reto.cliente.exceptions.validation;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class CustomFieldError {

    private String name;
    private Object rejectValue;
    private String message;

    public CustomFieldError(String name, Object rejectValue, String message) {
        this.name = name;
        this.rejectValue = rejectValue;
        this.message = message;
    }


}