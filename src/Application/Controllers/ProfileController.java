package Application.Controllers;

import Application.Data.Gestionnaire_De_Connection;
import javafx.fxml.Initializable;

import java.net.URL;
import java.util.ResourceBundle;

import javafx.fxml.FXML;
import javafx.scene.control.CheckBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Pane;
import javafx.scene.text.Text;

public class ProfileController implements Initializable {

    @FXML
    private AnchorPane PaneProfil;

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
       //ETUDIANT
        date_insc_etd.setDisable(true);
        date_naiss_etd.setDisable(true);
        email_etd.setDisable(true);
        tel_etd.setDisable(true);
        pw_txt.setDisable(true);
        user_txt.setDisable(true);
        adr_etd.setDisable(true);

        //ENSEIGNANT
        date_naiss_esg.setDisable(true);
        date_ctr_esg.setDisable(true);
        email_esg.setDisable(true);
        tel_esg.setDisable(true);
        adr_esg.setDisable(true);
        pw_txt_esg.setDisable(true);
        user_txt_esg.setDisable(true);

        //Personnel
        date_naiss_admin.setDisable(true);
        tel_pers.setDisable(true);
        email_pers.setDisable(true);
        adresse_pers.setDisable(true);
        user_txt_pers.setDisable(true);
        pw_txt_pers.setDisable(true);





    }
}
