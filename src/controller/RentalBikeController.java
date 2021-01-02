package controller;

import entity.bike.Bike;

import java.sql.SQLException;

public class RentalBikeController {
    private Bike bike;
    public boolean checkStateBike(Integer id) throws SQLException {
        bike= new Bike();

        return bike.checkStateBike(id);
    }
}
