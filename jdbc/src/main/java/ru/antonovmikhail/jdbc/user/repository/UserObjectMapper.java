package ru.antonovmikhail.jdbc.user.repository;

import org.springframework.stereotype.Component;
import ru.antonovmikhail.jdbc.abstractions.EntityMapper;
import ru.antonovmikhail.jdbc.user.model.User;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

@Component
public class UserObjectMapper implements EntityMapper<User> {
    private static final String TABLE_NAME = "users";
    private static final List<String> TABLE_FIELDS = List.of(
            "name",
            "email"
    );

    public String getTableName() {
        return TABLE_NAME;
    }

    public List<String> getTableFields() {
        return TABLE_FIELDS;
    }

    public Map<String, Object> toMap(User user) {
        Map<String, Object> params = new LinkedHashMap<>();
        params.put(TABLE_FIELDS.get(0), user.getName());
        params.put(TABLE_FIELDS.get(1), user.getEmail());
        return params;
    }

    public User mapRow(ResultSet rs, int rowNum) throws SQLException {

        return User.builder()
                .id(rs.getLong("id"))
                .name(rs.getString(TABLE_FIELDS.get(0)))
                .email(rs.getString(TABLE_FIELDS.get(1)))
                .build();
    }
}
