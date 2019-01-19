package sample;

import graph.Edge;
import graph.Graph;
import graph.MatrixGraph;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import javafx.util.converter.DoubleStringConverter;
import javafx.util.converter.IntegerStringConverter;

import java.net.URL;
import java.rmi.RemoteException;
import java.util.*;
import java.util.function.UnaryOperator;

public class Controller implements Initializable {

    @FXML
    VBox source;

    @FXML
    VBox dest;

    @FXML
    VBox weight;

    @FXML
    ComboBox<String> serviceCombo;

    @FXML
    TextArea resultArea, edges, vertices;

    @FXML
    TextField numE;

    @FXML
    Label plusEdge, userInfo, creditInfo, cardInfo;

    private ServerConnection serverConnection;
    private Client currClient;
    private int edgeNum;
    private UnaryOperator<TextFormatter.Change> doubleFilter;

    public void setServerConnection(ServerConnection connection) {
        serverConnection = connection;
        currClient = connection.getCurrClient();

        userInfo.setText(currClient.getName() + "  " + currClient.getSurname());
        creditInfo.setText(currClient.getCredit().toString());
        cardInfo.setText(currClient.getCardNo().toString());
    }


    @Override
    public void initialize(URL location, ResourceBundle resources) {

        edgeNum = 0;

        ObservableList<String> suits = FXCollections.observableArrayList();
        suits.setAll("Minimum Spannig Tree", "Incidence Matrix");
        serviceCombo.setItems(suits);

        UnaryOperator<TextFormatter.Change> integerFilter = change -> { // set input Format  with regular expression
            String newText = change.getControlNewText();
            if (newText.matches("-?([1-9][0-9]*)?")) {
                return change;
            }
            return null;
        };

        numE.setTextFormatter(new TextFormatter<Integer>(new IntegerStringConverter(),
                0,
                integerFilter));

        numE.textProperty().addListener((observable, oldValue, newValue) -> { // change the row number of table by changes to edge number
            plusEdge.setText("");
            edgeNum = 0;
            if (newValue.equals(""))
                return;

            source.getChildren().clear();
            dest.getChildren().clear();
            weight.getChildren().clear();

            int numEdge = Integer.parseInt(newValue);
            for (int i = 0; i < numEdge; i++) { // add new row to table
                TextField textField = new TextField();
                textField.setTextFormatter(new TextFormatter<Double>(new DoubleStringConverter(),
                        0.0,
                        doubleFilter));

                source.getChildren().add(0, new TextField());
                dest.getChildren().add(0, new TextField());
                weight.getChildren().add(0, textField);
            }
        });

        doubleFilter = change -> {  // set input Format with regular expression
            String newText = change.getControlNewText();
            if (newText.matches("-?(([1-9][0-9]*)|0)?(\\.[0-9]*)?")) {
                return change;
            }
            return null;
        };
    }

    @FXML
    public void run(ActionEvent event) throws RemoteException {

        int numEdge = Integer.parseInt(numE.getText()) + edgeNum;
        if (numEdge < 1) {
            printErr("Edge Number should be greater than 0");
            return;
        }

        Set<String> vertexes = new HashSet<>();
        List<Edge> edgeList = new ArrayList<>();
        for (int i = 0; i < numEdge; i++) {
            String src = ((TextField) source.getChildren().get(i)).getText().toLowerCase();
            String dst = ((TextField) dest.getChildren().get(i)).getText().toLowerCase();
            double weigt = Double.parseDouble(((TextField) weight.getChildren().get(i)).getText());

            if (src.equals("") || dst.equals("")) {
                printErr("Enter all edge informations !!");
                return;
            } else if (weigt == 0) {
                printErr("Weight can't be 0!!");
                return;
            } else {
                if (!vertexes.contains(src))
                    vertexes.add(src);
                if (!vertexes.contains(dst))
                    vertexes.add(dst);
                edgeList.add(new Edge(src, dst, weigt));
            }
        }
        Graph graph = new MatrixGraph(vertexes.size());       // create graph
        edgeList.forEach(edge -> graph.insert(edge));

        resultArea.setStyle("-fx-text-fill: #000000; ");

        String serviceType = serviceCombo.getValue();
        if (serviceType == null) {
            printErr("Select the Service !!");
            return;
        } else if (serviceType.equals("Minimum Spannig Tree")) {    // minimum spanning tree service
            int credit = currClient.getCredit();
            if (credit < 10) {
                printErr("you don't have enough credit for \nminSpanningTree service !");
                return;
            }

            ArrayList<Edge> tree = serverConnection.getServices().minSpanningTree(graph, 0, currClient.getId());
            String result = "";
            double totalWeight = 0;
            for (Edge el :
                    tree) {
                result += el.getSource() + " -> " + el.getDest() + " weight : "+ el.getWeight() + "\n";
                totalWeight += el.getWeight();
            }
            result += "Total weight : "+ totalWeight + "\n";
            resultArea.setText(result);
        } else {                    // incidence matrix service
            int credit = currClient.getCredit();
            if (credit < 5) {
                printErr("you don't have enough credit for \ngetIncidenceMatrix service !");
                return;
            }
            int[][] matrix = serverConnection.getServices().getIncidenceMatrix(graph, currClient.getId());  // run process on server

            // shows result on gui
            String result = "";
            for (int i = 0; i < matrix[0].length; i++) {
                result += String.format("%3d", (i + 1));
            }
            edges.setText(result);
            result = "";
            for (int i = 0; i < graph.getNumV(); i++) {
                for (Map.Entry<String, Integer> entry : graph.getVertexes().entrySet()) {
                    if (Objects.equals(i, entry.getValue())) {
                        result += entry.getKey() + "\n";
                        break;
                    }
                }
            }
            vertices.setText(result);
            result = "";
            for (int i = 0; i < matrix.length; i++) {
                for (int j = 0; j < matrix[0].length; j++) {
                    result += String.format("%3d", matrix[i][j]);
                }
                result += "\n";
            }
            resultArea.setText(result);
        }
        currClient.setCredit(serverConnection.getServices().getCredit(currClient.getId())); // set new balance
        creditInfo.setText(currClient.getCredit().toString());  // show new balance on gui
    }

    @FXML
    void openLoadBalance() throws Exception {
        Stage newWindow = new Stage();

        FXMLLoader loader = new FXMLLoader(getClass().getResource("gui_fxml/loadbalance.fxml"));
        Parent root = loader.load();

        BalanceController controller = loader.<BalanceController>getController();
        controller.setCommonComponent(serverConnection, creditInfo); // send to next Controller

        newWindow.setTitle("Load Balance");
        newWindow.setScene(new Scene(root, 600, 400));

        newWindow.show();
    }

    @FXML
    public void addNewRow(ActionEvent event) {

        TextField textField = new TextField();
        textField.setTextFormatter(new TextFormatter<Double>(new DoubleStringConverter(),
                0.0,
                doubleFilter));


        Integer numEdge = Integer.parseInt(numE.getText());

        String plus = plusEdge.getText();
        if (plus.equals("")) {
            plusEdge.setText("+ " + 1);
            edgeNum += 1;
        } else {
            String[] tokens = plus.split(" ");
            int num = Integer.parseInt(tokens[1]);
            plusEdge.setText("+ " + (num + 1));
            numEdge += num;
            edgeNum += 1;
        }

        source.getChildren().add(numEdge, new TextField());
        dest.getChildren().add(numEdge, new TextField());
        weight.getChildren().add(numEdge, textField);
    }

    /**
     * @param err message that will be showed
     */
    private void printErr(String err) {
        resultArea.setStyle("-fx-text-fill: #ff0000; ");
        resultArea.setText(err);
    }
}
