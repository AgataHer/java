package com.sdabyd2.programowanie.shared;

import com.sdabyd2.programowanie.componentsOfLibraryAndStack.Book;
import com.sdabyd2.programowanie.componentsOfLibraryAndStack.Library;
import com.sdabyd2.programowanie.componentsOfLibraryAndStack.Stack;
import com.sdabyd2.programowanie.libraryController.LibraryController;
import com.sdabyd2.programowanie.stackController.StackController;
import javafx.application.Platform;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.Pane;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class PrimaryViewController implements DataManagement {

    public static Library myLibrary;
    public static List<Book> listOfReadBooks;
    public static Stack myStack;
    public static List<Book> listOfStack;

    MainController mainController;
    @FXML
    private Button library;
    @FXML
    private Button stackOfBooks;
    @FXML
    private Button exit;
    @FXML
    private Label quoteOfTheDay;

    public void initialize(){
        myLibrary = Library.instanceOf();
        myStack = Stack.instanceOf();
        listOfReadBooks = new ArrayList<>();
        listOfStack = new ArrayList<>();
        DataManagement.loadListOfBooks("books.json",listOfReadBooks);
        DataManagement.loadListOfBooks("stack.json",listOfStack);
        quoteOfTheDay.setText(myLibrary.randomQuotationOfDay());
        quoteOfTheDay.setWrapText(true);
    }
    @FXML
    public void goToStackView(){
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/stackView.fxml"));
        try {
            Pane pane = loader.load();
            StackController stackController = loader.getController();
            stackController.setMainController(mainController);
            mainController.setScene(pane);
            refreshStackTableView(stackController);
        }catch(IOException exp){
            exp.printStackTrace();
        }
    }

    @FXML
    public void goToLibraryView(){
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/libraryView.fxml"));
        try {
            Pane pane = loader.load();
            LibraryController libraryController = loader.getController();
            libraryController.setMainController(mainController);
            mainController.setScene(pane);
            refreshLibraryTableView(libraryController);
        }catch(IOException exp){
            exp.printStackTrace();
        }
    }

    public void refreshLibraryTableView(LibraryController libraryController) {
        libraryController.setPrimary(this);
    }
    public void refreshStackTableView(StackController stackController) {
        stackController.setPrimary(this);
    }

    @FXML
    public void exitFromProgrm(){
        Platform.exit();
    }

    public void setMainController(MainController mainController){
        this.mainController = mainController;
    }
}
