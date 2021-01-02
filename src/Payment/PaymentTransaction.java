package Payment;

import com.mysql.jdbc.Connection;
import com.mysql.jdbc.Statement;
import controller.BikeController;
import entity.bike.Bike;
import entity.db.ConnectionDB;


import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class PaymentTransaction {
    int transactionID;
    String customerID;
    Integer bikeID;
    int deposit;
    int fee;
    Timestamp start;
    Timestamp end;
    int status;

    public PaymentTransaction(int transactionID, String customerID, Integer bikeID, int deposit, int fee, Timestamp start, Timestamp end, int status) {
        this.transactionID = transactionID;
        this.customerID = customerID;
        this.bikeID = bikeID;
        this.deposit = deposit;
        this.fee = fee;
        this.start = start;
        this.end = end;
        this.status = status;
    }

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    public PaymentTransaction() {
    }

    Statement st=null;
    ResultSet rs=null;
    Connection conn=null;
    PreparedStatement ps=null;
    private BikeController bikeController;
    public void makeTransaction(String customerId, Integer bikeId) throws SQLException, ClassNotFoundException {
        bikeController= new BikeController();
        Bike bike= bikeController.getBikeById(bikeId);
        conn=(Connection) ConnectionDB.ConnectionDB();
        String sql= "insert into transaction (id,customer_id,bike_id,deposit,fee,start,end,status)"
                +"values(?,?,?,?,'0',?,?,'0')";
        ps=conn.prepareCall(sql);
        Random random=new Random();
        Integer transaction_id=random.nextInt();
        ps.setString(1, String.valueOf(transaction_id));
        ps.setString(2,customerId);
        ps.setInt(3,bikeId);
        ps.setDouble(4,bike.calculateDeposit());
        ps.setTimestamp(5, (new Timestamp(System.currentTimeMillis())));
        ps.setTimestamp(6, (new Timestamp(System.currentTimeMillis())));
        ps.execute();
    }
    public PaymentTransaction getTransaction(String customerId) throws SQLException, ClassNotFoundException {
        conn=(Connection) ConnectionDB.ConnectionDB();
        String sql = "SELECT * FROM transaction WHERE customer_id = ? and status = ? ";
        ps = conn.prepareCall(sql);
        ps.setString(1,customerId);
        ps.setInt(2,0);
        rs = ps.executeQuery();

        if(rs.next()) {
            PaymentTransaction paymentTransaction = null;
            paymentTransaction = new PaymentTransaction(
                    rs.getInt("id"),
                    rs.getString("customer_id"),
                    rs.getInt("bike_id"),
                    rs.getInt("deposit"),
                    rs.getInt("fee"),
                    rs.getTimestamp("start"),
                    rs.getTimestamp("end"),
                    rs.getInt("status"));
            return paymentTransaction;
        }else
            return null;

    }


    public PaymentTransaction getTransactionById(int transactionId) throws SQLException, ClassNotFoundException {
        conn=(Connection) ConnectionDB.ConnectionDB();
        String sql = "SELECT * FROM transaction WHERE transaction_id = ?";
        ps = conn.prepareCall(sql);
        ps.setInt(1,transactionId);
        rs = ps.executeQuery();
        rs.next();
        PaymentTransaction paymentTransaction = null;
        paymentTransaction = new PaymentTransaction(
                rs.getInt("transaction_id"),
                rs.getString("customer_id"),
                rs.getInt("bike_id"),
                rs.getInt("deposit"),
                rs.getInt("total_fees"),
                rs.getTimestamp("start"),
                rs.getTimestamp("ended"),
                rs.getInt("status"));
        return paymentTransaction;
    }
    public List<PaymentTransaction> getAllTransaction() throws SQLException, ClassNotFoundException {
        conn=(Connection) ConnectionDB.ConnectionDB();
        List<PaymentTransaction> transactionList = new ArrayList<>();
        String sql = "select * from transaction";
        st = (Statement) conn.createStatement();
        rs = st.executeQuery(sql);
        while (rs.next()){
            PaymentTransaction transaction = new PaymentTransaction(
                    rs.getInt("transaction_id"),
                    rs.getString("customer_id"),
                    rs.getInt("bike_id"),
                    rs.getInt("deposit"),
                    rs.getInt("total_fees"),
                    rs.getTimestamp("start"),
                    rs.getTimestamp("ended"),
                    rs.getInt("status"));

            transactionList.add(transaction);
        }

        return transactionList;
    }



    public void saveTransaction(PaymentTransaction paymentTransaction,long fees) throws SQLException, ClassNotFoundException {
        conn=(Connection) ConnectionDB.ConnectionDB();
        String sql = "update transaction set end = ? , fee = ? , status=? where id = ?";
        ps = conn.prepareCall(sql);
        ps.setTimestamp(1,new Timestamp(System.currentTimeMillis()));
        ps.setLong(2,fees);
        ps.setInt(3,1);
        ps.setInt(4,paymentTransaction.getTransactionID());
        ps.executeUpdate();
    }



    public int getTransactionID() {
        return transactionID;
    }

    public void setTransactionID(int transactionID) {
        this.transactionID = transactionID;
    }

    public String getCustomerID() {
        return customerID;
    }

    public void setCustomerID(String customerID) {
        this.customerID = customerID;
    }

    public Integer getBikeID() {
        return bikeID;
    }

    public void setBikeID(Integer bikeID) {
        this.bikeID = bikeID;
    }

    public int getDeposit() {
        return deposit;
    }

    public void setDeposit(int deposit) {
        this.deposit = deposit;
    }

    public int getFee() {
        return fee;
    }

    public void setFee(int fee) {
        this.fee = fee;
    }

    public Timestamp getStart() {
        return start;
    }

    public void setStart(Timestamp start) {
        this.start = start;
    }

    public Timestamp getEnd() {
        return end;
    }

    public void setEnd(Timestamp end) {
        this.end = end;
    }
}
