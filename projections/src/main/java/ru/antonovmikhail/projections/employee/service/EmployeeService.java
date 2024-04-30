package ru.antonovmikhail.projections.employee.service;

import ru.antonovmikhail.projections.employee.model.Employee;
import ru.antonovmikhail.projections.employee.model.EmployeeDtoIn;
import ru.antonovmikhail.projections.employee.model.EmployeeProjection;
import ru.antonovmikhail.projections.employee.model.NewEmployeeDto;

import java.util.List;
import java.util.UUID;

public interface EmployeeService {

    EmployeeProjection findById(UUID id);

    List<EmployeeProjection> findAll();

    Employee create(NewEmployeeDto dtoIn);

    Employee update(EmployeeDtoIn dtoIn);

    Employee delete(UUID id);

}
