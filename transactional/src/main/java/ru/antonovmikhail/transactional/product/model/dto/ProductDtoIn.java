package ru.antonovmikhail.transactional.product.model.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import jakarta.validation.constraints.PositiveOrZero;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.validator.constraints.UUID;

import java.math.BigDecimal;


@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class ProductDtoIn {
    @UUID
    private String id;
    @NotNull
    @Positive
    private BigDecimal price;
    @NotBlank
    private String name;
    private String description;
    @PositiveOrZero
    private Long quantity;
}
