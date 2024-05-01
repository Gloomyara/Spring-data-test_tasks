package ru.antonovmikhail.transactional.customer.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.antonovmikhail.transactional.customer.model.Customer;

import java.util.UUID;

public interface CustomerRepository extends JpaRepository<Customer, UUID> {
}
