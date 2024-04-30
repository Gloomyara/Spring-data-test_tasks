package ru.antonovmikhail.projections.employee.repository;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import lombok.SneakyThrows;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import ru.antonovmikhail.projections.department.model.Department;
import ru.antonovmikhail.projections.employee.model.Employee;

import java.math.BigDecimal;

import static org.assertj.core.api.Assertions.assertThat;

@DataJpaTest
class EmployeeRepositoryTest {
    @Autowired
    private TestEntityManager entityManager;
    @Autowired
    private EmployeeRepository repository;

    Department department = Department.builder()
            .name("department")
            .description("omg")
            .build();

    Employee employee = Employee.builder()
            .firstName("firstname")
            .lastName("lastname")
            .position("wtvr")
            .salary(BigDecimal.valueOf(100))
            .build();
    @BeforeEach
    void setUp() {
        entityManager.persist(department);
        employee.setDepartment(department);
        entityManager.persist(employee);
    }
    @Test
    @SneakyThrows
    void findProjectionById() {
        assertThat(repository.findProjectionById(employee.getId())).isPresent();
        ObjectMapper om = new ObjectMapper();
        om.enable(SerializationFeature.INDENT_OUTPUT);
        String str = om.writeValueAsString(repository.findProjectionById(employee.getId()).get());
        System.out.println();
        System.out.println(str);
    }
}