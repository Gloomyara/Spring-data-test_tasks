package ru.antonovmikhail.transactional.order.controller;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.hibernate.validator.constraints.UUID;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import ru.antonovmikhail.transactional.order.model.dto.NewOrderDto;
import ru.antonovmikhail.transactional.order.model.dto.OrderDtoIn;
import ru.antonovmikhail.transactional.order.model.dto.OrderDtoOut;
import ru.antonovmikhail.transactional.order.service.OrderService;

import java.util.List;

@Slf4j
@Validated
@Controller
@RequestMapping("api/v1")
@RequiredArgsConstructor
public class OrderController {
    private final OrderService orderService;

    @PatchMapping("orders/{id}/pay/{customerId}")
    public ResponseEntity<OrderDtoOut> placeOrder(@UUID String id, @UUID String customerId) {
        log.info("Received PATCH orders/{}/pay/{} request ", id, customerId);
        return ResponseEntity.ok(orderService.placeOrder(id, customerId));
    }

    @GetMapping("orders")
    public ResponseEntity<List<OrderDtoOut>> getAll() {
        log.info("Received GET api/v1/users request.");
        return ResponseEntity.ok(orderService.findAll());
    }


    @GetMapping("orders/{id}")
    public ResponseEntity<OrderDtoOut> getOrder(@UUID String id) {
        log.info("Received GET api/v1/orders/{} request.", id);
        return ResponseEntity.ok(orderService.getOrderById(id));
    }

    @PostMapping("order")
    public ResponseEntity<OrderDtoOut> createNewOrder(
            @RequestBody @Valid NewOrderDto dtoIn) {
        log.info("Received POST api/v1/order request, orderDtoIn: {}.", dtoIn);
        return ResponseEntity
                .status(HttpStatus.CREATED)
                .body(orderService.saveNewOrder(dtoIn));
    }

    @PutMapping("order")
    public ResponseEntity<OrderDtoOut> put(@RequestBody @Valid OrderDtoIn dtoIn) {
        log.info("Received PUT api/v1/order request, orderDtoIn = {}", dtoIn);
        return ResponseEntity.ok(orderService.update(dtoIn));
    }

    @DeleteMapping("order/{id}")
    public ResponseEntity<OrderDtoOut> delete(@UUID String id) {
        log.info("Received DELETE api/v1/order/{} request.", id);
        return ResponseEntity.ok(orderService.delete(id));
    }
}



