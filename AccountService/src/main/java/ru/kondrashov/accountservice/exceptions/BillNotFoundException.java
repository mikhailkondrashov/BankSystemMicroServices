package ru.kondrashov.accountservice.exceptions;

public class BillNotFoundException extends RuntimeException {

    public BillNotFoundException(String s) {
        super(s);
    }
}
