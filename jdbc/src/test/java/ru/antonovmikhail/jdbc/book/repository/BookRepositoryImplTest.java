package ru.antonovmikhail.jdbc.book.repository;

import lombok.RequiredArgsConstructor;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.jdbc.core.JdbcTemplate;
import ru.antonovmikhail.jdbc.abstractions.Repository;
import ru.antonovmikhail.jdbc.book.model.Book;
import ru.antonovmikhail.jdbc.user.model.User;
import ru.antonovmikhail.jdbc.user.repository.UserRepository;

import java.time.LocalDate;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest
@AutoConfigureTestDatabase
@RequiredArgsConstructor(onConstructor_ = @Autowired)
class BookRepositoryImplTest {
    private final JdbcTemplate jdbcTemplate;
    private final BookRepository repository;
    private final UserRepository userRepository;
    private final User user = User.builder()
            .name("test1")
            .email("test1@test.omg")
            .build();


    @BeforeEach
    void setUp() {
        userRepository.save(user);
    }

    @Test
    void findAll() {
        for (int i = 1; i <= 15; i++) {
            repository.save(new Book(null,
                    user.getId(),
                    "wtvr" + i,
                    "wtvr" + i,
                    LocalDate.now().minusDays(i)));
        }
        List<Book> foundBooks = repository
                .findAll();
        assertThat(foundBooks).hasSize(15);
    }
}