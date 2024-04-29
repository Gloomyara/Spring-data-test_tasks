package ru.antonovmikhail.jdbc.abstractions;

import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.simple.SimpleJdbcInsert;
import org.springframework.jdbc.support.rowset.SqlRowSet;
import ru.antonovmikhail.jdbc.util.handler.EntityNotFoundException;

import java.util.List;
import java.util.Optional;

public abstract class AbstractRepository<V extends Entity<Long>> implements Repository<Long, V> {

    protected final JdbcTemplate jdbcTemplate;
    protected final EntityMapper<V> mapper;

    protected AbstractRepository(JdbcTemplate jdbcTemplate,
                                 EntityMapper<V> mapper) {
        this.jdbcTemplate = jdbcTemplate;
        this.mapper = mapper;
    }

    @Override
    public void containsOrElseThrow(Long id) {
        SqlRowSet rows = jdbcTemplate.queryForRowSet(
                "select id from " + mapper.getTableName() + " where id = ?", id);
        if (!rows.next()) {
            throw new EntityNotFoundException(mapper.getTableName() + " with Id: " + id + " not found");
        }
    }

    @Override
    public V save(V v) {
        v.setId(new SimpleJdbcInsert(jdbcTemplate)
                .withTableName(mapper.getTableName())
                .usingGeneratedKeyColumns("id")
                .executeAndReturnKey(mapper.toMap(v)).longValue());
        return v;
    }

    @Override
    public V update(V v) {
        String sql = "UPDATE " + mapper.getTableName() +
                " SET " + getFieldsWithQuestionMark() +
                " WHERE ID = " + v.getId();
        jdbcTemplate.update(sql, mapper.toMap(v).values().toArray());
        return v;
    }

    @Override
    public Optional<V> findById(Long id) {
        String sql = "SELECT ID," + getFieldsSeparatedByCommas() +
                " FROM " + mapper.getTableName() +
                " WHERE ID = ?";
        try {
            return Optional.ofNullable(jdbcTemplate.queryForObject(sql, mapper, id));
        } catch (EmptyResultDataAccessException e) {
            return Optional.empty();
        }
    }

    @Override
    public List<V> findAll() {
        return jdbcTemplate.query("SELECT ID, " +
                getFieldsSeparatedByCommas() +
                " FROM " + mapper.getTableName(), mapper);
    }

    @Override
    public Optional<V> deleteById(Long id) {
        Optional<V> optT = findById(id);
        jdbcTemplate.update("DELETE FROM " + mapper.getTableName() + " WHERE id = ?", id);
        return optT;
    }

    protected String getFieldsWithQuestionMark() {
        StringBuilder stringBuilder = new StringBuilder();
        for (String field : mapper.getTableFields()) {
            stringBuilder.append(field).append(" = ?, ");
        }
        if (stringBuilder.length() > 2) {
            stringBuilder.delete(stringBuilder.length() - 2, stringBuilder.length());
        }
        return stringBuilder.toString();
    }

    protected String getFieldsSeparatedByCommas() {
        StringBuilder stringBuilder = new StringBuilder();
        for (String field : mapper.getTableFields()) {
            stringBuilder.append(field).append(", ");
        }
        if (stringBuilder.length() > 2) {
            stringBuilder.delete(stringBuilder.length() - 2, stringBuilder.length());
        }
        return stringBuilder.toString();
    }
}