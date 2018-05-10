package com.sdabyd2.programowanie.libraryController;

import com.sdabyd2.programowanie.shared.PrimaryViewController;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TextArea;

public class AddSummaryController {
    @FXML
    private TextArea textAreaSummary;
    @FXML
    private Button buttonSave;

    public void initialize(){}

    @FXML
    public void saveTheSummary(){
        int quantity = PrimaryViewController.listOfReadBooks.size();
        PrimaryViewController.listOfReadBooks.get(quantity-1).addSummary(textAreaSummary.getText());
        System.out.println("Dodano streszczenie" + textAreaSummary.getText());
        textAreaSummary.setDisable(true);
    }
}
