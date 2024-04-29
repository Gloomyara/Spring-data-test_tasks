package ru.antonovmikhail.jdbc.util.handler;

public class EntityNotFoundException extends RuntimeException {
    public EntityNotFoundException(String s) {
        super(s);
    }

    public EntityNotFoundException() {

    }
}
