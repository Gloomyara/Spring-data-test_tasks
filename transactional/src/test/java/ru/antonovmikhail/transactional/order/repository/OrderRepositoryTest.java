package ru.antonovmikhail.transactional.order.repository;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import ru.antonovmikhail.transactional.customer.model.Customer;
import ru.antonovmikhail.transactional.customer.repository.CustomerRepository;
import ru.antonovmikhail.transactional.order.model.Order;
import ru.antonovmikhail.transactional.order.model.OrderProduct;
import ru.antonovmikhail.transactional.order.model.OrderProductKey;
import ru.antonovmikhail.transactional.order.service.OrderService;
import ru.antonovmikhail.transactional.order.service.OrderServiceImpl;
import ru.antonovmikhail.transactional.product.model.Product;
import ru.antonovmikhail.transactional.product.repository.ProductRepository;
import ru.antonovmikhail.transactional.util.handler.InsufficientAmountException;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.ArrayList;

@DataJpaTest
class OrderRepositoryTest {
    @Autowired
    private TestEntityManager entityManager;
    @Autowired
    private OrderRepository repository;
    @Autowired
    private CustomerRepository customerRepository;
    @Autowired
    private ProductRepository productRepository;
    private OrderService service;


    Customer customer = new Customer(null, "buba", null);
    Product product1 = new Product(null, "лампочка", "хорошая", BigDecimal.valueOf(100), 1000L);
    Product product2 = new Product(null, "лампочка", "китайская", BigDecimal.valueOf(10), 10000L);
    Order order = new Order(null, customer, "онлайн", false, LocalDateTime.now(), new ArrayList<>(), BigDecimal.valueOf(20000));
    OrderProduct orderProduct1 = new OrderProduct(null, product1, 100L);
    OrderProduct orderProduct2 = new OrderProduct(null, product2, 1000L);

    @BeforeEach
    void setUp() {
        customer.setBalance(BigDecimal.valueOf(100000));
        entityManager.persist(customer);
        entityManager.persist(product1);
        entityManager.persist(product2);
        entityManager.persist(order);
        OrderProductKey key1 = new OrderProductKey(order.getId(), product1.getId());
        OrderProductKey key2 = new OrderProductKey(order.getId(), product2.getId());
        orderProduct1.setKey(key1);
        orderProduct2.setKey(key2);
        entityManager.persist(orderProduct1);
        entityManager.persist(orderProduct2);
        order.setProducts(new ArrayList<>());
        order.getProducts().add(orderProduct1);
        order.getProducts().add(orderProduct2);
        service = new OrderServiceImpl(customerRepository, repository);
    }

    @Test
    void placeOrder() {
        System.out.println(product1);
        System.out.println(product2);
        System.out.println(customer);
        System.out.println(service.placeOrder(order.getId().toString(), customer.getId().toString()));
        System.out.println(product1);
        System.out.println(product2);
        System.out.println(customerRepository.findById(customer.getId()));
    }

    @Test
    void placeOrderWithInsufficientAmount_throwsException() {
        System.out.println(product1);
        System.out.println(product2);
        System.out.println(customer);
        customer.setBalance(BigDecimal.valueOf(19999));
        InsufficientAmountException ex1 = Assertions.assertThrows(
                InsufficientAmountException.class,
                () -> service.placeOrder(order.getId().toString(), customer.getId().toString())
        );
        System.out.println(productRepository.findAll());
        System.out.println(customerRepository.findById(customer.getId()));
    }

    @Test
    void placeOrderWithInsufficientQuantity_throwsException() {
        System.out.println(product1);
        System.out.println(product2);
        System.out.println(customer);
        product2.setQuantity(99L);
        InsufficientAmountException ex1 = Assertions.assertThrows(
                InsufficientAmountException.class,
                () -> service.placeOrder(order.getId().toString(), customer.getId().toString())
        );
        System.out.println(productRepository.findAll());
        System.out.println(customerRepository.findById(customer.getId()));
    }
}