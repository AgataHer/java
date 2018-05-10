package com.sdabyd2.programowanie.shared;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.sdabyd2.programowanie.componentsOfLibraryAndStack.Book;
import java.io.File;
import java.io.IOException;
import java.util.Arrays;
import java.util.List;

public interface DataManagement {

    static void saveTheBookList(String path, List<Book>list){
        ObjectMapper mapper = new ObjectMapper();
        File fileName = new File(path);
        try {
            fileName.createNewFile();
            //mapper.configure(SerializationFeature.INDENT_OUTPUT,true);
            mapper.writeValue(fileName, list);
        }catch(IOException exp){}
    }

    static void loadListOfBooks(String path, List<Book> list){
        ObjectMapper mapper = new ObjectMapper();
        try {
            Book[] booksTable = mapper.readValue(new File(path), Book[].class);
            list.addAll(Arrays.asList(booksTable));
        }catch (IOException exp){
            exp.printStackTrace();
        }
    }
}
