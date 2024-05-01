package ru.antonovmikhail.transactional.order.model.dto;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.validator.constraints.UUID;


@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class OrderProductDtoIn {
    @UUID
    String orderId;
    @NotNull
    @UUID
    String productId;
    @Positive
    Long quantity;
}
