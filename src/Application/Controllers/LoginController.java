package Application.Controllers;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

import java.net.URL;
import java.util.ResourceBundle;

public class LoginController implements Initializable {

    @FXML
        private Button btnClose;
    @FXML
        private Button btnSignup;


    @FXML
    public void CLose_Login_CLick(){
        Stage stage =(Stage) btnClose.getScene().getWindow();
        stage.close();
    }

    @FXML
    public void GoBackSignIn_click(){
        try {
            Parent root = FXMLLoader.load(getClass().getResource("Login.fxml"));
            Stage stage = new Stage();
            stage.setScene(new Scene(root));
            stage.initStyle(StageStyle.UNDECORATED);
            Stage stage2 = (Stage) btnSignup.getScene().getWindow();
            stage2.close();
            stage.toBack();

        }catch (Exception e){
            e.printStackTrace();
        }
    }

    @FXML
    public void signIn_CLick() throws  Exception{
        Parent root = FXMLLoader.load(getClass().getResource("Home.fxml"));
        Stage stage = new Stage();
        stage.setScene(new Scene(root));
        stage.initStyle(StageStyle.UNDECORATED);
        Stage stage2 = (Stage) btnSignup.getScene().getWindow();
        stage2.close();
        stage.show();
    }

    @FXML
    public void Inscrire_Click() throws Exception{
        Parent root = FXMLLoader.load(getClass().getResource("inscrire.fxml"));
        Stage stage = new Stage();
        stage.setScene(new Scene(root));
        stage.initStyle(StageStyle.UNDECORATED);
        stage.show();

    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {

     }


}
