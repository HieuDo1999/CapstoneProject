package views.screen;

import controller.RentalBikeController;
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

import java.awt.event.ActionEvent;
import java.io.IOException;
import java.sql.SQLException;
import java.util.List;

public class StandardBikeScreen extends BaseScreen {
    @FXML
    private TableColumn<StandardBike,Integer> dockid;

    @FXML
    private TableColumn<StandardBike, String> kind;

    @FXML
    private TableColumn<StandardBike, String> description;

    @FXML
    private TableColumn<StandardBike, Integer> id;

    @FXML
    private TableColumn<StandardBike, Integer> value;

    @FXML
    private TableView<Bike> standardbiketable;

    @FXML
    private TableColumn<StandardBike, String> status;
    private ObservableList<Bike> ListStandardBike;
    private ObservableList<Bike> standardbike;
    private RentalBikeController rentalBikeController;
    private Bike standardBike;
    private Bike bike;


    public List<Bike> getListBike() throws SQLException, ClassNotFoundException {
        this.standardBike= new StandardBike();
        return standardBike.getListBike();
    }
    public Bike getBikeById(Integer id){
        this.standardBike= new StandardBike();
        return standardBike.getBikeById(id);
    }
    public void setListStandardBike() throws SQLException, ClassNotFoundException {
        ListStandardBike= FXCollections.observableArrayList(
                this.getListBike()
        );
        id.setCellValueFactory(new PropertyValueFactory<StandardBike,Integer>("id"));
        kind.setCellValueFactory(new PropertyValueFactory<StandardBike,String>("kind"));
        dockid.setCellValueFactory(new PropertyValueFactory<StandardBike,Integer>("dockId"));
        value.setCellValueFactory(new PropertyValueFactory<StandardBike,Integer>("value"));
        description.setCellValueFactory(new PropertyValueFactory<StandardBike,String>("description"));
        status.setCellValueFactory(new PropertyValueFactory<StandardBike,String>("status"));
        standardbiketable.setItems(ListStandardBike);
    }
    public void changeSceneHome(ActionEvent e){
        this.changeSceneHome(e);
    }
    public void detail(Integer ID) throws SQLException, ClassNotFoundException {
        standardbike= FXCollections.observableArrayList(
               this.getBikeById(ID)
        );
        id.setCellValueFactory(new PropertyValueFactory<StandardBike,Integer>("id"));
        kind.setCellValueFactory(new PropertyValueFactory<StandardBike,String>("kind"));
        dockid.setCellValueFactory(new PropertyValueFactory<StandardBike,Integer>("dockId"));
        value.setCellValueFactory(new PropertyValueFactory<StandardBike,Integer>("value"));
        description.setCellValueFactory(new PropertyValueFactory<StandardBike,String>("description"));
        status.setCellValueFactory(new PropertyValueFactory<StandardBike,String>("status"));
        standardbiketable.setItems(standardbike);
    }
    @FXML
    void rental(javafx.event.ActionEvent e) throws IOException, SQLException {
        bike = standardbiketable.getSelectionModel().getSelectedItem();
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
