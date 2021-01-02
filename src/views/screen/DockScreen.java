package views.screen;

import controller.DockController;
import entity.Dock.Dock;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
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

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.util.List;
import java.util.ResourceBundle;

public class DockScreen extends BaseScreen {
    @FXML
    private TextField dockid;
    @FXML
    private TableColumn<Dock,String> address;

    @FXML
    private TableColumn<Dock, String> name;

    @FXML
    private TableColumn<Dock, Integer> id;

    @FXML
    private TableColumn<Dock, Integer> numberbike;
    @FXML
    private TableView<Dock> docktable;
    private ObservableList<Dock> ListDock;
    private DockController dockController;
    public List<Dock> getListDock() throws SQLException, ClassNotFoundException {
        this.dockController= new DockController();
        return dockController.getListDock();
    }
    public void setListDock() throws SQLException, ClassNotFoundException {

        ListDock= FXCollections.observableArrayList(
                this.getListDock()
        );
        id.setCellValueFactory(new PropertyValueFactory<Dock,Integer>("id"));
        name.setCellValueFactory(new PropertyValueFactory<Dock,String>("name"));
        address.setCellValueFactory(new PropertyValueFactory<Dock,String>("address"));
        numberbike.setCellValueFactory(new PropertyValueFactory<Dock,Integer>("numberBike"));
        docktable.setItems(ListDock);
    }

    public void changeSceneHome(ActionEvent e){
        this.changeSceneHome(e);
    }

    public void finddockbyid() throws SQLException, ClassNotFoundException {
        Integer ID= Integer.valueOf(dockid.getText());
        if(ID!=null) {
            ListDock = FXCollections.observableArrayList(
                    dockController.getListDockById(ID)
            );
            id.setCellValueFactory(new PropertyValueFactory<Dock, Integer>("id"));
            name.setCellValueFactory(new PropertyValueFactory<Dock, String>("name"));
            address.setCellValueFactory(new PropertyValueFactory<Dock, String>("address"));
            numberbike.setCellValueFactory(new PropertyValueFactory<Dock, Integer>("numberBike"));
            docktable.setItems(ListDock);
        }else{
           this.alertInputEmpty();
        }
    }




    public void detaildock(javafx.event.ActionEvent e) throws IOException {
        Dock dock= docktable.getSelectionModel().getSelectedItem();
        if(dock!=null) {
            Stage stage = (Stage) ((Node) e.getSource()).getScene().getWindow();
            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(getClass().getResource("/views/fxml/DockDetail.fxml"));
            Parent list = loader.load();
            Scene scene = new Scene(list);
            DockDetailScreen controller = loader.getController();
            controller.setDockDetail(dock);
            stage.setScene(scene);
        }
        else{
           this.alertInputEmpty();
        }
    }


}
