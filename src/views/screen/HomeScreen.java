package views.screen;



import Payment.PaymentTransaction;
import controller.PaymentController;
import controller.RentalBikeController;
import controller.TransactionController;
import entity.bike.Bike;
import entity.bike.TwinBike;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.util.List;
import java.util.ResourceBundle;

public class HomeScreen extends BaseScreen implements Initializable  {
    @FXML
    private TableColumn<Bike,Integer> dockId;

    @FXML
    private TableColumn<Bike,String> kind;
    @FXML
    private TableColumn<Bike,Integer> id;

    @FXML
    private TextField bikeId;

    @FXML
    private TableColumn<Bike, Integer> value;

    @FXML
    private TableView<Bike> biketable;

    @FXML
    private TableColumn<Bike, String> status;
    @FXML
    private TableColumn<Bike, String> description;

    private ObservableList<Bike> ListBike;
    private Bike bike;
    private List<Bike> list;
    private RentalBikeController rentalBikeController;

    public List<Bike> getListBike( ) throws SQLException, ClassNotFoundException {
        this.bike=new Bike();
         return  bike.getListBike();

    }
    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        try {
            ListBike= FXCollections.observableArrayList(
                   this.getListBike()
            );
        } catch (SQLException | ClassNotFoundException throwables) {
            throwables.printStackTrace();
        }
        id.setCellValueFactory(new PropertyValueFactory<Bike,Integer>("id"));
        kind.setCellValueFactory(new PropertyValueFactory<Bike,String>("kind"));
        value.setCellValueFactory(new PropertyValueFactory<Bike,Integer>("value"));
        dockId.setCellValueFactory(new PropertyValueFactory<Bike,Integer>("dockId"));
        status.setCellValueFactory(new PropertyValueFactory<Bike,String>("status"));
        description.setCellValueFactory(new PropertyValueFactory<Bike,String>("description"));
        biketable.setItems(ListBike);
    }



    @FXML
    void listdock(ActionEvent e) throws IOException, SQLException, ClassNotFoundException {
        Stage stage=(Stage)((Node) e.getSource()).getScene().getWindow();
        FXMLLoader loader =new FXMLLoader();
        loader.setLocation(getClass().getResource("/views/fxml/Dock.fxml"));
        Parent list= loader.load();
        Scene scene= new Scene(list);
        DockScreen controller = loader.getController();
        controller.setListDock();
        stage.setScene(scene);
    }
    @FXML
    void liststandardbike(ActionEvent e) throws IOException, SQLException, ClassNotFoundException {
        Stage stage=(Stage)((Node) e.getSource()).getScene().getWindow();
        FXMLLoader loader =new FXMLLoader();
        loader.setLocation(getClass().getResource("/views/fxml/StandardBike.fxml"));
        Parent list= loader.load();
        Scene scene= new Scene(list);
        StandardBikeScreen controller = loader.getController();
        controller.setListStandardBike();
        stage.setScene(scene);
    }
    @FXML
    void listtwinbike(ActionEvent e) throws IOException, SQLException, ClassNotFoundException {
        Stage stage=(Stage)((Node) e.getSource()).getScene().getWindow();
        FXMLLoader loader =new FXMLLoader();
        loader.setLocation(getClass().getResource("/views/fxml/TwinBike.fxml"));
        Parent list= loader.load();
        Scene scene= new Scene(list);
        TwinBikeScreen controller = loader.getController();
        controller.setListTwinBike();
        stage.setScene(scene);
    }
    @FXML
    void liststandardebike(ActionEvent e) throws IOException, SQLException, ClassNotFoundException {
        Stage stage=(Stage)((Node) e.getSource()).getScene().getWindow();
        FXMLLoader loader =new FXMLLoader();
        loader.setLocation(getClass().getResource("/views/fxml/StandardEBike.fxml"));
        Parent list= loader.load();
        Scene scene= new Scene(list);
        StandardEBikeScreen controller = loader.getController();
        controller.setListStandardEBike();
        stage.setScene(scene);
    }


    @FXML
    public void findbikebyid() throws SQLException {
        Integer bikeID= Integer.valueOf(bikeId.getText());
        if(bikeID!=null) {
            ListBike = FXCollections.observableArrayList(
                    bike.getBikeById(bikeID)
            );
            id.setCellValueFactory(new PropertyValueFactory<Bike, Integer>("id"));
            kind.setCellValueFactory(new PropertyValueFactory<Bike, String>("kind"));
            value.setCellValueFactory(new PropertyValueFactory<Bike, Integer>("value"));
            dockId.setCellValueFactory(new PropertyValueFactory<Bike, Integer>("dockId"));
            status.setCellValueFactory(new PropertyValueFactory<Bike, String>("status"));
            description.setCellValueFactory(new PropertyValueFactory<Bike, String>("description"));
            biketable.setItems(ListBike);
        }else{
            this.alertInputEmpty();
        }
    }
    @FXML
    public void detail(ActionEvent e) throws IOException, SQLException, ClassNotFoundException {
         bike = biketable.getSelectionModel().getSelectedItem();
        if (bike != null) {
             if(bike.getKind().equals("xe dap don")) {
                 Stage stage = (Stage) ((Node) e.getSource()).getScene().getWindow();
                 FXMLLoader loader = new FXMLLoader();
                 loader.setLocation(getClass().getResource("/views/fxml/StandardBike.fxml"));
                 Parent detail = loader.load();
                 Scene sceneDockDetail = new Scene(detail);
                 StandardBikeScreen controller = loader.getController();
                 controller.detail(bike.getId());
                 stage.setScene(sceneDockDetail);
             }else if(bike.getKind().equals("xe dap doi")){
                 Stage stage = (Stage) ((Node) e.getSource()).getScene().getWindow();
                 FXMLLoader loader = new FXMLLoader();
                 loader.setLocation(getClass().getResource("/views/fxml/TwinBike.fxml"));
                 Parent detail = loader.load();
                 Scene sceneDockDetail = new Scene(detail);
                 TwinBikeScreen controller = loader.getController();
                 controller.detail(bike.getId());

                 stage.setScene(sceneDockDetail);
             }else if(bike.getKind().equals("xe dap dien")){
                 Stage stage = (Stage) ((Node) e.getSource()).getScene().getWindow();
                 FXMLLoader loader = new FXMLLoader();
                 loader.setLocation(getClass().getResource("/views/fxml/StandardEBike.fxml"));
                 Parent detail = loader.load();
                 Scene sceneDockDetail = new Scene(detail);
                 StandardEBikeScreen controller = loader.getController();
                 controller.detail(bike.getId());

                 stage.setScene(sceneDockDetail);
             }
            } else {
               this.alertInputEmpty();
            }

    }

    @FXML
        void rental(ActionEvent e) throws IOException, SQLException, ClassNotFoundException {
        bike = biketable.getSelectionModel().getSelectedItem();
        if (bike != null) {
            transactionController = new TransactionController();
            PaymentTransaction paymentTransaction = transactionController.getTransaction("Group 7");
            if (paymentTransaction==null) {
                this.rentalBikeController = new RentalBikeController();
                if (rentalBikeController.checkStateBike(bike.getId())) {
                    Stage stage = (Stage) ((Node) e.getSource()).getScene().getWindow();
                    FXMLLoader loader = new FXMLLoader();
                    loader.setLocation(getClass().getResource("/views/fxml/RentalBike.fxml"));
                    Parent detail = loader.load();
                    Scene sceneDockDetail = new Scene(detail);
                    RentalBikeScreen controller = loader.getController();
                    controller.setRentalBike(bike);
                    stage.setScene(sceneDockDetail);
                } else {
                    this.alertBikeNotValid();
                }

            } else {
                Alert alert = new Alert(Alert.AlertType.INFORMATION);
                alert.setTitle("Rental Bike");
                alert.setHeaderText("Ban chi duoc thue 1 xe");
                alert.show();
            }
        } else{
            this.alertInputEmpty();
        }

    }

    TransactionController transactionController;
    @FXML
    void returnbike(ActionEvent e) throws SQLException, ClassNotFoundException, IOException {
        transactionController= new TransactionController();
        PaymentTransaction paymentTransaction=transactionController.getTransaction("Group 7");
        if(paymentTransaction!=null && (paymentTransaction.getStatus()==0)) {
            if (paymentTransaction.getStatus() == 0) {
                Stage stage = (Stage) ((Node) e.getSource()).getScene().getWindow();
                FXMLLoader loader = new FXMLLoader();
                loader.setLocation(getClass().getResource("/views/fxml/Return.fxml"));
                Parent detail = loader.load();
                Scene sceneDockDetail = new Scene(detail);
                ReturnScreen controller = loader.getController();
                controller.setReturn(paymentTransaction);
                stage.setScene(sceneDockDetail);
            } else {
                Alert alert = new Alert(Alert.AlertType.INFORMATION);
                alert.setTitle("Rental Bike");
                alert.setHeaderText("Ban chi duoc thue 1 xe");
                alert.show();
            }
        }else{
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("Rental Bike");
            alert.setHeaderText("Ban chua thue xe");
            alert.show();
        }
    }




}
