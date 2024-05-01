package ru.antonovmikhail.transactional.order.service;

import ru.antonovmikhail.transactional.order.model.dto.NewOrderDto;
import ru.antonovmikhail.transactional.order.model.dto.OrderDtoIn;
import ru.antonovmikhail.transactional.order.model.dto.OrderDtoOut;

import java.util.List;


public interface OrderService {

    OrderDtoOut placeOrder(String orderId, String customerId);

    OrderDtoOut getOrderById(String id);

    OrderDtoOut saveNewOrder(NewOrderDto dto);

    OrderDtoOut update(OrderDtoIn dtoIn);

    List<OrderDtoOut> findAll();

    OrderDtoOut delete(String id);
}
