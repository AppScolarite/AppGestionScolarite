package Application.Controllers;

import Application.Data.Gestionnaire_De_Connection;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.sql.*;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.ResourceBundle;

public class InscrireController implements Initializable {

    @FXML
    private Button btnSignin;
    @FXML
    private Button btnSignup;
    @FXML
    private ComboBox CBGroupe;
    @FXML
    private CheckBox doublanCB;
    @FXML
    private RadioButton HommeRadio;
    @FXML
    private RadioButton FemmeRadio;
    //Inscrire form
    @FXML
    private TextField txtMassar;
    @FXML
    private TextField txtNomComplet;
    @FXML
    private DatePicker DateNaissance;
    @FXML
    private TextField txtMail;
    @FXML
    private TextField txtTelephone;
    @FXML
    private TextField txtAdresse;
    @FXML
    private TextField txtUsername;
    @FXML
    private PasswordField txtPassword12;


    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

        Gestionnaire_De_Connection gdc = new Gestionnaire_De_Connection();
        Connection connection = gdc.getConnection();
        ObservableList<String> data = FXCollections.observableArrayList();
        ObservableList<Integer> dataVal = FXCollections.observableArrayList();
        try {
            Statement statement = connection.createStatement();
            ResultSet rs = statement.executeQuery("SELECT * FROM groupe");
            while (rs.next()) {
                rs.getRow();
                String groups = rs.getString("libelle_grp");
                Integer valuGroups = rs.getInt("id_groupe");
                data.add(groups);
                dataVal.add(valuGroups);

            }
        } catch (SQLException e) {
            e.getErrorCode();
        }
//                CBGroupe.setValue(dataVal);
        CBGroupe.setItems(data);
//        CBGroupe.setPromptText("Groupes");

    }

    @FXML
    public void sinscrire_Click() throws Exception {
        Gestionnaire_De_Connection deConnection = new Gestionnaire_De_Connection();
        Connection connection = deConnection.getConnection();


        try {


            String nomComplet[] = txtNomComplet.getText().split(" ");
            String nom = nomComplet[0];
            String prenom = nomComplet[1];
            DateFormat dateFormat = new SimpleDateFormat("dd/mm/yyyy");
            Calendar calendar = Calendar.getInstance();
            java.util.Date currentDate = calendar.getTime();
            java.sql.Date date = new java.sql.Date(currentDate.getTime());
            //        Date date = new Date(currentDate.getTime());
            PreparedStatement preparedStatement = connection.prepareStatement("insert into ETUDIANT(code_massar,nom, prenom, date_naissance,date_inscription,email,telephone,a_deja_redouble, " +
                    "sexe, adresse,groupe#, username, mot_de_passe)values(?,?,?,?,?,?,?,?,?,?,?,?,?)");
//            ajouter dessus champs groupe hakka bla groupe raha khdama mais fach nzidou makatkhdemch !!


            preparedStatement.setString(1, txtMassar.getText().toString());
            preparedStatement.setString(2, nom);
            preparedStatement.setString(3, prenom);
            preparedStatement.setDate(4, java.sql.Date.valueOf(DateNaissance.getValue()));
            preparedStatement.setDate(5, date);
            preparedStatement.setString(6, txtMail.getText().toString());
            preparedStatement.setString(7, txtTelephone.getText().toString());
            preparedStatement.setString(10, txtAdresse.getText().toString());
            if (doublanCB.isSelected()) {
                doublanCB.setText("1");
                preparedStatement.setInt(8, Integer.valueOf(doublanCB.getText()));
                doublanCB.setText("doublant");
            } else {
                doublanCB.setText("0");
                preparedStatement.setInt(8, Integer.valueOf(doublanCB.getText()));
                doublanCB.setText("doublant");
            }
            if (HommeRadio.isSelected()) {
                preparedStatement.setString(9, HommeRadio.getText());
            }
            if (FemmeRadio.isSelected()) {
                preparedStatement.setString(9, FemmeRadio.getText());
            }
            preparedStatement.setInt(11, CBGroupe.getSelectionModel().getSelectedIndex() + 1);
            preparedStatement.setString(12, txtUsername.getText());
            preparedStatement.setString(13, txtPassword12.getText());
            preparedStatement.executeUpdate();


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
        } catch (SQLException e) {
            e.printStackTrace();
        } catch (NumberFormatException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
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
}
