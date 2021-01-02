package controller;

import Payment.PaymentTransaction;

import java.sql.SQLException;

public class TransactionController {
    private PaymentTransaction tran;
    public void makeTransaction(String customerId, Integer bikeId) throws SQLException, ClassNotFoundException {
        tran= new PaymentTransaction();
        tran.makeTransaction(customerId,bikeId);
    }
    public PaymentTransaction getTransaction(String customerId) throws SQLException, ClassNotFoundException {
        tran= new PaymentTransaction();
        return  tran.getTransaction(customerId);
    }
}
