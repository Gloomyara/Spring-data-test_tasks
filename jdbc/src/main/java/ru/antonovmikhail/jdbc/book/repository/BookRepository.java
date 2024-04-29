package ru.antonovmikhail.jdbc.book.repository;

import ru.antonovmikhail.jdbc.abstractions.Repository;
import ru.antonovmikhail.jdbc.book.model.Book;

public interface BookRepository extends Repository<Long, Book> {
}
