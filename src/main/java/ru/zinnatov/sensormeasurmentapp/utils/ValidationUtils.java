package ru.zinnatov.sensormeasurmentapp.utils;

import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;

import java.util.List;

public class ValidationUtils {
    public static StringBuilder getErrorMsg(BindingResult bindingResult) {
        StringBuilder errorMsg = new StringBuilder();
        List<FieldError> fieldErrors = bindingResult.getFieldErrors();
        for (FieldError error : fieldErrors) {
            errorMsg.append(error.getField()).append(" - ").append(error.getDefaultMessage()).append(";");
        }
        return errorMsg;
    }
}
