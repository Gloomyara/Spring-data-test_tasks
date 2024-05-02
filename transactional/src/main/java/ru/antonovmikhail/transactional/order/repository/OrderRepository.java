package ru.antonovmikhail.transactional.order.repository;

import jakarta.persistence.Entity;
import jakarta.persistence.LockModeType;
import jakarta.persistence.QueryHint;
import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Lock;
import org.springframework.data.jpa.repository.QueryHints;
import ru.antonovmikhail.transactional.order.model.Order;

import java.util.Optional;
import java.util.UUID;

public interface OrderRepository extends JpaRepository<Order, UUID> {

    @Lock(LockModeType.PESSIMISTIC_WRITE)
    @QueryHints({@QueryHint(name = "jakarta.persistence.lock.timeout", value = "3000")})
    @EntityGraph(attributePaths = {"products", "products.product"})
    Optional<Order> getByIdAndCustomerIdAndPaid(UUID id, UUID customerId, boolean paid);
}
