package controller;

import entity.transaction;

import java.sql.SQLException;

public class TransactionController {
    private transaction tran;
    public void makeTransaction(String customerId, Integer bikeId) throws SQLException, ClassNotFoundException {
        tran= new transaction();
        tran.makeTransaction(customerId,bikeId);
    }
}
