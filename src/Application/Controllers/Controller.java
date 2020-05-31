package Application.Controllers;

import Application.Data.Gestionnaire_De_Connection;
import Application.Models.ClassementViewModel;
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
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
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

    private List<ClassementViewModel> persons;

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
    private TableView tblViewCLassement;

    @FXML
    private Pane panelGestionEtudiant;

    @FXML
    private Label matiereLbl;

    @FXML
    private Label userLBL;

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
    private ImageView imgUser;

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
//                System.out.println("etudiant has rows");

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
    private void statistique_Etudiant_Click(ActionEvent event) {

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
                    "and n.etud" +
                    "iant_ = '" + Gestionnaire_De_Connection.etudiant_connecte + "'");
            dataReader.next();
            int notePositive = dataReader.getInt("notePositive");

            dataReader = sqlCommand.executeQuery("select count(n.Valeur_Note) as noteNegative\n" +
                    "from ETUDIANT et inner join GROUPE grp on et.groupe# = grp.id_groupe \n" +
                    "\t\t\t\tinner join ENSEIGNEMENT en on en.groupe# = grp.id_groupe\n" +
                    "\t\t\t\tinner join MATIERE ma on ma.id_matiere = en.matiere#\n" +
                    "\t\t\t\tinner join NOTE n on n.matiere# = ma.id_matiere\n" +
                    "where n.Valeur_Note < 10" +
                    "and n.etudiant_ = '" + Gestionnaire_De_Connection.etudiant_connecte + "'");
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
                    "where n.etudiant_ = '" + Gestionnaire_De_Connection.etudiant_connecte + "'" +
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
            series2.getData().add(new XYChart.Data<>("", 10.0));
            barchartEtudiant.getData().add(series2);

            XYChart.Series<String, Number> series3 = new XYChart.Series<>();
            series3.setName("Administration System");
            series3.getData().add(new XYChart.Data<>("", 11.0));
            barchartEtudiant.getData().add(series3);

            XYChart.Series<String, Number> series4 = new XYChart.Series<>();
            series4.setName("Docker");
            series4.getData().add(new XYChart.Data<>("", 15.0));
            barchartEtudiant.getData().add(series4);

            XYChart.Series<String, Number> series5 = new XYChart.Series<>();
            series5.setName("WPF");
            series5.getData().add(new XYChart.Data<>("", 18.0));
            barchartEtudiant.getData().add(series5);

            XYChart.Series<String, Number> series6 = new XYChart.Series<>();
            series6.setName("Art of speaking");
            series6.getData().add(new XYChart.Data<>("", 11.0));
            barchartEtudiant.getData().add(series6);
            //****************************************************************************
            //*************** Remplissage du tableau**************************
            dataReader = sqlCommand.executeQuery("select etd.nom as nom_complet , sum(n.Valeur_Note) / count(*) as moyenne_etudiant\n" +
                    "from NOTE n inner join MATIERE ma on n.matiere# = ma.id_matiere\n" +
                    "\t\t\t--inner join ENSEIGNEMENT en on en.matiere# = ma.id_matiere\n" +
                    "\t\t\t--inner join GROUPE grp on grp.id_groupe = en.groupe#\n" +
                    "\t\t\t--inner join ETUDIANT etd on etd.groupe# = grp.id_groupe\n" +
                    "\t\t\tinner join ETUDIANT etd  on etd.code_massar = n.etudiant_ \n" +
                    "group by etd.nom, ma.LBL_Matiere\n");
            int classement = 1;
            while (dataReader.next()) {
                ClassementViewModel person = new ClassementViewModel(dataReader.getString("nom_complet"), dataReader.getDouble("moyenne_etudiant"), classement);
                this.FillData();
                tblViewCLassement.getItems().addAll(persons);
                classement++;
                break;
            }
            //****************************************************************************
        } catch (SQLException e) {
            e.printStackTrace();
        }

        panelStatistiques.toFront();
        btnClose.toFront();
        btnMinimize.toFront();
    }

    private void FillData() {
        // Pour le test/prototypage
        persons = new ArrayList<>();
        persons.add(new ClassementViewModel("Nourredine Yagoubi", 16.75, 1));
        persons.add(new ClassementViewModel("Hicham Oussama Saffih", 15.9, 2));
        persons.add(new ClassementViewModel("Amina Essirioui", 15.82, 3));
        persons.add(new ClassementViewModel("Nisrine Hadiwi", 15.70, 4));
        persons.add(new ClassementViewModel("Ahmed tizniti", 14.20, 5));
        persons.add(new ClassementViewModel("Nihal Bkkay", 14.00, 6));
        persons.add(new ClassementViewModel("Mustafa nourawi", 13.95, 7));
        persons.add(new ClassementViewModel("Roqaya Aamari", 13.93, 8));
        persons.add(new ClassementViewModel("Adam abdlawi", 13.00, 9));
        persons.add(new ClassementViewModel("Ilyass Bekkal", 12.77, 10));
        persons.add(new ClassementViewModel("Imane LLhlo", 11.37, 11));
        persons.add(new ClassementViewModel("Noura Blkhir", 11.28, 12));
        persons.add(new ClassementViewModel("Anass Boukhari", 10.9, 13));
        persons.add(new ClassementViewModel("Mouslim saadani", 9.50, 14));
    }

    private void ChangerCouleur(ObservableList<PieChart.Data> pieChartData, String... pieColors) {
        int i = 0;
        for (PieChart.Data data : pieChartData) {
            data.getNode().setStyle("-fx-pie-color: " + pieColors[i % pieColors.length] + ";");
            i++;
        }
    }

    @FXML
    private void btnMenuGestion_click(ActionEvent e) {
        panelGestionEtudiant.toFront();
        btnClose.toFront();
        btnMinimize.toFront();
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        panelNotes.toFront();
        btnNotes_click();
        System.out.println(Gestionnaire_De_Connection.etudiant_connecte);
        System.out.println(Gestionnaire_De_Connection.personnel_connecte);
        System.out.println(Gestionnaire_De_Connection.prof_connecte);
        System.out.println(Gestionnaire_De_Connection.NomConnecte);
        userLBL.setText(Gestionnaire_De_Connection.NomConnecte);
        Gestionnaire_De_Connection gestionnaire_de_connection = new Gestionnaire_De_Connection();
        Connection connection = gestionnaire_de_connection.getConnection();
        String NomComplet[] = Gestionnaire_De_Connection.NomConnecte.split(" ");
        String Nom = NomComplet[0];
        String Prenom = NomComplet[1];
        try {
            Statement statement =  connection.createStatement();
            ResultSet resultSet = statement.executeQuery("SELECT * from Personnel WHERE nom_personnel = '" + Nom + "' and prenom_personnel = '" + Prenom + "'");
            if(resultSet.next()){
                imgUser.setVisible(true);
            }
            else imgUser.setVisible(false);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        //todo : ne pas supprimer ce code hhhh
        //connection avec BD (MSSQL JDBC)
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
