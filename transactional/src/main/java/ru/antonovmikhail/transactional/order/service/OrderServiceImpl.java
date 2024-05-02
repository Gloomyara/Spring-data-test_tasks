package ru.antonovmikhail.transactional.order.service;

import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.mapstruct.factory.Mappers;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import ru.antonovmikhail.transactional.customer.model.Customer;
import ru.antonovmikhail.transactional.customer.repository.CustomerRepository;
import ru.antonovmikhail.transactional.order.mapper.OrderMapper;
import ru.antonovmikhail.transactional.order.model.Order;
import ru.antonovmikhail.transactional.order.model.OrderProduct;
import ru.antonovmikhail.transactional.order.model.dto.NewOrderDto;
import ru.antonovmikhail.transactional.order.model.dto.OrderDtoIn;
import ru.antonovmikhail.transactional.order.model.dto.OrderDtoOut;
import ru.antonovmikhail.transactional.order.repository.OrderRepository;
import ru.antonovmikhail.transactional.product.model.Product;
import ru.antonovmikhail.transactional.product.repository.ProductRepository;
import ru.antonovmikhail.transactional.util.handler.InsufficientAmountException;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;

@Service
@Transactional
@RequiredArgsConstructor
public class OrderServiceImpl implements OrderService {

    private final CustomerRepository customerRepository;
    private final OrderRepository repository;
    private final ProductRepository productRepository;
    private final OrderMapper mapper = Mappers.getMapper(OrderMapper.class);

    @Transactional
    @Override
    public OrderDtoOut placeOrder(String orderId, String customerId) {
        Order order = repository.getByIdAndCustomerIdAndPaid(UUID.fromString(orderId), UUID.fromString(customerId), false)
                .orElseThrow(() -> new EntityNotFoundException());
        checkStock(order);
        checkAmountAndSetPaid(order);
        return mapper.toDto(order);
    }

    @Transactional(propagation = Propagation.SUPPORTS)
    @Override
    public void checkStock(Order order) {
        for (OrderProduct orderProduct : order.getProducts()) {
            Product product = orderProduct.getProduct();
            Long stock = product.getQuantity();
            stock -= orderProduct.getQuantity();
            if (stock < 0) {
                throw new InsufficientAmountException("На складе не хватает:" + stock * -1 + " " + product.getName());
            }
            product.setQuantity(stock);
        }
    }

    @Transactional(propagation = Propagation.SUPPORTS)
    @Override
    public void checkAmountAndSetPaid(Order order) {
        Customer customer = order.getCustomer();
        BigDecimal balance = customer.getBalance().subtract(order.getTotalAmount());
        if (balance.compareTo(BigDecimal.ZERO) < 0) throw new InsufficientAmountException("Недостаточно средств");
        customer.setBalance(balance);
        order.setPaid(true);
    }

    @Transactional(readOnly = true)
    @Override
    public OrderDtoOut getOrderById(String id) throws EntityNotFoundException {
        return mapper.toDto(repository.findById(UUID.fromString(id)).orElseThrow(() -> new EntityNotFoundException()));
    }

    @Override
    public OrderDtoOut saveNewOrder(NewOrderDto dtoIn) throws EntityNotFoundException {
        Customer customer = customerRepository.findById(UUID.fromString(dtoIn.getCustomerId())).orElseThrow(() -> new EntityNotFoundException());
        Order order = mapper.toEntity(dtoIn);
        order.setCustomer(customer);
        order.setUpdateDate(LocalDateTime.now());
        return mapper.toDto(order);
    }

    @Override
    public OrderDtoOut update(OrderDtoIn dtoIn) throws EntityNotFoundException {
        if (customerRepository.existsById(UUID.fromString(dtoIn.getCustomerId()))) {
            Order order = repository.findById(UUID.fromString(dtoIn.getId())).orElseThrow(() -> new EntityNotFoundException());
            mapper.update(dtoIn, order);
            order.setUpdateDate(LocalDateTime.now());
            return mapper.toDto(repository.save(order));
        }
        throw new EntityNotFoundException();
    }

    @Override
    @Transactional(readOnly = true)
    public List<OrderDtoOut> findAll() {
        return mapper.toDto(repository.findAll());
    }

    @Override
    public OrderDtoOut delete(String id) throws EntityNotFoundException {
        UUID uuid = UUID.fromString(id);
        Order order = repository.findById(uuid).orElseThrow(() -> new EntityNotFoundException());
        repository.deleteById(uuid);
        return mapper.toDto(order);
    }
}
