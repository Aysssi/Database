package com.example.jsonproductsshop.domain.service;

import com.example.jsonproductsshop.domain.dtos.CategorySeedDto;
import com.example.jsonproductsshop.domain.entities.Category;

import java.util.List;
import java.util.Random;

public interface CategoryService {
  void  seedCategories(CategorySeedDto[] categorySeedDtos);

List<Category> getRandomCategories();
}
