package com.example.examen.controller;

import com.example.examen.domain.Oras;
import com.example.examen.domain.Persoana;
import com.example.examen.service.Service;
import com.example.examen.utils.validator.ValidatorExcept;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.input.MouseButton;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;

import java.io.IOException;

public class HelloController {
    private Service service;
    @FXML
    private ListView<String> signedInListView;
    @FXML
    private TextField numeTF;
    @FXML
    private TextField prenumeTF;
    @FXML
    private TextField usernameTF;
    @FXML
    private TextField parolaTF;
    @FXML
    private TextField stradaTF;
    @FXML
    private TextField nrStradaTF;
    @FXML
    private TextField telefonTF;
    @FXML
    private ComboBox<String> orasComboBox;
    private ObservableList<String> persoanaModel = FXCollections.observableArrayList();

    public void setService(Service service){
        this.service = service;
        orasComboBox.setItems(FXCollections.observableArrayList(Oras.CLUJ.toString(), Oras.BUCURESTI.toString(), Oras.GALATI.toString(), Oras.MOSCOVA.toString(), Oras.VLADIVOSTOK.toString()));
        initModel();
    }

    @FXML
    private void onClickSignUp(ActionEvent actionEvent) {
        String nume = numeTF.getText();
        String prenume = prenumeTF.getText();
        String username = usernameTF.getText();
        String parola = parolaTF.getText();
        String strada = stradaTF.getText();
        String nrStrada = nrStradaTF.getText();
        String telefon = telefonTF.getText();
        Oras oras = Oras.valueOf(orasComboBox.getValue());
        long lastID = service.getAllPersoane().get(service.getAllPersoane().size() - 1).getId();
        Persoana persoana = new Persoana(lastID + 1, nume, prenume, username, parola, oras, strada, nrStrada, telefon);
        try{
            service.savePersoana(persoana);
        } catch (ValidatorExcept ve){
            Alert alert = new Alert(Alert.AlertType.CONFIRMATION, ve.getMessage(), ButtonType.YES, ButtonType.NO, ButtonType.CANCEL);
            alert.showAndWait();
        }
        initModel();
    }

    @FXML
    public void initialize(){
        signedInListView.setItems(persoanaModel);
    }

    public void initModel(){
        persoanaModel.setAll(service.getAllUsernames());
        clicker();
    }

    public void clicker(){
        var selected = signedInListView.getFocusModel().getFocusedItem();
        Oras oras = service.getOrasByUsername(selected);
        Persoana person = service.getIdByPersoana(selected);
        signedInListView.setOnMouseClicked(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent event) {
                if(event.getButton().equals(MouseButton.PRIMARY) && event.getClickCount() == 2){
                    FXMLLoader loader = new FXMLLoader(getClass().getResource("/com/example/examen/help-view.fxml"));
                    Parent root;
                    try {
                        root = loader.load();
                    } catch (IOException e) {
                        throw new RuntimeException(e);
                    }
                    HelpController helpController = loader.getController();
                    helpController.setOras(oras);
                    helpController.setLoggedInUser(person);
                    helpController.setService(service);
                    Stage stage = new Stage();
                    stage.setScene(new Scene(root, 900, 800));
                    stage.setTitle("Hello!");
                    stage.show();
                }
            }
        });
    }
}