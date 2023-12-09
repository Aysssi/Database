package com.softuni.springintroex.service;

import com.softuni.springintroex.constants.GlobalConstants;
import com.softuni.springintroex.domain.entities.Category;
import com.softuni.springintroex.domain.repositories.CategoryRepository;
import com.softuni.springintroex.utils.FileUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.IOException;

@Service
public class CategoryServiceImpl implements CategoryService{
     private final FileUtil fileUtil;
     private final CategoryRepository categoryRepository;

     @Autowired
    public CategoryServiceImpl(FileUtil fileUtil, CategoryRepository categoryRepository) {
        this.fileUtil = fileUtil;
        this.categoryRepository = categoryRepository;
    }

    @Override
    public void seedCategoriesInDB() throws IOException {
        //read Categories txt
        String [] lines = fileUtil.readFileContent(GlobalConstants.CATEGORIES_FILE_PATH);
        //new Category()
        for (String line : lines) {
            Category category = new Category(line);
            // save in DB
          this.categoryRepository.saveAndFlush(category);
        }


    }
}
