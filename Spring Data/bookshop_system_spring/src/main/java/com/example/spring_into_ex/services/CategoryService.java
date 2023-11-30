package com.example.spring_into_ex.services;

import com.example.spring_into_ex.entities.Category;

import java.io.IOException;

public interface CategoryService {

    void seedCategories() throws IOException;

    Category getCategoryById(Long Id);
}
