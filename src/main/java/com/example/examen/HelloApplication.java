package com.example.examen;

import com.example.examen.controller.HelloController;
import com.example.examen.repo.NevoieRepoBD;
import com.example.examen.repo.PersoanaRepoBD;
import com.example.examen.service.Service;
import com.example.examen.utils.validator.ValidatorPersoana;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;

public class HelloApplication extends Application {
    @Override
    public void start(Stage stage) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(HelloApplication.class.getResource("hello-view.fxml"));
        Scene scene = new Scene(fxmlLoader.load(), 800, 800);
        stage.setTitle("Hello!");
        stage.setScene(scene);
        HelloController helloController = fxmlLoader.getController();
        helloController.setService(new Service(new NevoieRepoBD(), new PersoanaRepoBD(), new ValidatorPersoana()));
        stage.show();
    }

    public static void main(String[] args) {
        launch();
    }
}