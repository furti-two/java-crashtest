package com.example.labseven;

import com.example.FactorialOperation;
import com.example.items.Customer;
import com.example.items.GmailSender;
import com.example.items.MySqlDatabase;
import com.example.items.Product;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

public class OrderServiceTest {
    private final MySqlDatabase database = new MySqlDatabase();
    private final GmailSender mailSender = new GmailSender();
    OrderService order;


    @BeforeEach
    void setUp() {
        order = new OrderService(database, mailSender);
    }

    @Test
    @DisplayName("Customer without delivery")
    void customerWithoutDelivery() {
        Customer customer = new Customer(0, null);
        Product product = new Product("potato",10, 2, 10);

        double result = order.process(customer, List.of(product), 0, false);

        assertEquals(20.0, result);
        assertEquals(8, product.stock);
    }

    @Test
    @DisplayName("Vip customer wsith delivery")
    void vipCustomerWithDelivery() {
        Customer customer = new Customer(2, null);
        Product product = new Product("potato",100, 2, 10);

        double result = order.process(customer, List.of(product), 2, false);

        assertEquals(175.0, result);
        assertEquals(8, product.stock);
    }

    @Test
    @DisplayName("Zero stock throws exception")
    void zeroStockThrowsException() {
        Customer customer = new Customer(0, null);
        Product product = new Product("potato", 50, 1, 0);

        assertThrows(
                RuntimeException.class,
                () -> order.process(customer, List.of(product), 0, false)
        );
    }

    @Test
    @DisplayName("Insufficient stock")
    void insufficientStock() {
        Customer customer = new Customer(0, null);
        Product product = new Product("potato", 20, 5, 2);

        double result = order.process(customer, List.of(product), 0, false);

        assertEquals(100.0, result);
        assertEquals(-3, product.stock);
    }
}
