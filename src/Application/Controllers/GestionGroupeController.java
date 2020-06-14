package Application.Controllers;

import Application.Data.Gestionnaire_De_Connection;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
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
import javafx.scene.image.ImageView;
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
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ResourceBundle;

public class GestionGroupeController implements Initializable {

    private Stage stage;

    @FXML
    private FlowPane floawLayout_matiere;

    @FXML
    private FlowPane floawLayout_prof;

    @FXML
    private ImageView id_refresh;

    @FXML
    private ComboBox<String> cb_branche;
    ObservableList<String> Mt_list = FXCollections.observableArrayList();

    @FXML
    private ComboBox<String> cb_prof;
    ObservableList<String> Pr_list = FXCollections.observableArrayList();



    @FXML
    private ComboBox<String> cb_brc;
    ObservableList<String> Br_list = FXCollections.observableArrayList();

    @FXML
    private ComboBox<String> cb_niveau;
    ObservableList<String> Nv_list = FXCollections.observableArrayList();

    @FXML
    private TextField titleGrp;

    @FXML
    private TextField mg_txt;

    @FXML
    private TextField title;

    @FXML
    private Button btn_ajt;

    private Gestionnaire_De_Connection dbConnection = new Gestionnaire_De_Connection();

    @FXML
    private void btnClose_Click(ActionEvent e) {
        stage = (Stage) ((Button) e.getSource()).getScene().getWindow();
        stage.close();
    }


    @FXML
    private void cb_branche_selected() {

        //bach yjib title
        title.setText(titleGrp.getText());

        if (!cb_branche.getSelectionModel().getSelectedItem().equals("-Choisir-")) {
            Label label = new Label();
            label.setText(cb_branche.getSelectionModel().getSelectedItem());
            label.setAlignment(Pos.CENTER);
            label.setFont(Font.font("System", FontWeight.BOLD, 12));
            label.setTextAlignment(TextAlignment.CENTER);
            label.setPrefHeight(18.0);
            label.setPrefWidth(label.getText().length() + 80);
            label.setOnMouseClicked(new EventHandler<MouseEvent>() {
                @Override
                public void handle(MouseEvent mouseEvent) {
                    matiere_click(mouseEvent);
                }
            });
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
    public void Br_Selected(ActionEvent event) throws SQLException {

        String lib_brc = cb_brc.getSelectionModel().getSelectedItem().toString();
        Connection cnx = dbConnection.getConnection();
        Statement stm = cnx.createStatement();
        ResultSet rs = stm.executeQuery(
                String.format(
                        "select prerequis_note \n" + "from BRANCHE \n" + "where libelle_branche='%s' \n", lib_brc
                )
        );
        if (rs.next()) {
            String requis = rs.getString("prerequis_note");
            mg_txt.setText(requis);
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



    @FXML
    void actualiser(MouseEvent event) {

        try {
            Connection con = (Connection) dbConnection.getConnection();
            String sql = "SELECT  LBL_Matiere FROM MATIERE ";
            ResultSet rs = con.createStatement().executeQuery(sql);
            while (rs.next()) {
                Mt_list.add(rs.getString("LBL_Matiere"));
            }
            cb_branche.setItems(Mt_list);
        } catch (Exception e) {
            // TODO: handle exception
        }

        try {
            Connection con = (Connection) dbConnection.getConnection();
            String sql = "SELECT  libelle_branche FROM branche ";
            ResultSet rs = con.createStatement().executeQuery(sql);
            while (rs.next()) {
                Br_list.add(rs.getString("libelle_branche"));
            }
            cb_brc.setItems(Br_list);
        } catch (Exception e) {
            // TODO: handle exception
        }

        try {
            Connection con = (Connection) dbConnection.getConnection();
            String sql = "SELECT  libelle_niveau FROM niveau ";
            ResultSet rs = con.createStatement().executeQuery(sql);
            while (rs.next()) {
                Nv_list.add(rs.getString("libelle_niveau"));
            }
            cb_niveau.setItems(Nv_list);
        } catch (Exception e) {
            // TODO: handle exception
        }


    }



    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

        try {
            Connection con = (Connection) dbConnection.getConnection();
            String sql = "SELECT  LBL_Matiere FROM MATIERE ";
            ResultSet rs = con.createStatement().executeQuery(sql);
            while (rs.next()) {
                Mt_list.add(rs.getString("LBL_Matiere"));
            }
            cb_branche.setItems(Mt_list);
        } catch (Exception e) {
            // TODO: handle exception
        }

        try {
            Connection con = (Connection) dbConnection.getConnection();
            String sql = "SELECT  libelle_branche FROM branche ";
            ResultSet rs = con.createStatement().executeQuery(sql);
            while (rs.next()) {
                Br_list.add(rs.getString("libelle_branche"));
            }
            cb_brc.setItems(Br_list);
        } catch (Exception e) {
            // TODO: handle exception
        }

        try {
            Connection con = (Connection) dbConnection.getConnection();
            String sql = "SELECT  libelle_niveau FROM niveau ";
            ResultSet rs = con.createStatement().executeQuery(sql);
            while (rs.next()) {
                Nv_list.add(rs.getString("libelle_niveau"));
            }
            cb_niveau.setItems(Nv_list);
        } catch (Exception e) {
            // TODO: handle exception
        }

        try {
            Connection con = (Connection) dbConnection.getConnection();
            String sql = "SELECT  Nom FROM PROFESSEUR ";
            ResultSet rs = con.createStatement().executeQuery(sql);
            while (rs.next()) {
                Pr_list.add(rs.getString("Nom"));
            }
            cb_prof.setItems(Pr_list);
        } catch (Exception e) {
            // TODO: handle exception
        }



    }

    @FXML
    public void refresh_click(MouseEvent mouseEvent) {
    }

    public void Nouveau(ActionEvent actionEvent) {
        Connection conection = dbConnection.getConnection();
        try {
            Statement sqlCommand = conection.createStatement();
            sqlCommand.execute
                    (
                            String.format
                                    (
                                            "INSERT INTO Groupe values (%d,%d,'%s')",
                                            (cb_brc.getSelectionModel().getSelectedIndex() + 1),
                                            (cb_niveau.getSelectionModel().getSelectedIndex() + 1),
                                            titleGrp.getText()
                                    )
                    );
            sqlCommand = conection.createStatement();
            sqlCommand.execute
                    (
                            String.format
                                    (
                                            ""
                                    )
                    );

        } catch (SQLException ex) {
            ex.printStackTrace();
        }
    }
}
