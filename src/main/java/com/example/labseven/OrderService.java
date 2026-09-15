package com.example.labseven;

import com.example.items.Customer;
import com.example.items.GmailSender;
import com.example.items.MySqlDatabase;
import com.example.items.Product;

import java.util.List;

public class OrderService {

    private static final int PREMIUM_CUSTOMER_TYPE = 1;
    private static final int VIP_CUSTOMER_TYPE = 2;

    private static final double PREMIUM_DISCOUNT = 0.90;
    private static final double VIP_DISCOUNT = 0.80;

    private static final int DELIVERY_STANDARD = 1;
    private static final int DELIVERY_EXPRESS = 2;

    private static final double STANDARD_DELIVERY_COST = 5.0;
    private static final double EXPRESS_DELIVERY_COST = 15.0;

    private final MySqlDatabase database;
    private final GmailSender mailSender;

    /**
     * @param database
     * @param mailSender
     * Initialize ONE TIME the database in the class
     * Instead of: new MySqlDatabase().save(customer, products, total);
     * it creates a dependency : saveOrder() line 52 and notifyCustomer() line 55
     */
    public OrderService(MySqlDatabase database, GmailSender mailSender) {

        this.database = database;
        this.mailSender = mailSender;
    }

    /**
     * Previously process() method
     */
    public double process(Customer customer, List<Product> products,
                          int deliveryType, boolean sendEmail) {

        double product_total = calculateProductTotal(products);
        double discountedTotal = applyCustomerDiscount(product_total, customer);

        double total = discountedTotal + calculateDeliveryCost(deliveryType);

        updateStock(products);
        saveOrder(customer, products, total);

        if (sendEmail)
            notifyCustomer(customer, total);

        return total;
    }

    /**
     * Notice that stock is not modified here yet
     * compared to the legacy method
     */
    private double calculateProductTotal(List<Product> products) {
        double product_total = 0;

        for (Product product : products) {
            if (product.stock <= 0) {
                throw new RuntimeException("stock");
            }

            product_total += product.price * product.quantity;
        }

        return product_total;
    }

    /**
     * If there were 10 customer types, this would become difficult to maintain.
     * an interface could be useful, but it needs to be SOLID
     */
    private double applyCustomerDiscount(
            double subtotal,
            Customer customer) {

        if (customer.type == PREMIUM_CUSTOMER_TYPE) {
            return subtotal * PREMIUM_DISCOUNT;
        }

        if (customer.type == VIP_CUSTOMER_TYPE) {
            return subtotal * VIP_DISCOUNT;
        }

        return subtotal;
    }

    private double calculateDeliveryCost(int deliveryType) {
        if (deliveryType == DELIVERY_STANDARD) {
            return STANDARD_DELIVERY_COST;
        }

        if (deliveryType == DELIVERY_EXPRESS) {
            return EXPRESS_DELIVERY_COST;
        }

        return 0;
    }

    private void updateStock(List<Product> products) {
        for (Product product : products) {
            if (product.stock <= 0) {
                throw new RuntimeException("stock");
            }
            product.stock -= product.quantity;
        }
    }

    private void saveOrder(
            Customer customer,
            List<Product> products,
            double total) {

        database.save(customer, products, total);
    }

    private void notifyCustomer(
            Customer customer,
            double total) {

        if (customer.email != null) {
            mailSender.send(
                    customer.email,
                    "total=" + total
            );
        }
    }
}