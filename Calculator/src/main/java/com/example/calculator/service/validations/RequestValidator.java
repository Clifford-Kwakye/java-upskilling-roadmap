package com.example.calculator.service.validations;

import jakarta.validation.ConstraintViolation;
import jakarta.validation.Validation;
import jakarta.validation.Validator;
import jakarta.validation.ValidatorFactory;
import java.util.Collections;
import java.util.Set;
import java.util.stream.Collectors;
import org.springframework.stereotype.Component;

@Component
public class RequestValidator<T> implements GenericValidation<T> {
  private final ValidatorFactory factory = Validation.buildDefaultValidatorFactory();
  private final Validator validator = factory.getValidator();

  private Set<String> validate(T dto) {
    Set<ConstraintViolation<T>> violations = validator.validate(dto);
    if (!violations.isEmpty()) {
      return violations.stream().map(ConstraintViolation::getMessage).collect(Collectors.toSet());
    }
    return Collections.emptySet();
  }

  public void checkViolations(T request) {
    Set<String> violations = validate(request);

    if (!violations.isEmpty()) throw new IllegalArgumentException(String.join(" | ", violations));
  }
}
