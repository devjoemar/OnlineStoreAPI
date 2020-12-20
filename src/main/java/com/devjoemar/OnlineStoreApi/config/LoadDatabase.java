package com.devjoemar.OnlineStoreApi.config;

import com.devjoemar.OnlineStoreApi.model.OrderItem;
import com.devjoemar.OnlineStoreApi.model.Product;
import com.devjoemar.OnlineStoreApi.model.User;
import com.devjoemar.OnlineStoreApi.repository.OrderRepository;
import com.devjoemar.OnlineStoreApi.repository.ProductRepository;
import com.devjoemar.OnlineStoreApi.repository.UserRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@Configuration
class LoadDatabase {

    private static final Logger LOG = LoggerFactory.getLogger(LoadDatabase.class);

    @Bean
    CommandLineRunner initDatabase(UserRepository userRepository, ProductRepository productRepository, OrderRepository orderRepository) {

        return args -> {

            User user1 = new User("John Doe");
            User user2 = new User("Alexander");
            userRepository.save(user1);
            userRepository.save(user2);

            userRepository.findAll().forEach(user -> LOG.info("Preloaded customer {}", user));

            BigDecimal iphonePrice = new BigDecimal("1200.00");
            Product product1 = new Product("iPhone", "iPhone 12 Pro MAX 512GB", Arrays.asList("mobile", "5G"), iphonePrice);

            BigDecimal samsungPrice = new BigDecimal("900.00");
            Product product2 = new Product("Samsung", "Samsun Galaxy Note", Arrays.asList("mobile", "5G"), samsungPrice);
            productRepository.save(product1);
            productRepository.save(product2);

            List<Product> productSet = new ArrayList<>();
            productSet.add(product1);
            productSet.add(product1);

            orderRepository.save(new OrderItem(user1, productSet, LocalDate.of(2001, 2, 8)));
            orderRepository.save(new OrderItem(user1, productSet, LocalDate.now()));

            List<Product> productSet2 = new ArrayList<>(productSet);

            productSet2.add(product2);

            orderRepository.save(new OrderItem(user2, productSet2, LocalDate.now()));

            orderRepository.findAll().forEach(order -> LOG.info("Preloaded order {}", order));

            orderRepository.findOrdersByUser(1L).forEach(order -> LOG.info("Preloaded orderByUser {}", order));

            orderRepository.findUserOrdersByDateRange(1L, LocalDate.now(), LocalDate.now())
                    .forEach(order -> LOG.info("Preloaded userOrderByDate {}", order));
        };
    }
}