package com.devjoemar.OnlineStoreApi;

import com.devjoemar.OnlineStoreApi.controller.OrderController;
import com.devjoemar.OnlineStoreApi.model.OrderItem;
import com.devjoemar.OnlineStoreApi.representation.OrderItemAssembler;
import com.devjoemar.OnlineStoreApi.representation.OrderItemEntity;
import com.devjoemar.OnlineStoreApi.service.OrderService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.boot.test.mock.mockito.SpyBean;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

import static org.hamcrest.Matchers.containsString;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(OrderController.class)
public class OrderControllerTest {

    private final String BASE_URL = "/api/v1/orders";

    @Autowired
    private MockMvc mockMvc;
    @MockBean
    private OrderService orderService;
    @SpyBean
    private OrderItemAssembler orderItemAssembler;

    @Test
    void testAddOrder() throws Exception {


        OrderItem orderItem = new OrderItem();
        orderItem.setId(1L);
        orderItem.setProducts(TestHelper.createProducts());
        orderItem.setLocalDate(LocalDate.of(2020, Calendar.APRIL, 30));
        orderItem.setUser(TestHelper.getCreatedUser());
        when(orderService.createOrder(any(OrderItemEntity.class))).thenReturn(orderItem);

        this.mockMvc.perform(post(BASE_URL)
                .contentType(MediaType.APPLICATION_JSON_VALUE).content("{\n" +
                        "\t\"userId\" : 2,\n" +
                        "\t\"products\" : [\n" +
                        "\t\t{ \n" +
                        "\t\t\t\"id\" : 3,\n" +
                        "\t\t\t\"price\" : 1000.00\n" +
                        "\t\t},\n" +
                        "\t\t{ \n" +
                        "\t\t\t\"id\" : 1,\n" +
                        "\t\t\t\"price\" : 1000.00\n" +
                        "\t\t}\t\n" +
                        "\t]\n" +
                        "}")).andExpect(status().is(HttpStatus.CREATED.value()));
    }

    @Test
    void testGetUserOrdersByDateRange() throws Exception {
        OrderItem orderItem = new OrderItem();
        orderItem.setId(1L);
        orderItem.setProducts(TestHelper.createProducts());
        orderItem.setLocalDate(LocalDate.of(2020, 12, 19));
        orderItem.setUser(TestHelper.getCreatedUser());

        List<OrderItem> orderItemList = new ArrayList<>();
        orderItemList.add(orderItem);


        when(orderService.getUserOrdersByDateRange(anyLong(), any(LocalDate.class), any(LocalDate.class))).thenReturn(orderItemList);

        this.mockMvc.perform(get(BASE_URL + "?id=2&fromDate=2020-12-20&toDate=2020-12-21"))
                .andExpect(status().isOk())
                .andDo(print())
                .andExpect(content().string(containsString("\"localDate\":\"2020-12-19\"")));
    }
}
