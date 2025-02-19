package org.project.exception;


import jakarta.servlet.http.HttpServletRequest;
import lombok.RequiredArgsConstructor;
import org.project.error.ApiError;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RequiredArgsConstructor
@Component
@RestControllerAdvice
public class ExceptionHandler {

    @org.springframework.web.bind.annotation.ExceptionHandler(NotFoundException.class)
    public ResponseEntity<ApiError> handleInvalidActivationException (NotFoundException ex, HttpServletRequest request) {
        ApiError apiError = new ApiError();
        apiError.setPath(request.getRequestURI());
        apiError.setMessage(ex.getMessage());
        apiError.setStatus(ex.getStatus());
        return ResponseEntity.status(ex.getStatus()).body(apiError);
    }
}
