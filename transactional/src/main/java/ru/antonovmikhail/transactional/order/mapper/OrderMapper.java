package ru.antonovmikhail.transactional.order.mapper;

import org.mapstruct.*;
import ru.antonovmikhail.transactional.order.model.Order;
import ru.antonovmikhail.transactional.order.model.dto.NewOrderDto;
import ru.antonovmikhail.transactional.order.model.dto.OrderDtoIn;
import ru.antonovmikhail.transactional.order.model.dto.OrderDtoOut;

import java.util.List;

@Mapper(uses = {OrderProductMapper.class})
public interface OrderMapper {

    @BeanMapping(nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
    @Mapping(target = "products", source = "productBatch")
    Order toEntity(NewOrderDto dto);

    @Mapping(target = "customerName", source = "customer.username")
    @Mapping(target = "productBatch", source = "products")
    OrderDtoOut toDto(Order entity);

    List<OrderDtoOut> toDto(List<Order> entities);

    @BeanMapping(nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
    void update(OrderDtoIn dto, @MappingTarget Order entity);
}