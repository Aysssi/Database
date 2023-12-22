package com.example.jsonproductsshop.domain.service.impl;

import com.example.jsonproductsshop.domain.dtos.CategorySeedDto;
import com.example.jsonproductsshop.domain.entities.Category;
import com.example.jsonproductsshop.domain.repositories.CategoryRepository;
import com.example.jsonproductsshop.domain.service.CategoryService;
import com.example.jsonproductsshop.util.ValidatorUtil;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.validation.ConstraintViolation;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Random;

@Service
public class CategoryServiceImpl implements CategoryService {

     private final ModelMapper modelMapper;
     private final ValidatorUtil validatorUtil;

     private final CategoryRepository categoryRepository;

     @Autowired
    public CategoryServiceImpl(ModelMapper modelMapper, ValidatorUtil validatorUtil, CategoryRepository categoryRepository) {
        this.modelMapper = modelMapper;
        this.validatorUtil = validatorUtil;
        this.categoryRepository = categoryRepository;
    }

    @Override
    public void seedCategories(CategorySeedDto[] categorySeedDtos) {
        if (this.categoryRepository.count() != 0){
            return;
        }

        Arrays.stream(categorySeedDtos)
                .forEach(categorySeedDto -> {
                    if (this.validatorUtil.isValid(categorySeedDto)){
                        Category category = this.modelMapper
                                .map(categorySeedDto,Category.class);

                        this.categoryRepository.saveAndFlush(category);

                    }else {
                        this.validatorUtil.violations(categorySeedDto)
                                .stream()
                                .map(ConstraintViolation::getMessage)
                                .forEach(System.out::println);
                    }
                });

    }

    @Override
    public List<Category> getRandomCategories() {

        Random random = new Random();
        List<Category> resultList = new ArrayList<>();
        long randomCounter = random.nextInt(3) +1;
        for (long i = 0; i < randomCounter ; i++) {
            long randomId = random.nextInt((int) this.categoryRepository.count()) +1;

           resultList.add(this.categoryRepository.getOne(randomId));
        }
        return resultList;
    }
}
