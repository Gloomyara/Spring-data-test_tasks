package ru.antonovmikhail.projections.employee.controller;

import com.fasterxml.jackson.annotation.JsonView;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import ru.antonovmikhail.projections.employee.model.Employee;
import ru.antonovmikhail.projections.employee.model.EmployeeDtoIn;
import ru.antonovmikhail.projections.employee.model.EmployeeProjection;
import ru.antonovmikhail.projections.employee.model.NewEmployeeDto;
import ru.antonovmikhail.projections.employee.service.EmployeeService;

import java.util.List;
import java.util.UUID;

@RestController
@Validated
@RequiredArgsConstructor
@RequestMapping("api/v1")
@Slf4j
public class EmployeeController {

    private final EmployeeService employeeService;

    @GetMapping("users")
    public ResponseEntity<List<EmployeeProjection>> getAll() {
        log.info("Received GET api/v1/users request.");
        return ResponseEntity.ok(employeeService.findAll());
    }

    @GetMapping("user/{id}")
    public ResponseEntity<EmployeeProjection> getById(@org.hibernate.validator.constraints.UUID UUID id) {
        log.info("Received GET api/v1/user/{} request.", id);
        return ResponseEntity.ok(employeeService.findById(id));
    }

    @PostMapping("user")
    public ResponseEntity<Employee> post(@Valid @RequestBody NewEmployeeDto dtoIn) {
        log.info("Received POST api/v1/user request, userDtoIn = {}", dtoIn);
        return ResponseEntity
                .status(HttpStatus.CREATED)
                .body(employeeService.create(dtoIn));
    }

    @PutMapping("user")
    public ResponseEntity<Employee> put(@Valid @RequestBody EmployeeDtoIn dtoIn) {
        log.info("Received PUT api/v1/user request, userDtoIn = {}", dtoIn);
        return ResponseEntity.ok(employeeService.update(dtoIn));
    }

    @DeleteMapping("user/{id}")
    public ResponseEntity<Employee> delete(@org.hibernate.validator.constraints.UUID UUID id) {
        log.info("Received DELETE api/v1/user/{} request.", id);
        return ResponseEntity.ok(employeeService.delete(id));
    }
}
