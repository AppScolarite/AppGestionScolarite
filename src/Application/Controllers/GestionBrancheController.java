package Application.Controllers;

import Application.Data.Gestionnaire_De_Connection;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;

import java.net.URL;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ResourceBundle;

public class GestionBrancheController implements Initializable {

    private Gestionnaire_De_Connection gestionnaire_de_connection = new Gestionnaire_De_Connection();

    Stage stage;

    @FXML
    private TextField txt_branche;

    @FXML
    private TextField txt_seuil;

    @FXML
    private TextArea txt_description;

    @FXML
    private void btnClose_clicked(MouseEvent e) {
        stage = (Stage) ((ImageView) e.getSource()).getScene().getWindow();
        stage.close();
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

    }

    @FXML
    public void btnAjouter_clicked(ActionEvent e) {
        Connection connection = gestionnaire_de_connection.getConnection();
        try {
            Statement sqlCommand = connection.createStatement();
            sqlCommand.execute(
                    String.format
                            (
                                    "INSERT INTO [dbo].[BRANCHE]\n" +
                                            "           ([libelle_branche]\n" +
                                            "           ,[prerequis_note]\n" +
                                            "           ,[description_branche])\n" +
                                            "     VALUES\n" +
                                            "          ('%s',%s,'%s')",
                                    txt_branche.getText().replace("'", "''"),
                                    txt_seuil.getText(),
                                    txt_description.getText().replace("'", "''")
                            )
            );
            txt_branche.setText("");
            txt_seuil.setText("");
            txt_description.setText("");

        } catch (SQLException ex) {
            ex.printStackTrace();
        }
    }
}
