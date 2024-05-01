package ru.antonovmikhail.transactional.product.service;


import ru.antonovmikhail.transactional.product.model.dto.NewProductDto;
import ru.antonovmikhail.transactional.product.model.dto.ProductDtoIn;
import ru.antonovmikhail.transactional.product.model.dto.ProductDtoOut;

import java.util.List;

public interface ProductService {

    ProductDtoOut getProductById(String id);

    ProductDtoOut saveNewProduct(NewProductDto dto);

    ProductDtoOut update(ProductDtoIn dtoIn);

    List<ProductDtoOut> findAll();

    ProductDtoOut delete(String id);
}
