package com.devjoemar.OnlineStoreApi;

import com.devjoemar.OnlineStoreApi.controller.ProductController;
import com.devjoemar.OnlineStoreApi.model.Product;
import com.devjoemar.OnlineStoreApi.representation.ProductEntity;
import com.devjoemar.OnlineStoreApi.representation.ProductModelAssembler;
import com.devjoemar.OnlineStoreApi.service.ProductService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.boot.test.mock.mockito.SpyBean;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import java.util.Arrays;

import static org.hamcrest.Matchers.containsString;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;


@WebMvcTest(ProductController.class)
public class ProductControllerTest {

    private final String BASE_URL = "/api/v1/products";

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private ProductService productService;

    @SpyBean
    private ProductModelAssembler productModelAssembler;

    @Test
    void testGetOneProduct() throws Exception {
        Product product = TestHelper.createMockProduct(
                1L,
                "test iPhone 12",
                "test iPhone 12 Pro max 512GB",
                "1200.00", Arrays.asList("mobile", "5G", "Telephoto"));

        when(productService.getProduct(anyLong())).thenReturn(product);

        this.mockMvc.perform(get(BASE_URL + "/1"))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(content().string(containsString("\"id\":1")))
                .andExpect(content().string(containsString("\"name\":\"test iPhone 12\"")))
                .andExpect(content().string(containsString("\"description\":\"test iPhone 12 Pro max 512GB\"")))
                .andExpect(content().string(containsString("\"tags\":[\"mobile\",\"5G\",\"Telephoto\"]")))
                .andExpect(content().string(containsString("\"price\":1200.00")))
                .andExpect(content().string(containsString("\"self\":{\"href\":\"http://localhost/api/v1/products/1\"}")));
    }

    @Test
    void testGetAllProducts() throws Exception {

        when(productService.getAllProducts()).thenReturn(TestHelper.createProducts());

        this.mockMvc.perform(get(BASE_URL))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(content().string(containsString("test Product 1")))
                .andExpect(content().string(containsString("test Product 2")));
    }

    @Test
    void testAddProduct() throws Exception {
        Product product1 = TestHelper.createMockProduct(1L, "test Product 1",
                "test Description", "1200.00", Arrays.asList("mobile", "5G", "Telephoto"));

        ProductEntity productEntity = new ProductEntity();
        productEntity.setId(product1.getId());
        productEntity.setName(product1.getName());
        productEntity.setDescription(product1.getDescription());
        productEntity.setPrice(product1.getPrice());
        productEntity.setTags(product1.getTags());

        when(productService.createProduct(any(Product.class))).thenReturn(product1);

        this.mockMvc.perform(post(BASE_URL)
                .contentType(MediaType.APPLICATION_JSON_VALUE).content("{\n" +
                        "\n" +
                        "\t\"name\" : \"Oppo\",\n" +
                        "\t\"price\": 78.00,\n" +
                        "\t\"description\" : \"Oppo 2020 \",\n" +
                        "\t\"tags\": [ \"mobile\", \"5G\"]\n" +
                        "}"))
                .andExpect(status().is(HttpStatus.CREATED.value()));
    }


}
