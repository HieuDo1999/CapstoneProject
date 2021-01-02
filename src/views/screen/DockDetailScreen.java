package views.screen;

import entity.Dock.Dock;
import entity.bike.Bike;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;

import java.awt.event.ActionEvent;
import java.sql.SQLException;
import java.util.List;

public class DockDetailScreen  extends BaseScreen{

    @FXML
    private TableColumn<Bike, Integer> dockID;

    @FXML
    private Label dockid;

    @FXML
    private Label address;

    @FXML
    private TableColumn<Bike, String> kind;

    @FXML
    private TableColumn<Bike, Integer> bikeid;

    @FXML
    private Label name;

    @FXML
    private TableColumn<Bike, String> description;

    @FXML
    private Label numberbike;

    @FXML
    private TableColumn<Bike, Integer> value;

    @FXML
    private TableView<Bike> biketable;

    @FXML
    private TableColumn<Bike, String> status;

    private ObservableList<Bike> ListBike;
    private Dock dock;

    private List<Bike> getListBikeByDockId(Integer dockId) throws SQLException {
        this.dock=new Dock();
        return dock.getListBikeByDockId(dockId);

    }

    public void setDockDetail(Dock dock){

        dockid.setText(String.valueOf(dock.getId()));
        name.setText(dock.getName());
        address.setText(dock.getAddress());
        numberbike.setText(String.valueOf(dock.getNumberBike()));

        try {
            ListBike= FXCollections.observableArrayList(
                   this.getListBikeByDockId(dock.getId())
            );
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        bikeid.setCellValueFactory(new PropertyValueFactory<Bike,Integer>("id"));
        kind.setCellValueFactory(new PropertyValueFactory<Bike,String>("kind"));
        value.setCellValueFactory(new PropertyValueFactory<Bike,Integer>("value"));
        dockID.setCellValueFactory(new PropertyValueFactory<Bike,Integer>("dockId"));
        status.setCellValueFactory(new PropertyValueFactory<Bike,String>("status"));
        description.setCellValueFactory(new PropertyValueFactory<Bike,String>("description"));
        biketable.setItems(ListBike);

    }
    public void changeSceneHome(ActionEvent e){
        this.changeSceneHome(e);
    }


}
