package com.reto.cliente.exceptions.validation;

import lombok.Getter;
import lombok.Setter;
import org.springframework.web.multipart.MultipartFile;

@Getter
@Setter
public class CustomFieldError {

    private String name;
    private Object rejectValue;
    private String message;

    public CustomFieldError(String name, Object rejectValue, String message) {
        if(rejectValue instanceof MultipartFile){
            this.rejectValue = "MultipartFile";
        } else {
            this.rejectValue = rejectValue;
        }
        this.name = name;
        this.message = message;
    }


}