package com.softuni.springintroex.service;

import java.io.IOException;

public interface AuthorService {

    void printAllAuthorsWithEndingString (String star);
    void seedAuthorInDB() throws IOException;
}
