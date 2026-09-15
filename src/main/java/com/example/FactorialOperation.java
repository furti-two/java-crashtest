package com.example;

public class FactorialOperation {
    public long compute(int n) {
        if (n == 0) return 1;
        if (n < 0) throw new RuntimeException("Negative Input");

        long total = 1;
        for (int i = 1; i <= n; i++){
            if (total > Long.MAX_VALUE / i)
                throw new RuntimeException("TOO MUCH");
            total *= i;
        }
        return total;
    }
}
