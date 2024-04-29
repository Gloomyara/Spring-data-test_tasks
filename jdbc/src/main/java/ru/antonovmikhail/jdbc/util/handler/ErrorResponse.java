package ru.antonovmikhail.jdbc.util.handler;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Builder;
import lombok.Data;
import org.springframework.http.HttpStatus;
import ru.antonovmikhail.jdbc.util.Constants;

import java.time.LocalDateTime;


@Data
@Builder
public class ErrorResponse {
    private HttpStatus status;
    private String reason;
    private String message;
    @JsonFormat(pattern = Constants.DATE_TIME_PATTERN)
    private LocalDateTime timestamp;
}
