package ru.antonovmikhail.transactional.product.service;

import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.mapstruct.factory.Mappers;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.antonovmikhail.transactional.product.mapper.ProductMapper;
import ru.antonovmikhail.transactional.product.model.Product;
import ru.antonovmikhail.transactional.product.model.dto.NewProductDto;
import ru.antonovmikhail.transactional.product.model.dto.ProductDtoIn;
import ru.antonovmikhail.transactional.product.model.dto.ProductDtoOut;
import ru.antonovmikhail.transactional.product.repository.ProductRepository;

import java.util.List;
import java.util.UUID;

@Service
@Transactional
@RequiredArgsConstructor
public class ProductServiceImpl implements ProductService {
    private final ProductRepository repository;
    private final ProductMapper mapper = Mappers.getMapper(ProductMapper.class);

    @Override
    public ProductDtoOut getProductById(String id) {
        return mapper.toDto(repository.findById(UUID.fromString(id)).orElseThrow(() -> new EntityNotFoundException()));
    }

    @Override
    public ProductDtoOut saveNewProduct(NewProductDto dto) {
        Product product = mapper.toEntity(dto);
        return mapper.toDto(repository.save(product));
    }

    @Override
    public ProductDtoOut update(ProductDtoIn dtoIn) {
        Product product = repository.findById(UUID.fromString(dtoIn.getId())).orElseThrow(() -> new EntityNotFoundException());
        mapper.update(dtoIn, product);
        return mapper.toDto(product);
    }

    @Override
    public List<ProductDtoOut> findAll() {
        return mapper.toDto(repository.findAll());
    }

    @Override
    public ProductDtoOut delete(String id) {
        UUID uuid = UUID.fromString(id);
        Product product = repository.findById(uuid).orElseThrow(() -> new EntityNotFoundException());
        repository.deleteById(uuid);
        return mapper.toDto(product);
    }
}
