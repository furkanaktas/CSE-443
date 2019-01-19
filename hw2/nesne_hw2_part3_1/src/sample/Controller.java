package sample;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextArea;
import sample.plane.Plane;

import java.net.URL;
import java.util.ResourceBundle;

public class Controller implements Initializable {

    @FXML
    ComboBox<String> planeTypeCombo;

    @FXML
    TextArea resultArea;


    @Override
    public void initialize(URL location, ResourceBundle resources) {
        ObservableList<String> suits = FXCollections.observableArrayList();
        suits.setAll("Tpx100", "Tpx200", "Tpx300");
        planeTypeCombo.setItems(suits);
    }

    @FXML
    public void create(ActionEvent event){
        String planeType = planeTypeCombo.getValue();
        if (planeType == null)
        {
            resultArea.setStyle("-fx-text-fill: #ff0000; ");
            resultArea.setText("Uçak Tipini Seçiniz !!");
            return;
        }
        resultArea.setStyle("-fx-text-fill: #000000; ");

        PlaneStore store = new PlaneStore(new PlaneFactory());
        Plane plane = store.orderPlane(planeType.toLowerCase());

        String result = "name : " + plane.getName()+"\n" +
                        "\n"+ plane.getPurpose()+
                        "\n"+ plane.getSkeleton()+
                        "\n"+ plane.getEngine()+
                        "\n"+ plane.getSeat()+ "\n";

        resultArea.setText(result);

    }
}
