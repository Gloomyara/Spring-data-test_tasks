package ru.antonovmikhail.transactional.customer.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import lombok.SneakyThrows;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import ru.antonovmikhail.transactional.customer.model.CustomerDtoOut;
import ru.antonovmikhail.transactional.customer.model.NewCustomerDto;
import ru.antonovmikhail.transactional.customer.service.CustomerService;

import java.math.BigDecimal;
import java.nio.charset.StandardCharsets;

import static org.hamcrest.Matchers.is;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(controllers = CustomerController.class)
class CustomerControllerTest {
    @MockBean
    private CustomerService service;
    @Autowired
    private ObjectMapper mapper;
    @Autowired
    private MockMvc mvc;
    private final NewCustomerDto dtoIn = new NewCustomerDto("Buba", BigDecimal.valueOf(0));

    private final CustomerDtoOut dtoOut = new CustomerDtoOut("Buba");

    @SneakyThrows
    @Test
    void postWhenDtoInCorrect() {
        mapper.enable(SerializationFeature.INDENT_OUTPUT);
        String json = mapper.writeValueAsString(dtoIn);
        when(service.save(dtoIn)).thenReturn(dtoOut);
        System.out.println(mvc.perform(post("/api/v1/customer")
                        .content(json)
                        .characterEncoding(StandardCharsets.UTF_8)
                        .contentType(MediaType.APPLICATION_JSON)
                        .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isCreated())
                .andExpect(jsonPath("$.username", is(dtoIn.getUsername()))));
        verify(service, times(1))
                .save(any(NewCustomerDto.class));
    }

}