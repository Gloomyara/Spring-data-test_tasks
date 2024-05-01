package ru.antonovmikhail.transactional.order.model.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.validator.constraints.UUID;

import java.util.List;


@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class OrderDtoIn {
    @NotNull
    @UUID
    private String id;
    @NotNull
    @UUID
    @JsonProperty(value = "customer_uuid")
    private String customerId;
    @NotNull
    Status status;
    private String description;
    private List<OrderProductDtoIn> productBatch;

    public enum Status {
        WISHLIST,
        PAID,
        PENDING,
    }
}
