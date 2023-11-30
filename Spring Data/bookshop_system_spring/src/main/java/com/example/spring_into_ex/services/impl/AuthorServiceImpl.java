package com.example.spring_into_ex.services.impl;

import com.example.spring_into_ex.constants.GlobalConstants;
import com.example.spring_into_ex.entities.Author;
import com.example.spring_into_ex.repositories.AuthorRepository;
import com.example.spring_into_ex.services.AuthorService;
import com.example.spring_into_ex.utils.FileUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.util.Arrays;
import java.util.List;
import java.util.Stack;

@Service
public class AuthorServiceImpl implements AuthorService {
     private final AuthorRepository authorRepository;
     private final FileUtil fileUtil;

     @Autowired
    public AuthorServiceImpl(AuthorRepository authorRepository, FileUtil fileUtil) {
        this.authorRepository = authorRepository;
        this.fileUtil = fileUtil;
    }

    @Override
    public void seedAuthors() throws IOException {


        if (this.authorRepository.count() != 0){
            return;
        }
        String [] fileContent = this.fileUtil.readFileContent(GlobalConstants.AUTHORS_FILE_PATH);

        Arrays.stream(fileContent)
                .forEach(r->{
                    String [] params = r.split("\\s+");
                    Author author = new Author(params[0],params[1] );

                    this.authorRepository.saveAndFlush(author);
                });
    }

    @Override
    public int getAllAuthorsCount() {

        return (int) this.authorRepository.count();
    }

    @Override
    public Author findAuthorById(Long id) {
        return this.authorRepository
                .getOne(id);
    }

    @Override
    public List<Author> findAllAuthorsByCountOfBooks() {
        return this.authorRepository.findAuthorByCountOfBook();
    }
}
