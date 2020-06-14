package Application.Controllers;

import Application.Data.Gestionnaire_De_Connection;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.input.KeyEvent;
import javafx.scene.paint.Color;
import javafx.stage.Stage;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class AjoutActualiteController {

    Stage stage;

    @FXML
    private TextArea txt_Description;

    @FXML
    private Label lbl_limit;

    @FXML
    private Button btn_send;

    @FXML
    private TextField txt_Sujet;

    @FXML
    private void btnClose_Click(ActionEvent e) {
        stage = (Stage) ((Button) e.getSource()).getScene().getWindow();
        stage.close();
    }

    @FXML
    private void btn_send_click(ActionEvent e) {
        Gestionnaire_De_Connection connectionClass = new Gestionnaire_De_Connection();
        Connection connection = connectionClass.getConnection();
        try {
            Statement sqlCommand = connection.createStatement();
            sqlCommand.execute
                    (
                            String.format
                                    (
                                            "insert into ACTUALITE(sujet,description_actualite,ajoute_par_personnel#)\n" +
                                                    "values('%s','%s',%d)",
                                            txt_Sujet.getText(),
                                            txt_Description.getText(),
                                            Gestionnaire_De_Connection.personnel_connecte
                                    )
                    );
            btnClose_Click(e);
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
    }

    @FXML
    private void description_keyPressed(KeyEvent e) {
        if (txt_Description.getText().length() + 1 <= 0 || txt_Description.getText().length() + 1 > 250) {
            btn_send.setDisable(true);
            lbl_limit.setTextFill(Color.RED);
        } else {
            lbl_limit.setTextFill(Color.valueOf("#0075f3"));
            Integer calcul_char = 250 - (txt_Description.getText().length() + 1);
            lbl_limit.setText(calcul_char.toString());
        }
    }
}
