package ru.antonovmikhail.transactional.customer.mapper;

import org.mapstruct.BeanMapping;
import org.mapstruct.Mapper;
import org.mapstruct.MappingTarget;
import org.mapstruct.NullValuePropertyMappingStrategy;
import ru.antonovmikhail.transactional.customer.model.Customer;
import ru.antonovmikhail.transactional.customer.model.CustomerDtoIn;
import ru.antonovmikhail.transactional.customer.model.CustomerDtoOut;
import ru.antonovmikhail.transactional.customer.model.NewCustomerDto;

import java.util.List;

@Mapper
public interface CustomerMapper {
    @BeanMapping(nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
    Customer toEntity(NewCustomerDto dto);

    CustomerDtoOut toDto(Customer entity);

    List<CustomerDtoOut> toDto(List<Customer> entities);

    @BeanMapping(nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
    void update(CustomerDtoIn dto, @MappingTarget Customer entity);
}
