package com.reto.cliente.validation;

import org.springframework.web.multipart.MultipartFile;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;
import java.util.logging.Logger;


public class FileSizeValidator implements ConstraintValidator<FileSize, MultipartFile> {
    private long maxSize;
    private Logger logger = Logger.getLogger(FileSizeValidator.class.getName());
    @Override
    public void initialize(FileSize constraintAnnotation){
        this.maxSize = constraintAnnotation.maxSize();
    }

    @Override
    public boolean isValid(MultipartFile file, ConstraintValidatorContext context) {
        if (file == null) {
            // Si el archivo es nulo, se considera válido ya que la anotación @NotNull se encarga de eso.
            return true;
        }

        logger.info("MAX SIZE: " + maxSize);
        logger.info("NAME: " + file.getName());
        logger.info("FILE SIZE: " + file.getSize());
        if (file.getSize() > maxSize) {
            String errorMessage = "File size exceeds the maximum allowed. Actual size: " + file.getSize() + ". Maximum size: " + maxSize;
            context.buildConstraintViolationWithTemplate(errorMessage)
                    .addConstraintViolation()
                    .disableDefaultConstraintViolation();
            return false;
        }
        return true;
    }
}