package Application.Controllers;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.event.EventType;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.image.Image;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundFill;
import javafx.scene.layout.CornerRadii;
import javafx.scene.layout.FlowPane;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.scene.text.TextAlignment;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

import java.io.File;
import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ResourceBundle;

public class GestionGroupeController implements Initializable {

    private Stage stage;

    @FXML
    private FlowPane floawLayout_matiere;

    @FXML
    private ComboBox<String> cb_branche;

    @FXML
    private void btnClose_Click(ActionEvent e) {
        stage = (Stage) ((Button) e.getSource()).getScene().getWindow();
        stage.close();
    }

    @FXML
    private void cb_branche_selected() {
        if (!cb_branche.getSelectionModel().getSelectedItem().equals("-Choisir-")) {
            Label label = new Label();
            label.setText(cb_branche.getSelectionModel().getSelectedItem());
            label.setAlignment(Pos.CENTER);
            label.setFont(Font.font("System", FontWeight.BOLD, 12));
            label.setTextAlignment(TextAlignment.CENTER);
            label.setPrefHeight(18.0);
            label.setPrefWidth(label.getText().length() + 80);
            CornerRadii radius = new CornerRadii(30);
            label.setBackground(new Background(new BackgroundFill(Color.DODGERBLUE, radius, Insets.EMPTY)));
            floawLayout_matiere.setHgap(10);
            floawLayout_matiere.setVgap(10);
            floawLayout_matiere.getChildren().add(label);

            cb_branche.setOnAction(null);
            cb_branche.getItems().remove(cb_branche.getSelectionModel().getSelectedItem().toString());
            cb_branche.getSelectionModel().selectFirst();
            cb_branche.setOnAction(new EventHandler<ActionEvent>() {
                @Override
                public void handle(ActionEvent actionEvent) {
                    cb_branche_selected();
                }
            });
        }
    }

    @FXML
    public void matiere_click(MouseEvent e) {
        cb_branche.getItems().add(((Label) e.getSource()).getText());
        this.floawLayout_matiere.getChildren().remove(e.getSource());
    }

    @FXML
    public void btn_gestion_matiere_click(MouseEvent e) {
        try {
            FXMLLoader loader = new FXMLLoader(new File("src/Application/Views/GestionMatiere.fxml").toURI().toURL());
            Parent root = loader.load();
            Scene scene = new Scene(root);
            scene.setFill(Color.valueOf("transparent"));
            Stage stage = new Stage(StageStyle.TRANSPARENT);
            stage.setScene(scene);
            stage.show();
        } catch (IOException ex) {
            ex.printStackTrace();
        }
    }

    @FXML
    public void btn_gestion_branche_click(MouseEvent e) {
        try {
            FXMLLoader loader = new FXMLLoader(new File("src/Application/Views/GestionBranche.fxml").toURI().toURL());
            Parent root = loader.load();
            Scene scene = new Scene(root);
            scene.setFill(Color.valueOf("transparent"));
            Stage stage = new Stage(StageStyle.TRANSPARENT);
            stage.setScene(scene);
            stage.show();
        } catch (IOException ex) {
            ex.printStackTrace();
        }
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

        cb_branche.getItems().add("fr");
        cb_branche.getItems().add("ang");
        cb_branche.getItems().add("c#");
        cb_branche.getItems().add("php");

    }

    @FXML
    public void refresh_click(MouseEvent mouseEvent) {
    }
}
