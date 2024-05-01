package ru.antonovmikhail.transactional.product.controller;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.hibernate.validator.constraints.UUID;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import ru.antonovmikhail.transactional.product.model.dto.NewProductDto;
import ru.antonovmikhail.transactional.product.model.dto.ProductDtoIn;
import ru.antonovmikhail.transactional.product.model.dto.ProductDtoOut;
import ru.antonovmikhail.transactional.product.service.ProductService;

import java.util.List;


@Slf4j
@Validated
@Controller
@RequestMapping("api/v1")
@RequiredArgsConstructor
public class ProductController {
    private final ProductService service;

    @GetMapping("products")
    public ResponseEntity<List<ProductDtoOut>> getAll() {
        log.info("Received GET api/v1/products request.");
        return ResponseEntity.ok(service.findAll());
    }


    @GetMapping("products/{id}")
    public ResponseEntity<ProductDtoOut> getOrder(
            @UUID String id) {
        log.info("Received GET api/v1/products/{} request.", id);
        return ResponseEntity.ok(service.getProductById(id));
    }

    @PostMapping("product")
    public ResponseEntity<ProductDtoOut> saveNewProduct(
            @RequestBody @Valid NewProductDto dtoIn) {
        log.info("Received POST api/v1/product request, orderDtoIn: {}.", dtoIn);
        return ResponseEntity
                .status(HttpStatus.CREATED)
                .body(service.saveNewProduct(dtoIn));
    }

    @PutMapping("product")
    public ResponseEntity<ProductDtoOut> put(@RequestBody @Valid ProductDtoIn dtoIn) {
        log.info("Received PUT api/v1/product request, orderDtoIn = {}", dtoIn);
        return ResponseEntity.ok(service.update(dtoIn));

    }

    @DeleteMapping("product/{id}")
    public ResponseEntity<ProductDtoOut> delete(@UUID String id) {
        log.info("Received DELETE api/v1/product/{} request.", id);
        return ResponseEntity.ok(service.delete(id));
    }
}



