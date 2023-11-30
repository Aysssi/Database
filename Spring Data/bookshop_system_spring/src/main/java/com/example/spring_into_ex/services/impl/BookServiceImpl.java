package com.example.spring_into_ex.services.impl;

import com.example.spring_into_ex.constants.GlobalConstants;
import com.example.spring_into_ex.entities.*;
import com.example.spring_into_ex.repositories.BookRepository;
import com.example.spring_into_ex.services.AuthorService;
import com.example.spring_into_ex.services.BookService;
import com.example.spring_into_ex.services.CategoryService;
import com.example.spring_into_ex.utils.FileUtil;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.*;

@Service
@Transactional
public class BookServiceImpl implements BookService {
     private final AuthorService authorService;
     private final CategoryService categoryService;
    private final BookRepository bookRepository;
    private final FileUtil fileUtil;

    @Autowired
    public BookServiceImpl(AuthorService authorService, CategoryService categoryService, BookRepository bookRepository, FileUtil fileUtil) {
        this.authorService = authorService;
        this.categoryService = categoryService;
        this.bookRepository = bookRepository;
        this.fileUtil = fileUtil;
    }


    @Override
    public void seedBooks() throws IOException {

        if (this.bookRepository.count() != 0){
            return;
        }

        String [] fileContent = this.fileUtil.readFileContent(GlobalConstants.BOOKS_FILE_PATH);
        Arrays.stream(fileContent)
                .forEach(r -> {
                   String [] params = r.split("\\s+");

                    Author author = this.getRandomAuthor();

                    EditionType editionType = EditionType.values()[Integer.parseInt(params[0])];
                    DateTimeFormatter formatter = DateTimeFormatter.ofPattern("d/M/yyyy");
                    LocalDate releasedDate = LocalDate.parse(params[1], formatter);
                    int copies = Integer.parseInt(params[2]);
                    BigDecimal price = new BigDecimal(params[3]);
                    AgeRestriction ageRestriction = AgeRestriction.values()[Integer.parseInt(params[4])];
                    String  title = this.getTitle(params);

                    Set<Category> categorySet = this.getRandomCategories();

                    Book book = new Book();
                    book.setAuthor(author);
                    book.setEditionType(editionType);
                    book.setReleaseDate(releasedDate);
                    book.setCopies(copies);
                    book.setPrice(price);
                    book.setAgeRestriction(ageRestriction);
                    book.setCategories(categorySet);
                    book.setTitle(title);

                    this.bookRepository.saveAndFlush(book);
                });

    }

    @Override
    public List<Book> getAllBooksAfter2000() {


        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("d/M/yyyy");
        LocalDate releasedDate = LocalDate.parse("31/12/2000", formatter);
        return this.bookRepository.findAllByReleaseDateAfter(releasedDate);
    }

    private Set<Category> getRandomCategories() {
        Set<Category> result = new HashSet<>();
        Random random = new Random();
        int bound = random.nextInt(3) +1;



        for (int i = 1; i <= bound; i++) {
            int categoryId = random.nextInt(8) + 1;
            result.add(this.categoryService.getCategoryById((long) categoryId));
        }
        return result;
    }

    private String getTitle(String[] params) {
        StringBuilder sb = new StringBuilder();

        for (int i = 5; i < params.length ; i++) {
            sb.append(params[i])
                    .append(" ");
        }
        return sb.toString().trim();
    }

    private Author getRandomAuthor() {

        Random random = new Random();

        int randomId = random.nextInt(this.authorService.getAllAuthorsCount()) +1;

        return this.authorService.findAuthorById((long) randomId);

    }
}
