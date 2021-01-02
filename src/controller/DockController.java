package controller;

import entity.Dock.Dock;

import java.sql.SQLException;
import java.util.List;

public class DockController {
    private Dock dock= new Dock();
    public List<Dock> getListDock() throws SQLException, ClassNotFoundException {
        return dock.getListDock();
    }
    public List<Dock> getListDockById(Integer id) throws SQLException, ClassNotFoundException {
        return dock.getListDockById(id);
    }
}
