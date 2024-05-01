package ru.antonovmikhail.transactional.customer.service;


import ru.antonovmikhail.transactional.customer.model.CustomerDtoIn;
import ru.antonovmikhail.transactional.customer.model.CustomerDtoOut;
import ru.antonovmikhail.transactional.customer.model.NewCustomerDto;

import java.util.List;
import java.util.UUID;

public interface CustomerService {

    CustomerDtoOut findById(String id);

    List<CustomerDtoOut> findAll();

    CustomerDtoOut save(NewCustomerDto userDtoIn);

    CustomerDtoOut update(CustomerDtoIn userDtoIn);

    CustomerDtoOut delete(String id);

}
