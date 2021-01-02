package views.screen;

import controller.RentalBikeController;
import entity.bike.Bike;
import entity.bike.StandardEBike;
import entity.bike.TwinBike;
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

import java.awt.event.ActionEvent;
import java.io.IOException;
import java.sql.SQLException;
import java.util.List;

public class TwinBikeScreen  extends BaseScreen{
    @FXML
    private TableColumn<TwinBike,Integer> dockid;

    @FXML
    private TableColumn<TwinBike, String> kind;

    @FXML
    private TableColumn<TwinBike, String> description;

    @FXML
    private TableColumn<TwinBike, Integer> id;

    @FXML
    private TableColumn<TwinBike, Integer> value;

    @FXML
    private TableView<Bike> biketable;

    @FXML
    private TableColumn<TwinBike, String> status;
    private ObservableList<Bike> ListTwinBike;
    private ObservableList<Bike> twinbike;
    private Bike twinBike;
    private Bike bike;
    private RentalBikeController rentalBikeController;


    public List<Bike> getListBike() throws SQLException, ClassNotFoundException {
        this.twinBike= new StandardEBike();
        return twinBike.getListBike();
    }
    public void setListTwinBike() throws SQLException, ClassNotFoundException {
        ListTwinBike= FXCollections.observableArrayList(
                this.getListBike()
        );
        id.setCellValueFactory(new PropertyValueFactory<TwinBike,Integer>("id"));
        kind.setCellValueFactory(new PropertyValueFactory<TwinBike,String>("kind"));
        dockid.setCellValueFactory(new PropertyValueFactory<TwinBike,Integer>("dockId"));
        value.setCellValueFactory(new PropertyValueFactory<TwinBike,Integer>("value"));
        description.setCellValueFactory(new PropertyValueFactory<TwinBike,String>("description"));
        status.setCellValueFactory(new PropertyValueFactory<TwinBike,String>("status"));
        biketable.setItems(ListTwinBike);
    }
    public void changeSceneHome(ActionEvent e){
        this.changeSceneHome(e);
    }
    public void detail(Integer ID) throws SQLException, ClassNotFoundException {
        twinbike= FXCollections.observableArrayList(
                twinBike.getBikeById(ID)
        );
        id.setCellValueFactory(new PropertyValueFactory<TwinBike,Integer>("id"));
        kind.setCellValueFactory(new PropertyValueFactory<TwinBike,String>("kind"));
        dockid.setCellValueFactory(new PropertyValueFactory<TwinBike,Integer>("dockId"));
        value.setCellValueFactory(new PropertyValueFactory<TwinBike,Integer>("value"));
        description.setCellValueFactory(new PropertyValueFactory<TwinBike,String>("description"));
        status.setCellValueFactory(new PropertyValueFactory<TwinBike,String>("status"));
        biketable.setItems(twinbike);
    }
    @FXML
    void rental(javafx.event.ActionEvent e) throws IOException, SQLException {
        bike = biketable.getSelectionModel().getSelectedItem();
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
