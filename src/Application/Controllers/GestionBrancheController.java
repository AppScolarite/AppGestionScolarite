package Application.Controllers;

import Application.Data.Gestionnaire_De_Connection;
import Application.Models.GestionBrancheViewModel;
import Application.Models.GestionProfViewModel;
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

public class GestionBrancheController implements Initializable {

    private Gestionnaire_De_Connection gestionnaire_de_connection = new Gestionnaire_De_Connection();

    Stage stage;
    @FXML
    private TableView<GestionBrancheViewModel> brancheTableView;

    @FXML
    private TableColumn<GestionBrancheViewModel, Integer> refbranche_col;

    @FXML
    private TableColumn<GestionBrancheViewModel, String> libelle_col;

    @FXML
    private TableColumn<GestionBrancheViewModel, String> detail_col;

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
    public void initialize(URL url, ResourceBundle resourceBundle)   {
        try {
            listerBranche();
        }catch (SQLException q){

        }
    }

    ObservableList<GestionBrancheViewModel> Branches() throws SQLException {
        Connection connection = gestionnaire_de_connection.getConnection();
        ObservableList<GestionBrancheViewModel> branches = FXCollections.observableArrayList();

        Statement statement = connection.createStatement();
        ResultSet resultSet = statement.executeQuery("select * from branche ");
        while(resultSet.next()){
            branches.addAll(new GestionBrancheViewModel(
                    resultSet.getInt("id_branche"),
                    resultSet.getString("libelle_branche"),
                    resultSet.getString("description_branche")
            ));
        }
        return branches;
    }

    public void listerBranche() throws SQLException{
        refbranche_col.setCellValueFactory(new PropertyValueFactory("refBranche"));
        libelle_col.setCellValueFactory(new PropertyValueFactory("libelleBranche"));
        detail_col.setCellValueFactory(new PropertyValueFactory("detailBranche"));
        brancheTableView.setItems(Branches());
    }
    @FXML
    private void supprimerBranche(){
        GestionBrancheViewModel branche = (GestionBrancheViewModel) brancheTableView.getSelectionModel().getSelectedItem();
        if (branche == null) {
            System.out.println("aucun etudiant a supprimer !");
            return;
        }
        Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
        alert.setTitle("Supression d'une branche !");
        alert.setContentText("Etes vous totalement sur de vouloir supprimer une branche <" + branche.getRefBranche() + "-" + branche.getLibelleBranche() +"> ??\n");
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
                                                "delete from branche where id_branche = '%s' ;" +
                                                        "delete from groupe where branche# = '%s';",
                                                branche.getRefBranche(), branche.getRefBranche()
                                        )
                        );
                brancheTableView.getItems().remove(brancheTableView.getSelectionModel().getSelectedItem());
                brancheTableView.refresh();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
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
            brancheTableView.refresh();

        } catch (SQLException ex) {
            ex.printStackTrace();
        }
    }
}
