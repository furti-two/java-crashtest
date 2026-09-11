package com.example;

import com.example.items.Product;

import java.util.List;

public class TaskLabOne {
    /**
     * Premium customer: 10% discount.
     * VIP customer: 20% discount.
     * If the total after discount is below €100, add €5 shipping.
     * If a voucher is present, subtract €15.
     * The final amount can never be negative.
     **/

    double calc(List<Product> products, int subscription_level, boolean is_voucher) {

        final int MAX_DISCOUNT = 100;
        final int SHIPPING_FEES = 5;
        final int REDUCTION = 15;
        final int PREMIUM = 1;
        final int VIP = 2;

        final double PREMIUM_DISCOUNT = 1 - 0.10;
        final double VIP_DISCOUNT = 1 - 0.20;

        double total = 0;

        for (Product a : products) total += a.price * a.quantity;

        if (subscription_level == PREMIUM) total *= PREMIUM_DISCOUNT;
        else if (subscription_level == VIP) total *= VIP_DISCOUNT;

        if (total < MAX_DISCOUNT) total += SHIPPING_FEES;
        if (is_voucher) total -= REDUCTION;

        total = Math.max(0, total);

        return total;
    }



}
