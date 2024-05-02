package ru.antonovmikhail.transactional.order.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.antonovmikhail.transactional.order.model.OrderProduct;

import java.util.UUID;

public interface OrderProductRepository extends JpaRepository<OrderProduct, UUID> {
}
