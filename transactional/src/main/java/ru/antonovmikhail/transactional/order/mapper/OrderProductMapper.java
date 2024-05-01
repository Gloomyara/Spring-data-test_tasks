package ru.antonovmikhail.transactional.order.mapper;

import org.mapstruct.BeanMapping;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.NullValuePropertyMappingStrategy;
import ru.antonovmikhail.transactional.order.model.OrderProduct;
import ru.antonovmikhail.transactional.order.model.dto.OrderProductDtoIn;
import ru.antonovmikhail.transactional.order.model.dto.OrderProductDtoOut;


import java.util.List;

@Mapper
public interface OrderProductMapper {
    @Mapping(target = "name", source = "product.name")
    @Mapping(target = "description", source = "product.description")
    @Mapping(target = "unitPrice", source = "product.price")
    OrderProductDtoOut toDto(OrderProduct entity);

    @Mapping(target = "name", source = "product.name")
    @Mapping(target = "description", source = "product.description")
    @Mapping(target = "unitPrice", source = "product.price")
    List<OrderProductDtoOut> toDto(List<OrderProduct> entityList);

    @Mapping(target = "key.orderId", source = "orderId")
    @Mapping(target = "key.productId", source = "productId")
    @BeanMapping(nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
    OrderProduct toEntity(OrderProductDtoIn dto);

    @BeanMapping(nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
    List<OrderProduct> toEntity(List<OrderProductDtoIn> dtoList);
}
