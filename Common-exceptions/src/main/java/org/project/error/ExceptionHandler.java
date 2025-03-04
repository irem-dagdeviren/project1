package org.project.error;

import jakarta.servlet.http.HttpServletRequest;
import lombok.RequiredArgsConstructor;
import org.project.exception.*;
import org.project.shared.Messages;
import org.springframework.context.i18n.LocaleContextHolder;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.util.stream.Collectors;

@RequiredArgsConstructor
@Component
@RestControllerAdvice
public class ExceptionHandler {

    @org.springframework.web.bind.annotation.ExceptionHandler({
            MethodArgumentNotValidException.class,
            NotUniqueEmailException.class,
            InvalidTokenException.class,
            AuthenticationException.class,
            ActivationException.class,
            NotFoundException.class,
            AuthorizationException.class,
    })
    public ResponseEntity<ApiError> handleMethodArgumentNotValidException(Exception ex, HttpServletRequest request) {
        ApiError apiError = new ApiError();
        apiError.setPath(request.getRequestURI());
        apiError.setMessage(ex.getMessage());
        if(ex instanceof MethodArgumentNotValidException methodArgumentNotValidEx) {
            apiError.setMessage(Messages.getMessageForLocale("validation.exception.error", LocaleContextHolder.getLocale()));
            apiError.setStatus(methodArgumentNotValidEx.getStatusCode().value());
            apiError.setValidationErrors(methodArgumentNotValidEx.getBindingResult().getFieldErrors().stream().collect(Collectors.toMap(FieldError::getField, FieldError::getDefaultMessage, (existing, replacing) -> existing)));
        }
         if(ex instanceof InvalidTokenException invalidTokenEx) {
            apiError.setStatus(invalidTokenEx.getStatus());
        }
         if(ex instanceof AuthenticationException authenticationEx) {
            apiError.setStatus(authenticationEx.getStatus());
        }
         if(ex instanceof ActivationException activationEx) {
            apiError.setStatus(activationEx.getStatus());
        }
         if(ex instanceof NotUniqueEmailException notUniqueEmailEx) {
            apiError.setStatus(notUniqueEmailEx.getStatus());
            apiError.setValidationErrors(notUniqueEmailEx.getValidationErrors());
        }
         if(ex instanceof  NotFoundException notFoundException) {
            apiError.setMessage(notFoundException.getMessage());
            apiError.setStatus(notFoundException.getStatus());
        }
         if(ex instanceof AuthorizationException authorizationException) {
            apiError.setMessage(authorizationException.getMessage());
            apiError.setStatus(authorizationException.getStatus());
        }
         if(ex instanceof FeignAuthenticationException feignAuthenticationException) {
            apiError.setMessage(feignAuthenticationException.getMessage());
            apiError.setStatus(feignAuthenticationException.getStatus());

        }
         if(ex instanceof ServiceUnavailableException serviceUnavailableException) {
            apiError.setMessage(serviceUnavailableException.getMessage());
            apiError.setStatus(serviceUnavailableException.getStatus());
        }
        return ResponseEntity.status(apiError.getStatus()).body(apiError);
    }
}
