package com.example.calculator.service.validations;

public interface GenericValidation<T> {
    void checkViolations(T request);
}
