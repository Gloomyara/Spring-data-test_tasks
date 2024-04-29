package ru.antonovmikhail.jdbc.book.model;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.*;
import ru.antonovmikhail.jdbc.abstractions.Entity;

import java.time.LocalDate;

import static ru.antonovmikhail.jdbc.util.Constants.DATE_PATTERN;

@Data
@Builder
@RequiredArgsConstructor
@AllArgsConstructor
public class Book implements Entity<Long> {
    private Long id;
    @ToString.Exclude
    @EqualsAndHashCode.Exclude
    private Long author_id;
    private String title;
    private String description;
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = DATE_PATTERN)
    private LocalDate publishDate;
}
