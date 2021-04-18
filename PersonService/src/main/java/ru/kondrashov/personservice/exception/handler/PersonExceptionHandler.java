package ru.kondrashov.personservice.exception.handler;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;
import ru.kondrashov.personservice.exception.PersonNotFoundException;


@ControllerAdvice
public class PersonExceptionHandler extends ResponseEntityExceptionHandler {

    @ExceptionHandler(PersonNotFoundException.class)
    public ResponseEntity<NotFoundExceptionTemplate> notFoundExceptions(PersonNotFoundException exp){
        return new ResponseEntity<>(
                new NotFoundExceptionTemplate(
                        exp.getMessage(),
                404,
                "PersonNotFoundException"),
                HttpStatus.NOT_FOUND);
    }
}
