package entity;

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

public class transaction {
    int transactionID;
    String customerID;
    Integer bikeID;
    int deposit;
    int fee;
    Timestamp start;
    Timestamp end;

    public transaction() {
    }

    public transaction(int transactionID, String customerID, Integer bikeID, int deposit, int fee, Timestamp start, Timestamp end) {
        this.transactionID = transactionID;
        this.customerID = customerID;
        this.bikeID = bikeID;
        this.deposit = deposit;
        this.fee = fee;
        this.start = start;
        this.end = end;
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
        String sql= "insert into transaction (id,customer_id,bike_id,deposit,fee,start,end)"
                +"values(?,?,?,?,'0',?,?)";
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



    public transaction getTransactionById(int transactionId) throws SQLException, ClassNotFoundException {
        conn=(Connection) ConnectionDB.ConnectionDB();
        String sql = "SELECT * FROM transaction WHERE transaction_id = ?";
        ps = conn.prepareCall(sql);
        ps.setInt(1,transactionId);
        rs = ps.executeQuery();
        rs.next();
        transaction transaction = null;
        transaction = new transaction(
                rs.getInt("transaction_id"),
                rs.getString("customer_id"),
                rs.getString("bike_id"),
                rs.getInt("deposit"),
                rs.getInt("total_fees"),
                rs.getTimestamp("start"),
                rs.getTimestamp("ended"));
        return transaction;
    }
    public List<transaction> getAllTransaction() throws SQLException, ClassNotFoundException {
        conn=(Connection) ConnectionDB.ConnectionDB();
        List<transaction> transactionList = new ArrayList<>();
        String sql = "select * from transaction";
        st = (Statement) conn.createStatement();
        rs = st.executeQuery(sql);
        while (rs.next()){
            transaction transaction = new transaction(
                    rs.getInt("transaction_id"),
                    rs.getString("customer_id"),
                    rs.getString("bike_id"),
                    rs.getInt("deposit"),
                    rs.getInt("total_fees"),
                    rs.getTimestamp("start"),
                    rs.getTimestamp("ended"));

            transactionList.add(transaction);
        }

        return transactionList;
    }



    public void saveTransaction(int transactionId, Timestamp ended, int totalFees) throws SQLException, ClassNotFoundException {
        conn=(Connection) ConnectionDB.ConnectionDB();
        transaction transaction = getTransactionById(transactionId);
        BikeController bikeController= new BikeController();
        EBikeController eBikeController= new EBikeController();
        Bike bike = bikeController.getBikeById(transaction.getBikeID());
        if(bike.getBikeId()==null){
            bike= eBikeController.getEBikeById(transaction.getBikeID());
        }
        System.out.println(bike.getBikeId());
        String sql1 = "select * from transaction where transaction_id = ?";
        ps = conn.prepareCall(sql1);
        ps.setInt(1,transactionId);
        rs = ps.executeQuery();
        rs.next();
        Timestamp start = rs.getTimestamp("start");
        String sql = "update transaction set ended = ? , total_fees = ? where transaction_id = ?";
        ps = conn.prepareCall(sql);
        ps.setTimestamp(1,ended);
        ps.setInt(2,totalFees);
        ps.setInt(3,transactionId);
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
