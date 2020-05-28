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
import java.net.URL;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ResourceBundle;

public class InscrireController  implements Initializable {

    @FXML
    private Button btnSignin;
    @FXML
    private Button btnSignup;
    @FXML
    private ComboBox CBGroupe;
    //Inscrire form
    @FXML
    private TextField txtMassar;
    @FXML
    private TextField txtNomComplet;
    @FXML
    private TextField txtnaissance;
    @FXML
    private TextField txtMail;
    @FXML
    private TextField txtTelephone;
    @FXML
    private TextField txtAdresse;
    @FXML
    private PasswordField txtPassword12;




    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

        Gestionnaire_De_Connection gdc = new Gestionnaire_De_Connection();
        Connection connection = gdc.getConnection();
        ObservableList<String> data = FXCollections.observableArrayList();
        try {
            Statement statement = connection.createStatement();
            ResultSet rs = statement.executeQuery("SELECT * FROM groupe");
            while (rs.next()) {
                rs.getRow();
                String groups = rs.getString("libelle_grp");
                data.add(groups);
            }
        }catch (SQLException e)
        {
            e.getErrorCode();
        }
        CBGroupe.setItems(data);

    }

    @FXML
    public void sinscrire_Click() throws Exception {
        Gestionnaire_De_Connection deConnection = new Gestionnaire_De_Connection();
        Connection connection = deConnection.getConnection();


        //String nomComplet[] = txtNomComplet.getText().split(" ");
//        String nom = nomComplet[0];
//        String prenom = nomComplet[1];
//        DateFormat dateFormat = new SimpleDateFormat("dd/mm/yyyy");
//        Calendar calendar = Calendar.getInstance();
//        java.util.Date currentDate = calendar.getTime();
//        Date date = new Date(currentDate.getTime());
//        PreparedStatement preparedStatement = connection.prepareStatement("insert into ETUDIANT(code_massar,nom, prenom, date_naissance,date_inscription,email,telephone,a_deja_redouble, " +
//                "sexe, adresse,inscrit_par_personnel#, , username, mot_de_passe)values(?,?,?,?,?,?,?,?,?)");
//        preparedStatement.setString(1,txtMassar.getText().toString());
//        preparedStatement.setString(2,nom);
//        preparedStatement.setString(3,prenom);
//        preparedStatement.setDate(4, Date.valueOf(txtnaissance.getText()));
//        preparedStatement.setDate(5,date);
//        preparedStatement.setString(6,txtMail.getText().toString());
//        preparedStatement.setString(7,txtTelephone.getText().toString());
//        preparedStatement.setString(8,txtAdresse.getText().toString());


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
