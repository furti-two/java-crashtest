package com.example;

import com.example.items.Product;

public class TaskLabFour {
    boolean canOrder(User u, Product p) {
        if (u != null) {
            if (u.active) {
                if (!u.blocked) {
                    if (p.quantity > 0) return true;
                }
            }
        }
        return false;
    }
    public class User {

    }
}
