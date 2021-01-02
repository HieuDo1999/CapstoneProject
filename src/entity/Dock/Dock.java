package entity.Dock;

import com.mysql.jdbc.Connection;
import com.mysql.jdbc.Statement;
import entity.bike.Bike;
import entity.db.ConnectionDB;
import javafx.beans.value.ObservableValue;
import javafx.scene.control.TableColumn;
import javafx.util.Callback;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

/**
 * @author HieuDo
 * @version 2
 */
public class Dock {
    Integer id;
    String name;
    String address;
    Integer numberBike;
    List<Bike> listBike;

    public List<Bike> getListBike() {
        return listBike;
    }

    public void setListBike(List<Bike> listBike) {
        this.listBike = listBike;
    }

    public Dock(Integer id, String name, String address, Integer numberBike) {
        this.id = id;
        this.name = name;
        this.address = address;
        this.numberBike = numberBike;
    }

    public Dock() {
    }
    Statement st=null;
    ResultSet rs=null;
    Connection conn=null;
    PreparedStatement ps=null;

    /**
     *
     * @return danh sach dock
     * @throws SQLException
     */
    public List<Dock> getListDock() throws SQLException {
        String sql="select * from dock";
        List<Dock>listDock= new ArrayList<>();
        conn= (Connection) ConnectionDB.ConnectionDB();
        st = (Statement) conn.createStatement();
        rs=st.executeQuery(sql);
        while(rs.next()) {
            Dock dock =new Dock(
                    rs.getInt("id"),
                    rs.getString("name"),
                    rs.getString("address"),
                    rs.getInt("number_bike")

            );
            listDock.add(dock);
        }
        return listDock;
    }

    /**
     *
     * @param id
     * @return dock
     * @throws SQLException
     */
    public List<Dock> getListDockById(Integer id) throws SQLException {
        String sql="select * from dock where id ="+id;
        List<Dock>listDock= new ArrayList<>();
        conn= (Connection) ConnectionDB.ConnectionDB();
        st = (Statement) conn.createStatement();
        rs=st.executeQuery(sql);
        while(rs.next()) {
            Dock dock =new Dock(
                    rs.getInt("id"),
                    rs.getString("name"),
                    rs.getString("address"),
                    rs.getInt("number_bike")

            );
            listDock.add(dock);
        }
        return listDock;
    }

    /**
     *
     * @param dockId
     * @return danh sach bike trong 1 dock
     * @throws SQLException
     */
    public List<Bike> getListBikeByDockId(Integer dockId) throws SQLException {
        String sql="select * from bike where dock_id="+dockId;
        List<Bike>listBike= new ArrayList<>();
        conn= (Connection) ConnectionDB.ConnectionDB();
        st = (Statement) conn.createStatement();
        rs=st.executeQuery(sql);
        while(rs.next()) {
            Bike bike =new Bike(
                    rs.getInt("id"),
                    rs.getString("kind"),
                    rs.getInt("dock_id"),
                    rs.getInt("value"),
                    rs.getString("description"),
                    rs.getString("status")
            );
            listBike.add(bike);
        }
        this.listBike=listBike;
        return listBike;

    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public Integer getNumberBike() {
        return numberBike;
    }

    public void setNumberBike(Integer numberBike) {
        this.numberBike = numberBike;
    }
}
