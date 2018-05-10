package com.sdabyd2.programowanie.libraryController;

import com.sdabyd2.programowanie.componentsOfLibraryAndStack.Book;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TextArea;

public class ViewSummaryController {

    private LibraryController libraryController;
    private Book book;
    @FXML
    private TextArea textAreaSummary;
    @FXML
    private Button buttonDelete;
    @FXML
    private Button buttonSave;
    @FXML
    private Button buttonRead;

    public void initialize(){
        textAreaSummary.setWrapText(true);
        textAreaSummary.setText("Kliknij Odczytaj");
        buttonSave.setDisable(true);
        buttonDelete.setDisable(true);
    }
    @FXML
    public void readBook(){
        book = libraryController.getViewSummaryOfThisBook();
        if (book.getSummary()==null){
            textAreaSummary.setText("Brak wprowadzonych notatek");
            buttonSave.setDisable(false);
            buttonRead.setDisable(true);
        }else {
            textAreaSummary.setText(book.getSummary());
            buttonSave.setDisable(false);
            buttonDelete.setDisable(false);
            buttonRead.setDisable(true);
        }
    }
    @FXML
    public void deleteTheSummary(){
        book.addSummary(null);
        buttonDelete.setDisable(true);
    }
    @FXML
    public void saveSummary(){
        book.addSummary(textAreaSummary.getText());
        buttonSave.setDisable(true);
    }
    public void setControllerFromLibrary(LibraryController library){
        this.libraryController = library;
    }
}
