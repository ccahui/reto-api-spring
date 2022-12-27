package com.reto.cliente.exceptions.validation;

import com.reto.cliente.exceptions.ErrorMessage;
import org.springframework.validation.BindException;
import org.springframework.validation.FieldError;

import java.util.ArrayList;
import java.util.List;

public class ErrorMessageFields extends ErrorMessage {
    private static final String DESCRIPTION = "Bad Request, fields Error";

    private List<CustomFieldError> fieldsError = new ArrayList<>();

    public ErrorMessageFields(BindException exception, String path) {

        super(exception.getClass().getSimpleName(), DESCRIPTION, path);
        this.setFieldsError(exception);
    }

    public List<CustomFieldError> getFieldsError() {
        return fieldsError;
    }

    private void setFieldsError(BindException exception) {
        CustomFieldError error;
        for (FieldError fieldError : exception.getBindingResult().getFieldErrors()) {
            error = new CustomFieldError(fieldError.getField(), fieldError.getRejectedValue(),
                    fieldError.getDefaultMessage());
            fieldsError.add(error);
        }
    }
}