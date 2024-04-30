package ru.antonovmikhail.projections.employee.service;

import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.mapstruct.factory.Mappers;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.antonovmikhail.projections.employee.mapper.EmployeeMapper;
import ru.antonovmikhail.projections.employee.model.Employee;
import ru.antonovmikhail.projections.employee.model.EmployeeDtoIn;
import ru.antonovmikhail.projections.employee.model.EmployeeProjection;
import ru.antonovmikhail.projections.employee.model.NewEmployeeDto;
import ru.antonovmikhail.projections.employee.repository.EmployeeRepository;

import java.util.List;
import java.util.UUID;

@Service
@Transactional
@RequiredArgsConstructor
public class EmployeeServiceImpl implements EmployeeService {
    private final EmployeeRepository repository;
    private final EmployeeMapper mapper = Mappers.getMapper(EmployeeMapper.class);

    @Override
    @Transactional(readOnly = true)
    public EmployeeProjection findById(UUID id) {
        return repository.findProjectionById(id).orElseThrow(() -> new EntityNotFoundException());
    }

    @Override
    public Employee create(NewEmployeeDto dtoIn) {
        Employee employee = mapper.toEntity(dtoIn);
        return repository.save(employee);
    }

    @Override
    public Employee update(EmployeeDtoIn dtoIn) {
        Employee employee = repository.findById(dtoIn.getId()).orElseThrow(() -> new EntityNotFoundException());
        mapper.update(dtoIn, employee);
        return repository.save(employee);
    }

    @Override
    @Transactional(readOnly = true)
    public List<EmployeeProjection> findAll() {
        return repository.findAllProjections();
    }

    @Override
    public Employee delete(UUID id) {
        Employee employee = repository.findById(id).orElseThrow(() -> new EntityNotFoundException());
        repository.deleteById(id);
        return employee;
    }
}
