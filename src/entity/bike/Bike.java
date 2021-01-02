package entity.bike;

import com.mysql.jdbc.Connection;
import com.mysql.jdbc.Statement;
import entity.db.ConnectionDB;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;

/**
 * @author HieuDo
 * @version 2
 */
public class Bike implements CalculateFees {
    int id;
    String kind;
    int dockId;
    int value;
    String description;
    String status;

    /**
     * @param id
     * @param kind
     * @param dockId
     * @param value
     * @param description
     * @param status
     */
    public Bike(int id, String kind, int dockId, int value, String description, String status) {
        this.id = id;
        this.kind = kind;
        this.dockId = dockId;
        this.value = value;
        this.description = description;
        this.status = status;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getKind() {
        return kind;
    }

    public void setKind(String kind) {
        this.kind = kind;
    }

    public int getDockId() {
        return dockId;
    }

    public void setDockId(int dockId) {
        this.dockId = dockId;
    }

    public int getValue() {
        return value;
    }

    public void setValue(int value) {
        this.value = value;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }


    Statement st = null;
    ResultSet rs = null;
    Connection conn = null;

    public Bike() {
    }

    /**
     * @return danh sach bike
     * @throws SQLException
     * @throws ClassNotFoundException
     */
    public List getListBike() throws SQLException, ClassNotFoundException {
        String sql = "select * from bike";
        List<Bike> listBike = new ArrayList<>();
        conn = (Connection) ConnectionDB.ConnectionDB();
        st = (Statement) conn.createStatement();
        rs = st.executeQuery(sql);
        while (rs.next()) {
            Bike bike = new Bike(
                    rs.getInt("id"),
                    rs.getString("kind"),
                    rs.getInt("dock_id"),
                    rs.getInt("value"),
                    rs.getString("description"),
                    rs.getString("status")
            );
            listBike.add(bike);
        }
        return listBike;
    }

    public void changeStateBike(Integer id) throws SQLException {
        Bike bike = this.getBikeById(id);
        String sql = null;
        sql = "update  bike   set status='dang cho thue' where id=" + "'" + id + "'";
        Connection conn = (Connection) ConnectionDB.ConnectionDB();
        PreparedStatement ps = conn.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
        ps.executeUpdate();
    }

    /**
     * @param id
     * @return true neu xe hoat dong
     * @throws SQLException
     */
    public boolean checkStateBike(Integer id) throws SQLException {
        Bike bike = this.getBikeById(id);
        if (bike.getStatus().equals("hoat dong")) {
            return true;
        } else return false;

    }


    /**
     * @param id
     * @return bike theo id
     */
    public Bike getBikeById(Integer id) {
        String sql = "select * from bike where id =" + id;
        Bike bike = null;
        try {
            conn = (Connection) ConnectionDB.ConnectionDB();

            st = (Statement) conn.createStatement();
            rs = st.executeQuery(sql);
            rs.next();
            bike = new Bike(
                    rs.getInt("id"),
                    rs.getString("kind"),
                    rs.getInt("dock_id"),
                    rs.getInt("value"),
                    rs.getString("description"),
                    rs.getString("status")
            );

        } catch (Exception ex) {
            ex.printStackTrace();
        }
        return bike;
    }

    @Override
    public long calculateFees(Timestamp start, Timestamp end) {
        return Long.parseLong(null);
    }

    @Override
    public double calculateDeposit() {
        return this.value*0.4;
    }
}







