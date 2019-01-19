package sample;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextArea;
import sample.store.*;

import java.net.URL;
import java.util.ResourceBundle;

public class Controller implements Initializable {

    @FXML
    ComboBox<String> planeTypeCombo, marketCombo;

    @FXML
    TextArea resultArea;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        ObservableList<String> planeTypes = FXCollections.observableArrayList();
        planeTypes.setAll("Tpx100", "Tpx200", "Tpx300");
        planeTypeCombo.setItems(planeTypes);

        ObservableList<String> markets = FXCollections.observableArrayList();
        markets.setAll("Domestic", "Eurasia", "Other");
        marketCombo.setItems(markets);
    }

    @FXML
    public void create(ActionEvent event){

        String market = marketCombo.getValue();
        if (market == null)
        {
            resultArea.setStyle("-fx-text-fill: #ff0000; ");
            resultArea.setText("Hedef Market Seçiniz !!");
            return;
        }

        String planeType = planeTypeCombo.getValue();
        if (planeType == null)
        {
            resultArea.setStyle("-fx-text-fill: #ff0000; ");
            resultArea.setText("Uçak Tipini Seçiniz !!");
            return;
        }
        resultArea.setStyle("-fx-text-fill: #000000; ");

        PlaneStore store = null;
        if (market.equals("Domestic")){
            store = new DomesticStore();
        }else if (market.equals("Eurasia")){
            store = new EurasiaStore();
        }else if (market.equals("Other")){
            store = new OtherStore();
        }

        Plane plane= store.orderPlane(planeType.toLowerCase());

        String result = "name : " + plane.getName()+"\n" +
                "\n"+ plane.getPurpose()+
                "\n"+ plane.getSkeleton()+
                "\n"+ plane.getEngine()+
                "\n"+ plane.getSeat()+ "\n";

        resultArea.setText(result);

    }
}
