package ru.antonovmikhail.projections.employee.mapper;

import org.mapstruct.BeanMapping;
import org.mapstruct.Mapper;
import org.mapstruct.MappingTarget;
import org.mapstruct.NullValuePropertyMappingStrategy;
import ru.antonovmikhail.projections.employee.model.Employee;
import ru.antonovmikhail.projections.employee.model.EmployeeDtoIn;
import ru.antonovmikhail.projections.employee.model.NewEmployeeDto;

@Mapper
public interface EmployeeMapper {

    @BeanMapping(nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
    Employee toEntity(NewEmployeeDto dto);

    @BeanMapping(nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
    void update(EmployeeDtoIn dto, @MappingTarget Employee entity);
}
