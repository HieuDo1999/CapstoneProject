package test;

import controller.PaymentController;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class ValidateCardOwner {

    PaymentController paymentController;
    @BeforeEach
    void setUp() {
        paymentController= new PaymentController();
    }

    @Test
    void validateCardOwner(){
        boolean isValided= paymentController.validateCardOwner("Group 7");
        assertEquals(true,isValided);
    }


}