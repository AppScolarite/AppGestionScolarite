package Application.Controllers;

import Application.Data.Gestionnaire_De_Connection;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;

import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;

import java.net.URL;
import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ResourceBundle;

public class GestionMatiereController implements Initializable {

    private Stage stage;
    Gestionnaire_De_Connection gestionnaire_de_connection = new Gestionnaire_De_Connection();

    @FXML
    public TextField txt_matiere;

    @FXML
    public TextField txt_coeff;

    @FXML
    private void btnClose_clicked(MouseEvent e) {
        stage = (Stage) ((ImageView) e.getSource()).getScene().getWindow();
        stage.close();
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

    }

    @FXML
    public void ajoutmatiere_clicked(ActionEvent actionEvent) {
        Connection connection = gestionnaire_de_connection.getConnection();
        try {
            Statement sqlCommand = connection.createStatement();
            sqlCommand.execute(
                    String.format
                            (
                                    "INSERT INTO [dbo].[MATIERE]\n" +
                                            "           ([LBL_Matiere]\n" +
                                            "           ,[Date_Ajout]\n" +
                                            "           ,[Coeff])\n" +
                                            "     VALUES\n" +
                                            "           ('%s', GETDATE(), %s )",
                                    txt_matiere.getText().replace("'", "''"),
                                    txt_coeff.getText()
                            )
            );
            txt_matiere.setText("");
            txt_coeff.setText("");

        } catch (SQLException ex) {
            ex.printStackTrace();
        }
    }
}
