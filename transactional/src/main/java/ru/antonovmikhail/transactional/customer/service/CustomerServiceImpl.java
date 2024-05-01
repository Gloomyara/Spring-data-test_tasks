package ru.antonovmikhail.transactional.customer.service;

import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.mapstruct.factory.Mappers;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.antonovmikhail.transactional.customer.mapper.CustomerMapper;
import ru.antonovmikhail.transactional.customer.model.Customer;
import ru.antonovmikhail.transactional.customer.model.CustomerDtoIn;
import ru.antonovmikhail.transactional.customer.model.CustomerDtoOut;
import ru.antonovmikhail.transactional.customer.model.NewCustomerDto;
import ru.antonovmikhail.transactional.customer.repository.CustomerRepository;

import java.util.List;
import java.util.UUID;


@Service
@Transactional
@RequiredArgsConstructor
public class CustomerServiceImpl implements CustomerService {
    private final CustomerRepository repository;
    private final CustomerMapper mapper = Mappers.getMapper(CustomerMapper.class);

    @Override
    @Transactional(readOnly = true)
    public CustomerDtoOut findById(String id) {
        return mapper.toDto(repository.findById(UUID.fromString(id)).orElseThrow(() -> new EntityNotFoundException()));
    }

    @Override
    public CustomerDtoOut save(NewCustomerDto dtoIn) {
        Customer user = mapper.toEntity(dtoIn);
        return mapper.toDto(repository.save(user));
    }

    @Override
    public CustomerDtoOut update(CustomerDtoIn dtoIn) {
        Customer customer = repository.findById(UUID.fromString(dtoIn.getId())).orElseThrow(() -> new EntityNotFoundException());
        mapper.update(dtoIn, customer);
        return mapper.toDto(repository.save(customer));
    }

    @Override
    @Transactional(readOnly = true)
    public List<CustomerDtoOut> findAll() {
        return mapper.toDto(repository.findAll());
    }

    @Override
    public CustomerDtoOut delete(String id) {
        UUID uuid = UUID.fromString(id);
        Customer user = repository.findById(uuid).orElseThrow(() -> new EntityNotFoundException());
        repository.deleteById(uuid);
        return mapper.toDto(user);
    }
}
