package Application.Controllers;

import Application.Data.Gestionnaire_De_Connection;
import Application.Main;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
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
        Gestionnaire_De_Connection connectionClass = new Gestionnaire_De_Connection();
        Connection connection = connectionClass.getConnection();
        String username = txtUsername.getText().toString();
        String password = txtPassword.getText().toString();

        String query = "select username, mot_de_passe from Etudiant where username = ? and mot_de_passe = ?";
        try {

            PreparedStatement preparedStatement = connection.prepareStatement(query);
            preparedStatement.setString(1, username);
            preparedStatement.setString(2, password);

            ResultSet Reader = preparedStatement.executeQuery();
            if (!Reader.next()) {
                wrongLbl.setVisible(true);
            } else {
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
        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    @FXML
    public void Inscrire_Click() throws Exception {
//
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
