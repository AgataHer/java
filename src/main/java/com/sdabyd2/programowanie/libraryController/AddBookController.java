package com.sdabyd2.programowanie.libraryController;

import com.sdabyd2.programowanie.componentsOfLibraryAndStack.Book;
import com.sdabyd2.programowanie.shared.PrimaryViewController;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import java.io.IOException;

public class AddBookController {

    @FXML
    private Pane addBook;
    @FXML
    private Label head;
    @FXML
    private Button buttonAddToLibrary;
    @FXML
    private Button buttonSummary;
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
    private TextArea textAreaQuotation;
    @FXML
    private ComboBox comboBoxCategory;
    @FXML
    private ComboBox comboBoxMark;
    @FXML
    private TextArea textAreaKeyWords;
    @FXML
    private TextField textFieldDate;

    @FXML
    public void initialize(){
        head.setText("DODAWANIE KSIĄŻKI DO BIBLIOTECZKI");
        buttonAddToLibrary.setDisable(true);
        buttonSummary.setDisable(true);
        textFieldTitle.textProperty().addListener(new ChangeListener<String>() {
            @Override
            public void changed(ObservableValue<? extends String> observable, String oldValue, String newValue) {
                if (newValue.isEmpty()){
                }else {
                    textFieldAuthor.textProperty().addListener(new ChangeListener<String>() {
                        @Override
                        public void changed(ObservableValue<? extends String> observable, String oldValue, String newValue) {
                            if (newValue.isEmpty()) {
                            } else {
                                buttonAddToLibrary.setDisable(false);
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

        comboBoxMark.setItems(FXCollections.observableArrayList(1,2,3,4,5));
    }
    @FXML
    public void addToListOfBooks(){
        int numberOfBooksInLibrary = PrimaryViewController.myLibrary.addBookToTheLibrary(new Book());
        PrimaryViewController.listOfReadBooks.get(numberOfBooksInLibrary-1).setTitle(textFieldTitle.getText());
        PrimaryViewController.listOfReadBooks.get(numberOfBooksInLibrary-1).setAuthor(textFieldAuthor.getText());
        if (textFieldISBN.getText().isEmpty()){
            textFieldISBN.setText("brak danych");
        }
        PrimaryViewController.listOfReadBooks.get(numberOfBooksInLibrary-1)
                    .setISBN(textFieldISBN.getText());

        if (textFieldPublisher.getText().isEmpty()){
            textFieldPublisher.setText("brak danych");
        }
        PrimaryViewController.listOfReadBooks.get(numberOfBooksInLibrary-1)
                    .setPublisher(textFieldPublisher.getText());
        if (textFieldPublSeries.getText().isEmpty()){
            textFieldPublSeries.setText("brak danych");
        }
        PrimaryViewController.listOfReadBooks.get(numberOfBooksInLibrary-1)
                .setKeyWords(textAreaKeyWords.getText());
        if (textAreaKeyWords.getText().isEmpty()){
            textAreaKeyWords.setText("brak danych");
        }
        PrimaryViewController.listOfReadBooks.get(numberOfBooksInLibrary-1)
                    .setPublishingSeries(textFieldPublSeries.getText());
        if (textFieldPublYear.getText().isEmpty()){
            PrimaryViewController.listOfReadBooks.get(numberOfBooksInLibrary-1)
                    .setPublicationYear(0);
        }else {
            PrimaryViewController.listOfReadBooks.get(numberOfBooksInLibrary - 1)
                    .setPublicationYear(Integer.parseInt(textFieldPublYear.getText()));
        }
        try {
            PrimaryViewController.listOfReadBooks.get(numberOfBooksInLibrary - 1)
                    .setCategory(comboBoxCategory.getSelectionModel().getSelectedItem().toString());
        }catch(NullPointerException exp){
            PrimaryViewController.listOfReadBooks.get(numberOfBooksInLibrary - 1)
                    .setCategory("brak danych");
        }
        try {
            PrimaryViewController.listOfReadBooks.get(numberOfBooksInLibrary - 1)
                    .setMark(Integer.parseInt(comboBoxMark.getSelectionModel().getSelectedItem().toString()));
        }catch(NullPointerException exp){}
        if (!textAreaQuotation.textProperty().toString().isEmpty()){
            PrimaryViewController.listOfReadBooks.get(numberOfBooksInLibrary-1)
                    .addQuotation(textAreaQuotation.getText());
        }
        if(textFieldDate.textProperty().toString().isEmpty()) {
            PrimaryViewController.listOfReadBooks.get(numberOfBooksInLibrary-1).setDateOfReading("brak danych");
        }else{
            PrimaryViewController.listOfReadBooks.get(numberOfBooksInLibrary-1)
                    .setDateOfReading(textFieldDate.getText());
        }

        Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
        alert.setTitle("Dodawanie książki do biblioteczki");
        alert.setHeaderText("Dodano pozycję do twojej biblioteczki.");
        alert.setContentText("Teraz w twojej biblioteczce znajduje się " + numberOfBooksInLibrary + " książek.");
        alert.show();
        buttonAddToLibrary.setDisable(true);
        buttonSummary.setDisable(false);
    }
    @FXML
    public void openWindowForWritingSummary(){
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/frameAddSummary.fxml"));
        try{
            VBox frameaddSummary = loader.load();
            Scene addSummary = new Scene(frameaddSummary);
            Stage stageThree = new Stage();
            stageThree.setScene(addSummary);
            stageThree.setResizable(false);
            stageThree.setTitle("Wprowadzanie tekstu dla wybranej pozycji książkowej.");
            stageThree.show();
        } catch (IOException exception){
            exception.printStackTrace();
        }
    }
}
