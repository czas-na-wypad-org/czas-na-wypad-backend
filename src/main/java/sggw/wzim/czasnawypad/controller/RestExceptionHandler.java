package sggw.wzim.czasnawypad.controller;

import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.oauth2.server.resource.InvalidBearerTokenException;
import org.springframework.validation.BindException;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import sggw.wzim.czasnawypad.exception.ApplicationExceptions;
import sggw.wzim.czasnawypad.model.ErrorResponseDTO;

@RestControllerAdvice
@Slf4j
public class RestExceptionHandler {

    // Common handler for all the exceptions -  returns 500 Internal Server Error
    @ExceptionHandler(Exception.class)
    public ResponseEntity<ErrorResponseDTO> handleGlobalException(Exception ex) {
        // Since we are defining our custom exception handler we have to log the exception here
        log.error(ex.getMessage(), ex);
        HttpStatus status = HttpStatus.INTERNAL_SERVER_ERROR;
        return handleExceptionInternal(status);
    }

    @ExceptionHandler({
            HttpMessageNotReadableException.class,
            MethodArgumentNotValidException.class,
            BindException.class
    })
    public ResponseEntity<ErrorResponseDTO> handleHttpMessageNotReadableException(HttpMessageNotReadableException ex) {
        log.error(ex.getMessage(), ex);
        HttpStatus status = HttpStatus.BAD_REQUEST;
        return handleExceptionInternal(status);
    }

    @ExceptionHandler(AccessDeniedException.class)
    public ResponseEntity<ErrorResponseDTO> handleAccessDeniedException(AccessDeniedException ex) {
        log.error(ex.getMessage(), ex);
        HttpStatus status = HttpStatus.FORBIDDEN;
        return handleExceptionInternal(status);
    }

    @ExceptionHandler({
            UsernameNotFoundException.class,
            InvalidBearerTokenException.class
    })
    public ResponseEntity<ErrorResponseDTO> handleUsernameNotFoundException(Exception ex) {
        log.error(ex.getMessage(), ex);
        HttpStatus status = HttpStatus.UNAUTHORIZED;
        return handleExceptionInternal(status);
    }

    @ExceptionHandler({
            ApplicationExceptions.AttractionNotFoundException.class,
            ApplicationExceptions.UserNotFoundException.class,
            ApplicationExceptions.RatingNotFoundException.class
    })
    public ResponseEntity<ErrorResponseDTO> handleNotFoundExceptions(RuntimeException ex) {
        log.error(ex.getMessage(), ex);
        HttpStatus status = HttpStatus.NOT_FOUND;
        return handleExceptionInternal(status, ex.getMessage());
    }

    private ResponseEntity<ErrorResponseDTO> handleExceptionInternal(HttpStatus status) {
        return new ResponseEntity<>(new ErrorResponseDTO(status.value(), status.getReasonPhrase()), status);
    }

    private ResponseEntity<ErrorResponseDTO> handleExceptionInternal(HttpStatus status, String message) {
        return new ResponseEntity<>(new ErrorResponseDTO(status.value(), message), status);
    }
}
