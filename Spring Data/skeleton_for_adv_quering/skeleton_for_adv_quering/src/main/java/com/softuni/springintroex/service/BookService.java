package com.softuni.springintroex.service;

import javax.transaction.Transactional;
import java.io.IOException;

public interface BookService {
    void  seedBookInDB() throws IOException;

    void printAllBooksByAgeRestriction(String ageRest);

    void printAllBooksEditionTypeAndCopies();

    void printAllBooksByPriceInBound();

    void printAllBooksNotYear(String year);

    void printAllBooksBeforeDate(String date);

    void printAllBooksWithAuthorLastStaring(String start);


    void printNumberBooksCountWithTitleLengthBiggerThan(int length);

    @Transactional
    void seedBooksInDB() throws IOException;
}
