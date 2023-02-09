module com.example.examen {
    requires javafx.controls;
    requires javafx.fxml;
    requires java.sql;


    opens com.example.examen to javafx.fxml;
    exports com.example.examen;
    exports com.example.examen.controller;
    opens com.example.examen.controller to javafx.fxml;
    exports com.example.examen.domain;
    exports com.example.examen.repo;
    exports com.example.examen.service;
}