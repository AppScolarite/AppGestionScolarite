package Application.Controllers;

import Application.Data.Gestionnaire_De_Connection;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.image.Image;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

import javax.xml.transform.Result;
import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.sql.*;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ResourceBundle;

public class LoginController implements Initializable {

    @FXML
    private Button btnClose;
    @FXML
    private Button btnSignup;
    @FXML
    private Button btnSignin;
    //Inscrire form
    @FXML
    private TextField txtMassar;
    @FXML
    private TextField txtNomComplet;
    @FXML
    private TextField txtnaissance;
    @FXML
    private TextField txtTelephone;
    @FXML
    private TextField txtAdresse;
    @FXML
    private PasswordField txtPassword12;
    @FXML
    private ComboBox CBGroupe;
    @FXML
    private TextField txtMail;
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
    public void GoBackSignIn_click() {
        try {
            URL url = new File("src/Application/Views/Login.fxml").toURI().toURL();
            Parent root = FXMLLoader.load(url);
//            Parent root = FXMLLoader.load(getClass().getResource("Login.fxml"));
            Stage stage = new Stage();
            stage.setScene(new Scene(root));
            stage.initStyle(StageStyle.UNDECORATED);
            Stage stage2 = (Stage) btnSignup.getScene().getWindow();
            stage2.close();
            stage.toBack();

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @FXML
    public void signIn_CLick() throws Exception {
        Gestionnaire_De_Connection connectionClass = new Gestionnaire_De_Connection();
        Connection connection =  connectionClass.getConnection();
        String username = txtUsername.getText().toString();
        String password = txtPassword.getText().toString();

        String query = "select username, mot_de_passe from Personnel where username = ? and mot_de_passe = ?";
        try {
            PreparedStatement preparedStatement = connection.prepareStatement(query);
            preparedStatement.setString(1,username);
            preparedStatement.setString(2,password);

            ResultSet Reader = preparedStatement.executeQuery();
            if(!Reader.next()){
                wrongLbl.setVisible(true);
            }
            else {
                URL url = new File("src/Application/Views/Home.fxml").toURI().toURL();
                Parent root = FXMLLoader.load(url);
                //        Parent root = FXMLLoader.load(getClass().getResource("Home.fxml"));
                Stage stage = new Stage();
                stage.setScene(new Scene(root));
                stage.getIcons().add(new Image(getClass().getResourceAsStream("../../resources/images/LoginIcons/icons8_Google_Wallet_50px.png")));
                stage.initStyle(StageStyle.UNDECORATED);
                Stage stage2 = (Stage) btnSignup.getScene().getWindow();
                stage2.close();
                stage.show();
            }
        }catch (IOException e){
            e.printStackTrace();
        }

    }

    @FXML
    public void Inscrire_Click() throws Exception {
        URL url = new File("src/Application/Views/inscrire.fxml").toURI().toURL();
//        Parent root = FXMLLoader.load(getClass().getResource("@../Application/Views/inscrire.fxml"));
        Parent root = FXMLLoader.load(url);
        Stage stage = new Stage();
        stage.setScene(new Scene(root));
        stage.initStyle(StageStyle.UNDECORATED);
        stage.show();

    }

    @FXML
    public void sinscrire_Click()throws Exception{
        Gestionnaire_De_Connection deConnection = new Gestionnaire_De_Connection();
        Connection connection = deConnection.getConnection();
        String nomComplet[] = txtNomComplet.getText().split(" ");
        String nom = nomComplet[0];
        String prenom = nomComplet[1];
        DateFormat dateFormat = new SimpleDateFormat("dd/mm/yyyy");
        java.util.Date date = new java.util.Date();
        PreparedStatement preparedStatement = connection.prepareStatement("insert into ETUDIANT(code_massar,nom, prenom, date_naissance,date_inscription,email,telephone,a_deja_redouble, " +
                "sexe, adresse,inscrit_par_personnel#, , username, mot_de_passe)values(?,?,?,?,?,?,?,?,?)");
        preparedStatement.setString(1,txtMassar.getText().toString());
        preparedStatement.setString(2,nom);
        preparedStatement.setString(3,prenom);
        preparedStatement.setDate(4, Date.valueOf(txtnaissance.getText()));


        Parent root = FXMLLoader.load(getClass().getResource("../Views/Login.fxml"));
        Stage stage = new Stage();
        stage.setScene(new Scene(root));
        stage.initStyle(StageStyle.UNDECORATED);
        Stage stage2 = (Stage) btnSignin.getScene().getWindow();
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle("Inscription");
        alert.setContentText("Votre inscription a été bien effectué");
        alert.showAndWait();
        stage2.close();
        stage.toBack();
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {

    }


}
