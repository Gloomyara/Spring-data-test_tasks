package ru.antonovmikhail.projections.department.mapper;

import org.mapstruct.BeanMapping;
import org.mapstruct.Mapper;
import org.mapstruct.MappingTarget;
import org.mapstruct.NullValuePropertyMappingStrategy;
import ru.antonovmikhail.projections.department.model.Department;
import ru.antonovmikhail.projections.department.model.dto.DepartmentDtoIn;

@Mapper
public interface DepartmentMapper {

    @BeanMapping(nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
    Department toEntity(DepartmentDtoIn dto);

    @BeanMapping(nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
    void update(DepartmentDtoIn dto, @MappingTarget Department entity);
}
