package com.softuni.springintroex.service;

import com.softuni.springintroex.constants.GlobalConstants;
import com.softuni.springintroex.domain.entities.Author;
import com.softuni.springintroex.domain.repositories.AuthorRepository;
import com.softuni.springintroex.utils.FileUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.IOException;

@Service
public class AuthorServiceImpl implements AuthorService {
     private final FileUtil fileUtil;
     private final AuthorRepository authorRepository;

     @Autowired
    public AuthorServiceImpl(FileUtil fileUtil, AuthorRepository authorRepository) {
        this.fileUtil = fileUtil;
        this.authorRepository = authorRepository;
    }

    @Override
    public void printAllAuthorsWithEndingString(String star) {
        this.authorRepository.findAllByFirstNameEndingWith(star)
                .forEach(author -> System.out.printf("%s %s %n", author.getFirstName(), author.getLastName()));
    }

    @Override
    public void seedAuthorInDB() throws IOException {
     String [] lines = fileUtil.readFileContent(GlobalConstants.AUTHORS_FILE_PATH);

        for (String line : lines) {
            String [] tokens = line.split("\\s+");
            Author author = new Author(tokens[0], tokens[1]);

            this.authorRepository.saveAndFlush(author);
        }
    }
}
