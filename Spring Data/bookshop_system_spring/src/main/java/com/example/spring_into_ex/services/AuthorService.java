package com.example.spring_into_ex.services;

import com.example.spring_into_ex.entities.Author;

import java.io.IOException;
import java.util.List;

public interface AuthorService {

    void seedAuthors() throws IOException;


    int getAllAuthorsCount();

    Author findAuthorById(Long id);

    List<Author> findAllAuthorsByCountOfBooks();
}
