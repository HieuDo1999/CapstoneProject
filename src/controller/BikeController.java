package controller;


import entity.bike.Bike;
import entity.bike.StandardBike;

import java.sql.SQLException;
import java.util.List;

public class BikeController {
    private Bike bike;
    public List getListBike() throws SQLException, ClassNotFoundException {
        bike= new Bike();
        return bike.getListBike();
    }
    public Bike getBikeById(Integer id) throws SQLException, ClassNotFoundException {
        bike = new Bike();
        return bike.getBikeById(id);
    }

}
