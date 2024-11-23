package sggw.wzim.czasnawypad.controller;

import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
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

    @ExceptionHandler(HttpMessageNotReadableException.class)
    public ResponseEntity<ErrorResponseDTO> handleHttpMessageNotReadableException(HttpMessageNotReadableException ex) {
        log.error(ex.getMessage(), ex);
        HttpStatus status = HttpStatus.BAD_REQUEST;
        return handleExceptionInternal(status);
    }

    private ResponseEntity<ErrorResponseDTO> handleExceptionInternal(HttpStatus status) {
        return new ResponseEntity<>(new ErrorResponseDTO(status.value(), status.getReasonPhrase()), status);
    }

}
