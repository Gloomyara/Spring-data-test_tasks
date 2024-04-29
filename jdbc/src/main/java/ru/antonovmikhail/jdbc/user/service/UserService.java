package ru.antonovmikhail.jdbc.user.service;

import ru.antonovmikhail.jdbc.user.model.User;
import ru.antonovmikhail.jdbc.user.model.UserDtoIn;

import java.util.List;

public interface UserService {

    User findById(Long id);

    List<User> findAll();

    User create(UserDtoIn userDtoIn);

    User update(UserDtoIn userDtoIn);

    User delete(Long id);

}
