package sample;


import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.VBoxBuilder;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import javafx.util.converter.IntegerStringConverter;

import java.net.URL;
import java.util.ResourceBundle;
import java.util.function.UnaryOperator;

import static javafx.application.Platform.exit;


public class LoginController implements Initializable {

    @FXML
    private TextField username, regname, regsurname, reguname, cardNo;

    @FXML
    private PasswordField pass, regpass, regpass2;

    @FXML
    private Label loginStatus, regStatus;

    private ServerConnection serverConnection;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        serverConnection = new ServerConnection();
        try {
            serverConnection.connectServer();

        } catch (Exception e) {
            System.err.println(e.toString());
            e.printStackTrace();
            exit();
        }
    }


    @FXML
    public void logIn(ActionEvent event) throws Exception {

        String id = username.getText();
        if (id.equals("")) {
            loginStatus.setStyle("-fx-text-fill: #ff0000; ");
            loginStatus.setText("Enter username!!");
            return;
        }

        String password = pass.getText();
        if (password.equals("")) {
            loginStatus.setStyle("-fx-text-fill: #ff0000; ");
            loginStatus.setText("Enter password!!");
            return;
        }

        Client client = serverConnection.getServices().login(id, password); // login with server connection
        if (client != null) {

            Stage newWindow = (Stage) ((Node) event.getSource()).getScene().getWindow();

            FXMLLoader loader = new FXMLLoader(getClass().getResource("gui_fxml/client.fxml"));
            Parent root = loader.load();

            newWindow.setTitle("RMI Client");
            newWindow.setScene(new Scene(root, 800, 600));

            Controller controller = loader.<Controller>getController();
            serverConnection.setCurrClient(client);
            controller.setServerConnection(serverConnection);   // send info to next Controller

            newWindow.show();
        } else {
            loginStatus.setStyle("-fx-text-fill: #ff0000; ");
            loginStatus.setText("Login Failed!!");
            pass.setText("");
        }
    }

    @FXML
    public void register(ActionEvent event) throws Exception {
        Stage newWindow = new Stage();

        FXMLLoader loader = new FXMLLoader(getClass().getResource("gui_fxml/register.fxml"));
        Parent root = loader.load();

        LoginController loginController = loader.<LoginController>getController();
        loginController.setCardNo();

        newWindow.setTitle("Register");
        newWindow.setScene(new Scene(root, 600, 400));

        newWindow.show();
    }

    private void setCardNo(){
        UnaryOperator<TextFormatter.Change> integerFilter = change -> {   // set input Format with regular expression
            String newText = change.getControlNewText();
            if (newText.matches("-?([1-9][0-9]*)?")) {
                return change;
            }
            return null;
        };

        this.cardNo.setTextFormatter(new TextFormatter<Integer>(new IntegerStringConverter(), // force to get integer
                0,
                integerFilter));
    }

    @FXML
    public void doRegister(ActionEvent event) throws Exception {
        String name = regname.getText();
        String surname = regsurname.getText();
        String username = reguname.getText();
        String pass = regpass.getText();
        String pass2 = regpass2.getText();
        String cardno = cardNo.getText();

        if (name.equals("") || surname.equals("") || username.equals("") || pass.equals("") || pass2.equals("") || cardno.equals("") ) {
            regStatus.setStyle("-fx-text-fill: #ff0000; ");
            regStatus.setText("Fill all informations");
            return;
        }

        if (Integer.parseInt(cardno) == 0){
            regStatus.setStyle("-fx-text-fill: #ff0000; ");
            regStatus.setText("Enter card no!");
            return;
        }

        if (!pass.equals(pass2)) {
            regStatus.setStyle("-fx-text-fill: #ff0000; ");
            regStatus.setText("Passwords doesn't match!");
            return;
        }

        boolean result = serverConnection.getServices().register(username, pass, name, surname, Integer.parseInt(cardno)); // register the new user with server

        if (result) {   // if succes
            Stage currStage = (Stage) ((Node) event.getSource()).getScene().getWindow();
            Button okButton = new Button("OK");

            okButton.setOnAction(new EventHandler<ActionEvent>() {

                @Override
                public void handle(ActionEvent arg0) {
                    currStage.close();
                }

            });

            Scene myDialogScene = new Scene(VBoxBuilder.create()
                    .children(new Text("You have successfully registered!\n"), okButton)    // info pop up
                    .alignment(Pos.CENTER)
                    .padding(new Insets(10))
                    .build());

            currStage.setScene(myDialogScene);
            currStage.show();
        } else {
            regStatus.setStyle("-fx-text-fill: #ff0000; ");
            regStatus.setText("This username already exits!   \nPlease change");
            return;
        }
    }
}
