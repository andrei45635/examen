package com.example.examen.controller;

import com.example.examen.domain.Nevoie;
import com.example.examen.domain.Oras;
import com.example.examen.domain.Persoana;
import com.example.examen.service.Service;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseButton;
import javafx.scene.input.MouseEvent;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class HelpController {
    @FXML
    private TextField titluFormularTF;
    @FXML
    private TextField descriereFormularTF;
    @FXML
    private DatePicker deadlineFormularTF;
    @FXML
    private TableView<Nevoie> nevoiTableView;
    @FXML
    private TableColumn<Nevoie, String> titluNevoieColumn;
    @FXML
    private TableColumn<Nevoie, String> descNevoieColumn;
    @FXML
    private TableColumn<Nevoie, LocalDateTime> deadlineNevoieColumn;
    @FXML
    private TableColumn<Nevoie, Long> omNevoieNevoieColumn;
    @FXML
    private TableColumn<Nevoie, Long> salvatorColumn;
    @FXML
    private TableColumn<Nevoie, String> statusNevoieColumn;
    @FXML
    private TableView<Nevoie> fapteBuneTableView;
    @FXML
    private TableColumn<Nevoie, String> titluBunColumn;
    @FXML
    private TableColumn<Nevoie, String> descBunColumn;
    @FXML
    private TableColumn<Nevoie, LocalDateTime> deadlineBunColumn;
    @FXML
    private TableColumn<Nevoie, Long> nevoieBunColumn;
    @FXML
    private TableColumn<Nevoie, Long> salvatorBunColumn;
    @FXML
    private TableColumn<Nevoie, String> statusBunColumn;
    private Service service;
    private Oras selectedOras;
    private Persoana loggedInPersoana;
    private ObservableList<Nevoie> nevoieModel = FXCollections.observableArrayList();
    private ObservableList<Nevoie> fapteBuneModel = FXCollections.observableArrayList();
    
    public void setService(Service service){
        this.service = service;
        initModel();
        initModelFapte();
    }

    public void setOras(Oras oras){
        this.selectedOras = oras;
    }

    public void setLoggedInUser(Persoana persoana){
        this.loggedInPersoana = persoana;
    }

    @FXML
    public void initialize(){
        titluNevoieColumn.setCellValueFactory(new PropertyValueFactory<>("titlu"));
        descNevoieColumn.setCellValueFactory(new PropertyValueFactory<>("descriere"));
        deadlineNevoieColumn.setCellValueFactory(new PropertyValueFactory<>("deadline"));
        omNevoieNevoieColumn.setCellValueFactory(new PropertyValueFactory<>("omInNevoie"));
        salvatorColumn.setCellValueFactory(new PropertyValueFactory<>("omSalvator"));
        statusNevoieColumn.setCellValueFactory(new PropertyValueFactory<>("status"));

        nevoiTableView.setItems(nevoieModel);

        titluBunColumn.setCellValueFactory(new PropertyValueFactory<>("titlu"));
        descBunColumn.setCellValueFactory(new PropertyValueFactory<>("descriere"));
        deadlineBunColumn.setCellValueFactory(new PropertyValueFactory<>("deadline"));
        nevoieBunColumn.setCellValueFactory(new PropertyValueFactory<>("omInNevoie"));
        salvatorBunColumn.setCellValueFactory(new PropertyValueFactory<>("omSalvator"));
        statusBunColumn.setCellValueFactory(new PropertyValueFactory<>("status"));

        fapteBuneTableView.setItems(fapteBuneModel);
    }

    public void initModel(){
        List<Nevoie> nevoieList = service.getAllNevoiOras(selectedOras);
        List<Nevoie> avaialableNevoi = new ArrayList<>();
        for(Nevoie nevoie: nevoieList){
            if(nevoie.getOmSalvator() == 0){
                avaialableNevoi.add(nevoie);
            }
        }
        nevoieModel.setAll(avaialableNevoi);
        clicker();
    }

    public void initModelFapte(){
        List<Nevoie> nevoi = new ArrayList<>();
        for(Nevoie nevoie: service.getAllNevoiOras(selectedOras)){
            if(nevoie.getOmSalvator() == loggedInPersoana.getId()){
                nevoi.add(nevoie);
            }
        }
        fapteBuneModel.setAll(nevoi);
        //clicker();
    }

    public void clicker(){
        var selected = nevoiTableView.getFocusModel().getFocusedItem();
        nevoiTableView.setOnMouseClicked(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent event) {
                if(event.getButton().equals(MouseButton.PRIMARY) && event.getClickCount() == 2) {
                    for(Nevoie nevoi: service.getAllNevoiOras(selectedOras)){
                        if(Objects.equals(nevoi.getId(), selected.getId())){
                            service.saveNevoie(loggedInPersoana.getId(), nevoi.getOmInNevoie());
                            break;
                        }
                    }
                    Alert alert = new Alert(Alert.AlertType.CONFIRMATION, "Nevoia v-a fost asignata!", ButtonType.YES);
                    alert.showAndWait();
                    initModelFapte();
                    initModel();
                }
            }
        });
    }

    @FXML
    private void onClickSend(ActionEvent actionEvent) {
        String titlu = titluFormularTF.getText();
        String desc = descriereFormularTF.getText();
        LocalDateTime deadline = deadlineFormularTF.getValue().atStartOfDay();
        long lastID = service.getAllNevoiOras(selectedOras).get(service.getAllNevoiOras(selectedOras).size() - 1).getId();
        service.addNevoie(new Nevoie(lastID + 1, titlu, desc, deadline, loggedInPersoana.getId(), 0, "Caut erou!"));
        initModel();
    }
}
