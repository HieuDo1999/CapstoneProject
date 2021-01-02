package views.screen;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.stage.Stage;

import java.io.IOException;

public class BaseScreen {

    @FXML
    void changeSceneHome(ActionEvent e) throws IOException {
        Stage stage=(Stage)((Node) e.getSource()).getScene().getWindow();
        FXMLLoader loader =new FXMLLoader();
        loader.setLocation(getClass().getResource("/views/fxml/home.fxml"));
        Parent list= loader.load();
        Scene scene= new Scene(list);
        stage.setScene(scene);
    }

    void alertInputEmpty(){
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle("Rental Bike");
        alert.setHeaderText("Vui long cung cap du thong tin");
        alert.show();
    }
    void alertBikeNotValid(){
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle("Rental Bike");
        alert.setHeaderText("Xe nay dang duoc thue, vui long chon xe khac");
        alert.show();
    }

}
