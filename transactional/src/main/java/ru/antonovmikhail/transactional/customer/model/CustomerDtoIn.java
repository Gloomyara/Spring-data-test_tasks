package ru.antonovmikhail.transactional.customer.model;

import jakarta.validation.constraints.PositiveOrZero;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.validator.constraints.UUID;

import java.math.BigDecimal;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class CustomerDtoIn {

    @UUID
    private String id;
    private String username;
    @PositiveOrZero
    private BigDecimal balance = BigDecimal.valueOf(0);
}
