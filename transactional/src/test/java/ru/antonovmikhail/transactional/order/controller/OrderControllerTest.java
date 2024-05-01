package ru.antonovmikhail.transactional.order.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.SneakyThrows;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import ru.antonovmikhail.transactional.customer.model.Customer;
import ru.antonovmikhail.transactional.order.model.dto.NewOrderDto;
import ru.antonovmikhail.transactional.order.model.dto.OrderDtoOut;
import ru.antonovmikhail.transactional.order.model.dto.OrderProductDtoIn;
import ru.antonovmikhail.transactional.order.model.dto.OrderProductDtoOut;
import ru.antonovmikhail.transactional.order.service.OrderService;

import java.math.BigDecimal;
import java.nio.charset.StandardCharsets;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.UUID;

import static org.hamcrest.Matchers.is;
import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static ru.antonovmikhail.transactional.util.Constants.DATE_TIME_PATTERN;

@WebMvcTest(controllers = OrderController.class)
class OrderControllerTest {
    @MockBean
    private OrderService service;
    @Autowired
    private ObjectMapper mapper;
    @Autowired
    private MockMvc mvc;

    private UUID customerId;
    private UUID orderId;
    private UUID productId;
    private Customer customer;
    private OrderProductDtoIn orderProductDtoIn1;
    private OrderProductDtoOut orderProductDtoOut1;
    private OrderProductDtoIn orderProductDtoIn2;
    private OrderProductDtoOut orderProductDtoOut2;
    private NewOrderDto newOrderDto;
    private OrderDtoOut orderDtoOut;

    @BeforeEach
    void setUp() {
        customerId = UUID.randomUUID();
        orderId = UUID.randomUUID();
        productId = UUID.randomUUID();
        customer = new Customer(customerId, "Buba", BigDecimal.valueOf(1000000));
        orderProductDtoIn1 = new OrderProductDtoIn(orderId.toString(), productId.toString(), 5L);
        orderProductDtoOut1 = new OrderProductDtoOut("Лампочка", "китайская", BigDecimal.valueOf(100), 5L);
        orderProductDtoIn2 = new OrderProductDtoIn(orderId.toString(), productId.toString(), 50L);
        orderProductDtoOut2 = new OrderProductDtoOut("Лампочка", "не китайская", BigDecimal.valueOf(10), 50L);
        newOrderDto = new NewOrderDto(customerId.toString(), "testDescription", List.of(orderProductDtoIn1, orderProductDtoIn2));
        orderDtoOut = new OrderDtoOut("Buba", "testDescription", LocalDateTime.now(), List.of(orderProductDtoOut1, orderProductDtoOut2), BigDecimal.valueOf(0), false);
    }

    @SneakyThrows
    @Test
    void postWhenDtoInCorrect() {
        String json = mapper.writeValueAsString(newOrderDto);
        when(service.saveNewOrder(newOrderDto)).thenReturn(orderDtoOut);
        System.out.println(mvc.perform(post("/api/v1/order")
                        .content(json)
                        .characterEncoding(StandardCharsets.UTF_8)
                        .contentType(MediaType.APPLICATION_JSON)
                        .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isCreated())
                .andExpect(jsonPath("$.customerName", is(orderDtoOut.getCustomerName())))
                .andExpect(jsonPath("$.description", is(orderDtoOut.getDescription())))
                .andExpect(jsonPath("$.updateDate", is(orderDtoOut.getUpdateDate().format(DateTimeFormatter.ofPattern(DATE_TIME_PATTERN)))))
                .andExpect(jsonPath("$.productBatch[1].name", is("Лампочка")))
                .andExpect(jsonPath("$.paid", is(orderDtoOut.getPaid()))));
        verify(service, times(1))
                .saveNewOrder(any(NewOrderDto.class));
    }

}