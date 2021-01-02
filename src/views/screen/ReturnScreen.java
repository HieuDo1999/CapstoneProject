package views.screen;

import Payment.PaymentTransaction;
import controller.BikeController;
import controller.PaymentController;
import controller.RentalBikeController;
import controller.ReturnController;
import entity.bike.Bike;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;

import java.io.IOException;
import java.sql.SQLException;
import java.sql.Timestamp;

public class ReturnScreen extends BaseScreen {
    @FXML
    private TextField dockid;

    @FXML
    private Label fees;

    @FXML
    private Label now;

    @FXML
    private Label bikeid;

    @FXML
    private Label start;

    @FXML
    private Label deposit;


    private Bike bike;
    private BikeController bikeController;
    private PaymentController paymentController;
    private PaymentTransaction paymentTransaction;
    private ReturnController returnController;
    @FXML
    void setReturn(PaymentTransaction paymentTransaction) throws SQLException, ClassNotFoundException {

        this.paymentTransaction=paymentTransaction;
        bikeController= new BikeController();
        this.bike= bikeController.getBikeById(paymentTransaction.getBikeID());

        bikeid.setText(String.valueOf(bike.getId()));
        deposit.setText(String.valueOf(bike.calculateDeposit()));
        Timestamp Now= new Timestamp(System.currentTimeMillis());
        now.setText(String.valueOf(Now));
        start.setText(String.valueOf(paymentTransaction.getStart()));
        fees.setText(String.valueOf(bike.calculateFees(paymentTransaction.getStart(),Now)));
    }

    @FXML
    void confirm(ActionEvent event) throws IOException, SQLException, ClassNotFoundException {
        if(!dockid.getText().equals("")){
        paymentController= new PaymentController();
        paymentController.changeStateBike(Integer.valueOf(bikeid.getText()));
        returnController= new ReturnController();
        long fee= bike.calculateFees(paymentTransaction.getStart(),new Timestamp(System.currentTimeMillis()));
        returnController.savePaymentTransaction(paymentTransaction,fee);
        this.alertSuccessFull();
        this.changeSceneHome(event);
        }else{
            this.alertInputEmpty();
        }
    }
}
