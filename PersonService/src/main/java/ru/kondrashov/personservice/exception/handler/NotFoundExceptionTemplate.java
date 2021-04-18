package ru.kondrashov.personservice.exception.handler;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@AllArgsConstructor
@Setter
@Getter
public class NotFoundExceptionTemplate {

    private String message;
    private Integer httpCode;
    private String exception;
}
