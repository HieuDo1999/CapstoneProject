package entity.bike;

import com.mysql.jdbc.Connection;
import com.mysql.jdbc.Statement;
import entity.db.ConnectionDB;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.TimeUnit;

/**
 * @author HieuDo
 * @version 2
 */
public class StandardEBike extends Bike {
    int energy;
    float time;

    public int getEnergy() {
        return energy;
    }

    public void setEnergy(int energy) {
        this.energy = energy;
    }

    public float getTime() {
        return time;
    }

    public void setTime(float time) {
        this.time = time;
    }

    /**
     * ham khoi tao
     * @param id
     * @param kind
     * @param dockId
     * @param value
     * @param description
     * @param status
     * @param energy
     * @param time
     */
    public StandardEBike(int id, String kind, int dockId, int value, String description, String status, int energy, float time) {
        super(id, kind, dockId, value, description, status);
        this.energy = energy;
        this.time = time;
    }

    public StandardEBike() {
    }

    /**
     *
     * @return danh sach standard e bike
     */
    @Override
    public List<Bike> getListBike() {
        String sql="select * from standardebike INNER JOIN bike ON bike.id=standardebike.id ";
        List<Bike>listBike= new ArrayList<Bike>();
        try {
            conn= (Connection) ConnectionDB.ConnectionDB();

            st = (Statement) conn.createStatement();
            rs=st.executeQuery(sql);
            while(rs.next()) {

                StandardEBike standardEBike =new StandardEBike(
                        rs.getInt("id"),
                        rs.getString("kind"),
                        rs.getInt("dock_id"),
                        rs.getInt("value"),
                        rs.getString("description"),
                        rs.getString("status"),
                        rs.getInt("energy"),
                        rs.getFloat("time")
                );
                listBike.add(standardEBike);
            }
        }catch(Exception ex) {
            ex.printStackTrace();
        }
        return listBike;
    }

    /**
     *
     * @param id
     * @return bike
     */
    @Override
    public StandardEBike getBikeById(Integer id){
        String sql="select * from standardebike INNER JOIN bike ON bike.id=standardebike.id where bike.id="+id+";";
        StandardEBike standardEBike = null;
        try {
            conn= (Connection) ConnectionDB.ConnectionDB();

            st = (Statement) conn.createStatement();
            rs=st.executeQuery(sql);
            rs.next();
            standardEBike =new StandardEBike(
                    rs.getInt("id"),
                    rs.getString("kind"),
                    rs.getInt("dock_id"),
                    rs.getInt("value"),
                    rs.getString("description"),
                    rs.getString("status"),
                    rs.getInt("energy"),
                    rs.getFloat("time")
            );

        }catch (Exception ex){
            ex.printStackTrace();
        }
        return standardEBike;
    }

    @Override
    public long calculateFees(Timestamp start, Timestamp end) {
        long timeS= (end.getTime() - start.getTime());
        long timeM= TimeUnit.MILLISECONDS.toMinutes(timeS);
        if(timeM<=10) return 0;
        else{
            return (long) (1.5*(((timeM-10)/15)*3000+10000));
        }
    }
}
