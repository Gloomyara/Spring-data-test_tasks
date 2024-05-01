package ru.antonovmikhail.transactional.product.mapper;

import org.mapstruct.*;
import ru.antonovmikhail.transactional.product.model.Product;
import ru.antonovmikhail.transactional.product.model.dto.NewProductDto;
import ru.antonovmikhail.transactional.product.model.dto.ProductDtoIn;
import ru.antonovmikhail.transactional.product.model.dto.ProductDtoOut;


import java.util.List;

@Mapper
public interface ProductMapper {

    @BeanMapping(nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
    Product toEntity(NewProductDto dto);

    ProductDtoOut toDto(Product entity);

    List<ProductDtoOut> toDto(List<Product> entities);

    @BeanMapping(nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
    void update(ProductDtoIn dto, @MappingTarget Product entity);
}