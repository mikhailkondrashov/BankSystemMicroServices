package ru.kondrashov.accountservice.exceptions.handler;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@AllArgsConstructor
@Setter
@Getter
public class AccountServiceExceptionTemplate {

    private String message;
    private Integer httpCode;
    private String exception;
}
