package ru.antonovmikhail.jdbc.book.repository;

import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;
import ru.antonovmikhail.jdbc.abstractions.AbstractRepository;
import ru.antonovmikhail.jdbc.abstractions.EntityMapper;
import ru.antonovmikhail.jdbc.book.model.Book;

@Repository
public class BookRepositoryImpl extends AbstractRepository<Book> implements BookRepository {

    protected BookRepositoryImpl(JdbcTemplate jdbcTemplate, EntityMapper<Book> mapper) {
        super(jdbcTemplate, mapper);
    }
}
