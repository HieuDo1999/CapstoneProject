package views.screen;

import controller.PaymentController;
import controller.TransactionController;
import entity.bike.Bike;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;

import java.sql.SQLException;


public class PaymentScreen extends BaseScreen {
    @FXML
    private Label bikeid;

    @FXML
    private TextField customerid;
    @FXML
    private TextField experationdate;

    @FXML
    private TextField cardholdername;

    @FXML
    private TextField cardsercuritycode;

    @FXML
    private TextField cardnumber;

    private PaymentController paymentController;
    private TransactionController transactionController;

    public void setPayment(Integer bikeID){
        bikeid.setText(String.valueOf(bikeID));
    }


    @FXML
    void payment(ActionEvent event) throws SQLException, ClassNotFoundException {
        if(!(customerid.getText().equals("")&&
                cardholdername.getText().equals("")&&
                experationdate.getText().equals("")&&
                cardsercuritycode.getText().equals("")&&
                cardnumber.getText().equals("")))
        {

            //check bank
            //thay đổi state bike
            paymentController= new PaymentController();
            paymentController.changeStateBike(Integer.valueOf(bikeid.getText()));
            //make transaction
            transactionController= new TransactionController();
            transactionController.makeTransaction(customerid.getText(), Integer.valueOf(bikeid.getText()));
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setHeaderText("Thanh toan thanh cong");
            alert.show();


        }else {
            this.alertInputEmpty();
        }


    }
    public void changeSceneHome(java.awt.event.ActionEvent e){
        this.changeSceneHome(e);
    }

}
