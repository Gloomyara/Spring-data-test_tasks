package ru.antonovmikhail.transactional.customer.controller;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.hibernate.validator.constraints.UUID;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import ru.antonovmikhail.transactional.customer.model.CustomerDtoIn;
import ru.antonovmikhail.transactional.customer.model.CustomerDtoOut;
import ru.antonovmikhail.transactional.customer.model.NewCustomerDto;
import ru.antonovmikhail.transactional.customer.service.CustomerService;

import java.util.List;

@RestController
@Validated
@RequiredArgsConstructor
@RequestMapping("api/v1")
@Slf4j
public class CustomerController {

    private final CustomerService service;

    @GetMapping("customers")
    public ResponseEntity<List<CustomerDtoOut>> getAll() {
        log.info("Received GET api/v1/customers request.");
        return ResponseEntity.ok(service.findAll());
    }

    @GetMapping("customer/{id}")
    public ResponseEntity<CustomerDtoOut> getById(@UUID String id) {
        log.info("Received GET api/v1/customer/{} request.", id);
        return ResponseEntity.ok(service.findById(id));
    }

    @PostMapping("customer")
    public ResponseEntity<CustomerDtoOut> post(@RequestBody @Valid NewCustomerDto dtoIn) {
        log.info("Received POST api/v1/customer request, userDtoIn = {}", dtoIn);
        return ResponseEntity
                .status(HttpStatus.CREATED)
                .body(service.save(dtoIn));
    }

    @PutMapping("customer")
    public ResponseEntity<CustomerDtoOut> put(@RequestBody @Valid CustomerDtoIn dtoIn) {
        log.info("Received PUT api/v1/customer request, userDtoIn = {}", dtoIn);
        return ResponseEntity.ok(service.update(dtoIn));
    }


    @DeleteMapping("customer/{id}")
    public ResponseEntity<CustomerDtoOut> delete(@UUID String id) {
        log.info("Received DELETE api/v1/customer/{} request.", id);
        return ResponseEntity.ok(service.delete(id));
    }
}
