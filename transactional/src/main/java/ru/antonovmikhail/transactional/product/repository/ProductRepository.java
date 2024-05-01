package ru.antonovmikhail.transactional.product.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.antonovmikhail.transactional.product.model.Product;

import java.util.UUID;

public interface ProductRepository extends JpaRepository<Product, UUID> {

}
