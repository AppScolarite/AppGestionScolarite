package Application.Controllers;

import Application.Data.Gestionnaire_De_Connection;
import javafx.fxml.Initializable;

import java.net.URL;
import java.sql.Connection;
import java.sql.ResultSet;
import java.time.LocalDate;
import java.util.ResourceBundle;

import javafx.fxml.FXML;
import javafx.scene.control.CheckBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.Pane;
import javafx.scene.text.Text;

public class ProfileController implements Initializable {


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
    //*********************************************************************//
    Gestionnaire_De_Connection gest_cnx = new Gestionnaire_De_Connection();

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        /*ETUDIANT
        date_insc_etd.setDisable(true);
        date_naiss_etd.setDisable(true);
        email_etd.setDisable(true);
        tel_etd.setDisable(true);
        pw_txt.setDisable(true);
        user_txt.setDisable(true);
        adr_etd.setDisable(true);


        date_naiss_esg.setDisable(true);
        date_ctr_esg.setDisable(true);
        email_esg.setDisable(true);
        tel_esg.setDisable(true);
        adr_esg.setDisable(true);
        pw_txt_esg.setDisable(true);
        user_txt_esg.setDisable(true);


        date_naiss_admin.setDisable(true);
        tel_pers.setDisable(true);
        email_pers.setDisable(true);
        adresse_pers.setDisable(true);
        user_txt_pers.setDisable(true);
        pw_txt_pers.setDisable(true);*/

        //Si l'étudiant qui est connecté
        System.out.println(Gestionnaire_De_Connection.etudiant_connecte);
        if (Gestionnaire_De_Connection.etudiant_connecte != null) {
            Pane_etd.toFront();
            try {
                Connection con = gest_cnx.getConnection();
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

                    String red = rs.getString("a_deja_redouble");
                    if (red == "True") {
                        ck_redouble.setSelected(true);
                    } else {
                        ck_redouble.setSelected(false);
                    }

                    String sq = "SELECT  g.libelle_grp FROM groupe g inner join etudiant e on g.id_groupe = e.groupe# where e.groupe#= %d '" + rs.getString("groupe#") + "'";
                    ResultSet rd = con.createStatement().executeQuery(sq);
                    if (rd.next()) {
                        txt_groupe.setText(rs.getString("g.libelle_grp"));
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
                Connection con = (Connection) gest_cnx.getConnection();
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
                Connection con = (Connection) gest_cnx.getConnection();
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
}
