package com.sdabyd2.programowanie.stackController;

import com.sdabyd2.programowanie.componentsOfLibraryAndStack.Book;
import com.sdabyd2.programowanie.componentsOfLibraryAndStack.StateOfBook;
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
import javafx.stage.Stage;
import java.io.IOException;

public class StackController implements DataManagement{

    private MainController mainController;
    private PrimaryViewController primaryViewController;

    @FXML
    private Label head;
    @FXML
    private ChoiceBox choiceBoxFilter;
    @FXML
    private Button buttonFilterList;
    @FXML
    private TextField inputFraseForFiltering;
    @FXML
    private Button buttonMark;
    @FXML
    private Button buttonAdd;
    @FXML
    private Button buttonRemove;
    @FXML
    private Button buttonRefresh;
    @FXML
    private Button buttonReturnToPrimaryView;
    @FXML
    private Button buttonRemoveToLibrary;
    @FXML
    private TableView<Book> listOfBookOnTheStack;
    @FXML
    private TableColumn<Book,String>title;
    @FXML
    private TableColumn<Book,String>author;
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
    public void initialize() {

        title.setCellValueFactory(data->data.getValue().titleProperty());
        author.setCellValueFactory(data->data.getValue().authorProperty());
        publisher.setCellValueFactory(data->data.getValue().publisherProperty());
        publishingSeries.setCellValueFactory(data->data.getValue().publishingSeriesProperty());
        category.setCellValueFactory(data->data.getValue().categoryProperty());
        ISBN.setCellValueFactory(data->data.getValue().ISBNProperty());
        publishingYear.setCellValueFactory(data->data.getValue().publicationYearProperty().asObject());

        buttonFilterList.setDisable(true);
        ObservableList observableListForFiltering = FXCollections.observableArrayList("Tytuł", "Autor",
                "Wydawnictwo", "Seria Wydawnicza");
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
            obs = FXCollections.observableArrayList(PrimaryViewController.myStack.filterByTitle(inputFraseForFiltering.getText()));
        } else if (choiceBoxFilter.valueProperty().getValue().toString().contains("Autor")) {
            obs = FXCollections.observableArrayList(PrimaryViewController.myStack.filterByAuthor(inputFraseForFiltering.getText()));
        } else if (choiceBoxFilter.valueProperty().getValue().toString().contains("Wydawnictwo")) {
            obs = FXCollections.observableArrayList(PrimaryViewController.myStack.filterByPublisher(inputFraseForFiltering.getText()));
        } else if (choiceBoxFilter.valueProperty().getValue().toString().contains("Seria wydawnicza")) {
            obs = FXCollections.observableArrayList(PrimaryViewController.myStack.filterByPublishingSeries(inputFraseForFiltering.getText()));
        }
        if (obs.size()==0) {
            Alert fail = new Alert(Alert.AlertType.INFORMATION);
            fail.setTitle("Filtrowanie listy książek");
            fail.setContentText("Nie znaleziono pozycji książkowych pod kątem szukanej frazy");
            fail.setHeaderText("INFORMACJA");
            fail.show();
        }else {
            listOfBookOnTheStack.setItems(obs);
        }
    }
    @FXML
    public void addBook(){
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/frameForAddBookToStack.fxml"));
        try{
            Pane addBook = loader.load();
            Scene addBookToStack = new Scene(addBook);
            Stage stageTwo = new Stage();
            stageTwo.setScene(addBookToStack);
            stageTwo.setResizable(false);
            stageTwo.setTitle("Dane nowej pozycji książkowej");
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
        listOfBookOnTheStack.setItems(FXCollections.observableArrayList(
            PrimaryViewController.listOfStack));
    }
    /**
     * This method is started after click on the "Odśwież widok" button.
     */
    @FXML
    public void refreshView(){
          primaryViewController.refreshStackTableView(this);
    }
    @FXML
    public void removeBook(){
        Alert removeBookAlert = new Alert(Alert.AlertType.WARNING);
        removeBookAlert.setTitle("Usuwanie wybranej pozycji ze stosu");
        removeBookAlert.setContentText("Usunięto wybraną książkę");
        removeBookAlert.setHeaderText("UWAGA");
        removeBookAlert.show();
        PrimaryViewController.myStack.removeFromStack(listOfBookOnTheStack.getSelectionModel().getSelectedItem());
        listOfBookOnTheStack.getSelectionModel().clearSelection();
        primaryViewController.refreshStackTableView(this);
    }
    @FXML
    public void removeToLibrary(){
        boolean result = PrimaryViewController.myStack
                .replaceToLibrary(listOfBookOnTheStack.getSelectionModel().getSelectedItem());
        listOfBookOnTheStack.getSelectionModel().clearSelection();
        primaryViewController.refreshStackTableView(this);
        if (result) {
            Alert replaceBookAlert = new Alert(Alert.AlertType.INFORMATION);
            replaceBookAlert.setTitle("Przenoszenie książki ze stosu do biblioteczki.");
            replaceBookAlert.setContentText("Przeniesiono wybraną książkę do biblioteczki.");
            replaceBookAlert.setHeaderText("INFORMACJA");
            replaceBookAlert.show();
        }else{
            Alert replaceBookAlert = new Alert(Alert.AlertType.WARNING);
            replaceBookAlert.setTitle("Przenoszenie książki ze stosu do biblioteczki.");
            replaceBookAlert.setContentText("Niepowodzenie. Książka którą chcesz przenieść" +
                    "\n nie posiada stanu oznaczonego jako zakończony");
            replaceBookAlert.setHeaderText("OSTRZEŻENIE");
            replaceBookAlert.show();
        }
    }
    @FXML
    public void markAsFinished(){
        listOfBookOnTheStack.getSelectionModel().getSelectedItem().setState(StateOfBook.FINISHED);
        listOfBookOnTheStack.getSelectionModel().clearSelection();
        primaryViewController.refreshStackTableView(this);
    }
    /**
     * This method write book list from stack to JSON, next clear book list
     * and change current stage.
     */
    @FXML
    public void returnToPrimaryView(){
        DataManagement.saveTheBookList("stack.json",PrimaryViewController.listOfStack);
        PrimaryViewController.listOfStack.clear();
        DataManagement.saveTheBookList("books.json",PrimaryViewController.listOfReadBooks);
        PrimaryViewController.listOfReadBooks.clear();
        mainController.viewMainScreen();
    }
    public void setMainController(MainController mainController){
        this.mainController = mainController;
    }
}
