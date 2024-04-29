package ru.antonovmikhail.jdbc.user.repository;

import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.support.rowset.SqlRowSet;
import org.springframework.stereotype.Repository;
import ru.antonovmikhail.jdbc.abstractions.AbstractRepository;
import ru.antonovmikhail.jdbc.abstractions.EntityMapper;
import ru.antonovmikhail.jdbc.user.model.User;
import ru.antonovmikhail.jdbc.util.handler.EntityNotFoundException;

@Repository
public class UserRepositoryImpl extends AbstractRepository<User> implements UserRepository {

    protected UserRepositoryImpl(JdbcTemplate jdbcTemplate, EntityMapper<User> mapper) {
        super(jdbcTemplate, mapper);
    }

    @Override
    public void existsByEmail(String email) {
        SqlRowSet rows = jdbcTemplate.queryForRowSet(
                "select email from " + mapper.getTableName() + " where email = ?", email);
        if (!rows.next()) {
            throw new EntityNotFoundException(mapper.getTableName() + " with email: " + email + " already registered");
        }
    }
}
