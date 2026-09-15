package com.example;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

public class FactorialOperationTest {
    FactorialOperation fact;

    @BeforeEach
    void setUp() {
        fact = new FactorialOperation();
    }

    @Test
    @DisplayName("Should give a factioral for an int")
    void factorialClassic(){
        int chicken = 7;
        assertEquals(5040, fact.compute(chicken));
    }

    @Test
    @DisplayName("Try to give the factorial of zero")
    void factorialZero(){
        int chicken = 0;
        assertEquals(1, fact.compute(chicken));
    }

    @Test
    @DisplayName("Try to give the factorial of a negative number")
    void factorialNegative(){
        int chicken = -5;
        assertThrows(RuntimeException.class, () -> {
            long compute = fact.compute(chicken);
        });
    }


    @Test
    @DisplayName("Should throw an exception about max value")
    void factorialMaxLong(){
        int chicken = 200;
        assertThrows(RuntimeException.class, () -> {
            long compute = fact.compute(chicken);
        });
    }

}
