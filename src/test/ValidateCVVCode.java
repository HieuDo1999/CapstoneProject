package test;

import controller.PaymentController;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class ValidateCVVCode {
    PaymentController paymentController;
    @BeforeEach
    void setUp() {
        paymentController= new PaymentController();
    }

    @Test
    void validateCVVCode() {
        boolean isValided= paymentController.validateCVVCode("571");
        assertEquals(true,isValided);

    }
}