package ru.antonovmikhail.transactional.order.model.dto;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;

import static ru.antonovmikhail.transactional.util.Constants.DATE_TIME_PATTERN;


@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class OrderDtoOut {
    private String customerName;
    private String description;
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = DATE_TIME_PATTERN)
    private LocalDateTime updateDate;
    private List<OrderProductDtoOut> productBatch;
    private BigDecimal totalAmount;
    private Boolean paid;
}
