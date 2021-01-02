package views.screen;

import controller.DockController;
import controller.RentalBikeController;
import entity.Dock.Dock;
import entity.bike.Bike;
import entity.bike.StandardBike;
import entity.bike.StandardEBike;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.io.IOException;
import java.sql.SQLException;
import java.util.List;

public class StandardEBikeScreen extends BaseScreen {
    @FXML
    private TableColumn<StandardEBike,Integer> dockid;
    @FXML
    private TableColumn<StandardEBike,Integer> energy;
    @FXML
    private TableColumn<StandardEBike,Integer> time;


    @FXML
    private TableColumn<StandardEBike, String> kind;

    @FXML
    private TableColumn<StandardEBike, String> description;

    @FXML
    private TableColumn<StandardEBike, Integer> id;

    @FXML
    private TableColumn<StandardEBike, Integer> value;

    @FXML
    private TableView<Bike> standardebiketable;

    @FXML
    private TableColumn<StandardEBike, String> status;
    private ObservableList<Bike> ListStandardEBike;
    private ObservableList<Bike> standardebike;
    private Bike standardEBike;
    private Bike bike;
    private  RentalBikeController rentalBikeController;

    public List<Bike> getListBike() throws SQLException, ClassNotFoundException {
        this.standardEBike= new StandardEBike();
        return standardEBike.getListBike();
    }

    public Bike getBikeById(Integer id){
        this.standardEBike= new StandardEBike();
        return standardEBike.getBikeById(id);
    }

    public void setListStandardEBike() throws SQLException, ClassNotFoundException {

        ListStandardEBike= FXCollections.observableArrayList(
                this.getListBike()
        );
        id.setCellValueFactory(new PropertyValueFactory<StandardEBike,Integer>("id"));
        kind.setCellValueFactory(new PropertyValueFactory<StandardEBike,String>("kind"));
        dockid.setCellValueFactory(new PropertyValueFactory<StandardEBike,Integer>("dockId"));
        value.setCellValueFactory(new PropertyValueFactory<StandardEBike,Integer>("value"));
        description.setCellValueFactory(new PropertyValueFactory<StandardEBike,String>("description"));
        status.setCellValueFactory(new PropertyValueFactory<StandardEBike,String>("status"));
        time.setCellValueFactory(new PropertyValueFactory<StandardEBike,Integer>("time"));
        energy.setCellValueFactory(new PropertyValueFactory<StandardEBike,Integer>("energy"));
        standardebiketable.setItems(ListStandardEBike);
    }
    public void detail(Integer ID) throws SQLException, ClassNotFoundException {
        standardebike= FXCollections.observableArrayList(
                this.getBikeById(ID)
        );
        id.setCellValueFactory(new PropertyValueFactory<StandardEBike,Integer>("id"));
        kind.setCellValueFactory(new PropertyValueFactory<StandardEBike,String>("kind"));
        dockid.setCellValueFactory(new PropertyValueFactory<StandardEBike,Integer>("dockId"));
        value.setCellValueFactory(new PropertyValueFactory<StandardEBike,Integer>("value"));
        description.setCellValueFactory(new PropertyValueFactory<StandardEBike,String>("description"));
        status.setCellValueFactory(new PropertyValueFactory<StandardEBike,String>("status"));
        time.setCellValueFactory(new PropertyValueFactory<StandardEBike,Integer>("time"));
        energy.setCellValueFactory(new PropertyValueFactory<StandardEBike,Integer>("energy"));
        standardebiketable.setItems(standardebike);
    }
    public void changeSceneHome(ActionEvent e){
        this.changeSceneHome(e);
    }
    @FXML
    void rental(javafx.event.ActionEvent e) throws IOException, SQLException {
        bike = standardebiketable.getSelectionModel().getSelectedItem();
        if (bike != null) {
            this.rentalBikeController= new RentalBikeController();
            if(rentalBikeController.checkStateBike(bike.getId())) {
                Stage stage = (Stage) ((Node) e.getSource()).getScene().getWindow();
                FXMLLoader loader = new FXMLLoader();
                loader.setLocation(getClass().getResource("/views/fxml/RentalBike.fxml"));
                Parent detail = loader.load();
                Scene sceneDockDetail = new Scene(detail);
                RentalBikeScreen controller = loader.getController();
                controller.setRentalBike(bike);
                stage.setScene(sceneDockDetail);
            }else{
                this.alertBikeNotValid();
            }
        }else{
            this.alertInputEmpty();
        }
    }
}
