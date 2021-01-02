package test;

import controller.PaymentController;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class ValidateCardCode {
    PaymentController paymentController;
    @BeforeEach
    void setUp() {
        paymentController= new PaymentController();
    }

    @Test
    void validateCardNumber() {
        boolean isValided= paymentController.validateCardCode("118609_group7_2020");
        assertEquals(true,isValided);
    }
}