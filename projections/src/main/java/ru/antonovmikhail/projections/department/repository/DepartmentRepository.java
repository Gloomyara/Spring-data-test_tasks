package ru.antonovmikhail.projections.department.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.antonovmikhail.projections.department.model.Department;

import java.util.UUID;

public interface DepartmentRepository extends JpaRepository<Department, UUID> {
}
