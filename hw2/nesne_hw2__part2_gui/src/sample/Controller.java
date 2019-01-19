package sample;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.control.TextFormatter;
import javafx.util.converter.IntegerStringConverter;
import sample.accessories.AutoRifle;
import sample.accessories.Flamethrower;
import sample.accessories.Laser;
import sample.accessories.RocketLauncher;
import sample.suits.Armor;
import sample.suits.Dec;
import sample.suits.Ora;
import sample.suits.Tor;

import java.net.URL;
import java.text.NumberFormat;
import java.util.Locale;
import java.util.ResourceBundle;
import java.util.function.UnaryOperator;

public class Controller implements Initializable {

    @FXML
    ComboBox<String> suitTypeCombo;

    @FXML
    TextField flame;
    @FXML
    TextField rifle;
    @FXML
    TextField rocket;
    @FXML
    TextField laser;

    @FXML
    TextArea resultArea;


    @Override
    public void initialize(URL location, ResourceBundle resources) {

        ObservableList<String> suits = FXCollections.observableArrayList();
        suits.setAll("Dec", "Ora", "Tor");
        suitTypeCombo.setItems(suits);

        UnaryOperator<TextFormatter.Change> integerFilter = change -> {
            String newText = change.getControlNewText();
            if (newText.matches("-?([1-9][0-9]*)?")) {
                return change;
            }
            return null;
        };

        flame .setTextFormatter( new TextFormatter<Integer>(new IntegerStringConverter(), 0, integerFilter));  // sayı almaya zorlar
        rifle .setTextFormatter( new TextFormatter<Integer>(new IntegerStringConverter(), 0, integerFilter));  // sayı almaya zorlar
        rocket.setTextFormatter( new TextFormatter<Integer>(new IntegerStringConverter(), 0, integerFilter));  // sayı almaya zorlar
        laser .setTextFormatter( new TextFormatter<Integer>(new IntegerStringConverter(), 0, integerFilter));  // sayı almaya zorlar

        resultArea.setEditable(false);  // matrix'in gui'de gösterildiği alanın, düzenlenmesini engeller
    }


    @FXML
    void getResult(ActionEvent event){

        String suitType = suitTypeCombo.getValue();
        if ( suitType == null){
            printErr("Zırh Tipi Seçiniz !!");
            return;
        }
        resultArea.setStyle("-fx-text-fill: #000000; ");

        Armor suit = null;
        if (suitType.equals("Dec"))
            suit= new Dec();
        else if (suitType.equals("Ora"))
            suit= new Ora();
        else if (suitType.equals("Tor"))
            suit= new Tor();

        int weaponSize = Integer.parseInt(flame.getText());
        for (int i = 0; i < weaponSize; i++) {
            suit = new Flamethrower(suit);
        }

        weaponSize = Integer.parseInt(rifle.getText());
        for (int i = 0; i < weaponSize; i++) {
            suit = new AutoRifle(suit);
        }

        weaponSize = Integer.parseInt(rocket.getText());
        for (int i = 0; i < weaponSize; i++) {
            suit = new RocketLauncher(suit);
        }

        weaponSize = Integer.parseInt(laser.getText());
        for (int i = 0; i < weaponSize; i++) {
            suit = new Laser(suit);
        }

        NumberFormat numberFormatter = NumberFormat.getNumberInstance(Locale.US);

        String result = "Suit           : "+suitType +"\n"+
                "Cost          : "+ numberFormatter.format(suit.cost())+" TL\n"+
                "Weight      : "+ suit.weight() +" KG\n";


        resultArea.setText(result);
    }

    /**
     @param err  gösterilecek error mesajı
     */
    private void printErr(String err){
        resultArea.setStyle("-fx-text-fill: #ff0000; ");
        resultArea.setText(err);
    }
}
