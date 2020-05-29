package Application.Controllers;

import Application.Data.Gestionnaire_De_Connection;
import javafx.beans.Observable;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.chart.PieChart;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.TableView;
import javafx.scene.image.Image;
import javafx.scene.layout.*;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

import java.sql.*;

import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;

public class Controller implements Initializable {

    @FXML
    private AnchorPane anchorPane;
    Stage stage;

    @FXML
    private Pane panelStatistiquesPersonnel;

    @FXML
    private Pane panelNotesProf;

    @FXML
    private Pane panelNotes;

    @FXML
    private Pane panelStatistiques;

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
    private Button btnLogOut;

    @FXML
    private Button btnClose;

    @FXML
    private Button btnMinimize;

    @FXML
    private TableView tblView;

    @FXML
    private Pane panelEtudiant;

    @FXML
    private PieChart pieChart;

    @FXML
    private PieChart pieChartPersonnel;

    @FXML
    private Label matiereLbl;

    @FXML
    private Label CoeffLbl;

    @FXML
    private Label ProfLbl;

    @FXML
    private Label Cntrol1;
    @FXML
    private Label Cntrol2;
    @FXML
    private Label Cntrol3;
    @FXML
    private Label MyenneLbl;

    @FXML
    private ComboBox CB_Matiere;


    @FXML
    public void logOut_Click() throws Exception {
        Parent root = FXMLLoader.load(getClass().getResource("../Views/Login.fxml"));
        Stage stage = new Stage();
        stage.setScene(new Scene(root));
        stage.getIcons().add(new Image(getClass().getResourceAsStream("../../resources/images/LoginIcons/icons8_Google_Wallet_50px.png")));
        stage.initStyle(StageStyle.UNDECORATED);
        Stage stage2 = (Stage) btnLogOut.getScene().getWindow();
        stage2.close();
        stage.show();

    }

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
    public void GestionNote_Click(ActionEvent event) {
        panelNotesProf.toFront();
        btnClose.toFront();
        btnMinimize.toFront();
    }

    @FXML
    public void btnNotes_click() {
        //todo
        Gestionnaire_De_Connection gestionnaire_de_connection = new Gestionnaire_De_Connection();
        try {
            Connection connection = gestionnaire_de_connection.getConnection();
            Statement stmMatiere = connection.createStatement();
            ResultSet rss = stmMatiere.executeQuery("select * from matiere");
            ObservableList mat = FXCollections.observableArrayList();
            while (rss.next()) {
                rss.getRow();
                String matieres = rss.getString(2);
                mat.add(matieres);
            }
            CB_Matiere.setItems(mat);

            Statement statement = connection.createStatement();
            String query = "select MATIERE.LBL_Matiere, MATIERE.Coeff," +
                    " concat(PROFESSEUR.Nom, ' ' ,PROFESSEUR.Prenom ) as Nom_Professeur, NOTE.Valeur_Note\n" +
                    "from ETUDIANT join groupe on ETUDIANT.groupe# = groupe.id_groupe\n" +
                    "join ENSEIGNEMENT on GROUPE.id_groupe = ENSEIGNEMENT.groupe#\n" +
                    "join MATIERE on ENSEIGNEMENT.matiere# = MATIERE.id_matiere\n" +
                    "join PROFESSEUR on PROFESSEUR.Code_Pro_Nationnal = ENSEIGNEMENT.professeur#\n" +
                    "join NOTE on MATIERE.id_matiere = NOTE.matiere#\n" +
                    "where ETUDIANT.code_massar = 'H1'";

            String queryNotes = "select NOTE.Valeur_Note as notes\n" +
                    "from ETUDIANT join groupe on ETUDIANT.groupe# = groupe.id_groupe\n" +
                    "join ENSEIGNEMENT on GROUPE.id_groupe = ENSEIGNEMENT.groupe#\n" +
                    "join MATIERE on ENSEIGNEMENT.matiere# = MATIERE.id_matiere\n" +
                    "join PROFESSEUR on PROFESSEUR.Code_Pro_Nationnal = ENSEIGNEMENT.professeur#\n" +
                    "join NOTE on MATIERE.id_matiere = NOTE.matiere#\n" +
                    "where ETUDIANT.code_massar = 'H1'";


            Statement statement1 = connection.createStatement();
            ResultSet resultSet = statement.executeQuery(query);
            ResultSet rs = statement1.executeQuery(queryNotes);
            ObservableList<String> data = FXCollections.observableArrayList();
            while (resultSet.next() && rs.next()) {
                System.out.println("etudiant has rows");

                matiereLbl.setText(resultSet.getString(1));
                CoeffLbl.setText(String.valueOf(resultSet.getInt(2)));
                ProfLbl.setText(resultSet.getString(3));

                rs.getRow();
                String notes = String.valueOf(rs.getDouble("notes"));
                data.add(notes);
            }
            Cntrol1.setText(data.get(0));
            Cntrol2.setText(data.get(1));
            Cntrol3.setText(data.get(2));

            Double moyenne = ((Double.valueOf(Cntrol1.getText()) + Double.valueOf(Cntrol2.getText()) + Double.valueOf(Cntrol3.getText())) / 3);
            MyenneLbl.setText(String.valueOf(moyenne));

        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        panelNotes.toFront();
        btnClose.toFront();
        btnMinimize.toFront();
    }


    @FXML
    public void statistiquePersonnel_Click(ActionEvent event) {
        ObservableList<PieChart.Data> pieChartDataP = FXCollections.observableArrayList(
                new PieChart.Data("Titizz drari", 30),
                new PieChart.Data("Titizz bnat", 70));
        pieChartPersonnel.setData(pieChartDataP);
        pieChartPersonnel.setTitle("titiz SupMti");
        pieChartPersonnel.setClockwise(true);
        pieChartPersonnel.setLabelsVisible(true);
        pieChartPersonnel.setLabelLineLength(50);
        pieChartPersonnel.setStartAngle(180);
        this.ChangerCouleur(
                pieChartDataP,
                "red", "blue"
        );
        panelStatistiquesPersonnel.toFront();
        btnClose.toFront();
        btnMinimize.toFront();
    }

    @FXML
    private void statistique_Click(ActionEvent event) {

        Gestionnaire_De_Connection connectionClass = new Gestionnaire_De_Connection();
        Connection connection = connectionClass.getConnection();
        ResultSet dataReader;
        try {
            Statement sqlCommand = connection.createStatement();
            dataReader = sqlCommand.executeQuery("select count(n.Valeur_Note) as notePositive\n" +
                    "from ETUDIANT et inner join GROUPE grp on et.groupe# = grp.id_groupe \n" +
                    "\t\t\t\tinner join ENSEIGNEMENT en on en.groupe# = grp.id_groupe\n" +
                    "\t\t\t\tinner join MATIERE ma on ma.id_matiere = en.matiere#\n" +
                    "\t\t\t\tinner join NOTE n on n.matiere# = ma.id_matiere\n" +
                    "where n.Valeur_Note >= 10");
            dataReader.next();
            int notePositive = dataReader.getInt("notePositive");

            dataReader = sqlCommand.executeQuery("select count(n.Valeur_Note) as noteNegative\n" +
                    "from ETUDIANT et inner join GROUPE grp on et.groupe# = grp.id_groupe \n" +
                    "\t\t\t\tinner join ENSEIGNEMENT en on en.groupe# = grp.id_groupe\n" +
                    "\t\t\t\tinner join MATIERE ma on ma.id_matiere = en.matiere#\n" +
                    "\t\t\t\tinner join NOTE n on n.matiere# = ma.id_matiere\n" +
                    "where n.Valeur_Note < 10");
            dataReader.next();
            int noteNegative = dataReader.getInt("noteNegative");


            ObservableList<PieChart.Data> pieChartData = FXCollections.observableArrayList(
                    new PieChart.Data("Notes négatives", noteNegative),
                    new PieChart.Data("Notes positives", notePositive)
            );
            pieChart.setData(pieChartData);
            pieChart.setTitle("Valeurs des mes notes");
            pieChart.setClockwise(true);
            pieChart.setLabelsVisible(true);
            pieChart.setLabelLineLength(50);
            pieChart.setStartAngle(180);
            this.ChangerCouleur(
                    pieChartData,
                    "#CB5B5A", "#EABD5D"
            );
        } catch (SQLException e) {
            e.printStackTrace();
        }

        panelStatistiques.toFront();
        btnClose.toFront();
        btnMinimize.toFront();
    }

    private void ChangerCouleur(ObservableList<PieChart.Data> pieChartData, String... pieColors) {
        int i = 0;
        for (PieChart.Data data : pieChartData) {
            data.getNode().setStyle("-fx-pie-color: " + pieColors[i % pieColors.length] + ";");
            i++;
        }
    }

    @FXML
    private void btnGestion_click(ActionEvent e) {
        panelEtudiant.toFront();
        btnClose.toFront();
        btnMinimize.toFront();
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        System.out.println("testing inialise");
        panelNotes.toFront();
        btnNotes_click();
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
