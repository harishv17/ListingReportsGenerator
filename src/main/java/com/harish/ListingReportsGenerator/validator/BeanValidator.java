package com.harish.ListingReportsGenerator.validator;

import com.harish.ListingReportsGenerator.exceptions.ListingsReportGeneratorException;
import org.springframework.stereotype.Component;

import javax.validation.ConstraintViolation;
import javax.validation.Validation;
import javax.validation.Validator;
import javax.validation.ValidatorFactory;
import java.util.HashSet;
import java.util.Optional;
import java.util.Set;
import java.util.stream.Collectors;

@Component
public class BeanValidator {

    public <T> void validate(T objectToValidate) throws ListingsReportGeneratorException {
        ValidatorFactory factory = Validation.buildDefaultValidatorFactory();
        Validator validator = factory.getValidator();
        Set<ConstraintViolation<T>> violations = validator.validate(objectToValidate);
        String errorMessages = Optional.ofNullable(violations).orElse(new HashSet<>())
                .stream().map(constraintViolation -> constraintViolation.getMessage()).collect(Collectors.joining(","));
        if (errorMessages!=null && !errorMessages.isBlank()) {
            throw new ListingsReportGeneratorException(errorMessages);
        }
    }
}
