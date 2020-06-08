package Application.Controllers;

import Application.Data.Gestionnaire_De_Connection;
import Application.Models.ClassementViewModel;
import Application.Models.GestionEtudiantsViewModel;
import Application.Models.GestionNotesViewModel;
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
import javafx.scene.control.cell.TextFieldTableCell;
import javafx.scene.effect.DropShadow;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.InputMethodEvent;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

import java.io.File;
import java.io.IOException;
import java.net.MalformedURLException;
import java.sql.*;

import java.net.URL;
import java.text.DecimalFormat;
import java.util.*;
import java.util.Date;

public class Controller implements Initializable {

    @FXML
    public VBox VboxMenu;

    private Stage stage;

    //*********************statistiques du personnel*******
    @FXML
    private Pane panelStatistiquesPersonnel;

    @FXML
    private PieChart pieChartPersonnel;

    @FXML
    public BarChart barChartPersonnel;
    //*****************************************************

    //*************** Alert control/Exam ******************

    @FXML
    private ComboBox cb_groupesAlert;

    @FXML
    private DatePicker date_control;

    @FXML
    private RadioButton radio_modif;

    @FXML
    private RadioButton radio_fix;

    @FXML
    private TextField heureDebut_controle;

    @FXML
    private TextField heureFin_controle;

    @FXML
    private TextArea txt_description;

    @FXML
    private Pane panelAlert;

    //*****************************************************

    //*********************statistiques de l'etudiant******
    @FXML
    private Pane panelStatistiques;

    @FXML
    private PieChart pieChartEtudiant;

    @FXML
    private BarChart barchartEtudiant;

    @FXML
    private TableView tblViewCLassement;

    private List<ClassementViewModel> persons;

    //*****************************************************

    //************* Panels ********************************

    @FXML
    private Pane panelNotes;

    //*****************************************************

    //**************** Accueil ****************************
    @FXML
    private ImageView img1_Accueil;

    @FXML
    private ImageView img2_Accueil;

    @FXML
    private ImageView img3_Accueil;

    @FXML
    private Label labelinfo_Accueil;

    @FXML
    private VBox vbox_messagerie;

    @FXML
    private Pane panelAccueil;

    @FXML
    private ScrollPane scroll;

    @FXML
    private Button btn_AjoutNews;

    @FXML
    private Button btn_refresh;
    //*****************************************************

    //**************** Gestion des étudiants **************
    @FXML
    private Pane panelEtudiant;

    @FXML
    private TableView tableView_GestionEtudiant;

    @FXML
    private ComboBox CB_grp_gestionEtudiant;

    @FXML
    private TableColumn<GestionEtudiantsViewModel, String> col_nom;

    @FXML
    public TableColumn<GestionEtudiantsViewModel, String> col_prenom;

    @FXML
    public TableColumn col_date;

    @FXML
    public TableColumn<GestionEtudiantsViewModel, String> col_mail;

    @FXML
    public TableColumn<GestionEtudiantsViewModel, String> col_tlph;

    @FXML
    public TableColumn<GestionEtudiantsViewModel, String> col_doublant;

    @FXML
    public TableColumn<GestionEtudiantsViewModel, String> col_adrs;

    @FXML
    public TableColumn<GestionEtudiantsViewModel, String> col_sexe;

    @FXML
    private MenuItem supprimer;

    //*****************************************************

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
    private Button btnStatistiquesetudiant;

    @FXML
    private Button btnNotes;

    @FXML
    private Button btnLogOut;

    @FXML
    private Button btnClose;

    @FXML
    private Button btnMinimize;

    @FXML
    private Button btnAlert;

    @FXML
    private Label userLBL;

    @FXML
    private ImageView imgUser;

    //*****************************************************

    //**************** Mes notes **********************
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
    //*****************************************************

    //******************** util ******************
    private Gestionnaire_De_Connection gestionnaire_de_connection = new Gestionnaire_De_Connection();

    //************************

    //***************** Gestion des notes *********

    @FXML
    private Pane panelNotesProf;

    @FXML
    public TableColumn<GestionNotesViewModel, String> col_codeMassar;

    @FXML
    public TableColumn<GestionNotesViewModel, String> col_nomComplet;

    @FXML
    public TableColumn<GestionNotesViewModel, String> col_cntrl1;

    @FXML
    public TableColumn<GestionNotesViewModel, String> col_cntrl2;

    @FXML
    public TableColumn<GestionNotesViewModel, String> col_cntrl3;

    @FXML
    public TableColumn<GestionNotesViewModel, String> col_moyenne;

    @FXML
    private TableView tableView_GestionNotes;

    @FXML
    private ComboBox CB_grp_gestionNotes;

    //**********************************************

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
        try {
            Connection connection = gestionnaire_de_connection.getConnection();
            Statement stmMatiere = connection.createStatement();
            ResultSet rss = stmMatiere.executeQuery("select * from matiere");
            ObservableList mat = FXCollections.observableArrayList();
            while (rss.next()) {
                String matieres = rss.getString(2);
                mat.add(matieres);
            }
            CB_Matiere.setItems(mat);
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        panelNotes.toFront();
        btnClose.toFront();
        btnMinimize.toFront();
    }

    @FXML
    public void panelGestionEtudiant_click(ActionEvent e) {
        panelEtudiant.toFront();
        btnClose.toFront();
        btnMinimize.toFront();
    }

    @FXML
    public void selectedItem(ActionEvent event) {
        int id_mat = CB_Matiere.getSelectionModel().getSelectedIndex() + 1;
        Connection connection = gestionnaire_de_connection.getConnection();
        try {
            Statement sqlCommand = connection.createStatement();
            ResultSet dataReader = sqlCommand.executeQuery("select MATIERE.LBL_Matiere as libelleMatiere, MATIERE.Coeff as Coeff," +
                    " concat(PROFESSEUR.Nom, ' ' ,PROFESSEUR.Prenom ) as Nom_Professeur\n" +
                    "from ETUDIANT join groupe on ETUDIANT.groupe# = groupe.id_groupe\n" +
                    "join ENSEIGNEMENT on GROUPE.id_groupe = ENSEIGNEMENT.groupe#\n" +
                    "join MATIERE on ENSEIGNEMENT.matiere# = MATIERE.id_matiere\n" +
                    "join PROFESSEUR on PROFESSEUR.Code_Pro_Nationnal = ENSEIGNEMENT.professeur#\n" +
                    "where ETUDIANT.code_massar = '" + Gestionnaire_De_Connection.etudiant_connecte + "'\n" +
                    "and MATIERE.id_matiere = " + id_mat);

            ObservableList<String> data = FXCollections.observableArrayList();
            Statement statementNotes = connection.createStatement();
            ResultSet resultSet = statementNotes.executeQuery(" select Valeur_Note from note where etudiant_ = '" + Gestionnaire_De_Connection.etudiant_connecte + "' and matiere# = " + id_mat);

            if (dataReader.next()) {
                String LBLMAtiere = dataReader.getString("libelleMatiere");
                String Coeff = dataReader.getString("Coeff");
                String Nom_Professeur = dataReader.getString("Nom_Professeur");

                matiereLbl.setText(LBLMAtiere);
                CoeffLbl.setText(Coeff);
                ProfLbl.setText(Nom_Professeur);

            } else {
                matiereLbl.setText("");
                CoeffLbl.setText("");
                ProfLbl.setText("");

                Cntrol1.setText("");
                Cntrol2.setText("");
                Cntrol3.setText("");
                MyenneLbl.setText("");
            }
            while (resultSet.next()) {
                String note = String.valueOf(resultSet.getDouble("Valeur_Note"));
                data.add(note);
            }
            Cntrol1.setText(data.get(0));
            Cntrol2.setText(data.get(1));
            Cntrol3.setText(data.get(2));
            Double moyenne = ((Double.valueOf(Cntrol1.getText()) + Double.valueOf(Cntrol2.getText()) + Double.valueOf(Cntrol3.getText())) / 3);
            MyenneLbl.setText(String.valueOf(moyenne));


        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @FXML
    private void gestionGroupe_click(ActionEvent e) {
        try {
            FXMLLoader loader = new FXMLLoader(new File("src/Application/Views/GestionGroupe.fxml").toURI().toURL());
            Parent root = (Parent) loader.load();

            Scene scene = new Scene(root);
            scene.setFill(Color.valueOf("transparent"));

            Stage stage = new Stage(StageStyle.TRANSPARENT);
            stage.setTitle("Ajout d'actualités");
            stage.setScene(scene);
            stage.show();
        } catch (IOException ex) {
            ex.printStackTrace();
        }
    }

    @FXML
    public void statistiquePersonnel_Click(ActionEvent event) {
        panelStatistiquesPersonnel.toFront();
        btnClose.toFront();
        btnMinimize.toFront();
    }

    @FXML
    private void btnStatistiquesetudiant_click(ActionEvent e) {
        panelStatistiques.toFront();
        btnClose.toFront();
        btnMinimize.toFront();
    }

    private void statistiqueEtudiant_Load() {
        Connection connection = gestionnaire_de_connection.getConnection();
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
    }

    private void statistiquesPersonnel_Load() {
        try {
            int nbrFemme, nbrHomme;
            nbrFemme = nbrHomme = 0;

            Connection sqlConnection = gestionnaire_de_connection.getConnection();
            Statement sqlCommand = sqlConnection.createStatement();
            ResultSet dataReader = sqlCommand.executeQuery("select count(*) as nbrHomme\n" +
                    "from etudiant \n" +
                    "where sexe = 'Homme'");
            if (dataReader.next())
                nbrHomme = dataReader.getInt("nbrHomme");

            sqlCommand = sqlConnection.createStatement();
            dataReader = sqlCommand.executeQuery("select count(*) as nbrFemme\n" +
                    "from etudiant etd \n" +
                    "where etd.sexe = 'Femme'");
            if (dataReader.next())
                nbrFemme = dataReader.getInt("nbrFemme");
            ObservableList<PieChart.Data> pieChartDataP = FXCollections.observableArrayList(
                    new PieChart.Data("Femme", nbrFemme),
                    new PieChart.Data("Homme", nbrHomme));
            pieChartPersonnel.setData(pieChartDataP);
            pieChartPersonnel.setTitle("Divérsité des genres");
            pieChartPersonnel.setClockwise(true);
            pieChartPersonnel.setLabelsVisible(true);
            pieChartPersonnel.setLabelLineLength(50);
            pieChartPersonnel.setStartAngle(180);

            sqlCommand = sqlConnection.createStatement();
            dataReader = sqlCommand.executeQuery("select grp.libelle_grp as Groupe , count(etd.code_massar) as nbrEffectif\n" +
                    "from etudiant etd inner join groupe grp on etd.groupe# = grp.id_groupe\n" +
                    "group by grp.libelle_grp");
            String nomGroupe;
            int nbrEffectif;
            while (dataReader.next()) {
                nomGroupe = dataReader.getString("Groupe");
                nbrEffectif = dataReader.getInt("nbrEffectif");
                XYChart.Series<String, Number> serie = new XYChart.Series<>();
                serie.setName(nomGroupe);
                serie.getData().add(new XYChart.Data<>("", nbrEffectif));
                barChartPersonnel.getData().add(serie);
            }
            //*********** Dummy data (pour test & prototypage)
            XYChart.Series<String, Number> serie = new XYChart.Series<>();
            serie.setName("com 1");
            serie.getData().add(new XYChart.Data<>("", 5));
            barChartPersonnel.getData().add(serie);

            serie = new XYChart.Series<>();
            serie.setName("mtd 4");
            serie.getData().add(new XYChart.Data<>("", 8));
            barChartPersonnel.getData().add(serie);

            //************************************************
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @FXML
    private void btnAccueil_click() {
        Accueil_Load();
        panelAccueil.toFront();
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
    private void supprimer_click() {
        GestionEtudiantsViewModel etudiant = (GestionEtudiantsViewModel) tableView_GestionEtudiant.getSelectionModel().getSelectedItem();
        if (etudiant == null) {
            System.out.println("aucun etudiant a supprimer !");
            return;
        }
        Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
        alert.setTitle("Supression d'un étudiant !");
        alert.setContentText("Etes vous totalement sur de vouloir supprimer l'étudiant <" + etudiant.getCode_massar() + "-" + etudiant.getNom() + " " + etudiant.getPrenom() + "> ??\n Toutes ses notes seront ainsi supprimer !!");
        alert.getDialogPane().setMinHeight(Region.USE_PREF_SIZE);
        alert.setHeight(400);
        Optional<ButtonType> reponse = alert.showAndWait();
        if (reponse.get().equals(ButtonType.OK)) {
            try {
                Connection connection = gestionnaire_de_connection.getConnection();
                Statement sqlCommand = connection.createStatement();
                sqlCommand.execute
                        (
                                String.format
                                        (
                                                "delete from note where etudiant_ = '%s' ;" +
                                                        "delete from etudiant where code_massar = '%s';",
                                                etudiant.getCode_massar(), etudiant.getCode_massar()
                                        )
                        );
                tableView_GestionEtudiant.getItems().remove(tableView_GestionEtudiant.getSelectionModel().getSelectedItem());
                tableView_GestionEtudiant.refresh();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }

    private void PanelGestionEtudiant_Load() {
        try {
            Connection connection = gestionnaire_de_connection.getConnection();
            Statement stmGrp = connection.createStatement();
            ResultSet reader = stmGrp.executeQuery("select * from GROUPE");
            ObservableList groupes = FXCollections.observableArrayList();
            while (reader.next()) {
                String groupe = reader.getString("libelle_grp");
                groupes.add("Groupe " + groupe);
            }
            CB_grp_gestionEtudiant.setItems(groupes);
        } catch (SQLException sqlE) {
            sqlE.printStackTrace();
        }
        tableView_GestionEtudiant.setEditable(true);

        col_nom.setCellFactory(TextFieldTableCell.forTableColumn());
        col_nom.setOnEditCommit(e ->
                this.Update_Data("nom",
                        e.getNewValue(),
                        e.getTableView().getItems().get(e.getTablePosition().getRow()).getCode_massar()));

        col_prenom.setCellFactory(TextFieldTableCell.forTableColumn());
        col_prenom.setOnEditCommit(e ->
                this.Update_Data("prenom",
                        e.getNewValue(),
                        e.getTableView().getItems().get(e.getTablePosition().getRow()).getCode_massar()));

        col_mail.setCellFactory(TextFieldTableCell.forTableColumn());
        col_mail.setOnEditCommit(e ->
                this.Update_Data("email",
                        e.getNewValue(),
                        e.getTableView().getItems().get(e.getTablePosition().getRow()).getCode_massar()));

        col_tlph.setCellFactory(TextFieldTableCell.forTableColumn());
        col_tlph.setOnEditCommit(e ->
                this.Update_Data("telephone",
                        e.getNewValue(),
                        e.getTableView().getItems().get(e.getTablePosition().getRow()).getCode_massar()));

        col_doublant.setCellFactory(TextFieldTableCell.forTableColumn());
        col_doublant.setOnEditCommit(e ->
                this.Update_Data("a_deja_redouble",
                        e.getNewValue(),
                        e.getTableView().getItems().get(e.getTablePosition().getRow()).getCode_massar()));

        col_adrs.setCellFactory(TextFieldTableCell.forTableColumn());
        col_adrs.setOnEditCommit(e ->
                this.Update_Data("adresse",
                        e.getNewValue(),
                        e.getTableView().getItems().get(e.getTablePosition().getRow()).getCode_massar()));

        col_sexe.setCellFactory(TextFieldTableCell.forTableColumn());
        col_sexe.setOnEditCommit(e ->
                this.Update_Data("sexe",
                        e.getNewValue(),
                        e.getTableView().getItems().get(e.getTablePosition().getRow()).getCode_massar()));
    }

    private void Update_Data(String champs, String data, String id) {
        Object valeur = data;
        if (champs.equals("a_deja_redouble")) {
            valeur = data.equals("oui");
        }
        try {
            Connection connection = gestionnaire_de_connection.getConnection();
            Statement sqlCommand = connection.createStatement();
            sqlCommand.executeUpdate
                    (
                            String.format
                                    (
                                            "update etudiant set %s = '%s' where code_massar = '%s'",
                                            champs,
                                            valeur,
                                            id
                                    )
                    );
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    private void Form_Load() {
        userLBL.setText(Gestionnaire_De_Connection.nom_connecte);
        Gestionnaire_De_Connection gestionnaire_de_connection = new Gestionnaire_De_Connection();
        Connection connection = gestionnaire_de_connection.getConnection();
        String NomComplet[] = Gestionnaire_De_Connection.nom_connecte.split(" ");
        String Nom = NomComplet[0];
        String Prenom = NomComplet[1];
        try {
            Statement statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery("SELECT * from Personnel WHERE nom_personnel = '" + Nom + "' and prenom_personnel = '" + Prenom + "'");
            if (resultSet.next()) {
                imgUser.setVisible(true);
            } else imgUser.setVisible(false);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    private void PanelGestionNotes_Load() {
        try {
            Connection connection = gestionnaire_de_connection.getConnection();
            Statement sqlCommand = connection.createStatement();
            ResultSet dataReader = sqlCommand.executeQuery("select * from GROUPE");
            ObservableList groupes = FXCollections.observableArrayList();
            while (dataReader.next()) {
                String groupe = dataReader.getString("libelle_grp");
                groupes.add("Groupe " + groupe);
            }
            CB_grp_gestionNotes.setItems(groupes);

            tableView_GestionNotes.setEditable(true);

            col_cntrl1.setCellFactory(TextFieldTableCell.forTableColumn());
            col_cntrl1.setOnEditCommit(e ->
            {
                e.getTableView().getItems().get(e.getTablePosition().getRow()).setControl_1(e.getNewValue());
                e.getTableView().getItems().get(e.getTablePosition().getRow()).setMoyenne(
                        this.Calculer_moyenne
                                (
                                        Double.parseDouble(e.getNewValue().toString()),
                                        Double.parseDouble(e.getTableView().getItems().get(e.getTablePosition().getRow()).getControl_2().toString()),
                                        Double.parseDouble(e.getTableView().getItems().get(e.getTablePosition().getRow()).getControl_3().toString())
                                ));
                tableView_GestionNotes.refresh();
            });

            col_cntrl2.setCellFactory(TextFieldTableCell.forTableColumn());
            col_cntrl2.setOnEditCommit(e ->
            {
                e.getTableView().getItems().get(e.getTablePosition().getRow()).setControl_2(e.getNewValue());
                e.getTableView().getItems().get(e.getTablePosition().getRow()).setMoyenne(
                        this.Calculer_moyenne
                                (
                                        Double.parseDouble(e.getNewValue().toString()),
                                        Double.parseDouble(e.getTableView().getItems().get(e.getTablePosition().getRow()).getControl_1().toString()),
                                        Double.parseDouble(e.getTableView().getItems().get(e.getTablePosition().getRow()).getControl_3().toString())
                                ));
                tableView_GestionNotes.refresh();
            });

            col_cntrl3.setCellFactory(TextFieldTableCell.forTableColumn());
            col_cntrl3.setOnEditCommit(e ->
            {
                e.getTableView().getItems().get(e.getTablePosition().getRow()).setControl_3(e.getNewValue());
                e.getTableView().getItems().get(e.getTablePosition().getRow()).setMoyenne(
                        this.Calculer_moyenne
                                (
                                        Double.parseDouble(e.getNewValue().toString()),
                                        Double.parseDouble(e.getTableView().getItems().get(e.getTablePosition().getRow()).getControl_1().toString()),
                                        Double.parseDouble(e.getTableView().getItems().get(e.getTablePosition().getRow()).getControl_2().toString())
                                ));
                tableView_GestionNotes.refresh();
            });
            tableView_GestionNotes.refresh();

        } catch (SQLException sqlE) {
            sqlE.printStackTrace();
        }
    }

    private String Calculer_moyenne(Double note1, Double note2, Double note3) {
        return (new DecimalFormat("###.##")).format((note1 + note2 + note3) / 3);
    }

    @FXML
    private void btn_AjoutNews_click(ActionEvent e) {
        try {
            FXMLLoader loader = new FXMLLoader(new File("src/Application/Views/AjoutActualite.fxml").toURI().toURL());
            Parent root = (Parent) loader.load();

            Scene scene = new Scene(root);
            scene.setFill(Color.valueOf("transparent"));

            Stage stage = new Stage(StageStyle.TRANSPARENT);
            stage.setTitle("Ajout d'actualités");
            stage.setScene(scene);
            stage.show();
        } catch (Exception ex) {
            System.out.println(ex.getMessage());
        }
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        Form_Load();

        alertPanel_Load();
        PanelGestionEtudiant_Load();
        statistiqueEtudiant_Load();
        PanelGestionNotes_Load();
        statistiquesPersonnel_Load();
//        panelNotes.toFront();

        if (Gestionnaire_De_Connection.etudiant_connecte != null) {
            VboxMenu.getChildren().remove(btnListes);
            VboxMenu.getChildren().remove(btnStatistiques);
            VboxMenu.getChildren().remove(btnGestion);
            VboxMenu.getChildren().remove(btnAlert);
            btnStatistiquesetudiant.setVisible(true);
            btnNotes.setVisible(true);
        } else if (Gestionnaire_De_Connection.prof_connecte != null) {
            VboxMenu.getChildren().remove(btnStatistiques);
            VboxMenu.getChildren().remove(btnGestion);
            VboxMenu.getChildren().remove(btnStatistiquesetudiant);
            btnListes.setVisible(true);
            btnAlert.setVisible(true);
        } else {
            VboxMenu.getChildren().remove(btnListes);
            VboxMenu.getChildren().remove(btnAlert);
            btnGestion.setVisible(true);
            btnStatistiques.setVisible(true);
        }
        btnAccueil_click();

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
//                String test = dataReader.getString("libelle_branche");
//                System.out.println(test);
//            } else {
//                System.out.println("not cool");
//            }
//        } catch (SQLException e) {
//            e.printStackTrace();
//        }

    }

    @FXML
    private void exam_soumis(ActionEvent e) {

        String statut = radio_modif.isSelected() ? radio_modif.getText() : radio_fix.getText();
        Connection connection = gestionnaire_de_connection.getConnection();
        try {
            Statement sqlCommand = connection.createStatement();
            sqlCommand.execute
                    (
                            String.format
                                    (
                                            "INSERT INTO [dbo].[ALERT_CONTROLE]\n" +
                                                    "           ([groupe#]\n" +
                                                    "           ,[date_control]\n" +
                                                    "           ,[heure_debut]\n" +
                                                    "           ,[heure_fin]\n" +
                                                    "           ,[statut]\n" +
                                                    "           ,[description_control])\n" +
                                                    "     VALUES\n" +
                                                    "           (%d,CAST(N'%s' AS Date),'%s','%s','%s','%s')",
                                            (cb_groupesAlert.getSelectionModel().getSelectedIndex() + 1),
                                            date_control.getValue().toString(),
                                            heureDebut_controle.getText(),
                                            heureFin_controle.getText(),
                                            statut,
                                            "Par Mr/Mme " + Gestionnaire_De_Connection.nom_connecte + "\n" + txt_description.getText().replace("'", "''")
                                    )
                    );
            cb_groupesAlert.getSelectionModel().clearSelection();
            date_control.setValue(null);
            heureDebut_controle.setText("");
            heureFin_controle.setText("");
            radio_modif.setSelected(false);
            radio_fix.setSelected(false);
            txt_description.setText("");

        } catch (SQLException ex) {

            ex.printStackTrace();
        }
    }

    private void Accueil_Load() {
        if (Gestionnaire_De_Connection.etudiant_connecte != null || Gestionnaire_De_Connection.prof_connecte != null) {
            btn_AjoutNews.setVisible(false);
            btn_refresh.setVisible(false);
        }
        vbox_messagerie.getChildren().clear();
        vbox_messagerie.setSpacing(30);
        Connection connection = gestionnaire_de_connection.getConnection();
        if (Gestionnaire_De_Connection.etudiant_connecte != null) {
            try {
                Statement sqlCommand = connection.createStatement();
                ResultSet dataReader = sqlCommand.executeQuery
                        (
                                String.format
                                        (
                                                "select alrt.* , grp.libelle_grp\n" +
                                                        "from alert_controle alrt \n" +
                                                        "inner join groupe grp on alrt.groupe# = grp.id_groupe\n" +
                                                        "where grp.id_groupe = (" +
                                                        "                    select grpp.id_groupe\n" +
                                                        "                    from groupe grpp inner join etudiant etd on etd.groupe# = grpp.id_groupe\n" +
                                                        "                    where etd.code_massar = '%s'\n" +
                                                        "                     )",
                                                Gestionnaire_De_Connection.etudiant_connecte
                                        )
                        );

                while (dataReader.next()) {
                    labelinfo_Accueil.setVisible(false);
                    img1_Accueil.setVisible(false);
                    img2_Accueil.setVisible(false);
                    img3_Accueil.setVisible(true);
                    vbox_messagerie.setVisible(true);
                    scroll.setVisible(true);
                    TextArea actualite = new TextArea();
                    actualite.setText(
                            String.format
                                    (
                                            "Pour les étudiants du groupe : %s ,Vous avez un controle le %s , à partir de %s jusqu'à %s , la date reste alors %s !\n" +
                                                    "Plus d'information : %s",
                                            dataReader.getString("libelle_grp"),
                                            dataReader.getString("date_control"),
                                            dataReader.getString("heure_debut"),
                                            dataReader.getString("heure_fin"),
                                            dataReader.getString("statut"),
                                            dataReader.getString("description_control")
                                    ));
                    actualite.setPrefHeight(150);
                    actualite.setMaxHeight(150);
                    actualite.setMinHeight(150);
                    actualite.setEffect(new DropShadow());
                    actualite.setEditable(false);
                    actualite.setFont(Font.font("Arial", FontWeight.BOLD, 14));

                    actualite.setWrapText(true);
                    actualite.getStylesheets().add("resources/Styles/AccueilStyle.css");
                    actualite.getStyleClass().add("text-area");

                    vbox_messagerie.getChildren().add(actualite);
                    vbox_messagerie.setPrefHeight(vbox_messagerie.getPrefHeight() + 150);
                }
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        try {
            Statement sqlCommand = connection.createStatement();
            ResultSet dataReader = sqlCommand.executeQuery
                    (
                            "select act.sujet, act.description_actualite, CONCAT(per.nom_personnel, ' ', per.prenom_personnel ) as nomComplet\n" +
                                    "from ACTUALITE act inner join PERSONNEL per on act.ajoute_par_personnel# = per.id_personnel"
                    );

            while (dataReader.next()) {

                labelinfo_Accueil.setVisible(false);
                img1_Accueil.setVisible(false);
                img2_Accueil.setVisible(false);
                vbox_messagerie.setVisible(true);
                scroll.setVisible(true);

                TextArea actualite = new TextArea();
                actualite.setText
                        (
                                "sujet : "
                                        + dataReader.getString("sujet")
                                        + "\n\n"
                                        + dataReader.getString("description_actualite")
                                        + "\n\n"
                                        + "Par Mr/Mme : "
                                        + dataReader.getString("nomComplet")
                        );
                actualite.setPrefHeight(150);
                actualite.setMaxHeight(150);
                actualite.setMinHeight(150);
                actualite.setEffect(new DropShadow());
                actualite.setEditable(false);
//                actualite.setStyle("-fx-font-size: 14");
                actualite.setFont(Font.font("Arial", FontWeight.BOLD, 14));
                actualite.setWrapText(true);
                actualite.getStylesheets().add("resources/Styles/AccueilStyle.css");
                actualite.getStyleClass().add("text-area");

                vbox_messagerie.getChildren().add(actualite);
                vbox_messagerie.setPrefHeight(vbox_messagerie.getPrefHeight() + 150);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @FXML
    private void refresh_click(ActionEvent e) {
        this.Accueil_Load();
    }

    @FXML
    public void CB_grp_gestionEtudiant_selected(ActionEvent actionEvent) {
        tableView_GestionEtudiant.getItems().clear();
        int id_grp = CB_grp_gestionEtudiant.getSelectionModel().getSelectedIndex() + 1;

        Connection connection = gestionnaire_de_connection.getConnection();
        try {
            Statement sqlCommand = connection.createStatement();
            ResultSet dataReader = sqlCommand.executeQuery("select et.code_massar, et.prenom, et.nom, et.date_inscription, et.email, et.telephone, et.a_deja_redouble, et.sexe, et.adresse\n" +
                    "from etudiant et inner join groupe grp on et.groupe# = grp.id_groupe\n" +
                    "where grp.id_groupe = " + id_grp);

            while (dataReader.next()) {
                String code_massar = dataReader.getString("code_massar");
                String prenom = dataReader.getString("prenom");
                String nom = dataReader.getString("nom");
                Date date_inscription = dataReader.getDate("date_inscription");
                String email = dataReader.getString("email");
                String telephone = dataReader.getString("telephone");
                String a_deja_redouble = dataReader.getBoolean("a_deja_redouble") ? "oui" : "non";
                String sexe = dataReader.getString("sexe");
                String adresse = dataReader.getString("adresse");
                GestionEtudiantsViewModel etudiant = new GestionEtudiantsViewModel(
                        code_massar,
                        nom,
                        prenom,
                        date_inscription,
                        email,
                        telephone,
                        a_deja_redouble,
                        adresse,
                        sexe
                );
                tableView_GestionEtudiant.getItems().add(etudiant);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @FXML
    public void btnAlert_click() {
        panelAlert.toFront();
        btnClose.toFront();
        btnMinimize.toFront();
    }

    private void alertPanel_Load() {
        try {
            Connection connection = gestionnaire_de_connection.getConnection();
            Statement stmGrp = connection.createStatement();
            ResultSet reader = stmGrp.executeQuery("select * from GROUPE");
            ObservableList groupes = FXCollections.observableArrayList();
            while (reader.next()) {
                String groupe = reader.getString("libelle_grp");
                groupes.add("Groupe " + groupe);
            }

            cb_groupesAlert.setItems(groupes);
        } catch (SQLException sqlE) {
            sqlE.printStackTrace();
        }
    }

    @FXML
    public void CB_grp_gestionNotes_selected(ActionEvent actionEvent) {

        tableView_GestionNotes.getItems().clear();
        int id_grp = CB_grp_gestionNotes.getSelectionModel().getSelectedIndex() + 1;

        Connection connection = gestionnaire_de_connection.getConnection();
        try {
            Statement sqlCommand1 = connection.createStatement();
            ResultSet dataReader1 = sqlCommand1.executeQuery
                    (
                            String.format
                                    (
                                            "select etd.code_massar, CONCAT(etd.prenom , ' ' , etd.nom) as nom_complet \n" +
                                                    "from etudiant etd inner join groupe grp on etd.groupe# = grp.id_groupe\n" +
                                                    "\t\t\t\t  inner join enseignement en on en.groupe# = grp.id_groupe\n" +
                                                    "where grp.id_groupe = %d" +
                                                    "\t  and en.professeur# = '%s'",
                                            id_grp,
                                            gestionnaire_de_connection.prof_connecte
                                    )
                    );

            while (dataReader1.next()) {

                String code_massar = dataReader1.getString("code_massar");
                String nom_complet = dataReader1.getString("nom_complet");
                Statement sqlCommand2 = connection.createStatement();
                ResultSet dataReader2 = sqlCommand2.executeQuery
                        (
                                String.format
                                        (
                                                "select top 3 n.Valeur_Note \n" +
                                                        "from note n\n" +
                                                        "where n.etudiant_ = '%s'\n" +
                                                        "and matiere# = (select en.matiere#\n" +
                                                        "from professeur prof inner join ENSEIGNEMENT en on prof.Code_Pro_Nationnal = en.professeur#\n" +
                                                        "where prof.Code_Pro_Nationnal = '%s')",
                                                code_massar,
                                                gestionnaire_de_connection.prof_connecte
                                        )
                        );
                if (dataReader2.next()) {

                    String note_1 = dataReader2.getString("Valeur_Note");
                    dataReader2.next();
                    String note_2 = dataReader2.getString("Valeur_Note");
                    dataReader2.next();
                    String note_3 = dataReader2.getString("Valeur_Note");
                    String moyenne = this.Calculer_moyenne(Double.valueOf(note_1), Double.valueOf(note_2), Double.valueOf(note_3));
                    GestionNotesViewModel etudiant = new GestionNotesViewModel(
                            code_massar,
                            nom_complet,
                            note_1,
                            note_2,
                            note_3,
                            moyenne
                    );
                    tableView_GestionNotes.getItems().add(etudiant);

                } else {
                    GestionNotesViewModel etudiant = new GestionNotesViewModel(
                            code_massar,
                            nom_complet,
                            "0.00",
                            "0.00",
                            "0.00",
                            "0.00"
                    );
                    tableView_GestionNotes.getItems().add(etudiant);
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
