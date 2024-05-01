package ru.antonovmikhail.transactional.util.handler;

public class InsufficientAmountException extends RuntimeException {
    public InsufficientAmountException() {
    }

    public InsufficientAmountException(String s) {
        super(s);
    }
}
