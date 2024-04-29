package ru.antonovmikhail.jdbc.abstractions;

public interface Entity<K> {

    void setId(K k);
    K getId();
}