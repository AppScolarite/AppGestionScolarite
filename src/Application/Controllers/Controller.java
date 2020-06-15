package Application.Controllers;

import Application.Data.Gestionnaire_De_Connection;
import Application.Models.*;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.chart.*;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.control.cell.TextFieldTableCell;
import javafx.scene.effect.DropShadow;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.scene.text.Text;
import javafx.scene.text.TextAlignment;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

import java.io.File;
import java.io.IOException;
import java.sql.*;

import java.net.URL;
import java.text.DecimalFormat;
import java.time.LocalDate;
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
    private PieChart pieChartMoyenne;

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
    private Button btnProfil_etd;


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
    // *******************Profile*******************
//*************************Administrateur************************//
    @FXML
    private Pane Pane_pers;

    @FXML
    private Label complet_esg1;

    @FXML
    private DatePicker date_naiss_admin;

    @FXML
    private Text txt_sexe_pers;

    @FXML
    private TextField user_txt_pers;

    @FXML
    private TextField pw_txt_pers;

    @FXML
    private TextField email_pers;

    @FXML
    private TextField tel_pers;

    @FXML
    private TextField adresse_pers;

    //******************************************************
    //*******************Etudiant***************//
    @FXML
    private Pane Pane_etd;

    @FXML
    private Label complet_etd;

    @FXML
    private Label cne_etd;

    @FXML
    private DatePicker date_naiss_etd;


    @FXML
    private DatePicker date_insc_etd;

    @FXML
    private TextField email_etd;

    @FXML
    private TextField tel_etd;

    @FXML
    private Text txt_sexe;

    @FXML
    private TextField adr_etd;

    @FXML
    private Text txt_groupe;

    @FXML
    private TextField user_txt;

    @FXML
    private TextField pw_txt;

    @FXML
    private CheckBox ck_redouble;


    //**********************ENSEIGNANT**************************//
    @FXML
    private Pane Pane_ensg;
    @FXML
    private Label complet_esg;

    @FXML
    private Label cin_esg;

    @FXML
    private Label code_esg;

    @FXML
    private DatePicker date_naiss_esg;

    @FXML
    private DatePicker date_ctr_esg;

    @FXML
    private TextField email_esg;

    @FXML
    private TextField tel_esg;

    @FXML
    private Text type_contrat;

    @FXML
    private Text txt_sexe_esg;

    @FXML
    private TextField user_txt_esg;

    @FXML
    private TextField pw_txt_esg;

    @FXML
    private Text txt_situation;

    @FXML
    private TextField adr_esg;
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


    //**************Gestion Prof********************
    @FXML
    private Button btnGestionProf;
    @FXML
    private Button Btn_Ajouter;
    @FXML
    private Pane addProfPanel;
    @FXML
    private Hyperlink HP_listProf;
    @FXML
    private Pane panelListerProf;
    @FXML
    private TableView<GestionProfViewModel> TableViewProfs;
    @FXML
    private TableColumn<GestionProfViewModel, String> col_codeProf;
    @FXML
    private TableColumn<GestionProfViewModel, String> col_CIN;
    @FXML
    private TableColumn<GestionProfViewModel, String> col_NomComplet;
    @FXML
    private TableColumn<GestionProfViewModel, String> col_typeContrat;
    @FXML
    private TableColumn<GestionProfViewModel, String> col_matiere;
    @FXML
    private TableColumn<GestionProfViewModel, String> col_groupe;
    @FXML
    private TextField txtCodeProf;
    @FXML
    private TextField txtCIN;
    @FXML
    private TextField txtNomProf;
    @FXML
    private TextField txtEmail;
    @FXML
    private TextField txtTel;
    @FXML
    private TextField txtAdresse;
    @FXML
    private TextField txtUsername;
    @FXML
    private TextField txtPassword;
    @FXML
    private DatePicker DP_naissance;
    @FXML
    private DatePicker DP_commencement;
    @FXML
    private ComboBox CB_contrat;
    @FXML
    private RadioButton RB_Femme;
    @FXML
    private RadioButton RB_Homme;
    @FXML
    private RadioButton RB_Marie;
    @FXML
    private RadioButton RB_Ccelib;
    @FXML
    private RadioButton RB_Div;
    @FXML
    private RadioButton RB_StaticProf;
    @FXML
    private RadioButton RB_StatisticGroupe;
    @FXML
    private RadioButton RB_StatisticMatiere;
    @FXML
    private ComboBox CB_Matieres;
    @FXML
    private ComboBox CB_Groupes;
    @FXML
    private TextField txtSearch;
    @FXML
    private FlowPane floawLayout_groupe;
    @FXML
    private Button Btn_Rechercher;
    //    @FXML
//    private MenuItem supprimerProf;
    @FXML
    private Label NbreEtudiant;
    @FXML
    private Label nbreProf;
    @FXML
    private Label nbreGroupe;
    @FXML
    private Label etumoySup;
    @FXML
    private Label etuNoteInf;

    //**********************************************


    //********************Profile Page *************
    @FXML
    private void profil_show()  {
//Si l'étudiant qui est connecté
        System.out.println(Gestionnaire_De_Connection.etudiant_connecte);
        if (Gestionnaire_De_Connection.etudiant_connecte != null) {
            Pane_etd.toFront();
            try {
                Connection con = gestionnaire_de_connection.getConnection();
                ResultSet rs = con.createStatement().executeQuery(
                        String.format("select * from etudiant  where code_massar = '" + Gestionnaire_De_Connection.etudiant_connecte + "'")
                );
                if (rs.next()) {
                    System.out.println("gdsgd");
                    cne_etd.setText(rs.getString("code_massar"));
                    complet_etd.setText(rs.getString("Nom") + " " + rs.getString("Prenom"));
                    email_etd.setText(rs.getString("email"));
                    tel_etd.setText(rs.getString("telephone"));
                    pw_txt.setText(rs.getString("mot_de_passe"));
                    user_txt.setText(rs.getString("username"));
                    txt_sexe.setText(rs.getString("sexe"));
                    adr_etd.setText(rs.getString("adresse"));
                    date_naiss_etd.setValue(LocalDate.parse(rs.getString("date_naissance")));
                    date_insc_etd.setValue(LocalDate.parse(rs.getString("date_inscription")));

                    Integer red = rs.getInt("a_deja_redouble");
                    if (red == 1) {
                        ck_redouble.setSelected(true);
                    } else {
                        ck_redouble.setSelected(false);
                    }

//                    int id =  rs.getInt("groupe#");


                    String sq = "SELECT g.libelle_grp as LibGrp FROM groupe g where g.id_groupe= " + rs.getInt("groupe#");
                    ResultSet rd = con.createStatement().executeQuery(sq);
                    if (rd.next()) {
                        txt_groupe.setText(rd.getString("LibGrp"));
                    }

                }
            } catch (Exception e) {
                // TODO: handle exception
            }
        }
        //Si l'enseignnat qui est connecté
        if (Gestionnaire_De_Connection.prof_connecte != null) {
            System.out.println(Gestionnaire_De_Connection.prof_connecte);
            System.out.println(Gestionnaire_De_Connection.nom_connecte);
            try {
                Pane_ensg.toFront();
                Connection con = gestionnaire_de_connection.getConnection();
                String sql = "SELECT  * FROM PROFESSEUR where Code_Pro_Nationnal = '" + Gestionnaire_De_Connection.prof_connecte  + "'";
                ResultSet rs = con.createStatement().executeQuery(sql);
                if (rs.next()) {
                    cin_esg.setText(rs.getString("Cin"));
                    code_esg.setText(rs.getString("code_Pro_Nationnal"));
                    complet_esg.setText(rs.getString("Nom") + " " + rs.getString("Prenom"));
                    date_naiss_esg.setValue(LocalDate.parse(rs.getString("date_naissance")));
                    date_ctr_esg.setValue(LocalDate.parse(rs.getString("Date_Commencement_Contrat")));
                    type_contrat.setText(rs.getString("Type_Contrat"));
                    email_esg.setText(rs.getString("email"));
                    tel_esg.setText(rs.getString("telephone"));
                    txt_sexe_esg.setText(rs.getString("sexe"));
                    adr_esg.setText(rs.getString("adresse"));
                    txt_situation.setText(rs.getString("Situation_Familliale"));
                    user_txt_esg.setText(rs.getString("username"));
                    pw_txt_esg.setText(rs.getString("mot_de_passe"));
                }
            } catch (Exception e) {
                // TODO: handle exception
            }
            //


        }
        //Si le personnel qui est connecté
        if (Gestionnaire_De_Connection.personnel_connecte != 0) {
            System.out.println(Gestionnaire_De_Connection.personnel_connecte);
            System.out.println(Gestionnaire_De_Connection.nom_connecte);
            Pane_pers.toFront();
            try {
                Connection con = gestionnaire_de_connection.getConnection();
                String sql = "SELECT  * FROM PERSONNEL WHERE id_personnel = " + Gestionnaire_De_Connection.personnel_connecte;
                ResultSet rs = con.createStatement().executeQuery(sql);
                if (rs.next()) {
                    complet_esg1.setText(Gestionnaire_De_Connection.nom_connecte);
                    date_naiss_admin.setValue(LocalDate.parse(rs.getString("date_naissance_personnel")));
                    email_pers.setText(rs.getString("email_personnel"));
                    tel_pers.setText(rs.getString("telephone_personnel"));
                    txt_sexe_pers.setText(rs.getString("sexe"));
                    adresse_pers.setText(rs.getString("adresse"));
                    user_txt_pers.setText(rs.getString("username"));
                    pw_txt_pers.setText(rs.getString("mot_de_passe"));
                }
            } catch (Exception e) {
                // TODO: handle exception
            }
        }
    }
    //**********************************************

    public Double moyenne;

    //*********Noureddine Gestion Prof****************
    @FXML
    private void supprimerProf() {
        GestionProfViewModel professeur = (GestionProfViewModel) TableViewProfs.getSelectionModel().getSelectedItem();
        if (professeur == null) {
            System.out.println("aucun etudiant a supprimer !");
            return;
        }
        Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
        alert.setTitle("Supression d'un étudiant !");
        alert.setContentText("Etes vous totalement sur de vouloir supprimer l'étudiant <" + professeur.getNomCodeProf() + "-" + professeur.getNomComplet() + " " + professeur.getCIN() + "> ??\n");
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
                                                "delete from ENSEIGNEMENT where professeur# = '%s' ;" +
                                                        "delete from PROFESSEUR where Code_Pro_Nationnal = '%s';",
                                                professeur.getNomCodeProf(), professeur.getNomCodeProf()
                                        )
                        );
                TableViewProfs.getItems().remove(TableViewProfs.getSelectionModel().getSelectedItem());
                TableViewProfs.refresh();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }


    @FXML
    public void Mode_Click(ActionEvent e) {
        if (((ToggleButton) e.getSource()).isSelected()) {
            for (int i = 0; i < floawLayout_groupe.getChildren().size(); i++) {
                System.out.println(IdGrp.get(i));
            }
            System.out.println("mode activated");
            Btn_Rechercher.setVisible(true);
            txtSearch.setVisible(true);
            Btn_Ajouter.setText("Modifier");
        } else {
            System.out.println("mode desactivated");
            Btn_Rechercher.setVisible(false);
            txtSearch.setVisible(false);
            Btn_Ajouter.setText("Ajouter");
            txtCodeProf.clear();
            txtCIN.clear();
            txtNomProf.clear();
            DP_naissance.setValue(null);
            DP_commencement.setValue(null);
            CB_Groupes.setPromptText("Contrat");
            txtEmail.clear();
            txtTel.clear();
            RB_Homme.setSelected(true);
            RB_Marie.setSelected(true);
            txtUsername.clear();
            txtPassword.clear();
            CB_Matieres.setPromptText("Matieres");
            CB_Groupes.setPromptText("Groupes");
            floawLayout_groupe.getChildren().clear();
            txtSearch.clear();
        }
    }

    @FXML
    public void groupe_click(MouseEvent e) {
        this.floawLayout_groupe.getChildren().remove(e.getSource());
        IdGrp.remove(e.getSource());
    }

    public List<Integer> IdGrp = new ArrayList<>();

    @FXML
    private void cb_groupe_selected() {
        if (!CB_Groupes.getSelectionModel().getSelectedItem().equals("-Choisir-")) {
            Label label = new Label();
            label.setText(CB_Groupes.getSelectionModel().getSelectedItem().toString());
            IdGrp.add(CB_Groupes.getSelectionModel().getSelectedIndex());
            System.out.println(IdGrp);
            label.setAlignment(Pos.CENTER);
            label.setFont(Font.font("System", FontWeight.BOLD, 12));
            label.setTextAlignment(TextAlignment.CENTER);
            label.setPrefHeight(18.0);
            label.setPrefWidth(label.getText().length() + 80);
            CornerRadii radius = new CornerRadii(30);
            label.setBackground(new Background(new BackgroundFill(Color.DODGERBLUE, radius, Insets.EMPTY)));
            label.setOnMouseClicked(new EventHandler<MouseEvent>() {
                @Override
                public void handle(MouseEvent mouseEvent) {
                    groupe_click(mouseEvent);
                }
            });
            floawLayout_groupe.setHgap(10);
            floawLayout_groupe.setVgap(10);
            floawLayout_groupe.getChildren().add(label);
        }

    }

    public void BindComboGroupe() {
        try {
            Connection connection = gestionnaire_de_connection.getConnection();
            Statement stmMatiere = connection.createStatement();
            ResultSet rss = stmMatiere.executeQuery("select * from GROUPE");
            ObservableList mat = FXCollections.observableArrayList();
            mat.add("-Choisir-");
            while (rss.next()) {
                String matieres = rss.getString("libelle_grp");
                mat.add(matieres);
            }
            CB_Groupes.setItems(mat);
            CB_Groupes.getSelectionModel().selectFirst();
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
    }

    public void BindComboMatiere() {
        try {
            Connection connection = gestionnaire_de_connection.getConnection();
            Statement stmMatiere = connection.createStatement();
            ResultSet rss = stmMatiere.executeQuery("select * from matiere where id_matiere not in (select matiere# from ENSEIGNEMENT)");
            ObservableList mat = FXCollections.observableArrayList();
            while (rss.next()) {
                String matieres = rss.getString(2);
                mat.add(matieres);
            }
            CB_Matieres.setItems(mat);
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
    }

    ObservableList<GestionProfViewModel> Professeurs() throws SQLException {
        Gestionnaire_De_Connection gestionnaire_de_connection = new Gestionnaire_De_Connection();
        Connection connection = gestionnaire_de_connection.getConnection();
        ObservableList<GestionProfViewModel> professeurs = FXCollections.observableArrayList();
        Statement statement = connection.createStatement();
        ResultSet resultSet = statement.executeQuery("  select professeur.Code_Pro_Nationnal as codeProf,professeur.Cin as cin," +
                " concat(professeur.Nom,' ',professeur.Prenom) as nomProf, \n" +
                "  professeur.Type_Contrat as typeContrat, matiere.LBL_Matiere as nomMatiere, groupe.libelle_grp as nomGroupe \n" +
                "  from PROFESSEUR join ENSEIGNEMENT on PROFESSEUR.Code_Pro_Nationnal = ENSEIGNEMENT.professeur#\n" +
                "  join MATIERE on ENSEIGNEMENT.matiere# = MATIERE.id_matiere\n" +
                "  join GROUPE on GROUPE.id_groupe = ENSEIGNEMENT.groupe#");
        while (resultSet.next()) {
            professeurs.addAll(new GestionProfViewModel(
                    resultSet.getString("codeProf"),
                    resultSet.getString("cin"),
                    resultSet.getString("nomProf"),
                    resultSet.getString("typeContrat"),
                    resultSet.getString("nomMatiere"),
                    resultSet.getString("nomGroupe")
            ));
        }
        return professeurs;
    }

    @FXML
    public void backGestionProf_click() {
        addProfPanel.toFront();
        btnMinimize.toFront();
        btnClose.toFront();
    }


    @FXML
    public void listProf_click() throws SQLException {
        panelListerProf.toFront();
        btnClose.toFront();
        btnMinimize.toFront();
        col_codeProf.setCellValueFactory(new PropertyValueFactory<>("nomCodeProf"));
        col_CIN.setCellValueFactory(new PropertyValueFactory<>("CIN"));
        col_NomComplet.setCellValueFactory(new PropertyValueFactory<>("nomComplet"));
        col_typeContrat.setCellValueFactory(new PropertyValueFactory<>("typeContrat"));
        col_matiere.setCellValueFactory(new PropertyValueFactory<>("Matiere"));
        col_groupe.setCellValueFactory(new PropertyValueFactory<>("Groupes"));
        col_groupe.setCellFactory(TextFieldTableCell.forTableColumn());
        TableViewProfs.setItems(Professeurs());
        TableViewProfs.setEditable(true);

    }

    @FXML
    public void gestionProf_click() {
        BindComboMatiere();
        BindComboGroupe();
        addProfPanel.toFront();
        btnClose.toFront();
        btnMinimize.toFront();
    }
//********************************************************************************************************************************//
    @FXML
    public void ajouterProf_click() {
        Gestionnaire_De_Connection gestionnaire_de_connection = new Gestionnaire_De_Connection();
        Connection connection = gestionnaire_de_connection.getConnection();
        if (Btn_Ajouter.getText().equals("Ajouter")) {
            try {
                String Nomcomplet[] = txtNomProf.getText().split(" ");
                String nomProf = Nomcomplet[0];
                String PrenomProf = Nomcomplet[1];

                PreparedStatement preparedStatement = connection.prepareStatement
                        (
                                "INSERT INTO PROFESSEUR (Code_Pro_Nationnal, Cin, Nom, Prenom, Date_Naissance, Date_Commencement_Contrat, Type_Contrat, Email," +
                                        " Telephone, sexe, Adresse, Situation_Familliale, username, mot_de_passe) VALUES (?,?,?,?,?,?,?,?,?,?,?,?,?,?)"
                        );
                preparedStatement.setString(1, txtCodeProf.getText());
                preparedStatement.setString(2, txtCIN.getText());
                preparedStatement.setString(3, nomProf);
                preparedStatement.setString(4, PrenomProf);
                preparedStatement.setDate(5, java.sql.Date.valueOf(DP_naissance.getValue()));
                preparedStatement.setDate(6, java.sql.Date.valueOf(DP_commencement.getValue()));
                if (CB_contrat.getSelectionModel().getSelectedIndex() + 1 == 1) {
                    preparedStatement.setString(7, "CDD");
                } else preparedStatement.setString(7, "CDI");
                preparedStatement.setString(8, txtEmail.getText());
                preparedStatement.setString(9, txtTel.getText());
                if (RB_Femme.isSelected()) {
                    preparedStatement.setString(10, RB_Femme.getText());
                }
                if (RB_Homme.isSelected()) preparedStatement.setString(10, RB_Homme.getText());
                preparedStatement.setString(11, txtAdresse.getText());
                if (RB_Ccelib.isSelected()) preparedStatement.setString(12, RB_Ccelib.getText());
                if (RB_Div.isSelected()) preparedStatement.setString(12, RB_Div.getText());
                if (RB_Marie.isSelected()) preparedStatement.setString(12, RB_Marie.getText());
                preparedStatement.setString(13, txtUsername.getText());
                preparedStatement.setString(14, txtPassword.getText());

                preparedStatement.executeUpdate();


            } catch (SQLException e) {
                e.getStackTrace();
            }

            try {
                for (int i = 0; i < floawLayout_groupe.getChildren().size(); i++) {
                    Statement sqlCommand = connection.createStatement();
                    ResultSet reader = sqlCommand.executeQuery
                            (
                                    String.format
                                            (
                                                    "select ma.id_matiere from MATIERE ma where ma.LBL_Matiere = '%s'",
                                                    CB_Matieres.getSelectionModel().getSelectedItem().toString()
                                            )
                            );
                    System.out.println(CB_Matieres.getSelectionModel().getSelectedItem().toString());
                    PreparedStatement preparedStatement = connection.prepareStatement("INSERT INTO ENSEIGNEMENT (professeur#,groupe#, matiere#) VALUES (?,?,?)");
                    preparedStatement.setString(1, txtCodeProf.getText());
                    preparedStatement.setInt(2, IdGrp.get(i));
                    reader.next();
                    preparedStatement.setInt(3, reader.getInt("id_matiere"));
                    preparedStatement.execute();
                }

            } catch (SQLException e) {
                e.getStackTrace();
            }

            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("Saisie avec succées");
            alert.setHeaderText("Un Professeur ajouté");
            alert.setContentText("Professeur a bien été ajouté !! ");
            alert.showAndWait();
        } else if (Btn_Ajouter.getText().equals("Modifier")) {
            try {
                String Nomcomplet[] = txtNomProf.getText().split(" ");
                String nomProf = Nomcomplet[0];
                String PrenomProf = Nomcomplet[1];

                PreparedStatement preparedStatement = connection.prepareStatement("UPDATE PROFESSEUR SET Cin = ? , Nom = ? , Prenom = ?, Date_Naissance = ?, Date_Commencement_Contrat = ? ," +
                        " Type_Contrat = ?, Email = ?, Telephone = ? , sexe = ?, Adresse = ?, Situation_Familliale = ?, username= ?, mot_de_passe= ? WHERE Code_Pro_Nationnal = ?");
                preparedStatement.setString(1, txtCIN.getText());
                preparedStatement.setString(2, nomProf);
                preparedStatement.setString(3, PrenomProf);
                preparedStatement.setDate(4, java.sql.Date.valueOf(DP_naissance.getValue()));
                preparedStatement.setDate(5, java.sql.Date.valueOf(DP_commencement.getValue()));
                if (CB_contrat.getSelectionModel().getSelectedIndex() + 1 == 1) {
                    preparedStatement.setString(6, "CDD");
                } else preparedStatement.setString(6, "CDI");
                preparedStatement.setString(7, txtEmail.getText());
                preparedStatement.setString(8, txtTel.getText());
                if (RB_Femme.isSelected()) {
                    preparedStatement.setString(9, RB_Femme.getText());
                } else if (RB_Homme.isSelected()) preparedStatement.setString(9, RB_Homme.getText());
                preparedStatement.setString(10, txtAdresse.getText());
                if (RB_Ccelib.isSelected()) preparedStatement.setString(11, RB_Ccelib.getText());
                if (RB_Div.isSelected()) preparedStatement.setString(11, RB_Div.getText());
                if (RB_Marie.isSelected()) preparedStatement.setString(11, RB_Marie.getText());
                preparedStatement.setString(12, txtUsername.getText());
                preparedStatement.setString(13, txtPassword.getText());
                preparedStatement.setString(14, txtCodeProf.getText());

                preparedStatement.executeUpdate();


            } catch (SQLException e) {
                e.getStackTrace();
            }
            try {
                for (int i = 0; i < floawLayout_groupe.getChildren().size(); i++) {
                    PreparedStatement preparedStatement = connection.prepareStatement("UPDATE ENSEIGNEMENT set groupe# = ?, matiere# = ? where professeur# = ?");
                    preparedStatement.setInt(1, IdGrp.get(i));
                    preparedStatement.setInt(2, CB_Matieres.getSelectionModel().getSelectedIndex() + 1);
                    preparedStatement.setString(3, txtCodeProf.getText());
                    preparedStatement.executeUpdate();
                }

            } catch (SQLException e) {
                e.getStackTrace();
            }

            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("Modification professeur");
            alert.setHeaderText("Un Presseur Modifier");
            alert.setContentText("Professeur a été bien Modifier !! ");
            alert.showAndWait();
        }
    }
//*******************************************************************************************************************************************************//
    @FXML
    public void chercherProf_click() {
        Btn_Ajouter.setText("Modifier");
        Gestionnaire_De_Connection getGestionnaire_de_connection = new Gestionnaire_De_Connection();
        Connection connection = getGestionnaire_de_connection.getConnection();
        try {
            Statement statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery("  select * from professeur  where Code_Pro_Nationnal ='" + txtSearch.getText() + "'");
            if (resultSet.next()) {
                txtCodeProf.setText(resultSet.getString("Code_Pro_Nationnal"));
                txtCIN.setText(resultSet.getString("Cin"));
                txtNomProf.setText(resultSet.getString("Nom") + " " + resultSet.getString("Prenom"));
                DP_naissance.setValue(LocalDate.parse(resultSet.getString("Date_Naissance")));
                DP_commencement.setValue(LocalDate.parse(resultSet.getString("Date_Commencement_Contrat")));
                CB_contrat.setPromptText(resultSet.getString("Type_Contrat"));
                txtEmail.setText(resultSet.getString("Email"));
                txtTel.setText(resultSet.getString("Telephone"));
                if (resultSet.getString("sexe").equals("Homme")){
                    RB_Homme.setSelected(true);
                    RB_Femme.setSelected(false);
                }
                else {
                    RB_Femme.setSelected(true);
                    RB_Homme.setSelected(false);
                }
                txtAdresse.setText(resultSet.getString("Adresse"));
                if (resultSet.getString("Situation_Familliale").equals("Celibataire")) RB_Ccelib.setSelected(true);
                else if (resultSet.getString("Situation_Familliale").equals("divorcé(e)")) RB_Div.setSelected(true);
                else if (resultSet.getString("Situation_Familliale").equals("Marié(e)")) RB_Marie.setSelected(true);
                txtUsername.setText(resultSet.getString("username"));
                txtPassword.setText(resultSet.getString("mot_de_passe"));
            }
            else {
                txtCodeProf.clear();
                txtCIN.clear();
                txtNomProf.clear();
                DP_naissance.setValue(null);
                DP_commencement.setValue(null);
                CB_Groupes.setPromptText("Contrat");
                txtEmail.clear();
                txtTel.clear();
                RB_Homme.setSelected(true);
                RB_Marie.setSelected(true);
                txtUsername.clear();
                txtPassword.clear();
                CB_Matieres.setPromptText("Matieres");
                CB_Groupes.setPromptText("Groupes");
                floawLayout_groupe.getChildren().clear();
            }

            Statement groupeProfstm = connection.createStatement();
            ResultSet groupeResultSet = groupeProfstm.executeQuery("  SELECT GROUPE.libelle_grp as libelleGroupe from GROUPE JOIN ENSEIGNEMENT on GROUPE.id_groupe = ENSEIGNEMENT.groupe# WHERE ENSEIGNEMENT.professeur# = '" + txtSearch.getText() + "'");
            while (groupeResultSet.next()) {
                floawLayout_groupe.getChildren().clear();
                Label label = new Label();
                label.setText(groupeResultSet.getString("libelleGroupe"));
                label.setAlignment(Pos.CENTER);
                IdGrp.add(CB_Groupes.getSelectionModel().getSelectedIndex());
                label.setFont(Font.font("System", FontWeight.BOLD, 12));
                label.setTextAlignment(TextAlignment.CENTER);
                label.setPrefHeight(18.0);
                label.setPrefWidth(label.getText().length() + 80);
                CornerRadii radius = new CornerRadii(30);
                label.setBackground(new Background(new BackgroundFill(Color.DODGERBLUE, radius, Insets.EMPTY)));
                label.setOnMouseClicked(new EventHandler<MouseEvent>() {
                    @Override
                    public void handle(MouseEvent mouseEvent) {
                        groupe_click(mouseEvent);
                    }
                });
                floawLayout_groupe.setHgap(10);
                floawLayout_groupe.setVgap(10);
                floawLayout_groupe.getChildren().add(label);
            }

            Statement matiereStm = connection.createStatement();
            ResultSet matiResultSet = matiereStm.executeQuery("SELECT MATIERE.LBL_Matiere as lblMatiere FROM MATIERE JOIN ENSEIGNEMENT on  ENSEIGNEMENT.matiere# = MATIERE.id_matiere WHERE ENSEIGNEMENT.professeur# = '" + txtSearch.getText() + "'");
            if (matiResultSet.next()) {
                CB_Matieres.setPromptText(matiResultSet.getString("lblMatiere"));
            }

        } catch (SQLException s) {
            s.getStackTrace();
        }
    }
    //***********************************

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
            if (resultSet.next()) {
                Cntrol1.setText(resultSet.getString("Valeur_Note"));
                resultSet.next();
                Cntrol2.setText(resultSet.getString("Valeur_Note"));
                resultSet.next();
                Cntrol3.setText(resultSet.getString("Valeur_Note"));
                moyenne = ((Double.valueOf(Cntrol1.getText()) + Double.valueOf(Cntrol2.getText()) + Double.valueOf(Cntrol3.getText())) / 3);
                MyenneLbl.setText(String.valueOf(moyenne));
            }

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

    //fill piechart
    private void statistiqueGenres() {
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

            //************************************************
        } catch (SQLException e) {
            e.printStackTrace();
        }


    }

    private void statistiqueMoyenne() {
        try {
            int noteSup, noteInf;
            noteSup = noteInf = 0;

            Connection sqlConnection = gestionnaire_de_connection.getConnection();
            Statement sqlCommand = sqlConnection.createStatement();
            ResultSet dataReader = sqlCommand.executeQuery("SELECT count(*) as noteSup From note " +
                    "where Valeur_Note >= 10");
            if (dataReader.next())
                noteSup = dataReader.getInt("noteSup");

            sqlCommand = sqlConnection.createStatement();
            dataReader = sqlCommand.executeQuery("SELECT count(*) as noteInf From note " +
                    "where Valeur_Note < 10");
            if (dataReader.next())
                noteInf = dataReader.getInt("noteInf");
            ObservableList<PieChart.Data> pieChartDataM = FXCollections.observableArrayList(
                    new PieChart.Data("Moyenne >= 10", noteSup),
                    new PieChart.Data("Moyenne < 10", noteInf));
            pieChartMoyenne.setData(pieChartDataM);
            pieChartMoyenne.setTitle("Divérsité des moyennes");
            pieChartMoyenne.setClockwise(true);
            pieChartMoyenne.setLabelsVisible(true);
            pieChartMoyenne.setLabelLineLength(50);
            pieChartMoyenne.setStartAngle(180);
        } catch (SQLException s) {
            s.getStackTrace();
        }

    }

    private void statistiquebarChart() {
        try {
            Connection sqlConnection = gestionnaire_de_connection.getConnection();
            Statement sqlCommand = sqlConnection.createStatement();
            ResultSet dataReader = sqlCommand.executeQuery("select grp.libelle_grp as Groupe , count(etd.code_massar) as nbrEffectif\n" +
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

    private void statistiquesPersonnel_Load() {
        statistiqueGenres();
        statistiquebarChart();
        statistiqueMoyenne();
        Connection connection = gestionnaire_de_connection.getConnection();
        try {
            //nbre total etudiant
            Statement nbreStm = connection.createStatement();
            ResultSet nbreResult = nbreStm.executeQuery("select count(*) as totalEtu from etudiant");
            if (nbreResult.next()) NbreEtudiant.setText(String.valueOf(nbreResult.getInt("totalEtu")));

            //nbre total prof
            nbreStm = connection.createStatement();
            nbreResult = nbreStm.executeQuery("select count(*) as totalProf from professeur ");
            if (nbreResult.next()) nbreProf.setText(String.valueOf(nbreResult.getInt("totalProf")));

            //nbre total groupe
            nbreStm = connection.createStatement();
            nbreResult = nbreStm.executeQuery("select count(*) as totalgroupe from groupe ");
            if (nbreResult.next()) nbreGroupe.setText(String.valueOf(nbreResult.getInt("totalgroupe")));

            //nbre total etudiant ayant leur moyenne
            nbreStm = connection.createStatement();
            nbreResult = nbreStm.executeQuery("select count(*) as totaletuMoySup from note where Valeur_Note >= 10 ");
            if (nbreResult.next()) etumoySup.setText(String.valueOf(nbreResult.getInt("totaletuMoySup")));

            //nbre total etudiant ayant pas leur moyenne
            nbreStm = connection.createStatement();
            nbreResult = nbreStm.executeQuery("select count(*) as totaletuMoyInf from note where Valeur_Note < 10 ");
            if (nbreResult.next()) etuNoteInf.setText(String.valueOf(nbreResult.getInt("totaletuMoyInf")));
        } catch (SQLException s) {
            s.getStackTrace();
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
            ResultSet dataReader = sqlCommand.executeQuery
                    (
                            String.format
                                    (
                                            "select grp.*\n" +
                                                    "from groupe grp inner join enseignement en on grp.id_groupe = en.groupe#\n" +
                                                    "where en.professeur# = '%s'",
                                            Gestionnaire_De_Connection.prof_connecte
                                    )
                    );
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
            VboxMenu.getChildren().remove(btnGestionProf);
            btnStatistiquesetudiant.setVisible(true);
            btnNotes.setVisible(true);

        } else if (Gestionnaire_De_Connection.prof_connecte != null) {
            VboxMenu.getChildren().remove(btnStatistiques);
            VboxMenu.getChildren().remove(btnGestion);
            VboxMenu.getChildren().remove(btnStatistiquesetudiant);
            VboxMenu.getChildren().remove(btnGestionProf);
            btnListes.setVisible(true);
            btnAlert.setVisible(true);
        } else {
            ObservableList data = FXCollections.observableArrayList("CDD", "CDI");
            CB_contrat.setItems(data);
            VboxMenu.getChildren().remove(btnListes);
            VboxMenu.getChildren().remove(btnAlert);
            btnGestionProf.setVisible(true);
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
                                                        "and matiere# = (select top 1 en.matiere#\n" +
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
        } catch (
                SQLException e) {
            e.printStackTrace();
        }
    }
}
