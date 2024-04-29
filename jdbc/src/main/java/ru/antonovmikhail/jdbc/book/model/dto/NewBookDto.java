package ru.antonovmikhail.jdbc.book.model.dto;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
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
public class NewBookDto {

    @Positive
    @NotNull
    @JsonProperty(value = "author_id")
    private Long authorId;
    @NotBlank
    private String title;
    @NotBlank
    private String description;

    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = DATE_PATTERN)
    private LocalDateTime publishDate;
}
