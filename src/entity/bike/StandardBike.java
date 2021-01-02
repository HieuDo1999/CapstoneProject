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
public class StandardBike extends Bike {
    /**
     * ham khoi tao
     * @param id
     * @param kind
     * @param dockId
     * @param value
     * @param description
     * @param status
     */
    public StandardBike(int id, String kind, int dockId, int value, String description, String status) {
        super(id, kind, dockId, value, description, status);
    }

    public StandardBike() {
    }

    /**
     *
     * @return danh sach standard bike
     */
    @Override
    public List getListBike() {
        String sql="select * from bike where kind ="+"'xe dap don'";
        List<Bike>listBike= new ArrayList<>();
        try {
            conn= (Connection) ConnectionDB.ConnectionDB();

            st = (Statement) conn.createStatement();
            rs=st.executeQuery(sql);
            while(rs.next()) {

                StandardBike standardBike=new StandardBike(
                        rs.getInt("id"),
                        rs.getString("kind"),
                        rs.getInt("dock_id"),
                        rs.getInt("value"),
                        rs.getString("description"),
                        rs.getString("status")
                );
                listBike.add(standardBike);
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
    public StandardBike getBikeById(Integer id){
        String sql="select * from bike where id ="+id;
        StandardBike standardBike = null;
        try {
            conn= (Connection) ConnectionDB.ConnectionDB();

            st = (Statement) conn.createStatement();
            rs=st.executeQuery(sql);
            rs.next();
            standardBike=new StandardBike(
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
        return standardBike;
    }

    @Override
    public long calculateFees(Timestamp start, Timestamp end) {
        long timeS= (end.getTime() - start.getTime());
        long timeM= TimeUnit.MILLISECONDS.toMinutes(timeS);
        if(timeM<=10) return 0;
        else{
            return (((timeM-10)/15)*3000+10000);
        }
    }


}
