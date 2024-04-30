package ru.antonovmikhail.projections.department.service;

import org.springframework.transaction.annotation.Transactional;
import ru.antonovmikhail.projections.department.model.Department;
import ru.antonovmikhail.projections.department.model.dto.DepartmentDtoIn;

import java.util.List;
import java.util.UUID;

public interface DepartmentService {


    Department getOrderById(UUID id);

    Department saveNewOrder(DepartmentDtoIn dto);

    Department update(DepartmentDtoIn dtoIn);

    @Transactional(readOnly = true)
    List<Department> findAll();

    Department delete(UUID id);
}
