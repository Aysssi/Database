package com.example.spring_into_ex.services;

import com.example.spring_into_ex.entities.Author;
import com.example.spring_into_ex.entities.Book;

import java.io.IOException;
import java.util.List;

public interface BookService {

    void seedBooks() throws IOException;

    List<Book> getAllBooksAfter2000();

}
