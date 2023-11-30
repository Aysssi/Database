package com.example.spring_into_ex.controllers;

import com.example.spring_into_ex.entities.Book;
import com.example.spring_into_ex.services.AuthorService;
import com.example.spring_into_ex.services.BookService;
import com.example.spring_into_ex.services.CategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Controller;

import java.util.List;

@Controller
public class AppController implements CommandLineRunner {
    private final CategoryService categoryService;
    private final AuthorService authorService;

    private final BookService bookService;

    @Autowired
    public AppController(CategoryService categoryService, AuthorService authorService, BookService bookService) {
        this.categoryService = categoryService;
        this.authorService = authorService;
        this.bookService = bookService;
    }

    @Override
    public void run(String... args) throws Exception {
        //seed date
        this.categoryService.seedCategories();
        this.authorService.seedAuthors();
        this.bookService.seedBooks();


        // Ex p01
     //   List<Book> books = this.bookService.getAllBooksAfter2000();

        //Ex p03
       this.authorService
               .findAllAuthorsByCountOfBooks()
               .forEach(author -> {
                   System.out.printf("%s %s %d%n", author.getFirstName(), author.getLastName(), author.getBooks().size());
               });

    }

}