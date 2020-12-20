package com.devjoemar.OnlineStoreApi;

import com.devjoemar.OnlineStoreApi.model.Product;
import com.devjoemar.OnlineStoreApi.model.User;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class TestHelper {

    public static Product createMockProduct(Long id, String name, String description, String price, List<String> tags) {
        Product product = new Product();
        product.setId(id);
        product.setName(name);
        product.setDescription(description);
        product.setPrice(new BigDecimal(price));
        product.setTags(tags);
        return product;
    }

    public static List<Product> createProducts() {
        Product product1 = TestHelper.createMockProduct(1L, "test Product 1",
                "test Description", "1200.00", Arrays.asList("mobile", "5G", "Telephoto"));

        Product product2 = TestHelper.createMockProduct(2L, "test Product 2",
                "test Description", "99.00", Arrays.asList("mobile", "5G", "Telephoto"));

        List<Product> products = new ArrayList<>();
        products.add(product1);
        products.add(product2);

        return products;
    }

    public static User getCreatedUser() {
        User user1 = new User();
        user1.setId(1L);
        user1.setName("John Doe");
        return user1;
    }
}
