package sample;

import java.net.URL;
import java.sql.*;
import java.util.ArrayList;
import java.util.ResourceBundle;

import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextField;

public class Controller {

    @FXML
    private ComboBox<Agent> comboBoxAgents;

    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;

    @FXML
    private TextField txtAgentId;

    @FXML
    private TextField txtAgentFirstName;

    @FXML
    private TextField txtAgentLastName;

    @FXML
    private TextField txtAgentMiddleInitial;

    @FXML
    private TextField txtAgentBusinessPhone;

    @FXML
    private TextField txtAgentEmail;

    @FXML
    private TextField txtAgentPosition;

    @FXML
    private TextField txtAgentAgencyId;

    @FXML
    private Button btnEdit;

    @FXML
    private Button btnSave;

    @FXML
    void initialize() {
        assert comboBoxAgents != null : "fx:id=\"comboBoxAgents\" was not injected: check your FXML file 'sample.fxml'.";
        assert txtAgentId != null : "fx:id=\"txtAgentId\" was not injected: check your FXML file 'sample.fxml'.";
        assert txtAgentFirstName != null : "fx:id=\"txtAgentFirstName\" was not injected: check your FXML file 'sample.fxml'.";
        assert txtAgentLastName != null : "fx:id=\"txtAgentLastName\" was not injected: check your FXML file 'sample.fxml'.";
        assert txtAgentMiddleInitial != null : "fx:id=\"txtAgentMiddleInitial\" was not injected: check your FXML file 'sample.fxml'.";
        assert txtAgentBusinessPhone != null : "fx:id=\"txtAgentBusinessPhone\" was not injected: check your FXML file 'sample.fxml'.";
        assert txtAgentEmail != null : "fx:id=\"txtAgentEmail\" was not injected: check your FXML file 'sample.fxml'.";
        assert txtAgentPosition != null : "fx:id=\"txtAgentPosition\" was not injected: check your FXML file 'sample.fxml'.";
        assert txtAgentAgencyId != null : "fx:id=\"txtAgentAgencyId\" was not injected: check your FXML file 'sample.fxml'.";
        assert btnEdit != null : "fx:id=\"btnEdit\" was not injected: check your FXML file 'sample.fxml'.";
        assert btnSave != null : "fx:id=\"btnSave\" was not injected: check your FXML file 'sample.fxml'.";

        btnSave.setDisable(true);
        txtAgentId.setDisable(true);
        textDisabled(true);

        agentDropdownData();

        comboBoxAgents.getSelectionModel().selectedItemProperty().addListener(new ChangeListener<Agent>()
        {
            @Override
            public void changed(ObservableValue<? extends Agent> observable, Agent oldValue, Agent newValue)
            {
                if (newValue != null)
                {
                    txtAgentId.setText(newValue.getAgentId() + "");
                    txtAgentFirstName.setText(newValue.getAgentFirstName());
                    txtAgentMiddleInitial.setText(newValue.getAgentMiddleInitial());
                    txtAgentLastName.setText(newValue.getAgentLastName());
                    txtAgentBusinessPhone.setText(newValue.getAgentBusinessPhone());
                    txtAgentEmail.setText(String.valueOf(newValue.getAgentEmail()));
                    txtAgentPosition.setText(String.valueOf(newValue.getAgentPosition()));
                    txtAgentAgencyId.setText(String.valueOf(newValue.getAgentAgencyId()));
                }
            }
        });
    }

    /*-------------------------[ action events ]-------------------------*/
    
    // edit button action event - enable text fields
    public void editButtonAction(ActionEvent actionEvent)
    {
        textDisabled(false);
        btnSave.setDisable(false);
        btnEdit.setDisable(true);
    }

    // save button action event and update query
    public void saveButtonAction(ActionEvent actionEvent)
    {
        btnSave.setDisable(true);
        btnEdit.setDisable(false);
        textDisabled(true);

        // update database
        try
        {
            Connection conn = ConnectionManager.getConnection();
            String query = "UPDATE agents SET `AgtFirstName`=?, `AgtMiddleInitial`=?, `AgtLastName`=?, `AgtBusPhone`=?, `AgtEmail`=?, `AgtPosition`=?, `AgencyId`=? WHERE `AgentId`=?";
            PreparedStatement prepareStmt = conn.prepareStatement(query);
            prepareStmt.setString(1, txtAgentFirstName.getText());
            prepareStmt.setString(2, txtAgentMiddleInitial.getText());
            prepareStmt.setString(3, txtAgentLastName.getText());
            prepareStmt.setString(4, txtAgentBusinessPhone.getText());
            prepareStmt.setString(5, txtAgentEmail.getText());
            prepareStmt.setString(6, txtAgentPosition.getText());
            prepareStmt.setInt(7, Integer.parseInt(txtAgentAgencyId.getText()));
            prepareStmt.setInt(8, Integer.parseInt(txtAgentId.getText()));

            int row = prepareStmt.executeUpdate();
            if (row == 0)
            {
                Alert alert = new Alert(Alert.AlertType.ERROR);
                alert.setTitle("Update Failed");
                alert.setHeaderText("Update Failed");
                alert.setContentText("An error occurred. Please try again later.");
                alert.showAndWait();
            }
            else
            {
                agentDropdownData();
            }

            conn.close();
        } catch (SQLException e)
        {
            e.printStackTrace();
        }
    }

    /*-------------------------[ functions ]-------------------------*/

    // get agent drop down data query
    private void agentDropdownData()
    {
        try
        {
            Statement stmt = ConnectionManager.getConnection().createStatement();
            ResultSet rs = stmt.executeQuery("SELECT * FROM agents");
            ArrayList<Agent> agentArrayList = new ArrayList<>();
            while (rs.next())
            {
                agentArrayList.add(new Agent(rs.getInt(1), rs.getString(2), rs.getString(3), rs.getString(4), rs.getString(5), rs.getString(6), rs.getString(7), rs.getInt(8)));
            }
            ObservableList<Agent> agents = FXCollections.observableArrayList(agentArrayList);
            comboBoxAgents.setItems(agents);
        } catch (SQLException e)
        {
            e.printStackTrace();
        }
    }

    // disable text fields
    private void textDisabled(Boolean state)
    {
        //txtAgentId.setDisable(state);
        txtAgentFirstName.setDisable(state);
        txtAgentMiddleInitial.setDisable(state);
        txtAgentLastName.setDisable(state);
        txtAgentBusinessPhone.setDisable(state);
        txtAgentEmail.setDisable(state);
        txtAgentPosition.setDisable(state);
        txtAgentAgencyId.setDisable(state);
    }
}
