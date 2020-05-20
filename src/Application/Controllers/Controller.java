package Application.Controllers;

import Application.Data.Gestionnaire_De_Connection;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.chart.PieChart;
import javafx.scene.control.Button;
import javafx.scene.control.TableView;
import javafx.scene.layout.*;
import javafx.stage.Stage;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import java.net.URL;
import java.util.ResourceBundle;

public class Controller implements Initializable {

    @FXML
    private AnchorPane anchorPane;
    Stage stage;

    @FXML
    private Pane panelNotes;

    @FXML
    private Button btnAccueil;

    @FXML
    private Button btnListes;

    @FXML
    private Button btnStatistiques;

    @FXML
    private Button btnGestion;

    @FXML
    private Button btnNotes;

    @FXML
    private TableView tblView;

    @FXML
    private BorderPane panelGestion;

    @FXML
    private PieChart pieChart;

    @FXML
    private void btnClose_Click(ActionEvent e) {
        stage = (Stage) ((Button) e.getSource()).getScene().getWindow();
        stage.close();
    }

    @FXML
    private void btnMinimize_Click(ActionEvent e) {
        stage = (Stage) ((Button) e.getSource()).getScene().getWindow();
        stage.setIconified(true);
    }

    @FXML
    private void btnNotes_click(ActionEvent e) {
        //todo
        //regler TAB design

        btnNotes.setStyle("-fx-background-color : #02030A");
        btnNotes.setStyle("-fx-background-radius :  30 0 0 30;");

        panelNotes.toFront();
    }

    @FXML
    private void btnGestion_click(ActionEvent e) {
        panelGestion.toFront();
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
//        System.out.println("testing inialise");
////        panelNotes.toFront();
//        Gestionnaire_De_Connection connectionClass = new Gestionnaire_De_Connection();
//        Connection connection = connectionClass.getConnection();
//        try {
//            Statement sqlCommand = connection.createStatement();
//            ResultSet dataReader = sqlCommand.executeQuery("select * from branche");
//
//            if (dataReader.next()) { // ze3ma if (exist())
//                System.out.println("cool");
//                dataReader.getRow();
//                String test = dataReader.getString("libelle_branche");
//                System.out.println(test);
//            } else {
//                System.out.println("not cool");
//            }
//        } catch (SQLException e) {
//            e.printStackTrace();
//        }
        ObservableList<PieChart.Data> pieChartData = FXCollections.observableArrayList(
                new PieChart.Data("Titizz drari", 20),
                new PieChart.Data("Titizz bnat", 80));
        pieChart.setData(pieChartData);
        pieChart.setTitle("titiz SupMti");
        pieChart.setClockwise(true);
        pieChart.setLabelsVisible(true);
        pieChart.setLabelLineLength(50);
        pieChart.setStartAngle(180);
        this.ChangerCouleur(
                pieChartData,
                "red", "blue"
        );
    }

    private void ChangerCouleur(ObservableList<PieChart.Data> pieChartData, String... pieColors) {
        int i = 0;
        for (PieChart.Data data : pieChartData) {
            data.getNode().setStyle("-fx-pie-color: " + pieColors[i % pieColors.length] + ";");
            i++;
        }
    }

//    @Override
//    public void initialize(URL location, ResourceBundle resources) {
//        Node[] nodes = new Node[10];
//        for (int i = 0; i < nodes.length; i++) {
//            try {
//
//                final int j = i;
//                nodes[i] = FXMLLoader.load(getClass().getResource("Item.fxml"));
//
//                //give the items some effect
//
//                nodes[i].setOnMouseEntered(event -> {
//                    nodes[j].setStyle("-fx-background-color : #0A0E3F");
//                });
//                nodes[i].setOnMouseExited(event -> {
//                    nodes[j].setStyle("-fx-background-color : #02030A");
//                });
//                pnItems.getChildren().add(nodes[i]);
//            } catch (IOException e) {
//                e.printStackTrace();
//            }
//        }
//
//    }


//    public void handleClicks(ActionEvent actionEvent) {
//        if (actionEvent.getSource() == btnCustomers) {
//            pnlCustomer.setStyle("-fx-background-color : #1620A1");
//            pnlCustomer.toFront();
//        }
//        if (actionEvent.getSource() == btnMenus) {
//            pnlMenus.setStyle("-fx-background-color : #53639F");
//            pnlMenus.toFront();
//        }
//        if (actionEvent.getSource() == btnOverview) {
//            pnlOverview.setStyle("-fx-background-color : #02030A");
//            pnlOverview.toFront();
//        }
//        if(actionEvent.getSource()==btnOrders)
//        {
//            pnlOrders.setStyle("-fx-background-color : #464F67");
//            pnlOrders.toFront();
//        }
//    }
}
