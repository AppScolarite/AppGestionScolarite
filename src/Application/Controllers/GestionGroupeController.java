package Application.Controllers;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.Event;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.*;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundFill;
import javafx.scene.layout.CornerRadii;
import javafx.scene.layout.FlowPane;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.FontPosture;
import javafx.scene.text.FontWeight;
import javafx.scene.text.TextAlignment;
import javafx.stage.Stage;

import java.net.URL;
import java.util.ResourceBundle;

public class GestionGroupeController implements Initializable {

    private Stage stage;

    @FXML
    private FlowPane floawLayout_matiere;

    @FXML
    private ComboBox cb_branche;

    @FXML
    private void btnClose_Click(ActionEvent e) {
        stage = (Stage) ((Button) e.getSource()).getScene().getWindow();
        stage.close();
    }

    @FXML
    private void cb_branche_selected(ActionEvent e) {
        Label label = new Label();
        label.setText(cb_branche.getSelectionModel().getSelectedItem().toString());
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
        cb_branche.getItems().remove(cb_branche.getSelectionModel().getSelectedItem().toString());
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {


        cb_branche.getItems().add("fr");
        cb_branche.getItems().add("ang");
        cb_branche.getItems().add("c#");
        cb_branche.getItems().add("php");

    }
}
