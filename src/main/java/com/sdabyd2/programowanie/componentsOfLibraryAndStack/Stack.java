package com.sdabyd2.programowanie.componentsOfLibraryAndStack;

import com.sdabyd2.programowanie.shared.PrimaryViewController;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class Stack {
    private static Stack instance = null;
    private List<Book> helpedList = new ArrayList<>();

    private Stack(){}

    public static Stack instanceOf(){
        if (instance==null){
            instance = new Stack();
        }return instance;
    }

    public int addToTheStack (Book book){
        PrimaryViewController.listOfStack.add(book);
        return PrimaryViewController.listOfStack.size();
    }
    public boolean replaceToLibrary(Book book){
        boolean result = false;
        try {
            List<Book> helpedList =
                    PrimaryViewController.listOfStack.stream()
                            .filter(b -> (b.getState().equals(StateOfBook.FINISHED)))
                            .filter(b -> (b.equals(book)))
                            .collect(Collectors.toList());

                PrimaryViewController.listOfReadBooks.addAll(helpedList);
                PrimaryViewController.listOfStack.remove(book);
            result = true;
        }catch(NullPointerException exception){
            result = false;
        }
        return result;
    }
    public void removeFromStack(Book book){
        PrimaryViewController.listOfStack.remove(book);
    }
    public List<Book> filterByTitle(String titleFromUser){
        List<Book> listOfFilteredBooks = PrimaryViewController.listOfStack.stream()
                .filter(book -> (book.getTitle().toLowerCase().contains(titleFromUser.toLowerCase().trim())))
                .collect(Collectors.toList());
        return listOfFilteredBooks;
    }
    public List<Book> filterByAuthor(String authorFromUser){
        helpedList = PrimaryViewController.listOfStack.stream()
                .filter(book -> (book.getAuthor().toLowerCase().contains(authorFromUser.toLowerCase().trim())))
                .collect(Collectors.toList());
        return helpedList;
    }
    public List<Book> filterByPublisher(String publisherFromUser){
        helpedList = PrimaryViewController.listOfStack.stream()
                .filter(book -> (book.getPublisher().toLowerCase().contains(publisherFromUser.toLowerCase().trim())))
                .collect(Collectors.toList());
        return helpedList;
    }
    public List<Book> filterByPublishingSeries(String nameOfSeriesFromUser){
        helpedList = PrimaryViewController.listOfStack.stream()
                .filter(book -> (book.getPublishingSeries().toLowerCase().contains(nameOfSeriesFromUser.toLowerCase().trim())))
                .collect(Collectors.toList());
        return helpedList;
    }

}
