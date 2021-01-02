package controller;


import entity.bike.Bike;
import entity.bike.StandardBike;
import entity.bike.StandardEBike;
import entity.bike.TwinBike;

import java.sql.SQLException;
import java.util.List;

public class BikeController {
    private Bike bike;
    private StandardBike standardBike;
    private StandardEBike standardEBike;
    private TwinBike twinBike;
    public List getListBike() throws SQLException, ClassNotFoundException {
        bike= new Bike();
        return bike.getListBike();
    }
    public Bike getBikeById(Integer id) throws SQLException, ClassNotFoundException {
        bike = new Bike();
        Bike BIKE= bike.getBikeById(id);
        if(BIKE.getKind().equals("xe dap don")){
            return standardBike= new StandardBike().getBikeById(id);

        }else  if(BIKE.getKind().equals("xe dap doi")){
           return twinBike = new TwinBike().getBikeById(id);
        }
        else  if(BIKE.getKind().equals("xe dap dien")){
          return   standardEBike = new StandardEBike().getBikeById(id);
        }
        return null;
    }

}
