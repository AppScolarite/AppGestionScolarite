package Application.Controllers;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.image.Image;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

import java.io.File;
import java.net.URL;

public class LandingController {
    
    @FXML
    private Button startlogin;

    public void goTologin() throws Exception{
        URL url = new File("src/Application/Views/Login.fxml").toURI().toURL();
        Parent root = FXMLLoader.load(url);
        Stage stage = new Stage();
        stage.setScene(new Scene(root));
        stage.getIcons().add(new Image(getClass().getResourceAsStream("../../resources/images/LoginIcons/icons8_Google_Wallet_50px.png")));
        stage.initStyle(StageStyle.UNDECORATED);


        Stage stage2 = (Stage) startlogin.getScene().getWindow();
        stage2.close();
        stage.show();
    }

    @FXML
    public void btnClose_Click(){
        Stage stage = (Stage) startlogin.getScene().getWindow();
        stage.close();
    }
}
