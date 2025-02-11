package org.project.error;

import lombok.RequiredArgsConstructor;
import org.project.exception.ActivationException;
import org.project.exception.InvalidTokenException;
import org.project.exception.NotUniqueEmailException;
import org.project.shared.Messages;
import org.springframework.context.i18n.LocaleContextHolder;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.util.stream.Collectors;

import static org.project.config.RestApis.AUTHSERVICE;

@RequiredArgsConstructor
@Component
@RestControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<ApiError> handleMethodArgumentNotValidException(MethodArgumentNotValidException ex) {
        ApiError apiError = new ApiError();
        apiError.setPath(AUTHSERVICE);
        apiError.setMessage(Messages.getMessageForLocale("validation.exception.error", LocaleContextHolder.getLocale()));
        apiError.setStatus(ex.getStatusCode().value());
        apiError.setValidationErrors(ex.getBindingResult().getFieldErrors().stream().collect(Collectors.toMap(FieldError::getField, FieldError::getDefaultMessage, (existing, replacing) -> existing)));
        return ResponseEntity.badRequest().body(apiError);
    }

    @ExceptionHandler(NotUniqueEmailException.class)
    public ResponseEntity<ApiError> handleNotUniqueEmailException (NotUniqueEmailException ex) {
        ApiError apiError = new ApiError();
        apiError.setPath(AUTHSERVICE);
        apiError.setMessage(ex.getMessage());
        apiError.setStatus(ex.getStatus());
        apiError.setValidationErrors(ex.getValidationErrors());
        return ResponseEntity.badRequest().body(apiError);
    }

    @ExceptionHandler(ActivationException.class)
    public ResponseEntity<ApiError> handleActivationException (ActivationException ex) {
        ApiError apiError = new ApiError();
        apiError.setPath(AUTHSERVICE);
        apiError.setMessage(ex.getMessage());
        apiError.setStatus(ex.getStatus());
        return ResponseEntity.status(ex.getStatus()).body(apiError);
    }
    @ExceptionHandler(InvalidTokenException.class)
    public ResponseEntity<ApiError> handleInvalidActivationException (InvalidTokenException ex) {
        ApiError apiError = new ApiError();
        apiError.setPath(AUTHSERVICE);
        apiError.setMessage(ex.getMessage());
        apiError.setStatus(ex.getStatus());
        return ResponseEntity.status(ex.getStatus()).body(apiError);
    }
}
