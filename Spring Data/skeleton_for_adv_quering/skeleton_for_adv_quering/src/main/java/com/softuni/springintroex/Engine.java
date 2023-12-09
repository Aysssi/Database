package com.softuni.springintroex;

import com.softuni.springintroex.service.AuthorService;
import com.softuni.springintroex.service.BookService;
import com.softuni.springintroex.service.CategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

@Component
public class Engine implements CommandLineRunner {
     private final CategoryService categoryService;
     private final AuthorService authorService;
     private final BookService bookService;
     @Autowired
    public Engine(CategoryService categoryService, AuthorService authorService, BookService bookService) {
         this.categoryService = categoryService;
         this.authorService = authorService;
         this.bookService = bookService;
     }

    @Override
    public void run(String... args) throws Exception {
      // seedData();
        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(System.in));
        this.bookService.printAllBooksByAgeRestriction(bufferedReader.readLine());
        this.bookService.printAllBooksEditionTypeAndCopies();
        this.bookService.printAllBooksByPriceInBound();
        this.bookService.printAllBooksNotYear(bufferedReader.readLine());
        this.bookService.printAllBooksBeforeDate(bufferedReader.readLine());
        this.authorService.printAllAuthorsWithEndingString(bufferedReader.readLine());
        this.bookService.printAllBooksWithAuthorLastStaring(bufferedReader.readLine());
        this.bookService.printNumberBooksCountWithTitleLengthBiggerThan(Integer.parseInt(bufferedReader.readLine()));

     }

    void seedData() throws IOException {
        this.categoryService.seedCategoriesInDB();
        this.authorService.seedAuthorInDB();
        this.bookService.seedBookInDB();
    }
}
