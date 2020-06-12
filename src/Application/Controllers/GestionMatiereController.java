package Application.Controllers;

import Application.Data.Gestionnaire_De_Connection;
import Application.Models.GestionBrancheViewModel;
import Application.Models.GestionMatiereViewModel;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;

import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.Region;
import javafx.stage.Stage;

import java.net.URL;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Optional;
import java.util.ResourceBundle;

public class GestionMatiereController implements Initializable {

    private Stage stage;
    Gestionnaire_De_Connection gestionnaire_de_connection = new Gestionnaire_De_Connection();
    @FXML
    private TableView<GestionMatiereViewModel> matiereTableView;

    @FXML
    private TableColumn<GestionMatiereViewModel, Integer> refMatiere_col;
    @FXML
    private TableColumn<GestionMatiereViewModel, String> libelleMati_col;
    @FXML
    private TableColumn<GestionMatiereViewModel, Integer> coeffMatiere_col;

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
    public void initialize(URL url, ResourceBundle resourceBundle)   {
        try {
            listerMatiere();
        }catch (SQLException q){

        }
    }

    ObservableList<GestionMatiereViewModel> Matieres() throws SQLException {
        Connection connection = gestionnaire_de_connection.getConnection();
        ObservableList<GestionMatiereViewModel> matieres = FXCollections.observableArrayList();

        Statement statement = connection.createStatement();
        ResultSet resultSet = statement.executeQuery("select * from matiere ");
        while(resultSet.next()){
            matieres.addAll(new GestionMatiereViewModel(
                    resultSet.getInt("id_matiere"),
                    resultSet.getString("LBL_Matiere"),
                    resultSet.getInt("Coeff")
            ));
        }
        return matieres;
    }

    public void listerMatiere() throws SQLException{
        refMatiere_col.setCellValueFactory(new PropertyValueFactory("refMatiere"));
        libelleMati_col.setCellValueFactory(new PropertyValueFactory("LibelleMatiere"));
        coeffMatiere_col.setCellValueFactory(new PropertyValueFactory("coeffMatiere"));
        matiereTableView.setItems(Matieres());
    }
    @FXML
    private void supprimerMatiere(){
        GestionMatiereViewModel matiere = (GestionMatiereViewModel) matiereTableView.getSelectionModel().getSelectedItem();
        if (matiere == null) {
            System.out.println("aucun etudiant a supprimer !");
            return;
        }
        Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
        alert.setTitle("Supression d'une branche !");
        alert.setContentText("Etes vous totalement sur de vouloir supprimer une branche <" + matiere.getRefMatiere() + "-" + matiere.getLibelleMatiere() +"> ??\n");
        alert.getDialogPane().setMinHeight(Region.USE_PREF_SIZE);
        alert.setHeight(400);
        Optional<ButtonType> reponse = alert.showAndWait();
        if (reponse.get().equals(ButtonType.OK)) {
            try {
                Connection connection = gestionnaire_de_connection.getConnection();
                Statement sqlCommand = connection.createStatement();
                sqlCommand.execute
                        (
                                String.format
                                        (
                                                "delete from matiere where id_matiere = '%s' ;" +
                                                        "delete from enseignement where matiere# = '%s';" +
                                                        "delete from NOTE where matiere# = '%s'",
                                                matiere.getRefMatiere(), matiere.getRefMatiere(),matiere.getRefMatiere()
                                        )
                        );
                matiereTableView.getItems().remove(matiereTableView.getSelectionModel().getSelectedItem());
                matiereTableView.refresh();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
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
