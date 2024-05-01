package ru.antonovmikhail.transactional.customer.model;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.PositiveOrZero;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class NewCustomerDto {
    @NotBlank
    private String username;
    @PositiveOrZero
    private BigDecimal balance = BigDecimal.valueOf(0);
}
