package ru.antonovmikhail.jdbc.book.model.dto;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

import static ru.antonovmikhail.jdbc.util.Constants.DATE_PATTERN;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class BookDtoOut {

    private String authorName;
    private String title;
    private String description;
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = DATE_PATTERN)
    private LocalDateTime publishDate;
}
