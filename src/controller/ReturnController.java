package controller;

import Payment.PaymentTransaction;

import java.sql.SQLException;

public class ReturnController {
        private PaymentTransaction paymentTransaction;
        public void savePaymentTransaction(PaymentTransaction payment,long fees) throws SQLException, ClassNotFoundException {
            paymentTransaction= new PaymentTransaction();
            paymentTransaction.saveTransaction(payment,fees);
        }

}
