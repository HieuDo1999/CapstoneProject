package views.screen;

import entity.bike.Bike;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

import java.io.IOException;
import java.sql.Timestamp;

public class RentalBikeScreen extends  BaseScreen {

    @FXML
    private TextField kind;

    @FXML
    private TextField bikeid;

    @FXML
    private TextField deposit;

    @FXML
    private TextField value;

    public void setRentalBike(Bike bike){
        bikeid.setText(String.valueOf(bike.getId()));
        value.setText(String.valueOf(bike.getValue()));
        kind.setText(bike.getKind());
        deposit.setText(String.valueOf(bike.calculateDeposit()));
    }
    @FXML
    void confirm(ActionEvent e) throws IOException {

        Stage stage=(Stage)((Node) e.getSource()).getScene().getWindow();
        FXMLLoader loader =new FXMLLoader();
        loader.setLocation(getClass().getResource("/views/fxml/Payment.fxml"));
        Parent list= loader.load();
        Scene scene= new Scene(list);
        PaymentScreen controller=loader.getController();
        Integer bikeID= Integer.valueOf((bikeid.getText()));
        controller.setPayment((bikeID));
        stage.setScene(scene);
    }
    public void changeSceneHome(java.awt.event.ActionEvent e){
        this.changeSceneHome(e);
    }

}
