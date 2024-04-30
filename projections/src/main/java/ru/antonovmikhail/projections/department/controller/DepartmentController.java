package ru.antonovmikhail.projections.department.controller;

import jakarta.validation.Valid;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import ru.antonovmikhail.projections.department.model.Department;
import ru.antonovmikhail.projections.department.model.dto.DepartmentDtoIn;
import ru.antonovmikhail.projections.department.service.DepartmentService;

import java.util.List;
import java.util.UUID;

@Slf4j
@Validated
@Controller
@RequestMapping("api/v1")
public class DepartmentController {
    private final DepartmentService departmentService;

    public DepartmentController(DepartmentService departmentService) {
        this.departmentService = departmentService;
    }

    @GetMapping("orders")
    public ResponseEntity<List<Department>> getAll() {
        log.info("Received GET api/v1/users request.");
        return ResponseEntity.ok(departmentService.findAll());
    }


    @GetMapping("orders/{id}")
    public ResponseEntity<Department> getOrder(
            @PathVariable UUID id) {
        log.info("Received GET api/v1/orders/{} request.", id);
        return ResponseEntity.ok(departmentService.getOrderById(id));
    }

    @PostMapping("order")
    public ResponseEntity<Department> createNewOrder(
            @Valid @RequestBody DepartmentDtoIn dtoIn) {
        log.info("Received POST api/v1/order request, orderDtoIn: {}.", dtoIn);
        return ResponseEntity
                .status(HttpStatus.CREATED)
                .body(departmentService.saveNewOrder(dtoIn));
    }

    @PutMapping("order")
    public ResponseEntity<Department> put(@Valid @RequestBody DepartmentDtoIn dtoIn) {
        log.info("Received PUT api/v1/order request, orderDtoIn = {}", dtoIn);
        return ResponseEntity.ok(departmentService.update(dtoIn));
    }

    @DeleteMapping("order/{id}")
    public ResponseEntity<Department> delete(@org.hibernate.validator.constraints.UUID UUID id) {
        log.info("Received DELETE api/v1/order/{} request.", id);
        return ResponseEntity.ok(departmentService.delete(id));
    }
}



