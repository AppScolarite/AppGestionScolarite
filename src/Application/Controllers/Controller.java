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
import javafx.scene.chart.*;
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
import java.util.Arrays;
import java.util.List;
import java.util.ResourceBundle;

public class Controller implements Initializable {

    @FXML
    private AnchorPane anchorPane;
    Stage stage;

    //*********************statistiques du personnel*******
    @FXML
    private Pane panelStatistiquesPersonnel;

    @FXML
    private PieChart pieChartPersonnel;

    //*****************************************************

    //*********************statistiques de l'etudiant******
    @FXML
    private Pane panelStatistiques;

    @FXML
    private PieChart pieChartEtudiant;

    @FXML
    private BarChart barchartEtudiant;

    //*****************************************************
    @FXML
    private Pane panelNotesProf;

    @FXML
    private Pane panelNotes;

    //****************** Menu ************************
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
    //*****************************************************

    @FXML
    private TableView tblView;

    @FXML
    private Pane panelGestionEtudiant;

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
            /// **************** Remplissage pieChartEtudiant ******************************************************

            dataReader = sqlCommand.executeQuery("select count(n.Valeur_Note) as notePositive\n" +
                    "from ETUDIANT et inner join GROUPE grp on et.groupe# = grp.id_groupe \n" +
                    "\t\t\t\tinner join ENSEIGNEMENT en on en.groupe# = grp.id_groupe\n" +
                    "\t\t\t\tinner join MATIERE ma on ma.id_matiere = en.matiere#\n" +
                    "\t\t\t\tinner join NOTE n on n.matiere# = ma.id_matiere\n" +
                    "where n.Valeur_Note >= 10" +
                    "and n.etudiant_ = '" + Gestionnaire_De_Connection.user_connecte + "'");
            dataReader.next();
            int notePositive = dataReader.getInt("notePositive");

            dataReader = sqlCommand.executeQuery("select count(n.Valeur_Note) as noteNegative\n" +
                    "from ETUDIANT et inner join GROUPE grp on et.groupe# = grp.id_groupe \n" +
                    "\t\t\t\tinner join ENSEIGNEMENT en on en.groupe# = grp.id_groupe\n" +
                    "\t\t\t\tinner join MATIERE ma on ma.id_matiere = en.matiere#\n" +
                    "\t\t\t\tinner join NOTE n on n.matiere# = ma.id_matiere\n" +
                    "where n.Valeur_Note < 10" +
                    "and n.etudiant_ = '" + Gestionnaire_De_Connection.user_connecte + "'");
            dataReader.next();
            int noteNegative = dataReader.getInt("noteNegative");


            ObservableList<PieChart.Data> pieChartData = FXCollections.observableArrayList(
                    new PieChart.Data("Notes négatives", noteNegative),
                    new PieChart.Data("Notes positives", notePositive)
            );
            pieChartEtudiant.setData(pieChartData);
            pieChartEtudiant.setTitle("Mes notes");
            pieChartEtudiant.setClockwise(true);
            pieChartEtudiant.setLabelsVisible(true);
            pieChartEtudiant.setLabelLineLength(50);
            pieChartEtudiant.setStartAngle(180);
            this.ChangerCouleur(
                    pieChartData,
                    "#CB5B5A", "#EABD5D"
            );
            //********************************************************************************************

            //************remplissage barChart************************************************************
            //Defining the axes
            CategoryAxis xAxis = new CategoryAxis();
            xAxis.setLabel("Matiére");

            NumberAxis yAxis = new NumberAxis();
            yAxis.setLabel("Moyenne");

            barchartEtudiant.setTitle("Mes matiéres & moyennes");
            barchartEtudiant.setCategoryGap(3);

            dataReader = sqlCommand.executeQuery("select ma.LBL_Matiere as matiere, sum(n.Valeur_Note) / count(*) as moyenne\n" +
                    "from NOTE n inner join MATIERE ma on n.matiere# = ma.id_matiere\n" +
                    "where n.etudiant_ = '" + Gestionnaire_De_Connection.user_connecte + "'" +
                    "group by ma.LBL_Matiere");
            while (dataReader.next()) {
                String matiere = dataReader.getString("matiere");
                double moyenne = dataReader.getDouble("moyenne");
                XYChart.Series<String, Number> serie = new XYChart.Series<>();
                serie.setName(matiere);
                serie.getData().add(new XYChart.Data<>("", moyenne));
                barchartEtudiant.getData().add(serie);
            }

            //******************Dummy Data*******************************************
            XYChart.Series<String, Number> series2 = new XYChart.Series<>();
            series2.setName("Java");
            series2.getData().add(new XYChart.Data<>("", 4.0));
            barchartEtudiant.getData().add(series2);

            XYChart.Series<String, Number> series3 = new XYChart.Series<>();
            series3.setName("Administration System");
            series3.getData().add(new XYChart.Data<>("", 6.0));
            barchartEtudiant.getData().add(series3);

            XYChart.Series<String, Number> series4 = new XYChart.Series<>();
            series4.setName("Docker");
            series4.getData().add(new XYChart.Data<>("", 6.0));
            barchartEtudiant.getData().add(series4);

            XYChart.Series<String, Number> series5 = new XYChart.Series<>();
            series5.setName("WPF");
            series5.getData().add(new XYChart.Data<>("", 6.0));
            barchartEtudiant.getData().add(series5);

            XYChart.Series<String, Number> series6 = new XYChart.Series<>();
            series6.setName("Art of speaking");
            series6.getData().add(new XYChart.Data<>("", 6.0));
            barchartEtudiant.getData().add(series6);
            //********************************************************************************************

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
        panelGestionEtudiant.toFront();
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
}
