package com.sdabyd2.programowanie.componentsOfLibraryAndStack;

import javafx.beans.property.IntegerProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;
import java.util.ArrayList;
import java.util.List;

public class Book {
    private StringProperty title = new SimpleStringProperty();
    private StringProperty ISBN = new SimpleStringProperty();
    private StringProperty author = new SimpleStringProperty();
    private StringProperty publisher = new SimpleStringProperty();
    private IntegerProperty publicationYear = new SimpleIntegerProperty();
    private StringProperty publishingSeries = new SimpleStringProperty();
    private IntegerProperty mark = new SimpleIntegerProperty();
    private List<String> quotation=new ArrayList<>();
    private String summary;
    private StringProperty keyWords = new SimpleStringProperty();
    private StringProperty dateOfReading = new SimpleStringProperty();
    private StringProperty category = new SimpleStringProperty();
    private StateOfBook state;

    public String getTitle() {
        return title.get();
    }

    public StringProperty titleProperty() {
        return title;
    }

    public void setTitle(String title) {
        this.title.set(title.trim());
    }
    public String getCategory() {
        return category.get();
    }

    public StringProperty categoryProperty() {
        return category;
    }

    public void setCategory(String category) {
        this.category.set(category);
    }

    public String getISBN() {
        return ISBN.get();
    }

    public StringProperty ISBNProperty() {
        return ISBN;
    }

    public void setISBN(String ISBN) {
        this.ISBN.set(ISBN);
    }

    public String getAuthor() {
        return author.get();
    }

    public StringProperty authorProperty() {
        return author;
    }

    public void setAuthor(String author) {
        this.author.set(author.trim());
    }

    public String getPublisher() {
        return publisher.get();
    }

    public StringProperty publisherProperty() {
        return publisher;
    }

    public void setPublisher(String publisher) {
        this.publisher.set(publisher);
    }

    public int getPublicationYear() {
        return publicationYear.get();
    }

    public IntegerProperty publicationYearProperty() {
        return publicationYear;
    }

    public void setPublicationYear(int publicationYear) {
        this.publicationYear.set(publicationYear);
    }

    public int getMark() {
        return mark.get();
    }

    public IntegerProperty markProperty() {
        return mark;
    }

    public void setMark(int mark) {
        this.mark.set(mark);
    }

    public String getPublishingSeries() {
        return publishingSeries.get();
    }

    public StringProperty publishingSeriesProperty() {
        return publishingSeries;
    }

    public void setPublishingSeries(String publishingSeries) {
        this.publishingSeries.set(publishingSeries);
    }

    public List<String> getQuotation() {
        return this.quotation;
    }

    public String getSummary() {
        return summary;
    }

    public String getKeyWords() {
        return keyWords.get();
    }

    public StringProperty keyWordsProperty() {
        return keyWords;
    }

    public void setKeyWords(String keyWords) {
        this.keyWords.set(keyWords);
    }

    public String getDateOfReading() {
        return dateOfReading.get();
    }

    public StringProperty dateOfReadingProperty() {
        return dateOfReading;
    }

    public void setDateOfReading(String dateOfReading) {
        this.dateOfReading.set(dateOfReading);
    }

    public StateOfBook getState() {
        return state;
    }

    public void setState(StateOfBook state) {
        this.state = state;
    }

    public void addQuotation(String quotation){
        this.quotation.add(quotation.trim());
    }
    public void addSummary(String text){
        this.summary = text.trim();
    }
}
