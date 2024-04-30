package ru.antonovmikhail.projections.employee.repository;


import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import ru.antonovmikhail.projections.employee.model.Employee;
import ru.antonovmikhail.projections.employee.model.EmployeeProjection;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

public interface EmployeeRepository extends JpaRepository<Employee, UUID> {
    String employeeJql = "select " +
            "CONCAT(e.firstName, ' ', e.lastName) as fullName, " +
            "e.position as position, " +
            "department.name as departmentName " +
            "from Employee e " +
            "JOIN e.department ";

    @Query(employeeJql)
    List<EmployeeProjection> findAllProjections();
    @Query(employeeJql +
    "WHERE e.id = :id")
    Optional<EmployeeProjection> findProjectionById(UUID id);
}
