package org.example.homemade2.exceptions;

import jakarta.servlet.http.HttpServletRequest;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;

import java.time.LocalDate;
import java.util.Date;

@ControllerAdvice
@Slf4j
public class GlobalExceptionHandler {
    @ExceptionHandler(NotFoundException.class)
    @ResponseStatus(code = HttpStatus.NOT_FOUND)
    public ResponseEntity<ExceptionDTO> notFoundException(Exception e) {
        ExceptionDTO response = new ExceptionDTO(404 , new Date(),e.getMessage());
        log.error(" Not Found  :{}",e.getMessage());
        return new ResponseEntity<>(response, HttpStatus.NOT_FOUND);
    }


    @ExceptionHandler(AlreadyExistException.class)
    @ResponseStatus(code = HttpStatus.CONFLICT)
    public ResponseEntity<ExceptionDTO> alreadyExistException(Exception e) {
        ExceptionDTO response = new ExceptionDTO(409 , new Date(),e.getMessage());
        log.error(" Already exist  :{}",e.getMessage());
        return new ResponseEntity<>(response, HttpStatus.CONFLICT);
    }
}
