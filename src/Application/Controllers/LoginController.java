package Application.Controllers;

import Application.Data.Gestionnaire_De_Connection;
import Application.Main;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.fxml.LoadListener;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.image.Image;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

import javax.xml.transform.Result;
import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.sql.*;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;
import java.util.ResourceBundle;
import java.sql.Date;
import java.util.logging.Level;
import java.util.logging.Logger;


public class LoginController implements Initializable {

    @FXML
    private Button btnClose;
    @FXML
    private Button btnSignup;
    @FXML
    private Button btnSignin;
    @FXML
    private TextField txtUsername;
    @FXML
    private PasswordField txtPassword;
    @FXML
    private Label wrongLbl;

    @FXML
    public void CLose_Login_CLick() {
        Stage stage = (Stage) btnClose.getScene().getWindow();
        stage.close();
    }

    @FXML
    public void signIn_CLick() throws Exception {
        Gestionnaire_De_Connection.etudiant_connecte = null;
        Gestionnaire_De_Connection.nom_connecte = null;
        Gestionnaire_De_Connection.personnel_connecte = 0;
        Gestionnaire_De_Connection.prof_connecte = null;

        Gestionnaire_De_Connection connectionClass = new Gestionnaire_De_Connection();
        Connection connection = connectionClass.getConnection();
        String username = txtUsername.getText();
        String password = txtPassword.getText();

//      Test Pour Etudiant
        String query = "select code_massar,concat(nom, ' ' , prenom) as nomEtudiant, username, mot_de_passe from Etudiant where username = ? and mot_de_passe = ?";
        try {

            PreparedStatement preparedStatement = connection.prepareStatement(query);
            preparedStatement.setString(1, username);
            preparedStatement.setString(2, password);

            ResultSet Reader = preparedStatement.executeQuery();
            if (!Reader.next()) {
                wrongLbl.setVisible(true);
            } else {
                Gestionnaire_De_Connection.etudiant_connecte = Reader.getString("code_massar");
                Gestionnaire_De_Connection.nom_connecte = Reader.getString("nomEtudiant");
                URL url = new File("src/Application/Views/Home.fxml").toURI().toURL();
                Parent root = FXMLLoader.load(url);
                Stage stage = new Stage();
                stage.setScene(new Scene(root));
                stage.getIcons().add(new Image(getClass().getResourceAsStream("../../resources/images/LoginIcons/icons8_Google_Wallet_50px.png")));
                stage.initStyle(StageStyle.UNDECORATED);


                Stage stage2 = (Stage) btnSignup.getScene().getWindow();
                stage2.close();
                stage.show();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }


        String queryPersonnel = "  select id_personnel,concat(nom_personnel,' ',prenom_personnel) as NomPersonnel, username, mot_de_passe from personnel where username = ? and mot_de_passe = ?";
        try {
            PreparedStatement preparedStatementP = connection.prepareStatement(queryPersonnel);
            preparedStatementP.setString(1, username);
            preparedStatementP.setString(2, password);

            ResultSet resultSetP = preparedStatementP.executeQuery();
            if (!resultSetP.next()) {
                wrongLbl.setVisible(true);
            } else {
                Gestionnaire_De_Connection.prof_connecte = null;
                Gestionnaire_De_Connection.etudiant_connecte = null;
                Gestionnaire_De_Connection.personnel_connecte = Integer.valueOf(resultSetP.getString("id_personnel"));
                Gestionnaire_De_Connection.nom_connecte = resultSetP.getString("NomPersonnel");
                URL url = new File("src/Application/Views/Home.fxml").toURI().toURL();
                Parent root = FXMLLoader.load(url);
                Stage stage = new Stage();
                stage.setScene(new Scene(root));
                stage.getIcons().add(new Image(getClass().getResourceAsStream("../../resources/images/LoginIcons/icons8_Google_Wallet_50px.png")));
                stage.initStyle(StageStyle.UNDECORATED);


                Stage stage2 = (Stage) btnSignup.getScene().getWindow();
                stage2.close();
                stage.show();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

//        Test Pour Prof
        String queryProf = "  select Code_Pro_Nationnal,concat(nom,' ',prenom) as NomProf, username, mot_de_passe from PROFESSEUR where username = ? and mot_de_passe = ?";
        try {
            PreparedStatement preparedStatementPr = connection.prepareStatement(queryProf);
            preparedStatementPr.setString(1, username);
            preparedStatementPr.setString(2, password);

            ResultSet resultSetPr = preparedStatementPr.executeQuery();
            if (!resultSetPr.next()) {
                wrongLbl.setVisible(true);
            } else {
                Gestionnaire_De_Connection.etudiant_connecte = null;
                Gestionnaire_De_Connection.personnel_connecte = 0;
                Gestionnaire_De_Connection.prof_connecte = resultSetPr.getString("Code_Pro_Nationnal");
                Gestionnaire_De_Connection.nom_connecte = resultSetPr.getString("NomProf");
                URL url = new File("src/Application/Views/Home.fxml").toURI().toURL();
                Parent root = FXMLLoader.load(url);
                Stage stage = new Stage();
                stage.setScene(new Scene(root));
                stage.getIcons().add(new Image(getClass().getResourceAsStream("../../resources/images/LoginIcons/icons8_Google_Wallet_50px.png")));
                stage.initStyle(StageStyle.UNDECORATED);

                Stage stage2 = (Stage) btnSignup.getScene().getWindow();
                stage2.close();
                stage.show();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }


    }

    @FXML
    public void Inscrire_Click() throws Exception {
        URL url = new File("src/Application/Views/inscrire.fxml").toURI().toURL();
        Parent root = FXMLLoader.load(url);
        Stage stage = new Stage();
        stage.setScene(new Scene(root));
        stage.initStyle(StageStyle.UNDECORATED);

        stage.show();
    }


    @Override
    public void initialize(URL location, ResourceBundle resources) {


    }


}
