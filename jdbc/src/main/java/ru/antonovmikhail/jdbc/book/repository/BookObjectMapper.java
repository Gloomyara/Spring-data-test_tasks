package ru.antonovmikhail.jdbc.book.repository;

import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Component;
import ru.antonovmikhail.jdbc.abstractions.EntityMapper;
import ru.antonovmikhail.jdbc.book.model.Book;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

@Component
public class BookObjectMapper implements EntityMapper<Book> {
    private static final String TABLE_NAME = "books";
    private static final List<String> TABLE_FIELDS = List.of(
            "title",
            "description",
            "author_id",
            "publish_date"
    );

    public String getTableName() {
        return TABLE_NAME;
    }

    public List<String> getTableFields() {
        return TABLE_FIELDS;
    }

    public Map<String, Object> toMap(Book book) {
        Map<String, Object> params = new LinkedHashMap<>();
        params.put(TABLE_FIELDS.get(0), book.getTitle());
        params.put(TABLE_FIELDS.get(1), book.getDescription());
        params.put(TABLE_FIELDS.get(2), book.getAuthor_id());
        params.put(TABLE_FIELDS.get(3), book.getPublishDate());
        return params;
    }

    public Book mapRow(ResultSet rs, int rowNum) throws SQLException {

        return Book.builder()
                .id(rs.getLong("id"))
                .title(rs.getString(TABLE_FIELDS.get(0)))
                .description(rs.getString(TABLE_FIELDS.get(1)))
                .author_id(rs.getLong(TABLE_FIELDS.get(2)))
                .publishDate(rs.getDate(TABLE_FIELDS.get(3)).toLocalDate())
                .build();
    }
}
