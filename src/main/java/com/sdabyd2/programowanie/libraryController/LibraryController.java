package com.sdabyd2.programowanie.libraryController;

import com.sdabyd2.programowanie.componentsOfLibraryAndStack.Book;
import com.sdabyd2.programowanie.shared.DataManagement;
import com.sdabyd2.programowanie.shared.MainController;
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

public class LibraryController implements DataManagement{

    private MainController mainController;
    private PrimaryViewController primaryViewController;
    private Book viewSummaryOfThisBook;
    @FXML
    private Label head;
    @FXML
    private ChoiceBox choiceBoxFilter;
    @FXML
    private Button buttonFilterList;
    @FXML
    private TextField inputFraseForFiltering;
    @FXML
    private Button buttonAdd;
    @FXML
    private Button buttonRemove;
    @FXML
    private Button buttonRefresh;
    @FXML
    private Button buttonReturnToPrimaryView;
    @FXML
    private Button buttonSummary;
    @FXML
    private TableView<Book> listOfBookInLibrary;
    @FXML
    private TableColumn<Book,String>title;
    @FXML
    private TableColumn<Book,String>author;
    @FXML
    private TableColumn<Book,Integer>mark;
    @FXML
    private TableColumn<Book,String>publisher;
    @FXML
    private TableColumn<Book,String>publishingSeries;
    @FXML
    private TableColumn<Book,Integer>publishingYear;
    @FXML
    private TableColumn<Book,String>category;
    @FXML
    private TableColumn<Book,String>ISBN;
    @FXML
    private TableColumn<Book,String>date;

    @FXML
    public void initialize() {
        head.setText("BIBLIOTECZKA");

        title.setCellValueFactory(data->data.getValue().titleProperty());
        author.setCellValueFactory(data->data.getValue().authorProperty());
        mark.setCellValueFactory(data->data.getValue().markProperty().asObject());
        publisher.setCellValueFactory(data->data.getValue().publisherProperty());
        publishingSeries.setCellValueFactory(data->data.getValue().publishingSeriesProperty());
        category.setCellValueFactory(data->data.getValue().categoryProperty());
        ISBN.setCellValueFactory(data->data.getValue().ISBNProperty());
        date.setCellValueFactory(data->data.getValue().dateOfReadingProperty());
        publishingYear.setCellValueFactory(data->data.getValue().publicationYearProperty().asObject());


        buttonFilterList.setDisable(true);
        ObservableList observableListForFiltering = FXCollections.observableArrayList("Tytuł", "Autor", "Wydawnictwo",
                "Ocena", "Seria Wydawnicza","Data przeczytania", "słowo klucz");
        choiceBoxFilter.setItems(observableListForFiltering);
        choiceBoxFilter.valueProperty().addListener(new ChangeListener<String>() {
            @Override
            public void changed(ObservableValue<? extends String> observable, String oldValue, String newValue) {
                if (newValue != null) {
                    inputFraseForFiltering.textProperty().addListener(new ChangeListener() {
                        @Override
                        public void changed(ObservableValue observable, Object oldValue, Object newValue) {
                            if (newValue != null){
                                buttonFilterList.setDisable(false);
                            }
                        }
                    });
                }
            }
        });
    }
    @FXML
    public void filter() {
        ObservableList<Book> obs = null;
        if (choiceBoxFilter.valueProperty().getValue().toString().contains("Tytuł")) {
            obs = FXCollections.observableArrayList(PrimaryViewController.myLibrary.filterByTitle(inputFraseForFiltering.getText()));
        } else if (choiceBoxFilter.valueProperty().getValue().toString().contains("Autor")) {
            obs = FXCollections.observableArrayList(PrimaryViewController.myLibrary.filterByAuthor(inputFraseForFiltering.getText()));
        } else if (choiceBoxFilter.valueProperty().getValue().toString().contains("Ocena")) {
            obs = FXCollections.observableArrayList(PrimaryViewController.myLibrary
                    .filterByMark(Integer.parseInt(inputFraseForFiltering.getText())));
        } else if (choiceBoxFilter.valueProperty().getValue().toString().contains("Wydawnictwo")) {
            obs = FXCollections.observableArrayList(PrimaryViewController.myLibrary.filterByPublisher(inputFraseForFiltering.getText()));
        } else if (choiceBoxFilter.valueProperty().getValue().toString().contains("Seria wydawnicza")) {
            obs = FXCollections.observableArrayList(PrimaryViewController.myLibrary.filterByPublishingSeries(inputFraseForFiltering.getText()));
        } else if (choiceBoxFilter.valueProperty().getValue().toString().contains("słowo klucz")) {
            obs = FXCollections.observableArrayList(PrimaryViewController.myLibrary.filterByKeyWord(inputFraseForFiltering.getText()));
        }else if (choiceBoxFilter.valueProperty().getValue().toString().contains("Data przeczytania")) {
            obs = FXCollections.observableArrayList(PrimaryViewController.myLibrary.filterByDate(inputFraseForFiltering.getText()));
        }
        if (obs.size()==0) {
            Alert fail = new Alert(Alert.AlertType.INFORMATION);
            fail.setTitle("Filtrowanie listy książek");
            fail.setContentText("Nie znaleziono pozycji książkowych pod kątem szukanej frazy");
            fail.setHeaderText("INFORMACJA");
            fail.show();
        }else {
            listOfBookInLibrary.setItems(obs);
        }
    }
    @FXML
    public void addBook(){
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/frameForAddBook.fxml"));
        try{
            Pane addBook = loader.load();
            Scene addBookToLibrary = new Scene(addBook);
            Stage stageTwo = new Stage();
            stageTwo.setScene(addBookToLibrary);
            stageTwo.setResizable(false);
            stageTwo.setTitle("Dane nowej pozycji książkowej.");
            stageTwo.show();
        } catch (IOException exception){
            exception.printStackTrace();
        }
    }
    /**
     * This method is needed for correct work refreshView method.
     * @param primary
     */
    public  void setPrimary(PrimaryViewController primary){
        this.primaryViewController = primary;
        listOfBookInLibrary.setItems(FXCollections.observableArrayList(
                PrimaryViewController.listOfReadBooks));
    }
    @FXML
    public void refreshView(){
        primaryViewController.refreshLibraryTableView(this);
    }
    @FXML
    public void removeBook(){
        Alert removeBookAlert = new Alert(Alert.AlertType.WARNING);
        removeBookAlert.setTitle("Usuwanie wybranej pozycji z biblioteki");
        removeBookAlert.setContentText("Usunięto wybraną książkę");
        removeBookAlert.setHeaderText("UWAGA");
        removeBookAlert.show();

        PrimaryViewController.myLibrary.removeBookFromLibrary(listOfBookInLibrary.getSelectionModel().getSelectedItem());
        listOfBookInLibrary.getSelectionModel().clearSelection();
        primaryViewController.refreshLibraryTableView(this);
    }

    /**
     * This method shows summary for selected book from table.
     */
    @FXML
    public void viewSummary(){
        viewSummaryOfThisBook = listOfBookInLibrary.getSelectionModel().getSelectedItem();
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/frameViewSummary.fxml"));
        try{
            VBox frameViewSummary = loader.load();
            Scene viewSummary = new Scene(frameViewSummary);
            Stage stageThree = new Stage();
            stageThree.setScene(viewSummary);
            stageThree.setResizable(false);
            stageThree.setTitle("Odczyt tekstu dla wybranej pozycji książkowej");
            stageThree.show();
            ViewSummaryController viewSummaryController = loader.getController();
            viewSummaryController.setControllerFromLibrary(this);
        } catch (IOException exception){}
    }
    public Book getViewSummaryOfThisBook() {
        return viewSummaryOfThisBook;
    }

    /**
     * This method write book list from library to JSON, next clear book list
     * and change current stage.
     */
    @FXML
    public void returnToPrimaryView(){
        DataManagement.saveTheBookList("books.json",PrimaryViewController.listOfReadBooks);
        PrimaryViewController.listOfReadBooks.clear();
        mainController.viewMainScreen();
    }
    public void setMainController(MainController mainController){
        this.mainController = mainController;
    }
}
