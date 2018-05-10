package com.sdabyd2.programowanie.shared;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.layout.Pane;
import java.io.IOException;

public class MainController{

    @FXML Pane mainPane;

    public void initialize(){
        viewMainScreen();
    }

    public void viewMainScreen() {
        FXMLLoader loader= new FXMLLoader(getClass().getResource("/readersNotebook.fxml"));
        try{
            Pane primaryView = loader.load();
            PrimaryViewController primaryViewController = loader.getController();
            primaryViewController.setMainController(this);
            setScene(primaryView);
        }catch(IOException exception){
            exception.printStackTrace();
        }
    }

    public void setScene(Pane primaryView) {
        mainPane.getChildren().clear();
        mainPane.getChildren().add(primaryView);
    }
}
