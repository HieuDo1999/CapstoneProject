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
public class TwinBike extends Bike{
    /**
     * ham khoi tao
     * @param id
     * @param kind
     * @param dockId
     * @param value
     * @param description
     * @param status
     */
    public TwinBike(int id, String kind, int dockId, int value, String description, String status) {
        super(id, kind, dockId, value, description, status);
    }

    public TwinBike() {
    }

    /**
     *
     * @return danh sach bike
     */
    @Override
    public List<Bike> getListBike() {
        String sql="select * from bike where kind='xe dap doi'";
        List<Bike>listBike= new ArrayList<Bike>();
        try {
            conn= (Connection) ConnectionDB.ConnectionDB();

            st = (Statement) conn.createStatement();
            rs=st.executeQuery(sql);
            while(rs.next()) {

                TwinBike twinBike =new TwinBike(
                        rs.getInt("id"),
                        rs.getString("kind"),
                        rs.getInt("dock_id"),
                        rs.getInt("value"),
                        rs.getString("description"),
                        rs.getString("status")
                );
                listBike.add(twinBike);
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
    public TwinBike getBikeById(Integer id){
        String sql="select * from bike where id ="+id;
        TwinBike bike = null;
        try {
            conn= (Connection) ConnectionDB.ConnectionDB();

            st = (Statement) conn.createStatement();
            rs=st.executeQuery(sql);
            rs.next();
            bike =new TwinBike(
                    rs.getInt("id"),
                    rs.getString("kind"),
                    rs.getInt("dock_id"),
                    rs.getInt("value"),
                    rs.getString("description"),
                    rs.getString("status")
            );

        }catch (Exception ex){
            ex.printStackTrace();
        }
        return bike;
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
