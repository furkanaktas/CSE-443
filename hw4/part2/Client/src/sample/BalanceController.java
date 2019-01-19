package sample;

import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.VBoxBuilder;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import javafx.util.converter.IntegerStringConverter;

import java.net.URL;
import java.rmi.RemoteException;
import java.util.ResourceBundle;
import java.util.function.UnaryOperator;

public class BalanceController implements Initializable {

    @FXML
    TextField amount, cardNo;

    @FXML
    Label loadInfo, creditInfo, cardLabel;

    @FXML
    CheckBox usecard;


    ServerConnection serverConnection;

    @Override
    public void initialize(URL location, ResourceBundle resources) {

        UnaryOperator<TextFormatter.Change> integerFilter = change -> {
            String newText = change.getControlNewText();
            if (newText.matches("-?([1-9][0-9]*)?")) {
                return change;
            }
            return null;
        };

        cardNo.setTextFormatter(new TextFormatter<Integer>(new IntegerStringConverter(),    // force to get integer
                0,
                integerFilter));

        amount.setTextFormatter(new TextFormatter<Integer>(new IntegerStringConverter(),    // force to get integer
                0,
                integerFilter));

        usecard.setSelected(false);
        usecard.selectedProperty().addListener(new ChangeListener<Boolean>() {
            @Override
            public void changed(ObservableValue<? extends Boolean> observable, Boolean oldValue, Boolean newValue) {
                if (newValue){
                    cardLabel.setVisible(false);    // change visibility according to checkbox
                    cardNo.setVisible(false);
                }else {
                    cardLabel.setVisible(true);
                    cardNo.setVisible(true);
                }
            }
        });
    }

    /**
     *  for using same references while changing other controller to this controller
     *
     * @param serverConnection that comes from other controller
     * @param creditInfo that comes from other controller
     */
    public void setCommonComponent(ServerConnection serverConnection, Label creditInfo){
        this.serverConnection = serverConnection;
        this.creditInfo = creditInfo;
    }

    @FXML
    void loadBalance(ActionEvent event) throws RemoteException {
        String cardNo = this.cardNo.getText();
        String amount = this.amount.getText();  // get infos from gui

        if (amount.equals("") || (!usecard.isSelected() && cardNo.equals(""))){
            loadInfo.setStyle("-fx-text-fill: #ff0000; ");
            loadInfo.setText("Enter All Informations");
            return;
        }
        Integer money = Integer.parseInt(amount);
        Integer no = Integer.parseInt(cardNo);
        if ((!usecard.isSelected() && no == 0) || money == 0){
            loadInfo.setStyle("-fx-text-fill: #ff0000; ");
            loadInfo.setText("Number can't be 0!");
            return;
        }

        Client client = serverConnection.getCurrClient();

        if (usecard.isSelected())   // if checkbox selected, use registered card no
            no = client.getCardNo();

        serverConnection.getServices().loadBalance(client.getId(), no, money);

        Stage currStage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        Button okButton = new Button("OK");

        okButton.setOnAction(new EventHandler<ActionEvent>() {

            @Override
            public void handle(ActionEvent arg0) {
                try {
                    client.setCredit(serverConnection.getServices().getCredit(client.getId()));     // update the infos on gui
                } catch (RemoteException e) {
                    e.printStackTrace();
                }
                creditInfo.setText(client.getCredit().toString());
                currStage.close();
            }

        });

        Scene myDialogScene = new Scene(VBoxBuilder.create()
                .children(new Text("You have successfully load!\n"), okButton)
                .alignment(Pos.CENTER)
                .padding(new Insets(10))
                .build());

        currStage.setScene(myDialogScene);
        currStage.show();
    }
}
