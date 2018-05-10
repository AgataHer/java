package com.sdabyd2.programowanie.componentsOfLibraryAndStack;

import com.sdabyd2.programowanie.shared.PrimaryViewController;
import java.util.*;
import java.util.stream.Collectors;

public class Library {
    private Random random = new Random();
    private static Library instance = null;
    private List<Book> helpedList = new ArrayList<>();

    private Library(){}

    public static Library instanceOf(){
        if (instance==null){
            instance = new Library();
        }
        return instance;
    }

    public int addBookToTheLibrary(Book book){
        PrimaryViewController.listOfReadBooks.add(book);
        return PrimaryViewController.listOfReadBooks.size();
    }
    public void removeBookFromLibrary(Book book){
        PrimaryViewController.listOfReadBooks.remove(book);
    }
    public String randomQuotationOfDay(){
        int indexRandomerBook;
        try {
            do {
                indexRandomerBook = random.nextInt(PrimaryViewController.listOfReadBooks.size() - 1);
            } while (PrimaryViewController.listOfReadBooks.get(indexRandomerBook).getQuotation().size() == 0);

            int indexRandomerQuotationInRadnomerBook = 0;
            if (PrimaryViewController.listOfReadBooks.get(indexRandomerBook).getQuotation().isEmpty()) {
            } else {
                if (PrimaryViewController.listOfReadBooks.get(indexRandomerBook).getQuotation().size() == 1) {
                    indexRandomerQuotationInRadnomerBook = 0;
                } else {
                    indexRandomerQuotationInRadnomerBook =
                            random.nextInt(PrimaryViewController.listOfReadBooks.get(indexRandomerBook).getQuotation().size() - 1);
                }
            }
            return "\"" + PrimaryViewController.listOfReadBooks.get(indexRandomerBook).getQuotation().get(indexRandomerQuotationInRadnomerBook) + "\"";
        }catch (IllegalArgumentException exp){
            return "Brak cytatów do wyświetlenia";
        }
    }
    public List<Book> filterByTitle(String titleFromUser){
        List<Book> listOfFilteredBooks = PrimaryViewController.listOfReadBooks.stream()
                .filter(book -> (book.getTitle().toLowerCase().contains(titleFromUser.toLowerCase().trim())))
                .collect(Collectors.toList());
        return listOfFilteredBooks;
    }
    public List<Book> filterByAuthor(String authorFromUser){
        helpedList = PrimaryViewController.listOfReadBooks.stream()
                .filter(book -> (book.getAuthor().toLowerCase().contains(authorFromUser.toLowerCase().trim())))
                .collect(Collectors.toList());
        return helpedList;
    }
    public List<Book> filterByPublisher(String publisherFromUser){
       helpedList = PrimaryViewController.listOfReadBooks.stream()
                .filter(book -> (book.getPublisher().toLowerCase().contains(publisherFromUser.toLowerCase().trim())))
                .collect(Collectors.toList());
        return helpedList;
    }
    public List<Book> filterByMark(Integer markFromUser){
        helpedList = PrimaryViewController.listOfReadBooks.stream()
                .filter(book -> (book.getMark()==(markFromUser)))
                .collect(Collectors.toList());
        return helpedList;
    }
    public List<Book> filterByPublishingSeries(String nameOfSeriesFromUser){
        helpedList = PrimaryViewController.listOfReadBooks.stream()
                .filter(book -> (book.getPublishingSeries().toLowerCase().contains(nameOfSeriesFromUser.toLowerCase().trim())))
                .collect(Collectors.toList());
        return helpedList;
    }
    public List<Book> filterByKeyWord(String keyWordFromUser){
        helpedList = PrimaryViewController.listOfReadBooks.stream()
                .filter(book -> (book.getKeyWords().contains(keyWordFromUser.toLowerCase().trim())))
                .collect(Collectors.toList());
        return helpedList;
    }
    public List<Book> filterByDate(String dateFromUser){
        helpedList = PrimaryViewController.listOfReadBooks.stream()
                .filter(book -> (book.getDateOfReading().contains(dateFromUser.trim())))
                .collect(Collectors.toList());
        return helpedList;
    }
}
