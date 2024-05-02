package ru.antonovmikhail.transactional.order.service;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import ru.antonovmikhail.transactional.customer.model.Customer;
import ru.antonovmikhail.transactional.customer.repository.CustomerRepository;
import ru.antonovmikhail.transactional.order.model.Order;
import ru.antonovmikhail.transactional.order.model.OrderProduct;
import ru.antonovmikhail.transactional.order.model.OrderProductKey;
import ru.antonovmikhail.transactional.order.repository.OrderProductRepository;
import ru.antonovmikhail.transactional.order.repository.OrderRepository;
import ru.antonovmikhail.transactional.order.service.OrderService;
import ru.antonovmikhail.transactional.product.model.Product;
import ru.antonovmikhail.transactional.product.repository.ProductRepository;
import ru.antonovmikhail.transactional.util.handler.InsufficientAmountException;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.ArrayList;

@SpringBootTest
class OrderServiceTest {
    @Autowired
    private OrderRepository repository;
    @Autowired
    private CustomerRepository customerRepository;
    @Autowired
    private ProductRepository productRepository;
    @Autowired
    private OrderProductRepository orderProductRepository;
    @Autowired
    private OrderService service;


    Customer customer = new Customer(null, "buba", null);
    Product product1 = new Product(null, "лампочка", "хорошая", BigDecimal.valueOf(100), 1000L);
    Product product2 = new Product(null, "лампочка", "китайская", BigDecimal.valueOf(10), 10000L);
    Order order = new Order(null, customer, "онлайн", false, LocalDateTime.now(), new ArrayList<>(), BigDecimal.valueOf(20000));
    OrderProduct orderProduct1 = new OrderProduct(null, null, 100L);
    OrderProduct orderProduct2 = new OrderProduct(null, null, 1000L);
  
    @BeforeEach
    void setUp() {
        customer.setBalance(BigDecimal.valueOf(100000));
        customer = customerRepository.save(customer);
        product1 = productRepository.save(product1);
        product2 = productRepository.save(product2);
        order = repository.save(order);
        OrderProductKey key1 = new OrderProductKey(order.getId(), product1.getId());
        OrderProductKey key2 = new OrderProductKey(order.getId(), product2.getId());
        orderProduct1.setId(key1);
        orderProduct2.setId(key2);
        orderProduct1.setProduct(product1);
        orderProduct2.setProduct(product2);
        System.out.println(orderProduct1);
        orderProductRepository.save(orderProduct1);
        orderProductRepository.save(orderProduct2);
        System.out.println(orderProduct1);
        order.setProducts(new ArrayList<>());
        order.getProducts().add(orderProduct1);
        order.getProducts().add(orderProduct2);
        order = repository.save(order);
    }

    @Test
    void placeOrder() {

        System.out.println(orderProductRepository.findAll());
        System.out.println(service.placeOrder(order.getId().toString(), customer.getId().toString()));
        System.out.println(productRepository.findAll());
        System.out.println(customerRepository.findById(customer.getId()));
    }

    @Test
    void placeOrderWithInsufficientAmount_throwsException() {
        System.out.println(product1);
        System.out.println(product2);
        System.out.println(customer);
        customer.setBalance(BigDecimal.valueOf(19999));
        customerRepository.save(customer);
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
        productRepository.save(product2);
        InsufficientAmountException ex1 = Assertions.assertThrows(
                InsufficientAmountException.class,
                () -> service.placeOrder(order.getId().toString(), customer.getId().toString())
        );
        System.out.println(productRepository.findAll());
        System.out.println(customerRepository.findById(customer.getId()));
    }
}