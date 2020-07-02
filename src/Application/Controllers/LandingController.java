package Application.Controllers;

import javafx.animation.Animation;
import javafx.animation.KeyFrame;
import javafx.animation.KeyValue;
import javafx.animation.Timeline;
import javafx.beans.property.IntegerProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ProgressBar;
import javafx.scene.image.Image;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import javafx.util.Duration;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.net.URL;
import java.util.Random;
import java.util.ResourceBundle;

public class LandingController implements Initializable {

    @FXML
    private Button startlogin;

    private double ii = 0.1;

    @FXML
    private ProgressBar progressBar;

    public void goTologin() throws Exception {
        startlogin.setVisible(false);
        progressBar.setVisible(true);

        URL url = new File("src/Application/Views/Login.fxml").toURI().toURL();
        Parent root = FXMLLoader.load(url);
        Stage stage = new Stage();
        stage.setScene(new Scene(root));
        stage.getIcons().add(new Image(getClass().getResourceAsStream("../../resources/images/LoginIcons/icons8_Google_Wallet_50px.png")));
        stage.initStyle(StageStyle.UNDECORATED);

        stage.show();
    }

    @FXML
    public void btnClose_Click() {
        Stage stage = (Stage) startlogin.getScene().getWindow();
        stage.close();
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        Timeline timeline = new Timeline(
                new KeyFrame(Duration.ZERO, new KeyValue(progressBar.progressProperty(), 0)),
                new KeyFrame(Duration.seconds(10), e -> {

                    try {
                        goTologin();
                        btnClose_Click();
                    } catch (Exception ex) {
                        ex.printStackTrace();
                    }

                }, new KeyValue(progressBar.progressProperty(), 1))
        );
        timeline.play();
        timeline.setOnFinished(actionEvent -> System.out.println("finish"));

    }
}
