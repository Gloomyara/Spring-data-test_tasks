package ru.antonovmikhail.jdbc.user.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.antonovmikhail.jdbc.user.model.User;
import ru.antonovmikhail.jdbc.user.model.UserDtoIn;
import ru.antonovmikhail.jdbc.user.repository.UserRepository;
import ru.antonovmikhail.jdbc.util.handler.EntityNotFoundException;

import java.util.List;

@Service
@Transactional
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {
    private final UserRepository repository;

    @Override
    @Transactional(readOnly = true)
    public User findById(Long id) {
        return repository.findById(id).orElseThrow(() -> new EntityNotFoundException());
    }

    @Override
    public User create(UserDtoIn dtoIn) {
        repository.existsByEmail(dtoIn.getEmail());
        User user = User.builder()
                .name(dtoIn.getName())
                .email(dtoIn.getEmail())
                .build();
        return repository.save(user);
    }

    @Override
    public User update(UserDtoIn dtoIn) {
        User user = repository.findById(dtoIn.getId()).orElseThrow(() -> new EntityNotFoundException());
        user.setName(dtoIn.getName());
        user.setEmail(dtoIn.getEmail());
        return repository.update(user);
    }

    @Override
    @Transactional(readOnly = true)
    public List<User> findAll() {
        return repository.findAll();
    }

    @Override
    public User delete(Long id) {
        User user = repository.findById(id).orElseThrow(() -> new EntityNotFoundException());
        repository.deleteById(id);
        return user;
    }
}
