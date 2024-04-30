package ru.antonovmikhail.projections.department.service;

import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.mapstruct.factory.Mappers;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.antonovmikhail.projections.department.mapper.DepartmentMapper;
import ru.antonovmikhail.projections.department.model.Department;
import ru.antonovmikhail.projections.department.model.dto.DepartmentDtoIn;
import ru.antonovmikhail.projections.department.repository.DepartmentRepository;
import ru.antonovmikhail.projections.employee.repository.EmployeeRepository;

import java.util.List;
import java.util.UUID;

@Service
@Transactional
@RequiredArgsConstructor
public class DepartmentServiceImpl implements DepartmentService {

    private final DepartmentRepository repository;
    private final EmployeeRepository employeeRepository;
    private final DepartmentMapper mapper = Mappers.getMapper(DepartmentMapper.class);

    @Transactional(readOnly = true)
    @Override
    public Department getOrderById(UUID id) throws EntityNotFoundException {
        return repository.findById(id).orElseThrow(() -> new EntityNotFoundException());
    }

    @Override
    public Department saveNewOrder(DepartmentDtoIn dtoIn) throws EntityNotFoundException {
        if (employeeRepository.existsById(dtoIn.getUserId())) {
            Department department = mapper.toEntity(dtoIn);
            return repository.save(department);
        }
        throw new EntityNotFoundException();
    }

    @Override
    public Department update(DepartmentDtoIn dtoIn) throws EntityNotFoundException {
        if (employeeRepository.existsById(dtoIn.getUserId())) {
            Department department = repository.findById(dtoIn.getId()).orElseThrow(() -> new EntityNotFoundException());
            mapper.update(dtoIn, department);
            return department;
        }
        throw new EntityNotFoundException();
    }

    @Override
    @Transactional(readOnly = true)
    public List<Department> findAll() {
        return repository.findAll();
    }

    @Override
    public Department delete(UUID id) throws EntityNotFoundException {
        Department department = repository.findById(id).orElseThrow(() -> new EntityNotFoundException());
        repository.deleteById(id);
        return department;
    }
}
