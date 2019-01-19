package sample;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.stage.FileChooser;
import javafx.util.converter.IntegerStringConverter;

import java.io.*;
import java.net.URL;
import java.util.Arrays;
import java.util.ResourceBundle;
import java.util.function.UnaryOperator;

public class Controller implements Initializable {

    @FXML
    Label fileText;

    @FXML
    TextField rowNumber;

    @FXML
    Button fileButton;

    @FXML
    ComboBox<String> methodCombo;

    @FXML
    ScrollPane tablePane;

    @FXML
    ListView resultTable;

    @FXML
    TextArea matrixArea;


    private File file;
    private String solutionMethod;
    private SolutionProcess solutionProcess;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        ObservableList<String> methods = FXCollections.observableArrayList();
        methods.setAll("Gaussian", "Inverse");
        methodCombo.setItems(methods);


        UnaryOperator<TextFormatter.Change> integerFilter = change -> {
            String newText = change.getControlNewText();
            if (newText.matches("-?([1-9][0-9]*)?")) {
                return change;
            }
            return null;
        };

        rowNumber.setTextFormatter(     // row sayısı alınırken , sayı almaya zorlar
                new TextFormatter<Integer>(new IntegerStringConverter(), 0, integerFilter));

        matrixArea.setEditable(false);  // matrix'in gui'de gösterildiği alanın, düzenlenmesini engeller
    }

    @FXML
    void chooseFile(ActionEvent event){
        FileChooser fc = new FileChooser();
        file = fc.showOpenDialog(null);

        if (file != null){
            fileText.setText(file.getAbsolutePath());
        }else {
            printErr("Dosya Seçiniz !!");
        }
    }

    @FXML
    void getResult(ActionEvent event){
        if (Integer.parseInt(rowNumber.getText()) == 0){
            printErr("Satır Sayısı Giriniz !!");
            return;
        }

        if (file != null){
            solutionMethod = methodCombo.getValue();
            if ( solutionMethod == null){
                printErr("Method Seçiniz !!");
                return;
            }
            matrixArea.setStyle("-fx-text-fill: #000000; ");
            int rowNum = Integer.parseInt(rowNumber.getText());

            //--------------    Strategy pattern sağladığı avantaj başladı
            if(solutionMethod.equals("Gaussian")){
                solutionProcess = new SolutionProcess(new GaussianElimination(), file, rowNum, matrixArea);
            }
            else { // inverse
                solutionProcess = new SolutionProcess(new MatrixInversion(), file, rowNum, matrixArea);
            }

            double [] result = solutionProcess.makeSolution(); // ortak method ile her ikisi de çalışabilir
            //-------------- Strategy pattern sağladığı avantaj burada bitti

            ObservableList<Double> methods = FXCollections.observableArrayList();
            if (result != null)
            {
                Arrays.stream(result).forEach(el -> methods.add(el));
            }
            resultTable.setItems(methods);  //  sonucu gui de göster
        }
        else {
            printErr("Dosya Seçiniz !!");
        }
    }

    /**
    @param err  gösterilecek error mesajı
    */
    private void printErr(String err){
        matrixArea.setStyle("-fx-text-fill: #ff0000; ");
        matrixArea.setText(err);
    }

}
