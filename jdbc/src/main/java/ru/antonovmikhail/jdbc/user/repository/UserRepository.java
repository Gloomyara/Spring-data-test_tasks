package ru.antonovmikhail.jdbc.user.repository;

import ru.antonovmikhail.jdbc.abstractions.Repository;
import ru.antonovmikhail.jdbc.user.model.User;

public interface UserRepository extends Repository<Long, User> {
    void existsByEmail(String email);
}
