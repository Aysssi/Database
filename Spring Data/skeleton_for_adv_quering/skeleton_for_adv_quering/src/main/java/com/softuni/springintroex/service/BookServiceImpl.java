package com.softuni.springintroex.service;

import com.softuni.springintroex.constants.GlobalConstants;
import com.softuni.springintroex.domain.entities.*;
import com.softuni.springintroex.domain.repositories.AuthorRepository;
import com.softuni.springintroex.domain.repositories.BookRepository;
import com.softuni.springintroex.domain.repositories.CategoryRepository;
import com.softuni.springintroex.utils.FileUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.io.IOException;
import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.HashSet;
import java.util.Random;
import java.util.Set;

@Service
public class BookServiceImpl implements BookService {

    private final FileUtil fileUtil;
    private final BookRepository bookRepository;
    private final CategoryRepository categoryRepository;
    private final AuthorRepository authorRepository;

    @Autowired
    public BookServiceImpl(FileUtil fileUtil, BookRepository bookRepository, CategoryRepository categoryRepository, AuthorRepository authorRepository) {
        this.fileUtil = fileUtil;
        this.bookRepository = bookRepository;
        this.categoryRepository = categoryRepository;
        this.authorRepository = authorRepository;
    }

    @Override
    public void seedBookInDB() throws IOException {

    }

    @Override
    public void printAllBooksByAgeRestriction(String ageRest) {
        this.bookRepository.findAllByAgeRestriction(AgeRestriction.valueOf(ageRest.toUpperCase()))
                .forEach(book -> System.out.println(book.getTitle()));
    }

    @Override
    public void printAllBooksEditionTypeAndCopies() {
        this.bookRepository.findAllByEditionTypeAndCopiesLessThan(EditionType.GOLD,5000)
                .forEach(book -> System.out.println(book.getTitle()));
    }

    @Override
    public void printAllBooksByPriceInBound() {
        this.bookRepository.findAllByPriceLessThanOrPriceGreaterThan(BigDecimal.valueOf(5),BigDecimal.valueOf(40))
                .forEach(book -> System.out.printf("%s - $%s%n", book.getTitle(),book.getPrice()));
    }


    @Override
    public void printAllBooksNotYear(String year) {
       this.bookRepository.findAllByReleaseDateNotInYear(year)
               .forEach(book -> System.out.print(book.getTitle()));
    }

    @Override
    public void printAllBooksBeforeDate(String date) {
        DateTimeFormatter dtf =  DateTimeFormatter.ofPattern("dd-MM-yyyy");
        LocalDate localDate = LocalDate.parse(date,dtf);
      this.bookRepository.findAllByReleaseDateIsLessThan(localDate)
              .forEach(book -> System.out.printf("%s %s %s%n",book.getTitle(),book.getEditionType(), book.getPrice() ));

    }

    @Override
    public void printAllBooksWithAuthorLastStaring(String start) {
        this.bookRepository.findAllByAuthorLastNameStartingWith(start)
                .forEach(book -> System.out.printf("%s (%s %s)%n", book.getTitle(),book.getAuthor().getFirstName(),book.getAuthor().getLastName()));
    }

    @Override
    public void printNumberBooksCountWithTitleLengthBiggerThan(int length) {
        System.out.println(this.bookRepository.getNumberOfBooksWithTitleLength(length));

    }


    @Override
    @Transactional
    public void seedBooksInDB() throws IOException {
        String[] lines = this.fileUtil.readFileContent(GlobalConstants.BOOKS_FILE_PATH);
        Random random = new Random();
        for (String line : lines) {
            String[] tokens = line.split("\\s+");
            long authorIndex = random.nextInt((int) this.authorRepository.count()) + 1;
            Author author = this.authorRepository.findById(authorIndex).get();
            EditionType editionType = EditionType.values()[Integer.parseInt(tokens[0])];
            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("d/M/yyyy");
            LocalDate localDate = LocalDate.parse(tokens[1], formatter);
            int copies = Integer.parseInt(tokens[2]);
            BigDecimal price = new BigDecimal(tokens[3]);
            AgeRestriction ageRestriction = AgeRestriction.values()[Integer.parseInt(tokens[4])];
            StringBuilder titleBuilder = new StringBuilder();
            for (int i = 5; i < tokens.length; i++) {
                titleBuilder.append(tokens[i]).append(" ");
            }

            String title = titleBuilder.toString().trim();

            Book book = new Book();
            book.setAuthor(author);
            book.setEditionType(editionType);
            book.setReleaseDate(localDate);
            book.setCopies(copies);
            book.setPrice(price);
            book.setAgeRestriction(ageRestriction);
            book.setTitle(title);
            book.setCategories(this.getRandomCategories());

            this.bookRepository.saveAndFlush(book);
        }
    }

    Set<Category> getRandomCategories() {
        Set<Category> categories = new HashSet<>();
        Random random = new Random();
        for (int i = 0; i < 3; i++) {
            long categoryIndex = random.nextInt((int) this.categoryRepository.count()) + 1;
            Category category = this.categoryRepository.findById(categoryIndex).get();

            categories.add(category);
        }

        return categories;
    }
}
