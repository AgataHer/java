package com.sdabyd2.programowanie.stackController;

import com.sdabyd2.programowanie.componentsOfLibraryAndStack.Book;
import com.sdabyd2.programowanie.componentsOfLibraryAndStack.StateOfBook;
import com.sdabyd2.programowanie.shared.PrimaryViewController;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.layout.Pane;
import java.util.Arrays;

public class AddBookToStackController {

    @FXML
    private Pane addBook;
    @FXML
    private Label head;
    @FXML
    private Button buttonAddToStack;
    @FXML
    private TextField textFieldTitle;
    @FXML
    private TextField textFieldAuthor;
    @FXML
    private TextField textFieldISBN;
    @FXML
    private TextField textFieldPublisher;
    @FXML
    private TextField textFieldPublSeries;
    @FXML
    private TextField textFieldPublYear;
    @FXML
    private ComboBox comboBoxCategory;
    @FXML
    private ComboBox comboBoxState;

    @FXML
    public void initialize(){
        head.setText("DODAWANIE KSIĄŻKI DO STOSU PODRĘCZNEGO");
        buttonAddToStack.setDisable(true);
        comboBoxState.setPromptText("Wymagane");
        comboBoxState.setItems(FXCollections.observableArrayList(Arrays.asList(StateOfBook.values())));
        textFieldTitle.textProperty().addListener(new ChangeListener<String>() {
            @Override
            public void changed(ObservableValue<? extends String> observable, String oldValue, String newValue) {
                if (newValue.isEmpty()) {
                } else {
                    textFieldAuthor.textProperty().addListener(new ChangeListener<String>() {
                        @Override
                        public void changed(ObservableValue<? extends String> observable, String oldValue, String newValue) {
                            if (newValue.isEmpty()) {
                            } else {
                                buttonAddToStack.setDisable(false);
                            }
                        }
                    });
                }
            }
        });
        ObservableList<String> kindsOfCategory = FXCollections.observableArrayList("nowela", "opowiadanie",
                "powieść społeczno obyczajowa", "powieść psychologiczna", "powieść łotrzykowska", "powieść przygodowa",
                "powieść gotycka", "fantastyka", "powieść historyczna", "powieść kryminalna", "powieść sensacyjna",
                "bajka", "epopeja", "przypowieść", "powieść poetycka", "esej", "felieton", "reportaż");
        comboBoxCategory.setItems(kindsOfCategory);
    }
    @FXML
    public void addToListOfBooksOnTheStack(){
        int numberOfBooksInLibrary = PrimaryViewController.myStack.addToTheStack(new Book());
        PrimaryViewController.listOfStack.get(numberOfBooksInLibrary-1).setTitle(textFieldTitle.getText());
        PrimaryViewController.listOfStack.get(numberOfBooksInLibrary-1).setAuthor(textFieldAuthor.getText());
        if (textFieldISBN.getText().isEmpty()){
            textFieldISBN.setText("brak danych");
        }
        PrimaryViewController.listOfStack.get(numberOfBooksInLibrary-1)
                    .setISBN(textFieldISBN.getText());

        if (textFieldPublisher.getText().isEmpty()){
            textFieldPublisher.setText("brak danych");
        }
        PrimaryViewController.listOfStack.get(numberOfBooksInLibrary-1)
                    .setPublisher(textFieldPublisher.getText());
        if (textFieldPublSeries.getText().isEmpty()){
            textFieldPublSeries.setText("brak danych");
        }
        PrimaryViewController.listOfStack.get(numberOfBooksInLibrary-1)
                    .setPublishingSeries(textFieldPublSeries.getText());
        if (textFieldPublYear.getText().isEmpty()){
            PrimaryViewController.listOfStack.get(numberOfBooksInLibrary-1)
                    .setPublicationYear(0);
        }else {
            PrimaryViewController.listOfStack.get(numberOfBooksInLibrary - 1)
                    .setPublicationYear(Integer.parseInt(textFieldPublYear.getText()));
        }
        try {
            PrimaryViewController.listOfStack.get(numberOfBooksInLibrary - 1)
                    .setCategory(comboBoxCategory.getSelectionModel().getSelectedItem().toString());
        }catch(NullPointerException exp){
            PrimaryViewController.listOfStack.get(numberOfBooksInLibrary - 1)
                    .setCategory("brak danych");
        }
        try {
            PrimaryViewController.listOfStack.get(numberOfBooksInLibrary - 1)
                    .setState(StateOfBook.valueOf(comboBoxState.getSelectionModel().getSelectedItem().toString()));
        }catch(NullPointerException exp){}

        Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
        alert.setTitle("Dodawanie książki do biblioteczki");
        alert.setHeaderText("Dodano pozycję do twojej biblioteczki.");
        alert.setContentText("Teraz w twojej biblioteczce znajduje się " + numberOfBooksInLibrary + " książek.");
        alert.show();
        buttonAddToStack.setDisable(true);
    }
}
