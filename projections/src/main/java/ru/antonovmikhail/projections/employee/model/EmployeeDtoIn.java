package ru.antonovmikhail.projections.employee.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.util.UUID;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class EmployeeDtoIn {

    @org.hibernate.validator.constraints.UUID
    private UUID id;
    private String firstName;
    private String lastName;
    private String position;
    private BigDecimal salary;
    @org.hibernate.validator.constraints.UUID
    private UUID departmentId;
}
