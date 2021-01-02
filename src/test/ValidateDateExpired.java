package test;

import controller.PaymentController;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class ValidateDateExpired {
    PaymentController paymentController;
    @BeforeEach
    void setUp() {
        paymentController= new PaymentController();
    }

    @Test
    void validatedateExpired() {
        boolean isValided= paymentController.validatedateExpired("1125");
        assertEquals(true,isValided);
    }
}