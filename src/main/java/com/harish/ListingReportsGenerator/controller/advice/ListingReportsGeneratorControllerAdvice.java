package com.harish.ListingReportsGenerator.controller.advice;

import com.harish.ListingReportsGenerator.dto.ListingReportError;
import com.harish.ListingReportsGenerator.exceptions.ListingsReportGeneratorException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;

import javax.validation.ConstraintViolationException;

@ControllerAdvice
public class ListingReportsGeneratorControllerAdvice {

    @ExceptionHandler({ListingsReportGeneratorException.class, ConstraintViolationException.class})
    public final ResponseEntity<ListingReportError> handleException(ConstraintViolationException e, HttpStatus status) {
        return new ResponseEntity<ListingReportError>(new ListingReportError(status.value(),
                e.getMessage()), status);
    }
}
